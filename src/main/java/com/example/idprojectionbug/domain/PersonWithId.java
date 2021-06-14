package com.example.idprojectionbug.domain;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
@Builder
public class PersonWithId implements PersonId {
    @Id
    private String id;
}
