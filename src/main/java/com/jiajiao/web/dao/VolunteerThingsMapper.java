package com.jiajiao.web.dao;

import org.apache.ibatis.annotations.Param;
import com.jiajiao.web.pojo.VolunteerThings;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VolunteerThingsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(VolunteerThings record);

    int insertSelective(VolunteerThings record);

    VolunteerThings selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(VolunteerThings record);

    int updateByPrimaryKey(VolunteerThings record);

    List<VolunteerThings> selectByUId(@Param("u_id") Integer u_id);

    List<VolunteerThings> selectByCIdAndStatus(@Param("c_id") Integer c_id,@Param("status") Integer status);

    void updateStatusById(@Param("id") Integer id,@Param("status")Integer status);
}