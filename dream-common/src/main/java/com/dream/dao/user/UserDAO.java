package com.dream.dao.user;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class UserDAO implements Serializable {
    private int id;
    private String userId;
    private String username;
    private String password;
    private String status;
    private String name;
    private String mobile;
    private String email;
}
