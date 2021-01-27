package com.jiajiao.web.service;

import com.jiajiao.web.WebApplicationTests;
import com.jiajiao.web.pojo.UserMessages;
import com.jiajiao.web.service.Impl.MessagesServiceImpl;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class IMessagesServiceTest extends WebApplicationTests {

    @Autowired
    MessagesServiceImpl messagesService;

    @Test
    public void getMessagesByUId() {
    }

    @Test
    public void addMessage() {
//        messagesService.sendParentMessage(22);
//        messagesService.sendStudentMessage(3);
    }
}