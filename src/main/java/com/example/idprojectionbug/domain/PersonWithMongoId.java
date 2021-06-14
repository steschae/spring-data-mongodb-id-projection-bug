package com.example.idprojectionbug.domain;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Data
@Builder
public class PersonWithMongoId implements PersonId {
    @MongoId
    private String id;
}
