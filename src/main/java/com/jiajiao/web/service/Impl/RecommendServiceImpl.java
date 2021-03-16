package com.jiajiao.web.service.Impl;

import com.jiajiao.web.dao.ParentNeedMapper;
import com.jiajiao.web.dao.StudentResumeMapper;
import com.jiajiao.web.dao.SubjectMapper;
import com.jiajiao.web.dao.TimeMapper;
import com.jiajiao.web.enums.UserReqConst;
import com.jiajiao.web.enums.UserRoleEnum;
import com.jiajiao.web.pojo.ParentNeed;
import com.jiajiao.web.pojo.StudentResume;
import com.jiajiao.web.pojo.Subject;
import com.jiajiao.web.pojo.Time;
import com.jiajiao.web.service.IRecommendService;
import com.jiajiao.web.vo.ParentNeedVo;
import com.jiajiao.web.vo.ResponseVo;
import com.jiajiao.web.vo.StudentResumeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecommendServiceImpl implements IRecommendService {
    @Autowired
    ParentNeedMapper parentNeedMapper;
    @Autowired
    StudentResumeMapper studentResumeMapper;
    @Autowired
    TimeMapper timeMapper;
    @Autowired
    SubjectMapper subjectMapper;
    double baseScore=20.0;

    @Autowired
    RedisServiceImpl redisService;
    @Autowired
    StringRedisTemplate redisTemplate;

    @Override
    public ResponseVo recommendNeedByNeed(Integer pNId) {


        return null;
    }

    @Override
    public ResponseVo recommendResumeByNeed(Integer pNId) {
        if(!redisTemplate.hasKey(pNId+"p")){
            ParentNeedVo parentNeedVo = getParentNeedVo(pNId);
            List<StudentResumeVo> studentResumeVoList = redisService.getStudentResumeVoList();
            for(StudentResumeVo studentResumeVo:studentResumeVoList){
                double score = countNeedWithResume(parentNeedVo, studentResumeVo);
                redisService.addNeedRecommend(parentNeedVo.getParentNeed().getId(),studentResumeVo,score);
            }
        }
        List<StudentResumeVo> needRecommend = redisService.getNeedRecommend(pNId);
        return ResponseVo.success("推荐成功",needRecommend);
    }

    @Override
    public ResponseVo recommendNeedByResume(Integer sRId) {
        if(!redisTemplate.hasKey(sRId+"s")){
            StudentResumeVo studentResumeVo = getStudentResumeVo(sRId);
            List<ParentNeedVo> parentNeedVoList = redisService.getParentNeedVoList();
            for(ParentNeedVo parentNeedVo:parentNeedVoList){
                double score = countResumeWithNeed(studentResumeVo, parentNeedVo);
                redisService.addResumeRecommend(sRId,parentNeedVo,score);
            }
        }
        List<ParentNeedVo> resumeRecommend = redisService.getResumeRecommend(sRId);

        return ResponseVo.success("推荐成功",resumeRecommend);
    }

    @Override
    public ResponseVo recommendResumeByResume(Integer sRId) {
        return null;
    }

    private ParentNeedVo getParentNeedVo(Integer pNId){
        ParentNeedVo parentNeedVo=new ParentNeedVo();

        ParentNeed parentNeed = parentNeedMapper.selectByPrimaryKey(pNId);
        List<Time> times = timeMapper.selectByOutKeyAndType(pNId, UserReqConst.TIME_PARENT_TYPE);
        List<Subject> subjects = subjectMapper.selectByOutKeyAndType(pNId, UserReqConst.SUBJECT_PARENT_TYPE);

        parentNeedVo.setTimeList(times);
        parentNeedVo.setSubjectList(subjects);
        parentNeedVo.setParentNeed(parentNeed);
        return parentNeedVo;
    }

    private StudentResumeVo getStudentResumeVo(Integer sRId){
        StudentResumeVo studentResumeVo=new StudentResumeVo();

        StudentResume studentResume = studentResumeMapper.selectByPrimaryKey(sRId);
        List<Time> times = timeMapper.selectByOutKeyAndType(sRId, UserReqConst.TIME_STUDENT_TYPE);
        List<Subject> subjects = subjectMapper.selectByOutKeyAndType(sRId, UserReqConst.SUBJECT_STUDENT_TYPE);

        studentResumeVo.setSubjectList(subjects);
        studentResumeVo.setTimeList(times);
        studentResumeVo.setStudentResume(studentResume);
        return studentResumeVo;
    }

    private double countNeedWithNeed(ParentNeedVo needVo1,ParentNeedVo needVo2){
        return 0;
    }

    private double countNeedWithResume(ParentNeedVo needVo,StudentResumeVo resumeVo){
        ParentNeed parentNeed = needVo.getParentNeed();
        StudentResume studentResume = resumeVo.getStudentResume();
        return countTimeScore(needVo.getTimeList(), resumeVo.getTimeList())
                +countSubjectScore(needVo.getSubjectList(),resumeVo.getSubjectList())
                +countSalary(parentNeed,studentResume)
                +countEducation(parentNeed,studentResume)
                +countClass(parentNeed,studentResume)
                +countGrade(studentResume.getGrade())
                +countUniversity(studentResume.getUniversity())
                +countResumeLength(studentResume)
                ;
    }
    private double countResumeWithNeed(StudentResumeVo resumeVo,ParentNeedVo needVo){
        ParentNeed parentNeed = needVo.getParentNeed();
        StudentResume studentResume = resumeVo.getStudentResume();
        return countTimeScore(resumeVo.getTimeList(),needVo.getTimeList())
                +countSubjectScore(resumeVo.getSubjectList(),needVo.getSubjectList())
                +countSalary(parentNeed,studentResume)
                +countEducation(parentNeed,studentResume)
                +countClass(parentNeed,studentResume)
                +countNeedDuration(parentNeed)
                +countNeedDuration(parentNeed)
                ;
    }
    private double countNeedDuration(ParentNeed parentNeed){
        return parentNeed.getDuration()*baseScore/3;
    }
    private double countNeedArriveTime(ParentNeed parentNeed){
        return 40-parentNeed.getArriveHours()/2;
    }

    private double countResumeWithResume(StudentResumeVo resumeVo1,StudentResumeVo resumeVo2){
        return 0;
    }
    private double countTimeScore(List<Time> times1,List<Time> times2){
        double count=0;
        for(Time temp1:times1){
            for(Time temp2:times2){
                if (temp1.getFreeTime().equals(temp2.getFreeTime())) {
                    count++;
                }
            }
        }
        double length=times1.size();
        return baseScore*count/length;
    }
    private double countSubjectScore(List<Subject> subjects1,List<Subject> subjects2){
        double count=0;
        for(Subject temp1:subjects1){
            for(Subject temp2:subjects2){
                if (temp1.getName().equals(temp2.getName())) {
                    count++;
                }
            }
        }
        double length=subjects1.size();
        return baseScore*count/length;
    }
    private double countEducation(ParentNeed parentNeed,StudentResume studentResume){
        Integer pEducation = parentNeed.getEducation();
        Integer sEducation = studentResume.getEducation();
        if(sEducation>=pEducation)
            return baseScore/2;
        return 0;
    }
    private double countSalary(ParentNeed parentNeed,StudentResume studentResume){
        Float sSalary = studentResume.getSalary();
        Float pSalary = parentNeed.getSalary();
        if(pSalary>=sSalary)
            return baseScore;
        else if((sSalary-pSalary)<=10)
            return baseScore/2;
        else
            return 0;
    }
    private double countClass(ParentNeed parentNeed,StudentResume studentResume){
        Integer studentClass = parentNeed.getStudentClass();
        Integer ableClass = studentResume.getAbleClass();
        if(studentClass<=ableClass)
            return baseScore;
        return 0;
    }
    private double countUniversity(String university){
        if(university.equals("深圳大学")){
            return baseScore/4;
        }else{
            return 0;
        }
    }
    private double countGrade(Float grade){
        return (grade/37.5-10)*2;
    }
    private double countResumeLength(StudentResume r){
        double res=0;
        double length = r.getCharacterCondiction().toCharArray().length+
                        r.getTeachGoal().toCharArray().length+
                        r.getTeachStress().toCharArray().length+
                        r.getFeedback().toCharArray().length+
                        r.getLearnMethods().toCharArray().length+
                        r.getShowSelf().toCharArray().length;

        res=length/2/40;
        return res>=30?25:res;
    }
}
