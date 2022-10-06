package com.damiansiemieniec.logbucket.listener;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MainTopicListener {
    private static final int MAX_BATCH_SIZE = 100;

    @KafkaListener(id = "mainTopicListener", topics = "mainTopic",  batch = "true", properties = {
            ConsumerConfig.MAX_POLL_RECORDS_CONFIG + "=" + MAX_BATCH_SIZE
    })
    public void listen(List<String> messages) throws InterruptedException {
        for (var d : messages) {
            System.out.println("Listener received: " + d);
        }

        System.out.println("");
        System.out.println("");
    }
}
