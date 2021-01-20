package com.jiajiao.web.dao;

import com.jiajiao.web.pojo.ParentSent;
import org.springframework.stereotype.Repository;

@Repository
public interface ParentSentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ParentSent record);

    int insertSelective(ParentSent record);

    ParentSent selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ParentSent record);

    int updateByPrimaryKey(ParentSent record);
}