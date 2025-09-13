package com.testSpring.Tests.model;

//Class Message
public class Message {

    //Attributes
    private long idMessage;
    private String sender;
    private String textMessage;
    private Chat chat;

    // Empty constructor to Message;
    public Message() {}

    //Constructor to Message;
    public Message(String sender, String textMessage, Chat chat) {
        this.sender = sender;
        this.textMessage = textMessage;
        this.chat = chat;
    }

    // Id getter and setter;
    public long getIdMessage() {
        return idMessage;
    }

    public void setIdMessage(long idMessage) {
        this.idMessage = idMessage;
    }

    // Sender getter and setter;
    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    // TextMessage getter and setter;
    public String getTextMessage() {
        return textMessage;
    }

    public void setTextMessage(String textMessage) {
        this.textMessage = textMessage;
    }

    // Chat getter and setter;
    public Chat getChat() {
        return chat;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }
}
