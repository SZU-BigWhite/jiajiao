package com.jiajiao.web.enums;

public enum HttpStatusEnum {
    SUCCESS(200,"成功"),
    ERROR(400,"失败"),
    ERROR_SERVER(500,"服务器错误")
    ;
    private Integer status;
    private String msg;

    HttpStatusEnum(Integer status,String msg) {
        this.status = status;
        this.msg = msg;
    }

    public Integer getStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }
}
