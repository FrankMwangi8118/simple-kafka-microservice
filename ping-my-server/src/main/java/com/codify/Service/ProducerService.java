package com.codify.Service;


import com.codify.Controller.Dto.ServerStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProducerService {
 private final KafkaTemplate<String,Object>kafkaTemplate;
    @Value("${spring.kafka.producer.topic}")
    private String topic;

    public ProducerService(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }
    public ResponseEntity<?>publish(ServerStatus status){
        kafkaTemplate.send(topic,status);
        return ResponseEntity.ok().build();
    }
}
