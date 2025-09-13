// Package;
package com.testSpring.Tests.controller;

// Imports;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.testSpring.Tests.model.Chat;
import com.testSpring.Tests.service.ChatService;

// Class Controller, a Bean, implementing the ProtocolMethod interface,
// With a Request Mapping in a path. Uses 5500 ports (Live Server) to access;  
@RestController
@RequestMapping("/chat")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class ChatController implements ProtocolMethods<Chat> {

    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    // Overriding and using Post method;
    @Override
    @PostMapping
    public Chat post(@RequestBody Chat chat) {
        return chatService.addChat(chat);
    }

    // Overriding and using Delete method;
    @Override
    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable long id) {
        chatService.deleteChat(id);
        return true;
    }

    // Overriding and using Put method;
    @Override
    @PutMapping("/{id}")
    public Chat put(@PathVariable long id, @RequestBody Chat chat) {
        chat.setIdChat(id);
        return chatService.updateChat(chat);
    }

    // Overriding and using Patch method;
    @Override
    @PatchMapping("/{id}")
    public Chat patch(@PathVariable long id, @RequestBody Chat chat) {
        chat.setIdChat(id);
        return chatService.updateChat(chat);
    }

    // Overriding and using Get method with ID Path;
    @Override
    @GetMapping("/{id}")
    public Chat getById(@PathVariable long id) {
        return chatService.getChatById(id);
    }

    // Overriding and using Get method, returning all;
    @Override
    @GetMapping
    public List<Chat> getAll() {
        return chatService.getAllChats();
    }
}
