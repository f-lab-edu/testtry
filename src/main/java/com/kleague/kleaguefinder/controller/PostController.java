package com.kleague.kleaguefinder.controller;

import com.kleague.kleaguefinder.request.PostCreate;
import com.kleague.kleaguefinder.request.PostSearch;
import com.kleague.kleaguefinder.response.PostResponse;
import com.kleague.kleaguefinder.service.PostService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("post/write")
    public Long write(@RequestBody PostCreate postCreate) {
        return postService.write(postCreate);
    }

    @GetMapping("post/{postId}")
    public PostResponse findOne(@PathVariable("postId") Long postId) {
        return postService.findOne(postId);
    }

    @GetMapping("post/all")
    public List<PostResponse> findAll() {
        return postService.findAll();
    }

    @PostMapping("post/search")
    public List<PostResponse> search(@RequestBody PostSearch postSearch) {
        return postService.findBySearch(postSearch);
    }
}
