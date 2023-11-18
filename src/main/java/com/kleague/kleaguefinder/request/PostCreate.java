package com.kleague.kleaguefinder.request;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostCreate {

    private String title;
    private String content;

    public PostCreate(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
