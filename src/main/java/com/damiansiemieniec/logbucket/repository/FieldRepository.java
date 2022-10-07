package com.damiansiemieniec.logbucket.repository;

import com.damiansiemieniec.logbucket.entity.Field;
import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.CassandraRepository;

import java.util.List;
import java.util.UUID;

public interface FieldRepository extends CassandraRepository<Field, UUID> {
    @AllowFiltering
    List<Field> findByIndexName(String indexName);
}
