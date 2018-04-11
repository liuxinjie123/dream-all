package com.dream.dao.user;

import com.dream.vo.Constants;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class BaseDAO implements Serializable {
    @DateTimeFormat(pattern = Constants.DATETIME_PATTERN)
    private LocalDateTime createTime;
    private String createUserId;
    @DateTimeFormat(pattern = Constants.DATETIME_PATTERN)
    private LocalDateTime lastUpdateTime;
    private String lastUpdateUserId;
}
