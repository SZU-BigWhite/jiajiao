package com.jiajiao.web.execption;

import com.jiajiao.web.vo.ResponseVo;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ParameterExecption {

    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseVo FormParamterNull(MethodArgumentNotValidException e){
        FieldError fieldError = e.getBindingResult().getFieldError();
        return ResponseVo.error(fieldError.getDefaultMessage());
    }
}
