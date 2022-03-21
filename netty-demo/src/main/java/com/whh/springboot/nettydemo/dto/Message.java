package com.whh.springboot.nettydemo.dto;

import lombok.Data;

@Data
public class Message {
    private String userId;
    private Integer msgType;
    private Object data;
}
