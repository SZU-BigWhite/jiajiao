package com.jiajiao.web.vo;

import com.jiajiao.web.enums.HttpStatusEnum;

public class ResponseVo<T> {
    Integer status;
    String msg;
    T data;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {

        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public ResponseVo(Integer status, String msg, T data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }
    public ResponseVo(Integer status, String msg) {
        this.status = status;
        this.msg = msg;
    }
    //todo 封装规范性
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
