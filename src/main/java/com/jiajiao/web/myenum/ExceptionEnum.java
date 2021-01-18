package com.jiajiao.web.myenum;


public enum ExceptionEnum {

    USERNAME_NOT_NULL(1001,"username不能为空"),
    PASSWORD_NOT_NULL(1002,"password不能为空");

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
