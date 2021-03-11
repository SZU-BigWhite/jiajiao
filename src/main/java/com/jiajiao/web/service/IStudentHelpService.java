package com.jiajiao.web.service;

import com.jiajiao.web.pojo.StudentHelp;
import com.jiajiao.web.vo.ResponseVo;

public interface IStudentHelpService {
    ResponseVo addStudentHelp(StudentHelp studentHelp,Integer uId);
    ResponseVo deleteStudentHelp(Integer id,Integer uId);
    ResponseVo getStudentsHelpList();
    ResponseVo getStudentHelpListByUid(Integer uId);
}
