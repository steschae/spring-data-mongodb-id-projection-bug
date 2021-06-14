package com.example.idprojectionbug.repository;

import com.example.idprojectionbug.domain.PersonId;
import com.example.idprojectionbug.domain.PersonWithId;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PersonWithIdRepository extends CrudRepository<PersonWithId, String> {
    @Query(value="{}", fields = "{ _id : 1 }")
    List<PersonId> findAllIds();
}