package com.dream.service;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class KafkaMessage implements Serializable {
    private long id;
    private String msg;
    private LocalDateTime createTime;
}
