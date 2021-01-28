package com.jiajiao.web.execption;

import com.jiajiao.web.enums.ExceptionEnum;
import com.jiajiao.web.vo.ResponseVo;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class ExecptionHandler {

    /**
     * 参数校验错误
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseVo FormParamterNull(MethodArgumentNotValidException e){
        FieldError fieldError = e.getBindingResult().getFieldError();
        return ResponseVo.error(fieldError.getDefaultMessage());
    }

    @ExceptionHandler(UserLoginExecption.class)
    public ResponseVo userLoginExection(UserLoginExecption e){
        return ResponseVo.message(ExceptionEnum.USER_NEED_LOGIN.getCode(),ExceptionEnum.PASSWORD_NOT_NULL.getMsg());
    }

    @ExceptionHandler(UnLoginExecption.class)
    public ResponseVo unLoginExection(UnLoginExecption e){
        return ResponseVo.error(e.getMessage());
    }


    /**
     * 其他异常错误
     * @param e
     * @return
     */
//    @ExceptionHandler(Exception.class)
    public ResponseVo otherException(Exception e){

        return ResponseVo.error("系统错误",e.getMessage());
    }
}
