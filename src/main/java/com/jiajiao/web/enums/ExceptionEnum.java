package com.jiajiao.web.enums;


public enum ExceptionEnum {

    USERNAME_NOT_NULL(1001,"username不能为空"),
    PASSWORD_NOT_NULL(1002,"password不能为空"),
    USER_NEED_LOGIN(1024,"用户需要先登录");

    private String msg;
    private Integer code;
    private ExceptionEnum(Integer code,String msg){
        this.code=code;
        this.msg=msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

}
