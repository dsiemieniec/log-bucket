package com.damiansiemieniec.logbucket.controller;

import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

@Service
public class UdpController {
    public void handleMessage(Message message) {
        String data = new String((byte[]) message.getPayload());
        System.out.println(data);
    }
}
