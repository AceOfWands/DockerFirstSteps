package com.ingsw2.model;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface QuestionRepository extends CrudRepository<Question, Integer> {
    @Query("select p.id from #{#entityName} p order by rand() limit ?1")
    List<Integer> getRandomIds(Integer n);
}