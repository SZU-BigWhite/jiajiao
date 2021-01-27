package com.jiajiao.web.dao;

import com.jiajiao.web.pojo.UserMessages;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMessagesMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserMessages record);

    int insertSelective(UserMessages record);

    UserMessages selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserMessages record);

    int updateByPrimaryKey(UserMessages record);

    List<UserMessages> selectByUId(Integer uid);

}