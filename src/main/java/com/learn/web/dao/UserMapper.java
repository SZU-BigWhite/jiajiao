package com.learn.web.dao;

import com.learn.web.pojo.User;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    User selectByPhone(Long phone);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}