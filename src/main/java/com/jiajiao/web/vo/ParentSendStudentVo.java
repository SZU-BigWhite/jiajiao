package com.jiajiao.web.vo;

import javax.validation.constraints.NotNull;

public class ParentSendStudentVo {
    @NotNull(message = "pNeedId 不能为空")
    private Integer pNeedId;
    @NotNull(message = "sResumeId 不能为空")
    private Integer sResumeId;

    @Override
    public String toString() {
        return "ParentSendStudentVo{" +
                "pNeedId=" + pNeedId +
                ", sResumeId=" + sResumeId +
                '}';
    }

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
}
