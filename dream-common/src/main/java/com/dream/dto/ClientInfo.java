package com.dream.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientInfo {
    @NonNull
    private String IP;                   //客户端IP地址
    @NonNull
    private String userAgent;            //客户端硬件信息
    @NonNull
    private String acceptLanguage;       //可接受的语言

}
