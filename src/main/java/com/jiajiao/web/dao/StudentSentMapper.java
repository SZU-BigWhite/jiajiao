package com.jiajiao.web.dao;

import com.jiajiao.web.pojo.StudentSent;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentSentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(StudentSent record);

    int insertSelective(StudentSent record);

    StudentSent selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(StudentSent record);

    int updateByPrimaryKey(StudentSent record);

    List<StudentSent> selectByStudentResumeId(Integer s_resume_id);

    List<StudentSent> selectByParentNeedId(Integer p_need_id);

    int deleteBySRIdAndPNId(@Param("s_resume_id") Integer s_resume_id, @Param("p_need_id") Integer p_need_id);

    int deleteByStudentResumeId(Integer s_resume_id);

    int deleteByParentNeedId(Integer p_need_id);

}