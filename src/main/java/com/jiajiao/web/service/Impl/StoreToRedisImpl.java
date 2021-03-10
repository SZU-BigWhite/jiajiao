package com.jiajiao.web.service.Impl;

import com.google.gson.Gson;
import com.jiajiao.web.dao.ParentNeedMapper;
import com.jiajiao.web.dao.StudentResumeMapper;
import com.jiajiao.web.pojo.ParentNeed;
import com.jiajiao.web.pojo.StudentResume;
import com.jiajiao.web.service.IStoreToRedis;
import com.jiajiao.web.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StoreToRedisImpl implements IStoreToRedis {

    @Autowired
    StudentResumeMapper studentResumeMapper;
    @Autowired
    Gson gson;
    @Autowired
    StringRedisTemplate redisTemplate;
//    @Autowired
//    RedisTemplate<String,Object> rTemplate;

    @Autowired
    ParentNeedMapper parentNeedMapper;
    private final String STUDENT_RESUME = "student_resume";
    private final String PARENT_NEED = "parent_need";

    /**
     * 从数据库拉取学生简历数据到redis
     * @return
     */
    public boolean storeStudentResume() {
        List<String> list = new ArrayList<>();
        List<StudentResume> studentResumeList = studentResumeMapper.selectAllByOrder(null);
        for (StudentResume studentResume : studentResumeList) {
            String s = gson.toJson(studentResume);
            list.add(s);
        }
        ListOperations<String, String> opsForList = redisTemplate.opsForList();
        opsForList.leftPushAll(STUDENT_RESUME, list);
        return true;
    }
    /**
     * 从数据库拉取家长需求数据到redis
     * @return
     */
    public boolean storeParentNeed() {
        List<String> list = new ArrayList<>();
        List<ParentNeed> parentNeedList = parentNeedMapper.selectAll();
        for (ParentNeed parentNeed : parentNeedList) {
            String s = gson.toJson(parentNeed);
            list.add(s);
        }
        ListOperations<String, String> opsForList = redisTemplate.opsForList();
        opsForList.leftPushAll(PARENT_NEED,list);
        return true;
    }
    public ResponseVo recommendResumeByNeed(ParentNeed parentNeed){
        ListOperations<String, String> opsForList = redisTemplate.opsForList();
        ZSetOperations<String, String> zSetOperations = redisTemplate.opsForZSet();

        int step=4;     //步长，每次取出的数据数
        List<String> range;
        for(Long start=0l;start<10000;start=start+step+1){
            range = opsForList.range(STUDENT_RESUME, start, start + step);
            for(String s:range){
                StudentResume studentResume = gson.fromJson(s, StudentResume.class);
                zSetOperations.add(parentNeed.getId()+"p",s,studentResume.getId());
            }

            if(range.size()<step)
                break;
        }

        return null;
    }
}
