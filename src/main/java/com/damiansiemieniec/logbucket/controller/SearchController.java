package com.damiansiemieniec.logbucket.controller;

import com.damiansiemieniec.logbucket.service.SolrService;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("/api/search")
public class SearchController {
    private final SolrService solrService;

    public SearchController(SolrService solrService) {
        this.solrService = solrService;
    }

    @GetMapping("/{indexName}")
    public SolrDocumentList search(@PathVariable String indexName, @RequestParam(required = false) Optional<String> q) {
        try {
            return solrService.search(indexName, q);
        } catch (SolrServerException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
