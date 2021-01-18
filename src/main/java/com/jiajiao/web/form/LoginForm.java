package com.jiajiao.web.form;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class LoginForm {

    Long phone;

    String password;

    @Override
    public String toString() {
        return "LoginForm{" +
                "username='" + phone + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
