package com.jiajiao.web.vo;

import com.jiajiao.web.myenum.HttpStatusEnum;

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
        return new ResponseVo<T>(HttpStatusEnum.SUCCESS.getStatus(),msg);
    }
    public static<T> ResponseVo<T> success(String msg,T data){
        return new ResponseVo<T>(HttpStatusEnum.SUCCESS.getStatus(),msg,data);
    }
    public static<T> ResponseVo<T> error(){
        return new ResponseVo<T>(HttpStatusEnum.ERROR_SERVER.getStatus(),HttpStatusEnum.ERROR_SERVER.getMsg());
    }
    public static<T> ResponseVo<T> error(String msg){
        return new ResponseVo<T>(HttpStatusEnum.ERROR.getStatus(),msg);
    }
    public static<T> ResponseVo<T> error(String msg,T data){
        return new ResponseVo<T>(HttpStatusEnum.ERROR.getStatus(),msg,data);
    }
    public static<T> ResponseVo<T> message(Integer status,String msg){
        return new ResponseVo<T>(status,msg);
    }
    public static<T> ResponseVo<T> message(Integer status,String msg,T data){
        return new ResponseVo<T>(status,msg,data);
    }
}
