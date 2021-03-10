package com.jiajiao.web.dao;

import com.jiajiao.web.pojo.Time;
import org.springframework.stereotype.Repository;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Repository
public interface TimeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Time record);

    int insertSelective(Time record);

    Time selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Time record);

    int updateByPrimaryKey(Time record);

    List<Time> selectByOutKeyAndType(@Param("out_id") Integer out_id,@Param("type") Integer type);

    int insertList(@Param("record_list") List<Time> record_list);

    int deleteByOutKey(@Param("out_id") Integer out_id,@Param("type") Integer type);
}