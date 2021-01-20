package com.jiajiao.web.dao;

import com.jiajiao.web.pojo.Subject;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Subject record);

    int insertSelective(Subject record);

    Subject selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Subject record);

    int updateByPrimaryKey(Subject record);

    List<Subject> selectByOutKeyAndType(@Param("out_id") Integer out_id, @Param("type") Integer type);

    int insertList(@Param("record_list") List<Subject> record_list);

    int deleteByOutKey(Integer out_id);

}