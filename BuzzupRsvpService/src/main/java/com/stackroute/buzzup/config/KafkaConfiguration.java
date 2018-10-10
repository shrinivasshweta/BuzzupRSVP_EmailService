package com.stackroute.buzzup.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import com.stackroute.buzzup.model.EventDetails;

@Configuration
public class KafkaConfiguration {
	static final String TOPIC = "rsvpEvent";
	// Producer factory of kafka which will hold the configuration details

	@Bean
	public ProducerFactory<String, EventDetails> producerFactory() {
		Map<String, Object> config = new HashMap<>();

		config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
		config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

		return new DefaultKafkaProducerFactory<>(config);
	}
	// Template imports the configuration from producerfactory

	@Bean
	public KafkaTemplate<String,EventDetails > kafkaTemplate() {
		return new KafkaTemplate<>(producerFactory());
	}
	public static String getTopic() {
		return TOPIC;
	}

	
}
