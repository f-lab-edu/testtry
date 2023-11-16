package com.kleague.kleaguefinder.request;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostSearch {

    private String title = "";
    private String content = "";

    public PostSearch(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public PostSearch() {
    }
}
