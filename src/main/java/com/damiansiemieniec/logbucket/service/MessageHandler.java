package com.damiansiemieniec.logbucket.service;

import com.damiansiemieniec.logbucket.adapter.IndexableLogMessage;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MessageHandler {

    private final SolrService solrService;

    public MessageHandler(SolrService solrService) {
        this.solrService = solrService;
    }
    
    public void handle(List<String> messages) {
        List<IndexableLogMessage> logs = messages.stream().map(IndexableLogMessage::new).toList();
        solrService.index("gettingstarted", logs);
    }
}
