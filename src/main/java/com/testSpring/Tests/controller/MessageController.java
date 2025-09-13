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

import com.testSpring.Tests.model.Message;
import com.testSpring.Tests.service.MessageService;

// Class Controller, a Bean, implementing the ProtocolMethod interface,
// With a Request Mapping in a path. Uses 5500 ports (Live Server) to access;  
@RestController
@RequestMapping("/message")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class MessageController implements ProtocolMethods<Message> {

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    // Overriding and using Post method;
    @Override
    @PostMapping
    public Message post(@RequestBody Message message) {
        return messageService.addMessage(message);
    }

    // Overriding and using Delete method;
    @Override
    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable long id) {
        messageService.deleteMessage(id);
        return true;
    }

    // Overriding and using Put method;
    @Override
    @PutMapping("/{id}")
    public Message put(@PathVariable long id, @RequestBody Message message) {
        message.setIdMessage(id);
        return messageService.updateMessage(message);
    }

    // Overriding and using Patch method;
    @Override
    @PatchMapping("/{id}")
    public Message patch(@PathVariable long id, @RequestBody Message message) {
        message.setIdMessage(id);
        return messageService.updateMessage(message);
    }

    // Overriding and using Get method with ID Path;
    @Override
    @GetMapping("/{id}")
    public Message getById(@PathVariable long id) {
        return messageService.getMessageById(id);
    }

    // Overriding and using Get method, returning all;
    @Override
    @GetMapping
    public List<Message> getAll() {
        return messageService.getAllMessages();
    }
}
