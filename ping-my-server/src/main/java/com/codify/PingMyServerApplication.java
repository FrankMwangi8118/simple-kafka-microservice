package com.codify;

import com.codify.Service.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;

@SpringBootApplication

public class PingMyServerApplication implements ApplicationRunner {
	@Autowired
 private ProducerService producerService;

	public static void main(String[] args) {
		SpringApplication.run(PingMyServerApplication.class, args);

	}

	@Override
	public void run(ApplicationArguments args) throws Exception {


		System.out.println("-----------published");
		for (int i=0;i<10;i++){
			producerService.publishMessage("hello");
		}
	}
}
