package com.jiajiao.web.utils;

import com.jiajiao.web.dao.SubjectMapper;
import com.jiajiao.web.dao.TimeMapper;
import com.jiajiao.web.enums.UserReqConst;
import com.jiajiao.web.pojo.StudentResume;
import com.jiajiao.web.pojo.Subject;
import com.jiajiao.web.pojo.Time;
import com.jiajiao.web.vo.StudentResumeVo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class StudentResumeUtil {

    //根据StudentResume 构建 Vo
    public static StudentResumeVo buildStudentResumeVo(StudentResume resume,TimeMapper timeMapper,SubjectMapper subjectMapper){
        //获取time、subject
        List<Subject> subjectList = subjectMapper.selectByOutKeyAndType(resume.getId(), UserReqConst.SUBJECT_STUDENT_TYPE);
        List<Time> timeList = timeMapper.selectByOutKeyAndType(resume.getId(),UserReqConst.TIME_STUDENT_TYPE);
        //构建Vo
        StudentResumeVo studentResumeVo=new StudentResumeVo();
        studentResumeVo.setStudentResume(resume);
        studentResumeVo.setSubjectList(subjectList);
        studentResumeVo.setTimeList(timeList);
        return studentResumeVo;
    }
    //根据StudentResumeList 组装VoList
    public static List<StudentResumeVo> buildStudentResumeVoList(List<StudentResume> studentResumeList,TimeMapper timeMapper,SubjectMapper subjectMapper){
        //组装Vo List
        List<StudentResumeVo> res=new ArrayList<StudentResumeVo>();
        for (StudentResume resume:studentResumeList){
            StudentResumeVo studentResumeVo=buildStudentResumeVo(resume,timeMapper,subjectMapper);
            res.add(studentResumeVo);
        }
        return res;
    }
}
