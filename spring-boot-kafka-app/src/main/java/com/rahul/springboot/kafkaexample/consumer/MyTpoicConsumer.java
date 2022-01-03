package com.rahul.springboot.kafkaexample.consumer;

import java.util.ArrayList;
import java.util.List;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class MyTpoicConsumer {
	
	private final List<String> messages= new ArrayList<>();
	
	@KafkaListener(topics="myTopic", groupId = "kafka-sandbox")
	public void listen(String message) {
		synchronized (message) {
		messages.add(message);	
		}
	}
	
	public List<String> getMessages(){
		return messages;
	}
}
