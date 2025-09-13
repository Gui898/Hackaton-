package com.testSpring.Tests.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.testSpring.Tests.model.Message;
import com.testSpring.Tests.persistence.DAO.MessageDAO;

@Service
public class MessageService {
    
    public MessageDAO messageDAO;

    public MessageService(MessageDAO messageDAO){
        this.messageDAO = messageDAO;
    }

    public Message addMessage(Message message){
        messageDAO.add(message);
        return message;
    }

    public long deleteMessage(long id){
        messageDAO.delete(id);
        return id;
    }

    public Message updateMessage(Message message){
        messageDAO.update(message);
        return message;
    }

    public Message getMessageById(long id){
        return messageDAO.selectById(id);
    }

    public List<Message> getAllMessages(){
        return messageDAO.selectAll();
    }
}
