package com.ingsw2.model;

import com.google.gson.annotations.Expose;

import javax.persistence.*;
import java.util.List;


@Entity
public class Question {

    @Id
    private String code;

    private String text;

    @OneToMany(mappedBy = "question", cascade={CascadeType.ALL, CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.EAGER)
    private List<Response> responses;

    public Question() {
        this.code = null;
        this.text = null;
        this.responses = null;
    }

    public Question(String code, String text, List<Response> responses) {
        this.code = code;
        this.text = text;
        this.responses = responses;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<Response> getResponses() {
        return responses;
    }

    public void setResponses(List<Response> responses) {
        this.responses = responses;
    }
}