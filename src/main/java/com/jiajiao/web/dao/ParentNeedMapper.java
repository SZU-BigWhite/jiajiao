package com.jiajiao.web.dao;

import com.jiajiao.web.form.GetParentNeedOrderForm;
import com.jiajiao.web.pojo.ParentNeed;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParentNeedMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ParentNeed record);

    int insertSelective(ParentNeed record);

    ParentNeed selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ParentNeed record);

    int updateByPrimaryKey(ParentNeed record);

    List<ParentNeed> selectByOutKey(Integer u_id);

    List<ParentNeed> selectAll();

    List<ParentNeed> selectAllByOrder(@Param("parentNeedOrder") GetParentNeedOrderForm parentNeedOrder);

    List<ParentNeed> selectByIdList(@Param("idList") List<Integer> idList);

    int selectParentNeedSum();
}