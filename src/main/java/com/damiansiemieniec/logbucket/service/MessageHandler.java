package com.damiansiemieniec.logbucket.service;

import com.damiansiemieniec.logbucket.adapter.IndexableLogMessage;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MessageHandler {

    private final SolrService solrService;
    private final CassandraService cassandraService;

    public MessageHandler(SolrService solrService, CassandraService cassandraService) {
        this.solrService = solrService;
        this.cassandraService = cassandraService;
    }
    
    public void handle(List<String> messages) {
        List<IndexableLogMessage> logs = messages.stream().map(IndexableLogMessage::new).toList();
        String indexName = "gettingstarted";
        solrService.index(indexName, logs);
        cassandraService.saveMetadata(indexName, logs);
    }
}
