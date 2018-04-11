package com.dream.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class BaseVO implements Serializable {
    @JsonFormat(pattern = Constants.DATETIME_PATTERN)
    private LocalDateTime createTime;
    private String createUserId;
    @JsonFormat(pattern = Constants.DATETIME_PATTERN)
    private LocalDateTime lastUpdateTime;
    private String lastUpdateUserId;
}
