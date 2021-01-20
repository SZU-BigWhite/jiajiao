package com.jiajiao.web.form;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class LoginForm {

    @NotNull(message = "phone can't be null")
    Long phone;
    @NotNull(message = "password can't be null")
    @NotEmpty(message = "password can't be null")
    String password;

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "LoginForm{" +
                "username='" + phone + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
