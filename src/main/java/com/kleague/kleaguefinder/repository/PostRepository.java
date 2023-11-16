package com.kleague.kleaguefinder.repository;

import com.kleague.kleaguefinder.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findByTitleContainingAndContentContaining(String title, String content);

}
