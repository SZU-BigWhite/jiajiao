package com.jiajiao.web.service.Impl;

import com.jiajiao.web.dao.StudentHelpMapper;
import com.jiajiao.web.dao.StudentSendMapper;
import com.jiajiao.web.pojo.StudentHelp;
import com.jiajiao.web.pojo.StudentSend;
import com.jiajiao.web.service.IStudentHelpService;
import com.jiajiao.web.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentHelpServiceImpl implements IStudentHelpService {
    @Autowired
    StudentHelpMapper studentHelpMapper;
    @Autowired
    StudentSendMapper studentSendMapper;

    @Override
    public ResponseVo addStudentHelp(StudentHelp studentHelp, Integer uId) {
        studentHelp.setuId(uId);
        studentHelpMapper.insertSelective(studentHelp);
        return ResponseVo.success("增加学生互助成功");
    }

    @Override
    public ResponseVo deleteStudentHelp(Integer id, Integer uId) {
        StudentHelp studentHelp = studentHelpMapper.selectByPrimaryKey(id);
        if(studentHelp==null||!studentHelp.getuId().equals(uId)){
            return ResponseVo.error("无法操作他人的互助内容");
        }
        studentHelpMapper.deleteByPrimaryKey(id);
        return ResponseVo.success("删除内容成功");
    }

    public ResponseVo getStudentsHelpList(){
        List<StudentHelp> studentsHelpList = studentHelpMapper.selectAll();
        return ResponseVo.success("所有学生互助内容查询成功",studentsHelpList);
    }

    public ResponseVo getStudentHelpListByUid(Integer uId){
        List<StudentHelp> studentHelpList = studentHelpMapper.selectByUId(uId);
        return ResponseVo.success("查询个人互助成功",studentHelpList);
    }


    public ResponseVo updateStudentHelp(StudentHelp studentHelp,Integer uId){
        Integer id = studentHelp.getId();
        StudentHelp studentHelpTemp = studentHelpMapper.selectByPrimaryKey(id);
        if(studentHelpTemp==null||!studentHelpTemp.getuId().equals(uId)){
            return ResponseVo.error("无法操作他人的互助信息");
        }
        studentHelp.setuId(uId);
        studentHelpMapper.updateByPrimaryKeySelective(studentHelp);
        return ResponseVo.success("更新成功");
    }

    @Override
    public ResponseVo sendStudentHelp(StudentSend studentSend, Integer uId) {
        studentSend.setuId(uId);
        studentSendMapper.insertSelective(studentSend);
        return ResponseVo.success("发送互助成功");
    }

}
