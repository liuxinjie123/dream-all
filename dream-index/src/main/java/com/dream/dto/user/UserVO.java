package com.dream.dto.user;

import com.dream.vo.BaseVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserVO extends BaseVO {
    private int id;
    private String userId;
    private String username;
    private String password;
    private String status;
    private String name;
    private String mobile;
    private String email;
}
