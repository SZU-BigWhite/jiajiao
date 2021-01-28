package com.jiajiao.web.service;

import com.jiajiao.web.form.GetStudentResumeOrderForm;
import com.jiajiao.web.vo.ResponseVo;
import com.jiajiao.web.vo.StudentResumeVo;
import com.jiajiao.web.vo.StudentSendParentVO;

public interface IStudentService {
    public ResponseVo getStudentAcademy();
    public ResponseVo getStudentResumeByUId(Integer uId);
    public ResponseVo addStudentResume(StudentResumeVo studentResumeVo,Integer uId);
    public ResponseVo updateStudentResume(StudentResumeVo studentResumeVo);
    public ResponseVo getStudentsResumes(GetStudentResumeOrderForm getStudentResumeOrderVo);
    public boolean checkIdAndUId(Integer id,Integer uId);
    public ResponseVo deleteStudentResume(Integer id);
    public ResponseVo sendStudentResume(StudentSendParentVO studentSendParentVO);
    public ResponseVo deleteSendStudentResume(StudentSendParentVO studentSendParentVO);
    public ResponseVo getStudentReceive(Integer id);
    public ResponseVo getStudentSent(Integer id);
    public ResponseVo getStudentResumeSum();
}
