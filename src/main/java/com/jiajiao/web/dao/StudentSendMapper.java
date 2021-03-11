package com.jiajiao.web.dao;

import com.jiajiao.web.pojo.StudentSend;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentSendMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(StudentSend record);

    int insertSelective(StudentSend record);

    StudentSend selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(StudentSend record);

    int updateByPrimaryKey(StudentSend record);
}