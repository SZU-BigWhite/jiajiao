package com.jiajiao.web.controller;

import com.jiajiao.web.service.Impl.StudentServiceImpl;
import com.jiajiao.web.utils.CookieUtils;
import com.jiajiao.web.form.GetStudentResumeOrderForm;
import com.jiajiao.web.vo.ResponseVo;
import com.jiajiao.web.vo.StudentResumeVo;
import com.jiajiao.web.vo.StudentSendParentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class StudentController {
    @Autowired
    StudentServiceImpl studentService;
    @Autowired
    StringRedisTemplate redisTemplate;

    /**
     * 获取学院名
     * @return
     */
    @GetMapping("/get/student/academy")
    public ResponseVo getStudentAcademy(){
        ResponseVo res = studentService.getStudentAcademy();
        return res;
    }

    /**
     * 获取个人的简历信息
     * @param request
     * @return
     */
    @GetMapping("/get/student/resumes")
    public ResponseVo getStudentResumes(HttpServletRequest request){
        int uId = CookieUtils.getUIdFromRedis(request, redisTemplate);
        ResponseVo res = studentService.getStudentResumeByUId(uId);
        return res;
    }

    /**
     * 新增个人简历信息
     * @param request
     * @param studentResumeVo
     * @return
     */
    @PostMapping("/add/student/resume")
    public ResponseVo addStudentResume(HttpServletRequest request, @RequestBody StudentResumeVo studentResumeVo){
        int uId = CookieUtils.getUIdFromRedis(request, redisTemplate);
        ResponseVo res = studentService.addStudentResume(studentResumeVo, uId);
        return  res;
    }

    /**
     * 更新个人简历信息
     * @param studentResumeVo
     * @return
     */
    @PostMapping("/update/student/resume")
    public ResponseVo updateStudentResume(@RequestBody StudentResumeVo studentResumeVo,HttpServletRequest request){
        int uId = CookieUtils.getUIdFromRedis(request, redisTemplate);  //校验id == uid
        if(!studentService.checkIdAndUId(studentResumeVo.getStudentResume().getId(),uId)){
            return ResponseVo.error("无法操作他人的简历");
        }

        ResponseVo res = studentService.updateStudentResume(studentResumeVo);
        return res;
    }

    /**
     * 获取所有学生的简历信息
     * @param studentResumeOrderVo
     * @return
     */
    @GetMapping("/get/students/resumelist/order")
    public ResponseVo getStudentsResumesOrder(GetStudentResumeOrderForm studentResumeOrderVo){
        System.out.println(studentResumeOrderVo);
        ResponseVo res = studentService.getStudentsResumes(studentResumeOrderVo);
        return res;
    }

    /**
     * 删除个人简历信息
     * @param request
     * @param id
     * @return
     */
    @DeleteMapping("/delete/student/resume")
    public ResponseVo deleteStudentResume(HttpServletRequest request,@RequestParam("id") Integer id){
        int uId = CookieUtils.getUIdFromRedis(request, redisTemplate);  //校验id == uid
        if(!studentService.checkIdAndUId(id,uId)){
            return ResponseVo.error("无法操作他人的简历");
        }

        ResponseVo res = studentService.deleteStudentResume(id);
        return res;
    }

    /**
     * 发送个人简历
     * @param studentSendParentVO
     * @return
     */
    @PostMapping("/send/student/resume")
    public ResponseVo sendStudentResume(@RequestBody StudentSendParentVO studentSendParentVO,HttpServletRequest request){
        int uId = CookieUtils.getUIdFromRedis(request, redisTemplate);  //校验id == uid
        if(!studentService.checkIdAndUId(studentSendParentVO.getsResumeId(),uId)){
            return ResponseVo.error("无法操作他人的简历");
        }

        ResponseVo res = studentService.sendStudentResume(studentSendParentVO);
        return res;
    }

    @PostMapping("/delete/send/student/resume")
    public ResponseVo deleteSendStudentResume(@RequestBody StudentSendParentVO studentSendParentVO,HttpServletRequest request){
        int uId = CookieUtils.getUIdFromRedis(request, redisTemplate);  //校验id == uid
        if(!studentService.checkIdAndUId(studentSendParentVO.getsResumeId(),uId)){
            return ResponseVo.error("无法操作他人的简历");
        }

        ResponseVo res = studentService.deleteSendStudentResume(studentSendParentVO);
        return res;
    }


    /**
     * 查看某个简历收到的需求
     * @param id
     * @return
     */
    @GetMapping("/get/student/receive")
    public ResponseVo getParentSent(Integer id,HttpServletRequest request){
        int uId = CookieUtils.getUIdFromRedis(request, redisTemplate);  //校验id == uid
        if(!studentService.checkIdAndUId(id,uId)){
            return ResponseVo.error("无法操作他人的简历");
        }

        ResponseVo res = studentService.getStudentReceive(id);
        return res;
    }
    /**
     * 查看某个简历发送过的请求
     * @param id
     * @return
     */
    @GetMapping("/get/student/sent")
    public ResponseVo getStudentSent(Integer id,HttpServletRequest request){
        int uId = CookieUtils.getUIdFromRedis(request, redisTemplate);  //校验id == uid
        if(!studentService.checkIdAndUId(id,uId)){
            return ResponseVo.error("无法操作他人的简历");
        }

        ResponseVo res = studentService.getStudentSent(id);
        return res;
    }

    /**
     * 获取学生简历的总数
     * @return
     */
    @GetMapping("/get/student/resume/sum")
    public ResponseVo getStudentResumeSum(){
        ResponseVo res = studentService.getStudentResumeSum();
        return res;
    }
}
