package com.kleague.kleaguefinder.service;

import com.kleague.kleaguefinder.repository.PostRepository;
import com.kleague.kleaguefinder.request.PostCreate;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;


@Transactional
@SpringBootTest
class PostServiceTest {

    @Autowired
    PostService postService;

    @Autowired
    PostRepository postRepository;

    @Test
    @DisplayName("글 작성 성공")
    public void testV1() {
        //given
        PostCreate postCreate = PostCreate.builder()
                .title("제목")
                .content("내용")
                .build();
        //when
        Long postId = postService.write(postCreate);
        //then
        assertThat(postId).isEqualTo(1L);
        assertThat(postRepository.count()).isEqualTo(1);
    }

}