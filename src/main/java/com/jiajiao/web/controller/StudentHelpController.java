package com.jiajiao.web.controller;

import com.jiajiao.web.pojo.StudentHelp;
import com.jiajiao.web.pojo.StudentSend;
import com.jiajiao.web.pojo.StudentSent;
import com.jiajiao.web.service.Impl.MessagesServiceImpl;
import com.jiajiao.web.service.Impl.StudentHelpServiceImpl;
import com.jiajiao.web.utils.CookieUtils;
import com.jiajiao.web.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class StudentHelpController {
    @Autowired
    StringRedisTemplate redisTemplate;
    @Autowired
    MessagesServiceImpl messagesService;
    @Autowired
    StudentHelpServiceImpl studentHelpService;

    /**
     * 添加学生互助
     * @param request
     * @param studentHelp
     * @return
     */
    @PostMapping("/add/student/help")
    public ResponseVo addStudentHelp(HttpServletRequest request, @RequestBody StudentHelp studentHelp){
        int uId = CookieUtils.getUIdFromRedis(request, redisTemplate);
        return studentHelpService.addStudentHelp(studentHelp, uId);
    }

    /**
     * 删除互助
     * @param request
     * @param id
     * @return
     */
    @DeleteMapping("/delete/student/help")
    public ResponseVo deleteStudentHelp(HttpServletRequest request,Integer id){
        int uId = CookieUtils.getUIdFromRedis(request, redisTemplate);
        return studentHelpService.deleteStudentHelp(id, uId);
    }

    /**
     * 获取所有学生互助
     * @return
     */
    @GetMapping("/get/students/helpList")
    public ResponseVo getStudentsHelpList(){
        return studentHelpService.getStudentsHelpList();
    }

    /**
     * 获取个人学生互助
     * @param request
     * @return
     */
    @GetMapping("/get/student/helpList")
    public ResponseVo getStudentHelpList(HttpServletRequest request){
        int uId = CookieUtils.getUIdFromRedis(request, redisTemplate);
        return studentHelpService.getStudentHelpListByUid(uId);
    }

    @GetMapping("/get/student/help/by/id")
    public ResponseVo getStudentHelpList(Integer id){
        return studentHelpService.getStudentHelpById(id);
    }

    /**
     * 更新个人学生互助
     * @param request
     * @param studentHelp
     * @return
     */
    @PostMapping("/update/student/help")
    public ResponseVo updateStudentHelp(HttpServletRequest request,@RequestBody StudentHelp studentHelp){
        int uId = CookieUtils.getUIdFromRedis(request, redisTemplate);
        return studentHelpService.updateStudentHelp(studentHelp, uId);
    }

    /**
     * 发送帮扶
     * @param request
     * @param studentSend
     * @return
     */
    @PostMapping("/send/student/help")
    public ResponseVo sendStudentHelp(HttpServletRequest request, @RequestBody StudentSend studentSend){
        int uId = CookieUtils.getUIdFromRedis(request, redisTemplate);
        return studentHelpService.sendStudentHelp(studentSend,uId);
    }

    /**
     * 拒绝帮扶
     * @param id
     * @return
     */
    @DeleteMapping("/refuse/help")
    public ResponseVo refuseHelp(Integer id){
        return studentHelpService.refuseHelp(id);
    }

    /**
     * 获取帮扶的接收的信息
     * @param id
     * @return
     */
    @GetMapping("/get/student/help/receive")
    public ResponseVo getStudentHelpReceive(Integer id){
        return studentHelpService.getStudentHelpReceive(id);
    }


}
