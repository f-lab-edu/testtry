package com.kleague.kleaguefinder.repository;

import com.kleague.kleaguefinder.domain.Post;
import com.kleague.kleaguefinder.request.PostSearch;

import java.util.List;

public interface CustomRepository {

    public List<Post> getList(PostSearch postSearch);
}
