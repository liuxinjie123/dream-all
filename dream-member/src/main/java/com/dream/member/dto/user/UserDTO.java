package com.dream.member.dto.user;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class UserDTO implements Serializable {
    private String userId;
    @NonNull
    private String username;
    @NonNull
    private String mobile;
}
