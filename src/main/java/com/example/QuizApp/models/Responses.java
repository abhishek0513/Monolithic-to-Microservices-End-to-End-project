package com.example.QuizApp.models;


public class Responses {
    private Integer id;
    private String selected;

    public Responses() {
    }

    public Responses(Integer id, String selected) {
        this.id = id;
        this.selected = selected;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSelected() {
        return selected;
    }

    public void setSelected(String selected) {
        this.selected = selected;
    }
}
