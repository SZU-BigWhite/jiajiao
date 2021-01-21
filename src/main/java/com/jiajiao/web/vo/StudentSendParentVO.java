package com.jiajiao.web.vo;

import javax.validation.constraints.NotNull;

public class StudentSendParentVO {
    @NotNull(message = "sResumeId 不能为空")
    private Integer sResumeId;
    @NotNull(message = "pNeedId 不能为空")
    private Integer pNeedId;

    public Integer getpNeedId() {
        return pNeedId;
    }

    public void setpNeedId(Integer pNeedId) {
        this.pNeedId = pNeedId;
    }

    public Integer getsResumeId() {
        return sResumeId;
    }

    public void setsResumeId(Integer sResumeId) {
        this.sResumeId = sResumeId;
    }

    @Override
    public String toString() {
        return "StudentSendParentVO{" +
                "sResumeId=" + sResumeId +
                ", pNeedId=" + pNeedId +
                '}';
    }
}
