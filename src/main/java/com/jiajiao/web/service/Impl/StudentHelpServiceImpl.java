package com.jiajiao.web.service.Impl;

import com.jiajiao.web.dao.StudentHelpMapper;
import com.jiajiao.web.dao.StudentSendMapper;
import com.jiajiao.web.pojo.StudentHelp;
import com.jiajiao.web.pojo.StudentSend;
import com.jiajiao.web.service.IStudentHelpService;
import com.jiajiao.web.vo.ResponseVo;
import com.jiajiao.web.vo.StudentHelpVo;
import com.jiajiao.web.vo.StudentResumeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        List<StudentHelpVo> studentHelpVos=new ArrayList<>();
        for(StudentHelp temp:studentsHelpList){
            StudentHelpVo vo = buildStudentHelpVo(temp);
            studentHelpVos.add(vo);
        }
        return ResponseVo.success("所有学生互助内容查询成功",studentHelpVos);
    }

    public ResponseVo getStudentHelpListByUid(Integer uId){
        List<StudentHelp> studentHelpList = studentHelpMapper.selectByUId(uId);
        List<StudentHelpVo> studentHelpVos=new ArrayList<>();
        for(StudentHelp temp:studentHelpList){
            StudentHelpVo vo = buildStudentHelpVo(temp);
            studentHelpVos.add(vo);
        }
        return ResponseVo.success("查询个人互助成功",studentHelpVos);
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

    @Override
    public ResponseVo getStudentHelpReceive(Integer id) {
        List<StudentSend> studentSends = studentSendMapper.selectByHelpId(id);
        return ResponseVo.success("获取接收的帮助成功",studentSends);
    }

    private Integer getReceiveNums(Integer id){
        List<StudentSend> studentSends = studentSendMapper.selectByHelpId(id);
        return studentSends.size();
    }

    private StudentHelpVo buildStudentHelpVo(StudentHelp studentHelp){
        Integer id = studentHelp.getId();
        Integer receiveNums = getReceiveNums(id);
        StudentHelpVo vo=new StudentHelpVo();
        vo.setCount(receiveNums);
        vo.setStudentHelp(studentHelp);
        return vo;
    }

}
