package com.learn.web.exception;

import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class MyExceptionHandler {

    @ResponseBody
    @ExceptionHandler(BindException.class)
    public String HandleFormException(BindException e, HttpServletRequest request){
        //获得错误信息
        String msg=e.getBindingResult().getFieldError().getDefaultMessage();
        return msg;
    }
}
