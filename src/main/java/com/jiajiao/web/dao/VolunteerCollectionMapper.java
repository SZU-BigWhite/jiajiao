package com.jiajiao.web.dao;

import com.jiajiao.web.pojo.VolunteerCollection;
import org.springframework.stereotype.Repository;

@Repository
public interface VolunteerCollectionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(VolunteerCollection record);

    int insertSelective(VolunteerCollection record);

    VolunteerCollection selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(VolunteerCollection record);

    int updateByPrimaryKey(VolunteerCollection record);
}