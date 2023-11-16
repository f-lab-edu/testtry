package com.kleague.kleaguefinder.request;

import lombok.Builder;
import lombok.Data;

@Data
public class PostCreate {

    private String title;
    private String content;

    @Builder
    public PostCreate(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
