package com.jiajiao.web.form;

public class RegisterForm {
    private Long phone;
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
