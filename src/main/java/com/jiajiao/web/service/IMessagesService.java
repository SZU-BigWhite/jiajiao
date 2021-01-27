package com.jiajiao.web.service;

import com.jiajiao.web.vo.ResponseVo;

public interface IMessagesService {
    ResponseVo getMessagesByUId(Integer uId);

    /**
     * 发送消息给学生，我投了需求
     * @param sRId
     * @return
     */
    boolean sendMessageToStudent(Integer sRId);

    /**
     * 发送消息给家长，我投了简历
     * @param pNId
     * @return
     */
    boolean sendMessageToParent(Integer pNId);
    ResponseVo deleteMessageById(Integer id);
    boolean checkIdAndUId(Integer id,Integer uId);
    ResponseVo setMessageToRead(Integer id);
}
