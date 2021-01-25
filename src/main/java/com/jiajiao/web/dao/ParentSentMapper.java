package com.jiajiao.web.dao;

import com.jiajiao.web.pojo.ParentSent;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParentSentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ParentSent record);

    int insertSelective(ParentSent record);

    ParentSent selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ParentSent record);

    int updateByPrimaryKey(ParentSent record);

    List<ParentSent> selectByStudentResumeId(Integer s_resume_id);

    List<ParentSent> selectByParentNeedId(Integer p_need_id);

    int deleteByPNIdAndSRId(@Param("p_need_id") Integer p_need_id,@Param("s_resume_id") Integer s_resume_id);

    int deleteByStudentResumeId(Integer s_resume_id);

    int deleteByParentNeedId(Integer p_need_id);
}