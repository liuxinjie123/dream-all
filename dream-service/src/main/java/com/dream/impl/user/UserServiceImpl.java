package com.dream.impl.user;

import com.dream.api.user.UserService;
import com.dream.dto.Session;
import com.dream.mapper.user.UserMapper;
import com.dream.dao.user.UserDAO;
import com.dream.api.redis.JedisClient;
import com.dream.utils.JsonUtils;
import com.dream.utils.SecureUtil;
import com.dream.vo.Constants;
import com.dream.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service("user.UserService")
@Transactional
@PropertySource(value = "classpath:redis.properties")
public class UserServiceImpl implements UserService {
    @Autowired
    private JedisClient jedisClient;
    @Value("${REDIS_USER_SESSION_KEY}")
    private String REDIS_USER_SESSION_KEY;
    @Value("${SSO_SESSION_EXPIRE}")
    private Integer SSO_SESSION_EXPIRE;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private Session session;

    @Override
    public UserDAO getByUserId(String userId) {
        UserDAO user = userMapper.getByUserId(userId);
        System.out.println("user=" + user.toString());
        return user;
    }

    @Override
    public UserDAO getByAccount(String account) {
        UserDAO user = userMapper.getByAccount(account);
        System.out.println("user=" + user);
        return user;
    }

    @Override
    @Transactional
    public Result addUser(UserDAO user) {
        // 检查用户名是否注册，一般在前端验证的时候处理，因为注册不存在高并发的情况，这里再加一层查询是不影响性能的
        if (null != getByAccount(user.getAccount())) {
            return Result.error(Constants.ACCOUNT_ALREADY_EXIST);
        }
        SecureUtil.entryptPassword(user);
        user.setStatus(Constants.USER_STATUS_01);
        user.setCreateUserId(user.getUserId());
        user.setLastUpdateUserId(user.getUserId());
        userMapper.addUser(user);
        // 注册成功后选择发送邮件激活。现在一般都是短信验证码
        return Result.success();
    }

    @Override
    @Transactional
    public Result login(String account, String password) {
        System.out.println(" ------ enter login method");
        // 判断账号密码是否正确
        UserDAO user = userMapper.getByAccount(account);
        if (user == null) {
            return Result.build(Constants.CODE_FAILURE, "账号或密码错误");
        }
        if (!SecureUtil.decryptPassword(user, password)) {
            return Result.build(Constants.CODE_FAILURE, "账号或密码错误");
        }
        // 生成token
        String token = UUID.randomUUID().toString();
        // 清空密码和盐避免泄漏
        String userPassword = user.getPassword();
        String userSalt = user.getSalt();
        user.setPassword(null);
        user.setSalt(null);
        // 把用户信息写入 redis
        jedisClient.set(REDIS_USER_SESSION_KEY + ":" + token, JsonUtils.objectToJson(user));
        // user 已经是持久化对象了，被保存在了session缓存当中，若user又重新修改了属性值，那么在提交事务时，
        // 此时 hibernate对象就会拿当前这个user对象和保存在session缓存中的user对象进行比较，如果两个对象相同，则不会发送update语句，
        // 否则，如果两个对象不同，则会发出update语句。
        user.setPassword(userPassword);
        user.setSalt(userSalt);
        // 设置 session 的过期时间
        jedisClient.expire(REDIS_USER_SESSION_KEY + ":" + token, SSO_SESSION_EXPIRE);
        // 返回token
        session.login(user);
        Map<String, String> resultMap = new HashMap<>();
        resultMap.put("token", token);
        resultMap.put("userId", user.getUserId());
        return Result.success().setData(resultMap);
    }

    @Override
    public void logout(String token) {
        jedisClient.del(REDIS_USER_SESSION_KEY + ":" + token);
        session.logout();
    }

    public Result queryUserByToken(String token) {
        // 根据token从redis中查询用户信息
        String json = jedisClient.get(REDIS_USER_SESSION_KEY + ":" + token);
        // 判断是否为空
        if (StringUtils.isEmpty(json)) {
            return Result.error(Constants.EXPIRED_LOGIN_AGAIN);
        }
        // 更新过期时间
        jedisClient.expire(REDIS_USER_SESSION_KEY + ":" + token, SSO_SESSION_EXPIRE);
        // 返回用户信息
        return Result.success().setData(JsonUtils.jsonToPojo(json, UserDAO.class));
    }
}
