package com.codify.notification_service.Configurations;

import com.codify.notification_service.Model.Dtos.Status;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
public class ConsumerConfigs {
    @Value("${kafka.bootstrap-servers}")
    private String kafkaAddress;
    @Value("${kafka.group-id}")
    private String groupId;

    @Bean
    public ConsumerFactory<String, Status> consumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaAddress);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, org.springframework.kafka.support.serializer.JsonDeserializer.class);
        props.put(org.springframework.kafka.support.serializer.JsonDeserializer.TRUSTED_PACKAGES, "*");
        props.put(JsonDeserializer.VALUE_DEFAULT_TYPE, "com.codify.notification_service.Model.Dtos.Status"); // FULL class name
        return new DefaultKafkaConsumerFactory<>(props);
    }

//    @Bean
//    public ConcurrentKafkaListenerContainerFactory<String,String >kafkaListenerContainerFactory(){
//        ConcurrentKafkaListenerContainerFactory<String,String>factory=new ConcurrentKafkaListenerContainerFactory<>();
//        factory.setConsumerFactory(consumerFactory());
//        return factory;
//    }

@Bean
    public KafkaListenerContainerFactory<?>kafkaListenerContainerFactory(){
        ConcurrentKafkaListenerContainerFactory<String,Status>factory=new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
//        factory.setRecordMessageConverter(new JsonMessageConverter());
        return factory;
}
}
