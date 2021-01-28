package com.jiajiao.web.service;

import com.jiajiao.web.vo.ResponseVo;

import javax.xml.ws.Response;

public interface IAdminService {
    boolean checkAdmin(Integer uId);
    ResponseVo deleteStudentResume(Integer id);
    ResponseVo deleteParentNeed(Integer id);
}
