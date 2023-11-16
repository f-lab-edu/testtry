package com.kleague.kleaguefinder.repository;

import com.kleague.kleaguefinder.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
