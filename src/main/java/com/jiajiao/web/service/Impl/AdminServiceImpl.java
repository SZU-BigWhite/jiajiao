package com.jiajiao.web.service.Impl;

import com.jiajiao.web.dao.ParentNeedMapper;
import com.jiajiao.web.dao.StudentResumeMapper;
import com.jiajiao.web.dao.UserMapper;
import com.jiajiao.web.enums.RedisIllegalConst;
import com.jiajiao.web.enums.UserRoleEnum;
import com.jiajiao.web.pojo.ParentNeed;
import com.jiajiao.web.pojo.StudentResume;
import com.jiajiao.web.pojo.User;
import com.jiajiao.web.service.IAdminService;
import com.jiajiao.web.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.support.SimpleTriggerContext;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

@Service
public class AdminServiceImpl implements IAdminService {


    @Autowired
    StringRedisTemplate redisTemplate;
    @Autowired
    StudentResumeMapper studentResumeMapper;
    @Autowired
    ParentNeedMapper parentNeedMapper;
    @Autowired
    UserMapper userMapper;
    @Override
    public boolean checkAdmin(Integer uId) {
        User user = userMapper.selectByPrimaryKey(uId);
        if(user!=null&&(user.getRole().equals(UserRoleEnum.ADMIN.getCode())||user.getRole().equals(UserRoleEnum.SUPER_ADMIN.getCode())))
            return true;
        return false;
    }

    @Override
    public ResponseVo deleteStudentResume(Integer id) {
        //记录 uId
        StudentResume studentResume = studentResumeMapper.selectByPrimaryKey(id);
        if(studentResume==null){
            return ResponseVo.error("该简历已被删除");
        }
        Integer uId = studentResume.getuId();
        recordIllegal(uId);

        //删除该简历
        int delete = studentResumeMapper.deleteByPrimaryKey(id);
        if(delete==0){
            return ResponseVo.error("删除失败|已无该简历");
        }
        return ResponseVo.success("简历删除成功");
    }

    @Override
    public ResponseVo deleteParentNeed(Integer id) {
        //记录 uId
        ParentNeed parentNeed = parentNeedMapper.selectByPrimaryKey(id);
        if(parentNeed==null){
            return ResponseVo.error("该需求已被删除");
        }
        Integer uId = parentNeed.getuId();
        recordIllegal(uId);

        //删除 该需求
        int delete = parentNeedMapper.deleteByPrimaryKey(id);
        if(delete==0){
            return ResponseVo.error("删除失败|已无该需求");
        }
        return ResponseVo.success("需求删除成功");

    }
    public void recordIllegal(Integer uId){
        //记录uId违规次数
        HashOperations<String, String, String> opsForHash = redisTemplate.opsForHash();
        String timesStr =(String)opsForHash.get(RedisIllegalConst.ILLEGAL_TIMES, uId+"");

        int times;
        if(timesStr==null||timesStr.equals("")) {
            times=1;
        } else {
            times=Integer.valueOf(timesStr)+1;
        }
        opsForHash.put(RedisIllegalConst.ILLEGAL_TIMES,uId+"",times+"");
        //判断是否需要封禁账号
        if(times>=5){
            //加入禁用列表
            User user = userMapper.selectByPrimaryKey(uId);
            opsForHash.put(RedisIllegalConst.ILLEGAL_PHONES,user.getPhone()+"","true");
            //删除session
            String userSession = DigestUtils.md5DigestAsHex(("" + user.getId() + user.getPhone()).getBytes());
            redisTemplate.delete(userSession);
        }
    }
}
