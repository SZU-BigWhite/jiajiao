package com.jiajiao.web.dao;

import com.jiajiao.web.pojo.VolunteerThings;
import org.springframework.stereotype.Repository;

@Repository
public interface VolunteerThingsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(VolunteerThings record);

    int insertSelective(VolunteerThings record);

    VolunteerThings selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(VolunteerThings record);

    int updateByPrimaryKey(VolunteerThings record);
}