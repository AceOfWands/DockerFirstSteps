package com.ingsw2.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gson.annotations.Expose;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;

@Entity
public class Response {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @Expose(serialize = false, deserialize = false)
    @JsonIgnore
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Question question;

    private String text;

    public Response() {}

    public Response(String text) {
        this.text = text;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
