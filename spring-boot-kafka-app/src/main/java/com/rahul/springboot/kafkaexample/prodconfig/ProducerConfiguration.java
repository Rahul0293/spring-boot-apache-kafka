package com.rahul.springboot.kafkaexample.prodconfig;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import org.apache.kafka.common.serialization.StringSerializer;

@Configuration
public class ProducerConfiguration {
	private static final String Kafka_Broker="localhost:9092";
	
	@Bean
	public ProducerFactory<String, String> producerFactory()
	{
		return new DefaultKafkaProducerFactory<>(producerConfiguration());
	}

	private Map<String, Object>producerConfiguration() {
		
		Map<String, Object> configurations=new HashMap<>();
		configurations.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, Kafka_Broker);
		configurations.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,StringSerializer.class);
		configurations.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		
		return configurations;
	}
	
	@Bean
	public KafkaTemplate<String, String> kafkaTemplate(){
		return new KafkaTemplate<>(producerFactory());
	}
	
}
