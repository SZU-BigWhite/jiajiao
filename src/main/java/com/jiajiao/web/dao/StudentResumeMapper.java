package com.jiajiao.web.dao;

import com.jiajiao.web.pojo.StudentResume;
import com.jiajiao.web.form.GetStudentResumeOrderForm;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentResumeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(StudentResume record);

    int insertSelective(StudentResume record);

    StudentResume selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(StudentResume record);

    int updateByPrimaryKey(StudentResume record);

    List<StudentResume> selectByUId(Integer u_id);

    List<StudentResume> selectAllByOrder(@Param("resumeOrderVo") GetStudentResumeOrderForm resumeOrderVo);

    int deleteByPrimaryKeyAndUId(@Param("id")Integer id,@Param("u_id")Integer u_id);

    List<StudentResume> selectByIdList(@Param("idList") List<Integer> idList);

}