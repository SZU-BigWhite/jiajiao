package com.learn.web.form;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class LoginForm {

    @NotNull(message = "phone 不能为空")
    Long phone;
    @NotNull(message = "password 不能为空")
    String password;

    @Override
    public String toString() {
        return "LoginForm{" +
                "username='" + phone + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
