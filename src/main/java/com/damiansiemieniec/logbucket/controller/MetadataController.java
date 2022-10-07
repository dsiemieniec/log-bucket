package com.damiansiemieniec.logbucket.controller;

import com.damiansiemieniec.logbucket.service.CassandraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/metadata")
public class MetadataController {
    private final CassandraService cassandraService;

    @Autowired
    public MetadataController(CassandraService cassandraService) {
        this.cassandraService = cassandraService;
    }

    @GetMapping("/{indexName}/fields")
    public List<String> getFields(@PathVariable String indexName) {
        return cassandraService.findFieldNamesByIndexName(indexName);
    }
}
