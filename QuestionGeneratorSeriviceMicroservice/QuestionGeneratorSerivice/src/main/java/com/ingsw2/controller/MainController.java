package com.ingsw2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ingsw2.model.Question;
import com.ingsw2.model.QuestionRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(path="/ecommerce")
public class MainController {
    @Autowired
    private QuestionRepository QuestionRepository;

    @GetMapping(path="/genRandomSeq")
    public @ResponseBody
    Iterable<Integer> getRandomQuestionSequence(@RequestParam Integer n) {
        return QuestionRepository.getRandomIds(n);
    }

    @GetMapping(path="/get")
    public @ResponseBody
    Optional<Question> getQuestionById(@RequestParam Integer id) {
        return QuestionRepository.findById(id);
    }
}