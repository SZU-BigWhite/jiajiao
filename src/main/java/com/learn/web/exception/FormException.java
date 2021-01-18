package com.learn.web.exception;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;

public class FormException extends BindException {

    public FormException(BindingResult bindingResult) {
        super(bindingResult);
    }

    public FormException(Object target, String objectName) {
        super(target, objectName);
    }
}
