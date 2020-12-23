package com.rap.restservicevalidator;


public class Message {

    private final String content;
    private final long counter;

    public Message(long counter, String content) {
        this.content = content;
        this.counter = counter;
    }

    public String getContent() {
        return content;
    }

    public long getCounter() {
        return counter;
    }
}
