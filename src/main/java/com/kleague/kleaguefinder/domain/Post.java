package com.kleague.kleaguefinder.domain;

import lombok.Getter;

import javax.persistence.Entity;

@Entity
@Getter
public class Post {

    private Long id;
    private String title;
    private String content;

    public Post() {
    }


}
