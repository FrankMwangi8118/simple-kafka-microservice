package com.codify.notification_service.Service;

import com.codify.notification_service.Mail.MailSenderImpl;
import com.codify.notification_service.Model.Dtos.Status;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumerListener {
private final MailSenderImpl mailSender;
    int num;

    public KafkaConsumerListener(MailSenderImpl mailSender) {
        this.mailSender = mailSender;
    }


    @KafkaListener(topics = "${kafka.topic.name}",groupId = "${kafka.group-id}")
    public void listen(Status status) throws Exception {
       boolean isSent= mailSender.sendDownTimeAlert(status.email(),status.name(),status.ip());
        num++;
        System.out.printf("received:[%,d] ip: [%s],name: [%s],%n" ,num,status.ip(),status.name());
    }
}
