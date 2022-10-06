package com.damiansiemieniec.logbucket.listener;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class MainTopicListener {

    @KafkaListener(id = "mainTopicListener", topics = "mainTopic")
    public void listen(String data) {
        System.out.println("Listener received: " + data);
    }
}
