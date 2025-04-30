package com.codify.Service;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.core.env.Environment;
@Service
public class ProducerService {
    @Value("${spring.kafka.producer.topic}")
    private String topicName;
    private final KafkaTemplate<String,String>kafkaTemplate;

    public ProducerService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
        this.topicName ="ping";
    }

    public void publishMessage(String msg) {
        kafkaTemplate.send(topicName,msg);
    }

}
