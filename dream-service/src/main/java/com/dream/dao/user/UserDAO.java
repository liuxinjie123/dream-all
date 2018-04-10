package com.dream.dao.user;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class UserDAO extends BaseDAO {
    private int id;
    private String userId;
    @NonNull
    private String username;
    private String password;
    private String status;
    private String name;
    @NonNull
    private String mobile;
    private String email;

}
