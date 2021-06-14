package com.example.idprojectionbug.repository;

import com.example.idprojectionbug.domain.PersonWithMongoId;
import com.example.idprojectionbug.domain.PersonId;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PersonWithMongoIdRepository extends CrudRepository<PersonWithMongoId, String> {
    @Query(value="{}", fields = "{ _id : 1 }")
    List<PersonId> findAllIds();
}