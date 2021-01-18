package com.learn.web.dao;

import com.learn.web.pojo.Time;
import org.springframework.stereotype.Component;

@Component
public interface TimeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Time record);

    int insertSelective(Time record);

    Time selectByPrimaryKey(Integer id);

    Time[] selectByOutKey(Integer out_id);

    int updateByPrimaryKeySelective(Time record);

    int updateByPrimaryKey(Time record);
}