package com.ingsw2.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ingsw2.model.ControllerResponse;
import com.ingsw2.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ingsw2.model.Question;
import com.ingsw2.model.QuestionRepository;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

@Controller
@RequestMapping(path="/ecommerce/question")
public class MainController {
    @Autowired
    private QuestionRepository QuestionRepository;

    @GetMapping(path="/genRandomSeq")
    public @ResponseBody
    ControllerResponse<Iterable<String>> getRandomQuestionSequence(@RequestParam Integer n) {
        return new ControllerResponse<Iterable<String>>(true, null, QuestionRepository.getRandomCodes(n));
    }

    @GetMapping(path="/get")
    public @ResponseBody
    ControllerResponse getQuestionByCode(@RequestParam String code) {
        Optional<Question> question = QuestionRepository.findByCode(code);
        if(question.isPresent())
            return new ControllerResponse<Optional<Question>>(true,null, question);
        else
            return new ControllerResponse(false, "Question Not Found");
    }
}