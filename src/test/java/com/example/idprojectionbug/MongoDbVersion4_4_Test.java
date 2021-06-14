package com.example.idprojectionbug;

import com.example.idprojectionbug.domain.PersonId;
import com.example.idprojectionbug.domain.PersonWithId;
import com.example.idprojectionbug.domain.PersonWithMongoId;
import com.example.idprojectionbug.repository.PersonWithIdRepository;
import com.example.idprojectionbug.repository.PersonWithMongoIdRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;

@DataMongoTest
@Testcontainers
class MongoDbVersion4_4_Test {

    @Autowired
    private PersonWithMongoIdRepository personWithMongoIdRepository;

    @Autowired
    private PersonWithIdRepository personWithIdRepository;

    @Container
    static MongoDBContainer mongoDbVersion4_4 = new MongoDBContainer(DockerImageName.parse("mongo:4.4"));

    @DynamicPropertySource
    static void setProperties(final DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.uri", mongoDbVersion4_4::getReplicaSetUrl);
    }

    @Test
    void should_find_all_person_ids_when_using_mongo_id_annotation() {
        // given
        final var person1 = PersonWithMongoId.builder().id("person1").build();
        final var person2 = PersonWithMongoId.builder().id("person2").build();
        final var person3 = PersonWithMongoId.builder().id("person3").build();

        personWithMongoIdRepository.saveAll(List.of(person1, person2, person3));

        // when
        final var ids = personWithMongoIdRepository.findAllIds()
                .stream()
                .map(PersonId::getId)
                .collect(toList());

        // then
        assertThat(ids, containsInAnyOrder("person1", "person2", "person3"));  // this fails, because ids is a list with ["1", "1", "1"]
    }

    @Test
    void should_find_all_person_ids_when_using_id_annotation() {
        // given
        final var person1 = PersonWithId.builder().id("person1").build();
        final var person2 = PersonWithId.builder().id("person2").build();
        final var person3 = PersonWithId.builder().id("person3").build();

        personWithIdRepository.saveAll(List.of(person1, person2, person3));

        // when
        final var ids = personWithIdRepository.findAllIds()
                .stream()
                .map(PersonId::getId)
                .collect(toList());

        // then
        assertThat(ids, containsInAnyOrder("person1", "person2", "person3"));
    }
}
