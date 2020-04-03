package com.ingsw2.model;

import javax.persistence.*;
import java.util.List;


@Entity
public class Question {

  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private Integer id;

  private String text;

  @OneToMany(mappedBy = "question")
  private List<Response> responses;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
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