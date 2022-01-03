package com.rahul.springboot.kafkaexample.controller;

import java.util.List;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rahul.springboot.kafkaexample.consumer.MyTpoicConsumer;

@RestController
public class KafkaController {
	
	private KafkaTemplate<String, String> template;
	private MyTpoicConsumer myTopicConsumer;
	
	public KafkaController(KafkaTemplate<String, String> template) {
		this.template=template;
	}
	
	@GetMapping("kafka/produce")
	public void prodoce(@RequestParam String message) {
		template.send("myTopic", message);
	}
	
	@GetMapping("kafka/messages")
	public List<String> getMessages() {
		return myTopicConsumer.getMessages();
	}

}
