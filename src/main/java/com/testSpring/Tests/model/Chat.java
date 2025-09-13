package com.testSpring.Tests.model;

import java.util.List;

//Class Chat
public class Chat {

    //Attributes
    private long idChat;
    private String typeChat;
    private Elderly elderly;
    private List<Message> messages;

    // Empty constructor to Chat;
    public Chat() {}

    //Constructor to Chat;
    public Chat(String typeChat, Elderly elderly) {
        this.typeChat = typeChat;
        this.elderly = elderly;
    }

    // Id getter and setter;
    public long getIdChat() {
        return idChat;
    }

    public void setIdChat(long idChat) {
        this.idChat = idChat;
    }

    // TypeChat getter and setter;
    public String getTypeChat() {
        return typeChat;
    }

    public void setTypeChat(String typeChat) {
        this.typeChat = typeChat;
    }

    // Elderly getter and setter;
    public Elderly getElderly() {
        return elderly;
    }

    public void setElderly(Elderly elderly) {
        this.elderly = elderly;
    }

    // Messages getter and setter;

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    // toString method;
    @Override
    public String toString() {
        return "Chat{" +
                "idChat=" + idChat +
                ", typeChat='" + typeChat + '\'' +
                ", elderly=" + elderly +
                '}';
    }
}
