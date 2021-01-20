package com.jiajiao.web.enums;

public enum  UserRoleEnum {
    SUPER_ADMIN(0,"超级管理员"),

    ADMIN(1,"管理员"),

    PARENT(2,"家长"),

    STUDENT(3,"学生"),

    CUMSTOMER(4,"游客");

    Integer code;
    String msg;

    UserRoleEnum(Integer code, String msg) {
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
