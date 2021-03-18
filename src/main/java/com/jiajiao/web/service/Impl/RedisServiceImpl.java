package com.jiajiao.web.service.Impl;

import com.google.gson.Gson;
import com.jiajiao.web.dao.ParentNeedMapper;
import com.jiajiao.web.dao.StudentResumeMapper;
import com.jiajiao.web.dao.SubjectMapper;
import com.jiajiao.web.dao.TimeMapper;
import com.jiajiao.web.enums.UserReqConst;
import com.jiajiao.web.pojo.ParentNeed;
import com.jiajiao.web.pojo.StudentResume;
import com.jiajiao.web.pojo.Subject;
import com.jiajiao.web.pojo.Time;
import com.jiajiao.web.service.IRedisService;
import com.jiajiao.web.vo.ParentNeedVo;
import com.jiajiao.web.vo.ResponseVo;
import com.jiajiao.web.vo.StudentResumeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Service
public class RedisServiceImpl implements IRedisService {
    @Autowired
    ParentNeedMapper parentNeedMapper;
    @Autowired
    StudentResumeMapper studentResumeMapper;
    @Autowired
    TimeMapper timeMapper;
    @Autowired
    SubjectMapper subjectMapper;
    @Autowired
    Gson gson;
    @Autowired
    StringRedisTemplate redisTemplate;


    private final String STUDENT_RESUME = "student_resume";
    private final String PARENT_NEED = "parent_need";
    Long recommendSize = 50l;


    @Override
    public List<StudentResumeVo> getStudentResumeVoList() {
        List<StudentResumeVo> res = new ArrayList<>();

        ListOperations<String, String> opsForList = redisTemplate.opsForList();
        Long size = opsForList.size(STUDENT_RESUME);
        List<String> range = opsForList.range(STUDENT_RESUME, 0l, size);
        for (String s : range) {
            StudentResumeVo studentResumeVo = gson.fromJson(s, StudentResumeVo.class);
            res.add(studentResumeVo);
        }
        return res;
    }

    @Override
    public List<ParentNeedVo> getParentNeedVoList() {
        List<ParentNeedVo> res = new ArrayList<>();

        ListOperations<String, String> opsForList = redisTemplate.opsForList();
        Long size = opsForList.size(PARENT_NEED);
        List<String> range = opsForList.range(PARENT_NEED, 0l, size);
        for (String s : range) {
            ParentNeedVo parentNeedVo = gson.fromJson(s, ParentNeedVo.class);
            res.add(parentNeedVo);
        }
        return res;
    }



    @Override
    public void addNeedRecommend(Integer pNId, StudentResumeVo studentResumeVo, double score) {
        redisTemplate.opsForZSet().add(pNId + "p", gson.toJson(studentResumeVo), score);
        redisTemplate.expire(pNId + "p", 5, TimeUnit.DAYS);
    }

    @Override
    public List<StudentResumeVo> getNeedRecommend(Integer pNId) {
        List<StudentResumeVo> res = new ArrayList<>();

        Set<String> range  = redisTemplate.opsForZSet().reverseRange(pNId + "p", 0, recommendSize);
        for(String vo:range){
            StudentResumeVo studentResumeVo = gson.fromJson(vo, StudentResumeVo.class);
            res.add(studentResumeVo);
        }
        return res;
    }

    @Override
    public void addResumeRecommend(Integer sRId, ParentNeedVo parentNeedVo, double score) {
        redisTemplate.opsForZSet().add(sRId + "s", gson.toJson(parentNeedVo), score);
        redisTemplate.expire(sRId + "s", 5, TimeUnit.DAYS);
    }

    @Override
    public List<ParentNeedVo> getResumeRecommend(Integer sRId) {
        List<ParentNeedVo> res = new ArrayList<>();

        Set<String> range = redisTemplate.opsForZSet().reverseRange(sRId + "s", 0, recommendSize);
        for (String vo : range) {
            ParentNeedVo parentNeedVo = gson.fromJson(vo, ParentNeedVo.class);
            res.add(parentNeedVo);
        }
        return res;
    }

    @Override
    public void addRelativeResume(Integer sRId, StudentResumeVo studentResumeVo, double score) {
        redisTemplate.opsForZSet().add(sRId + "ss", gson.toJson(studentResumeVo), score);
        redisTemplate.expire(sRId + "ss", 5, TimeUnit.HOURS);
    }

    @Override
    public List<StudentResumeVo> getRelativeResume(Integer sRId) {
        List<StudentResumeVo> res = new ArrayList<>();

        Set<String> range = redisTemplate.opsForZSet().reverseRange(sRId + "ss", 0, 6);
        for (String vo : range) {
            StudentResumeVo studentResumeVo = gson.fromJson(vo, StudentResumeVo.class);
            res.add(studentResumeVo);
        }
        return res;
    }

    @Override
    public void addRelativeNeed(Integer pNId, ParentNeedVo parentNeedVo, double score) {
        redisTemplate.opsForZSet().add(pNId + "pp", gson.toJson(parentNeedVo), score);
        redisTemplate.expire(pNId + "pp", 5, TimeUnit.HOURS);
    }

    @Override
    public List<ParentNeedVo> getRelativeNeed(Integer pNId) {
        List<ParentNeedVo> res = new ArrayList<>();

        Set<String> range = redisTemplate.opsForZSet().reverseRange(pNId + "pp", 0, recommendSize);
        for (String vo : range) {
            ParentNeedVo parentNeedVo = gson.fromJson(vo, ParentNeedVo.class);
            res.add(parentNeedVo);
        }
        return res;
    }

    private ParentNeedVo buildParentNeedVo(ParentNeed parentNeed) {
        ParentNeedVo parentNeedVo = new ParentNeedVo();

        List<Time> times = timeMapper.selectByOutKeyAndType(parentNeed.getId(), UserReqConst.TIME_PARENT_TYPE);
        List<Subject> subjects = subjectMapper.selectByOutKeyAndType(parentNeed.getId(), UserReqConst.SUBJECT_PARENT_TYPE);

        parentNeedVo.setTimeList(times);
        parentNeedVo.setSubjectList(subjects);
        parentNeedVo.setParentNeed(parentNeed);
        return parentNeedVo;
    }

    private StudentResumeVo buildStudentResumeVo(StudentResume studentResume) {
        StudentResumeVo studentResumeVo = new StudentResumeVo();

        List<Time> times = timeMapper.selectByOutKeyAndType(studentResume.getId(), UserReqConst.TIME_STUDENT_TYPE);
        List<Subject> subjects = subjectMapper.selectByOutKeyAndType(studentResume.getId(), UserReqConst.SUBJECT_STUDENT_TYPE);

        studentResumeVo.setSubjectList(subjects);
        studentResumeVo.setTimeList(times);
        studentResumeVo.setStudentResume(studentResume);
        return studentResumeVo;
    }

    /**
     * 从数据库拉取学生简历数据到redis
     *
     * @return
     */
    @PostConstruct
    @Scheduled(fixedRate = 36000000)        //每10分钟更新一次
    public boolean storeStudentResume() {
        List<String> list = new ArrayList<>();

        List<StudentResume> studentResumeList = studentResumeMapper.selectAllByOrder(null);
        for (StudentResume studentResume : studentResumeList) {
            StudentResumeVo studentResumeVo = buildStudentResumeVo(studentResume);
            list.add(gson.toJson(studentResumeVo));
        }

        ListOperations<String, String> opsForList = redisTemplate.opsForList();
        redisTemplate.delete(STUDENT_RESUME);
        opsForList.leftPushAll(STUDENT_RESUME, list);
        return true;
    }

    /**
     * 从数据库拉取家长需求数据到redis
     *
     * @return
     */
    @PostConstruct
    @Scheduled(fixedRate = 36000000)        //每10分钟更新一次
    public boolean storeParentNeed() {
        List<String> list = new ArrayList<>();

        List<ParentNeed> parentNeedList = parentNeedMapper.selectAll();
        for (ParentNeed parentNeed : parentNeedList) {
            ParentNeedVo parentNeedVo = buildParentNeedVo(parentNeed);
            list.add(gson.toJson(parentNeedVo));
        }

        ListOperations<String, String> opsForList = redisTemplate.opsForList();
        redisTemplate.delete(PARENT_NEED);
        opsForList.leftPushAll(PARENT_NEED, list);
        return true;
    }


}
