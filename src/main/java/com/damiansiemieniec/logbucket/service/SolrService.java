package com.damiansiemieniec.logbucket.service;

import com.damiansiemieniec.logbucket.adapter.IndexableLogMessage;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.Http2SolrClient;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class SolrService {
    private final Http2SolrClient solrClient;

    @Autowired
    public SolrService(Http2SolrClient solrClient) {
        this.solrClient = solrClient;
    }

    public void index(String collectionName, List<IndexableLogMessage> messages) {
        try {
            solrClient.add(collectionName, messages.stream().map(IndexableLogMessage::toSolrInputDocument).toList());
            solrClient.commit(collectionName);
        } catch (SolrServerException | IOException e) {
            e.printStackTrace();
        }
    }

    public SolrDocumentList search(String collectionName, Optional<String> queryTerm) throws SolrServerException, IOException {
        return solrClient.query(collectionName, new SolrQuery(queryTerm.orElse("*:*"))).getResults();
    }
}
