package com.jiajiao.web.dao;

import com.jiajiao.web.pojo.StudentSend;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface StudentSendMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(StudentSend record);

    int insertSelective(StudentSend record);

    StudentSend selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(StudentSend record);

    int updateByPrimaryKey(StudentSend record);

    List<StudentSend> selectByHelpId(@Param("help_id") Integer help_id);
}