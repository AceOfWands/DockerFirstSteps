package com.ingsw2.model;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface QuestionRepository extends CrudRepository<Question, Integer> {
    public Optional<Question> findByCode(String code);
}