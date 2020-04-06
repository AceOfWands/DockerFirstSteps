package com.ingsw2.model;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface QuestionRepository extends CrudRepository<Question, Integer> {
    @Query(value = "select q.code from question q order by rand() limit :n", nativeQuery = true)
    List<String> getRandomCodes(@Param("n") Integer n);

    Optional<Question> findByCode(@Param("code") String code);
}