package com.manoranjan.apnadriver.model;

public class MessageModel {
    public static String RECIEVED_MSG="recived";
    public static  String SEND_MSG="send";
    String sender,messagetype;

    public MessageModel() {
    }

    public MessageModel(String sender, String messagetype) {
        this.sender = sender;
        this.messagetype = messagetype;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getMessagetype() {
        return messagetype;
    }

    public void setMessagetype(String messagetype) {
        this.messagetype = messagetype;
    }
}
