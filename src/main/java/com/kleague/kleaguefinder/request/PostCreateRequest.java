package com.kleague.kleaguefinder.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class PostCreateRequest {

    @NotEmpty
    private String title;
    @NotEmpty
    private String content;

    public PostCreateRequest(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
