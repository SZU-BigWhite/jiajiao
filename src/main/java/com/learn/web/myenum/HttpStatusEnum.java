package com.learn.web.myenum;

public enum HttpStatusEnum {
    success(200),
    error(400);
    private Integer status;

    HttpStatusEnum(Integer status) {
        this.status = status;
    }

    public Integer getStatus() {
        return status;
    }
}
