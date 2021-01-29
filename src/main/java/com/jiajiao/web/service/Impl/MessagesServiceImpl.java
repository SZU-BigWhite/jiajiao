package com.jiajiao.web.service.Impl;

import com.jiajiao.web.dao.ParentNeedMapper;
import com.jiajiao.web.dao.StudentResumeMapper;
import com.jiajiao.web.dao.UserMessagesMapper;
import com.jiajiao.web.enums.MessageStatusEnum;
import com.jiajiao.web.enums.MessageTopicConst;
import com.jiajiao.web.pojo.ParentNeed;
import com.jiajiao.web.pojo.StudentResume;
import com.jiajiao.web.pojo.UserMessages;
import com.jiajiao.web.service.IMessagesService;
import com.jiajiao.web.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MessagesServiceImpl implements IMessagesService {

    @Autowired
    UserMessagesMapper userMessagesMapper;
    @Autowired
    ParentNeedMapper parentNeedMapper;
    @Autowired
    StudentResumeMapper studentResumeMapper;

    @Override
    public ResponseVo getMessagesByUId(Integer uId) {
        List<UserMessages> userMessages = userMessagesMapper.selectByUId(uId);
        return ResponseVo.success("消息获取成功",userMessages);
    }

    @Override
    public boolean sendMessageToStudent(Integer sRId) {
        //获取UId
        StudentResume studentResume = studentResumeMapper.selectByPrimaryKey(sRId);
        if(studentResume==null){
            return false;
        }
        Integer uId = studentResume.getuId();

        //获取主题内容
        String topic=String.format(MessageTopicConst.STUDENT_MESSAGE,sRId);

        //插入消息
        return insertMessage(uId,topic);
    }

    @Override
    public boolean sendMessageToParent(Integer pNId) {
        //获取UId
        ParentNeed parentNeed=parentNeedMapper.selectByPrimaryKey(pNId);
        if(parentNeed==null){
            return false;
        }
        Integer uId = parentNeed.getuId();

        //获取主题内容
        String topic =String.format(MessageTopicConst.PARENT_MESSAGE,pNId);

        //插入消息
        return insertMessage(uId,topic);
    }

    @Override
    public ResponseVo deleteMessageById(Integer id) {
        int delete = userMessagesMapper.deleteByPrimaryKey(id);
        if(delete!=0)
            return ResponseVo.success("删除成功");
        return ResponseVo.error("删除失败");
    }

    @Override
    public boolean checkIdAndUId(Integer id, Integer uId) {
        UserMessages userMessages = userMessagesMapper.selectByPrimaryKey(id);
        if(userMessages==null||!userMessages.getuId().equals(uId))
            return false;
        return true;
    }

    @Override
    public ResponseVo setMessageToRead(Integer id) {
        UserMessages message=new UserMessages();
        message.setStatus(MessageStatusEnum.READ.getCode());
        message.setId(id);
        userMessagesMapper.updateByPrimaryKeySelective(message);
        return ResponseVo.success("消息设置成已读");
    }

    //插入消息
    private boolean insertMessage(Integer uId,String topic){
        //组装消息
        UserMessages userMessage=new UserMessages();
        userMessage.setTopic(topic);
        userMessage.setuId(uId);
        userMessage.setStatus(MessageStatusEnum.UNREAD.getCode());
        //插入消息
        int i = userMessagesMapper.insertSelective(userMessage);
        if(i!=0)
            return true;
        return false;
    }
}
