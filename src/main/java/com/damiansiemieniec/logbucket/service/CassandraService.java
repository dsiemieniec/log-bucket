package com.damiansiemieniec.logbucket.service;

import com.damiansiemieniec.logbucket.adapter.IndexableLogMessage;
import com.damiansiemieniec.logbucket.entity.Field;
import com.damiansiemieniec.logbucket.repository.FieldRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CassandraService {
    private final FieldRepository fieldRepository;

    @Autowired
    public CassandraService(FieldRepository fieldRepository) {
        this.fieldRepository = fieldRepository;
    }

    public List<String> findFieldNamesByIndexName(String indexName) {
        return fieldRepository.findByIndexName(indexName).stream().map(Field::getName).toList();
    }

    public void saveMetadata(String indexName, List<IndexableLogMessage> logs) {
        List<String> currentFieldNames = this.findFieldNamesByIndexName(indexName);

        List<Field> newFields = new ArrayList<>();
        for (IndexableLogMessage log : logs) {
            for (String fieldName : log.getFieldNames()) {
                if (currentFieldNames.contains(fieldName)) {
                    continue;
                }

                Field field = new Field();
                field.setId(UUID.randomUUID());
                field.setName(fieldName);
                field.setIndexName(indexName);

                newFields.add(field);
            }
        }

        if (newFields.isEmpty()) {
            return;
        }

        fieldRepository.saveAll(newFields);
    }
}
