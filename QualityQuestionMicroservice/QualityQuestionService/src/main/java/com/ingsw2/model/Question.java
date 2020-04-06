package com.ingsw2.model;


import javax.persistence.*;
import java.util.List;


@Entity
public class Question {

    @Id
    private String code;


    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private QualityTest qualityTest;

    @OneToMany(mappedBy = "question", cascade={CascadeType.ALL, CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.EAGER)
    private List<Response> responses;

    public Question() {
        this.code = null;
        this.responses = null;
    }

    public Question(String code) {
        this.code = code;
    }

    public Question(String code, String text, List<Response> responses) {
        this.code = code;
        this.responses = responses;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public QualityTest getQualityTest() {
        return qualityTest;
    }

    public void setQualityTest(QualityTest qualityTest) {
        this.qualityTest = qualityTest;
    }

    public List<Response> getResponses() {
        return responses;
    }

    public void setResponses(List<Response> responses) {
        this.responses = responses;
    }
}