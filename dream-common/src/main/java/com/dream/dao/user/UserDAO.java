package com.dream.dao.user;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDAO extends BaseDAO {
    private static final long serialVersionUID = 7591686588782668017L;
    private int id;
    private String userId;
    private String username;
    private String password;
    private String status;
    private String name;
    private String mobile;
    private String email;
}
