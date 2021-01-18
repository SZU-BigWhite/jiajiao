package com.learn.web.vo;

import com.learn.web.myenum.HttpStatusEnum;

public class ResponseVo<T> {
    Integer status;
    String msg;
    T data;

    public ResponseVo(Integer status, String msg, T data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }
    public ResponseVo(Integer status, String msg) {
        this.status = status;
        this.msg = msg;
    }
    public static<T> ResponseVo<T> success(String msg){
        return new ResponseVo<T>(HttpStatusEnum.success.getStatus(),msg);
    }
    public static<T> ResponseVo<T> success(String msg,T data){
        return new ResponseVo<T>(HttpStatusEnum.success.getStatus(),msg,data);
    }
    public static<T> ResponseVo<T> error(String msg){
        return new ResponseVo<T>(HttpStatusEnum.error.getStatus(),msg);
    }
    public static<T> ResponseVo<T> error(String msg,T data){
        return new ResponseVo<T>(HttpStatusEnum.error.getStatus(),msg,data);
    }
    public static<T> ResponseVo<T> message(Integer status,String msg,T data){
        return new ResponseVo<T>(status,msg,data);
    }
}
