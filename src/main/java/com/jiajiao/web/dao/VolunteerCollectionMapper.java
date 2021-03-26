package com.jiajiao.web.dao;

import com.jiajiao.web.pojo.VolunteerCollection;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VolunteerCollectionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(VolunteerCollection record);

    int insertSelective(VolunteerCollection record);

    VolunteerCollection selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(VolunteerCollection record);

    int updateByPrimaryKey(VolunteerCollection record);

    List<VolunteerCollection> selectAll();

    List<VolunteerCollection> selectByUId(@Param("u_id") Integer u_id);

    void updateStatusById(@Param("id") Integer id,@Param("status")Integer status);

    Integer selectDataSum();
}