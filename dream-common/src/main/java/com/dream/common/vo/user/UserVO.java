package com.dream.common.vo.user;

import com.dream.common.user.BaseDAO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserVO extends BaseDAO {
    private Long id;						// 自增长主键
    private String userId;
    private String account;					// 登录的账号
    private String username;				// 注册的昵称
    private String status;
    private String salt;					// 用于加密的盐
    private String mobile;					// 手机号
    private String email;					// 邮箱

}

