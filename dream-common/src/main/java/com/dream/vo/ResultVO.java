package com.dream.vo;

import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class ResultVO<T> implements Serializable {
    @NonNull
    private String code;
    @NonNull
    private String msg;
    private T data;
}
