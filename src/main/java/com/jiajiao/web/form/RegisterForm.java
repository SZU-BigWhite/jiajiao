package com.jiajiao.web.form;

import javax.validation.constraints.NotNull;

public class RegisterForm {

    @NotNull(message = "phone can't be null")
    private Long phone;
    @NotNull(message = "code can't be null")
    private Integer code;

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "RegisterForm{" +
                "phone=" + phone +
                ", code=" + code +
                '}';
    }
}
