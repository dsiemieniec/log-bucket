package com.damiansiemieniec.logbucket.service;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.Http2SolrClient;
import org.apache.solr.common.SolrInputDocument;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class SolrService {
    private final Http2SolrClient solrClient;

    @Autowired
    public SolrService(Http2SolrClient solrClient) {
        this.solrClient = solrClient;
    }

    public void index(List<String> messages) {
        var documents = new ArrayList<SolrInputDocument>();
        for (var message : messages) {
            try {
                var json = new JSONObject(message);
                var document = new SolrInputDocument();
                for (var field : json.keySet()) {
                    document.addField(field, json.get(field));
                }
                documents.add(document);
            } catch (JSONException exception) {
                var document = new SolrInputDocument();
                document.addField("message", message);
                documents.add(document);
            }
        }

        try {
            solrClient.add("gettingstarted", documents);
            solrClient.commit("gettingstarted");
        } catch (SolrServerException | IOException e) {
            e.printStackTrace();
        }
    }
}
