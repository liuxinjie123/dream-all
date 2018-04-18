package com.dream.dao.user;

import com.dream.utils.SecureUtil;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDAO extends BaseDAO {
    private static final long serialVersionUID = 7591686588782668017L;
    private Long id;						// 自增长主键
    private String userId;
    private String account;					// 登录的账号
    private String username;				// 注册的昵称
    private String plainPassword; 			// 登录时的密码，不持久化到数据库
    private String password;				// 加密后的密码
    private String status;
    private String salt;					// 用于加密的盐
    private String mobile;					// 手机号
    private String email;					// 邮箱


}
