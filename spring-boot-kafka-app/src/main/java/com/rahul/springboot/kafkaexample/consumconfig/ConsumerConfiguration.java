package com.rahul.springboot.kafkaexample.consumconfig;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import org.apache.kafka.common.serialization.StringDeserializer;

@Configuration
public class ConsumerConfiguration {
	
	private static final String Kafka_Broker="localhost:9092";
	private static final String Group_Id="kafka-sandbox";
	
	@Bean
	public ConsumerFactory<String, String> consumerFactory(){
		return new DefaultKafkaConsumerFactory<>(consumerConfiguration());
	}

	private Map<String, Object>consumerConfiguration() {
		Map<String, Object> configuration=new HashMap<>();
		
		configuration.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, Kafka_Broker);
		configuration.put(ConsumerConfig.GROUP_ID_CONFIG, Group_Id);
		configuration.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		configuration.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		return configuration;
	}
	
	@Bean
	ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory(){
		ConcurrentKafkaListenerContainerFactory<String, String> factory=new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(consumerFactory());
		return factory;
	}
}
