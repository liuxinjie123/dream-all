package com.dream.dao.user;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class BaseDAO implements Serializable {
    private LocalDateTime createTime;
    private String createUserId;
    private LocalDateTime lastUpdateTime;
    private String lastUpdateUserId;
}
