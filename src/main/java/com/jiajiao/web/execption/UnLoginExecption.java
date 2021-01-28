package com.jiajiao.web.execption;

public class UnLoginExecption extends RuntimeException {
    @Override
    public String getMessage() {
        return "你还未登陆！！";
    }
}
