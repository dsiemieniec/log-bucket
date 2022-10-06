package com.damiansiemieniec.logbucket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

@Service
public class UdpController {
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public UdpController(KafkaTemplate<String, String> template) {
        this.kafkaTemplate = template;
    }
    public void handleMessage(Message message) {
        String data = new String((byte[]) message.getPayload());
        System.out.println("UDP received: " + data);
        kafkaTemplate.send("mainTopic", data);
    }
}
