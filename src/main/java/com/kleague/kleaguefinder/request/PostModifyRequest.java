package com.kleague.kleaguefinder.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class PostModifyRequest {

    @NotEmpty
    private String title;
    @NotEmpty
    private String content;

}
