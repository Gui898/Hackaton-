package com.testSpring.Tests.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.testSpring.Tests.model.Chat;
import com.testSpring.Tests.persistence.DAO.ChatDAO;

@Service
public class ChatService {
    
    public ChatDAO chatDAO;

    public ChatService(ChatDAO chatDAO){
        this.chatDAO = chatDAO;
    }

    public Chat addChat(Chat chat){
        chatDAO.add(chat);
        return chat;
    }

    public long deleteChat(long id){
        chatDAO.delete(id);
        return id;
    }

    public Chat updateChat(Chat chat){
        chatDAO.update(chat);
        return chat;
    }

    public Chat getChatById(long id){
        return chatDAO.selectById(id);
    }

    public List<Chat> getAllChats(){
        return chatDAO.selectAll();
    }
}
