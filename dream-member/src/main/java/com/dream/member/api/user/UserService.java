package com.dream.member.api.user;

import com.dream.member.dto.user.UserDTO;

public interface UserService {
    UserDTO getByUserId(String userId);
}
