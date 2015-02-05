package com.gxkj.taobaoservice.caches.services;

import java.util.Collection;
import java.util.Map;






import org.springframework.stereotype.Component;

import com.googlecode.ehcache.annotations.Cacheable;
import com.googlecode.ehcache.annotations.TriggersRemove;
import com.googlecode.ehcache.annotations.When;
import com.gxkj.taobaoservice.caches.dtos.Message;

@Component
public class MemoryMessageStorage implements MessageStorage {
	
	private Map<Long, Message> messages;
	//private AtomicLong newID;
	
	 
	public void initialize() {
		// add some messages
		addMessage(new Message("user:1", "content-1"));
		addMessage(new Message("user:2", "content-2"));
		addMessage(new Message("user:3", "content-3"));
		addMessage(new Message("user:4", "content-4"));
		addMessage(new Message("user:5", "content-5"));
	}
	
	@Cacheable(cacheName = "messageCache")
	public Message findMessage(long id) {
		 return null;
	}
	@Cacheable(cacheName = "messagesCache")
	public Collection<Message> findAllMessages() {
		 return null;
	}
	@TriggersRemove(cacheName = "messagesCache", when = When.AFTER_METHOD_INVOCATION, removeAll = true)
	public void addMessage(Message message) {
		
	}

	 
	public void setDelegate(MessageStorage storageDelegate) {
		 
		
	}
}
