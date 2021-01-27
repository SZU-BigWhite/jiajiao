package com.jiajiao.web.enums;

public enum  MessageStatusEnum {
    UNREAD(0,"未读"),

    READ(1,"已读"),

    DELETE(2,"删除");


    Integer code;
    String msg;

    MessageStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
