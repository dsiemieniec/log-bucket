package com.damiansiemieniec.logbucket.listener;

import com.damiansiemieniec.logbucket.service.SolrService;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MainTopicListener {
    private static final int MAX_BATCH_SIZE = 100;
    private final SolrService solrService;

    @Autowired
    public MainTopicListener(SolrService solrService) {
        this.solrService = solrService;
    }

    @KafkaListener(id = "mainTopicListener", topics = "mainTopic",  batch = "true", properties = {
            ConsumerConfig.MAX_POLL_RECORDS_CONFIG + "=" + MAX_BATCH_SIZE
    })
    public void listen(List<String> messages) throws InterruptedException {
        System.out.println("Listener received " + messages.size() + " message(s)");
        solrService.index(messages);
        Thread.sleep(2000); // ToDo: remove
    }
}
