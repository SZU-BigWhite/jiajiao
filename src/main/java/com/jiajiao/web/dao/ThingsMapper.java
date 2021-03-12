package com.jiajiao.web.dao;

import com.jiajiao.web.pojo.Subject;
import com.jiajiao.web.pojo.Things;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ThingsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Things record);

    int insertSelective(Things record);

    Things selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Things record);

    int updateByPrimaryKey(Things record);

    int insertList(@Param("things_list") List<Things> things_list);
}