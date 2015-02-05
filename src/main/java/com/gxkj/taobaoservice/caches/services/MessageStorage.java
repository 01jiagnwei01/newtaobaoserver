package com.gxkj.taobaoservice.caches.services;

import java.util.Collection;

import com.gxkj.taobaoservice.caches.dtos.Message;

public interface MessageStorage {
    Message findMessage(long id);
    Collection<Message> findAllMessages();
    void addMessage(Message message);
    void setDelegate(MessageStorage storageDelegate);
}