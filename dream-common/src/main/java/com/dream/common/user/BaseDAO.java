package com.dream.member.dao.user;

import com.dream.member.ext.jackson.LocalDateTimeDeserializer;
import com.dream.member.ext.jackson.LocalDateTimeSerializer;
import com.dream.member.vo.Constants;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class BaseDAO implements Serializable {
    private static final long serialVersionUID = 506909274541591812L;
    @DateTimeFormat(pattern = Constants.DATETIME_PATTERN)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constants.DATETIME_PATTERN)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime createTime;
    private String createUserId;
    @DateTimeFormat(pattern = Constants.DATETIME_PATTERN)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constants.DATETIME_PATTERN)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime lastUpdateTime;
    private String lastUpdateUserId;

    public BaseDAO() {}
}
