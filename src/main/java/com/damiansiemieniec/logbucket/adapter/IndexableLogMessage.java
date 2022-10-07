package com.damiansiemieniec.logbucket.adapter;

import org.apache.solr.common.SolrInputDocument;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Collection;

public class IndexableLogMessage {
    private JSONObject message;

    public IndexableLogMessage(String message) {
        try {
            this.message = new JSONObject(message);
        } catch (JSONException exception) {
            this.message = new JSONObject();
            this.message.append("message", message);
        }
    }

    public SolrInputDocument toSolrInputDocument() {
        SolrInputDocument document = new SolrInputDocument();
        for (String field : this.message.keySet()) {
            document.addField(field, this.message.get(field));
        }

        return document;
    }

    public Collection<String> getFieldNames() {
        return this.toSolrInputDocument().getFieldNames();
    }
}
