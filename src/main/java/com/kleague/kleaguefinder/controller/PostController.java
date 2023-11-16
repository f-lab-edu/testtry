package com.kleague.kleaguefinder.controller;

import com.kleague.kleaguefinder.request.PostCreate;
import com.kleague.kleaguefinder.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("post/write")
    public Long write(@RequestBody PostCreate postCreate) {
        return postService.write(postCreate);
    }
}
