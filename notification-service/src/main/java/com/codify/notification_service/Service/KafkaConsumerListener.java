package com.codify.notification_service.Service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumerListener {
    @KafkaListener(topics = "${kafka.topic.name}",groupId = "${kafka.group-id}")
    public void listen(String message){
        System.out.printf("received message: [%s],%n",message);
    }
}
