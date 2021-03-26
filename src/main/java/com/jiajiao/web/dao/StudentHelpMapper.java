package com.jiajiao.web.dao;

import com.jiajiao.web.pojo.StudentHelp;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentHelpMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(StudentHelp record);

    int insertSelective(StudentHelp record);

    StudentHelp selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(StudentHelp record);

    int updateByPrimaryKey(StudentHelp record);

    List<StudentHelp> selectAll();

    List<StudentHelp> selectByUId(Integer u_Id);

    Integer selectDataSum();
}