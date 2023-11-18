package com.kleague.kleaguefinder.request;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import static java.lang.Math.*;

@Getter
@Setter
@Builder
public class PostSearch {

    private static final int MAX_SIZE = 30;

    @Builder.Default
    private String title = "";
    @Builder.Default
    private String content = "";
    @Builder.Default
    private int page = 0;
    @Builder.Default
    private int size = 10;

    public PostSearch(String title, String content, int page, int size) {
        this.title = title;
        this.content = content;
        this.page = page;
        this.size = size;
    }

    public int getOffSet() {
        return (max(page, 0) * min(size, MAX_SIZE));
    }

}
