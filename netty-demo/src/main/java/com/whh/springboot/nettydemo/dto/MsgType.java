package com.whh.springboot.nettydemo.dto;

public enum MsgType {
    REGISTER(1,"用户注册");

    MsgType(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    private Integer code;
    private String desc;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
