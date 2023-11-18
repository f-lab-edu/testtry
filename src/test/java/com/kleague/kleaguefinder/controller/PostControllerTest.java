package com.kleague.kleaguefinder.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kleague.kleaguefinder.domain.Post;
import com.kleague.kleaguefinder.repository.PostRepository;
import com.kleague.kleaguefinder.request.PostCreate;
import com.kleague.kleaguefinder.request.PostSearch;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.StatusResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
@Transactional
class PostControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    PostRepository postRepository;

    @Test
    @DisplayName("글 작성 성공")
    public void testV1() throws Exception {
        //given
        PostCreate postCreate = new PostCreate("제목", "내용");
        //when
        String json = objectMapper.writeValueAsString(postCreate);
        //then
        mockMvc.perform(post("/post/write")
                        .contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("글 단건 검색 - 성공")
    public void testV2() throws Exception {
        //given
        Post post = Post.builder()
                .title("제목")
                .content("내용")
                .build();

        postRepository.save(post);
        //when
        String json = objectMapper.writeValueAsString(post);
        //then
        mockMvc.perform(get("/post/{postId}", post.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("제목"))
                .andExpect(jsonPath("$.content").value("내용"))
                .andDo(print());
    }

    @Test
    @DisplayName("글 단건 검색 - 없는 글 검색")
    public void testV3() throws Exception {
        //given
        Post post = Post.builder()
                .title("제목")
                .content("내용")
                .build();
        //when
        String json = objectMapper.writeValueAsString(post);
        //then
        mockMvc.perform(get("/post/{postId}", post.getId()))
                .andExpect(status().isNotFound())
                .andDo(print());
    }

    @Test
    @DisplayName("글 전체 검색")
    public void testV4() throws Exception {
        // given
        for (int i = 0; i < 5; i++) {
            Post post = Post.builder()
                    .title("제목 : " + i)
                    .content("내용 : " + i)
                    .build();

            postRepository.save(post);
        }

        // expected
        mockMvc.perform(get("/post/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("제목 : 0"))
                .andExpect(jsonPath("$[0].content").value("내용 : 0"))
                .andDo(print());
    }

    @Test
    @DisplayName("글 전체 검색 - 글 0건 등록")
    public void testV5() throws Exception {


        // expected
        mockMvc.perform(get("/post/all"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("글 검색 제목만 ")
    public void testV6() throws Exception {
        // given
        for (int i = 0; i < 3; i++) {
            Post post = Post.builder()
                    .title("제목 : " + i )
                    .content("내용 : " + i)
                    .build();

            postRepository.save(post);
        }

        Post anotherPost = Post.builder()
                .title("특이한 제목")
                .content("특이한 내용")
                .build();

        postRepository.save(anotherPost);

        // when
        PostSearch postSearch = PostSearch.builder().title("특이").build();
        String json = objectMapper.writeValueAsString(postSearch);

        // then
        mockMvc.perform(post("/post/search")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("특이한 제목"))
                .andExpect(jsonPath("$[0].content").value("특이한 내용"))
                .andDo(print());
    }

    @Test
    @DisplayName("글 검색 내용만 ")
    public void testV7() throws Exception {
        // given
        for (int i = 0; i < 3; i++) {
            Post post = Post.builder()
                    .title("제목 : " + i )
                    .content("내용 : " + i)
                    .build();

            postRepository.save(post);
        }

        Post anotherPost = Post.builder()
                .title("특이한 제목")
                .content("특이한 내용")
                .build();

        postRepository.save(anotherPost);

        // when
        PostSearch postSearch = PostSearch.builder()
                .content("특이")
                .build();
        String json = objectMapper.writeValueAsString(postSearch);

        // then
        mockMvc.perform(post("/post/search")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("특이한 제목"))
                .andExpect(jsonPath("$[0].content").value("특이한 내용"))
                .andDo(print());
    }

    @Test
    @DisplayName("글 검색 조건 없음 : 전체 검색  ")
    public void testV8() throws Exception {
        // given
        for (int i = 0; i < 3; i++) {
            Post post = Post.builder()
                    .title("제목 : " + i )
                    .content("내용 : " + i)
                    .build();

            postRepository.save(post);
        }

        Post anotherPost = Post.builder()
                .title("특이한 제목")
                .content("특이한 내용")
                .build();

        postRepository.save(anotherPost);

        // when
        PostSearch postSearch = PostSearch.builder().build();
        String json = objectMapper.writeValueAsString(postSearch);

        // then
        mockMvc.perform(post("/post/search")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("제목 : 0"))
                .andExpect(jsonPath("$[0].content").value("내용 : 0"))
                .andExpect(jsonPath("$[3].title").value("특이한 제목"))
                .andExpect(jsonPath("[3].content").value("특이한 내용"))
                .andDo(print());
    }




}