package com.dream.service.dao.user;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class UserDAO implements Serializable {
    private String userId;
    @NonNull
    private String username;
    private String password;
    @NonNull
    private String mobile;


}
