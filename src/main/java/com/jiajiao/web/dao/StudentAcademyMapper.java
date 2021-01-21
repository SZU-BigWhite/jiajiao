package com.jiajiao.web.dao;

import com.jiajiao.web.pojo.StudentAcademy;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentAcademyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(StudentAcademy record);

    int insertSelective(StudentAcademy record);

    StudentAcademy selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(StudentAcademy record);

    int updateByPrimaryKey(StudentAcademy record);

    List<StudentAcademy> selectAll();
}