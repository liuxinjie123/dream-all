package com.dream.vo.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginObject implements Serializable {
    @NotBlank(message = "account 不能为空")
    private String account;
    @NotBlank(message = "password 不能为空")
    private String password;

}
