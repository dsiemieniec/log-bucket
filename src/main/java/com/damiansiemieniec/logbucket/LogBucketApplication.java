package com.damiansiemieniec.logbucket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableIntegration
@EnableKafka
public class LogBucketApplication {

    public static void main(String[] args) {
        SpringApplication.run(LogBucketApplication.class, args);
    }
}
