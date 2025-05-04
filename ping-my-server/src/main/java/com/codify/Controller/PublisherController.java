package com.codify.Controller;

import com.codify.Model.ServerStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PublisherController {
    private final KafkaTemplate<String,Object>kafkaTemplate;

    public PublisherController(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }
  @PostMapping("/publish")
    public ResponseEntity<?> publishMessage(@RequestBody ServerStatus status){
        kafkaTemplate.send("ping",status);
        return ResponseEntity.ok("published successfully");
    }
}
