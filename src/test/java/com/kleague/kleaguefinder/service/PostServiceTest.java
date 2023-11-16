package com.kleague.kleaguefinder.service;

import com.kleague.kleaguefinder.domain.Post;
import com.kleague.kleaguefinder.exception.NoValueException;
import com.kleague.kleaguefinder.repository.PostRepository;
import com.kleague.kleaguefinder.request.PostCreate;
import com.kleague.kleaguefinder.request.PostSearch;
import com.kleague.kleaguefinder.response.PostResponse;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
        PostCreate postCreate = new PostCreate("제목", "내용");
        //when
        Long postId = postService.write(postCreate);
        //then
        assertThat(postId).isEqualTo(1L);
        assertThat(postRepository.count()).isEqualTo(1);
    }

    @Test
    @DisplayName("글 단건 검색 - 성공")
    public void testV2() {
        // given
        Post post = Post.builder()
                .title("제목")
                .content("내용")
                .build();

        postRepository.save(post);

        // when
        PostResponse postResponse = postService.findOne(post.getId());

        // then
        assertThat(postResponse.getTitle()).isEqualTo(post.getTitle());
        assertThat(postResponse.getContent()).isEqualTo(post.getContent());
    }

    @Test
    @DisplayName("글 단건 검색 - 없는 글 검색")
    public void testV3() {
        // given
        Post post = Post.builder()
                .title("제목")
                .content("내용")
                .build();

        postRepository.save(post);

        // expected
        assertThatThrownBy(() -> postService.findOne(post.getId() + 1L)).isInstanceOf(NoValueException.class);

    }

    @Test
    @DisplayName("글 전체 검색 - 페이징 X")
    public void testV4() {
        // given
        for (int i = 0; i < 5; i++) {
            Post post = Post.builder()
                    .title("제목 : " + i )
                    .content("내용 : " + i)
                    .build();

            postRepository.save(post);
        }
        // when
        List<PostResponse> postResponseList = postService.findAll();

        // then
        assertThat(postResponseList.size()).isEqualTo(5);
        assertThat(postResponseList.get(0).getTitle()).isEqualTo("제목 : 0");
        assertThat(postResponseList.get(0).getContent()).isEqualTo("내용 : 0");

    }

    @Test
    @DisplayName("글 전체 검색 - 글 미등록")
    public void testV5() {

        // when
        List<PostResponse> postResponseList = postService.findAll();

        // then
        assertThat(postResponseList.size()).isEqualTo(0);

    }

    @Test
    @DisplayName("글 검색 - 제목만")
    public void testV6() {
        // given
        for (int i = 0; i < 5; i++) {
            Post post = Post.builder()
                    .title("제목 : " + i )
                    .content("내용 : " + i)
                    .build();

            postRepository.save(post);
        }

        PostSearch postSearch = new PostSearch();
        postSearch.setTitle("제목");

        // when
        List<PostResponse> postResponseList = postService.findBySearch(postSearch);

        // then
        assertThat(postResponseList.size()).isEqualTo(5);
        assertThat(postResponseList.get(0).getContent()).isEqualTo("내용 : 0");
        assertThat(postResponseList.get(4).getContent()).isEqualTo("내용 : 4");
    }

    @Test
    @DisplayName("글 검색 - 제목만")
    public void testV7() {
        // given
        for (int i = 0; i < 5; i++) {
            Post post = Post.builder()
                    .title("제목 : " + i )
                    .content("내용 : " + i)
                    .build();

            postRepository.save(post);
        }

        Post post = Post.builder()
                .title("특이한 제목")
                .content("특이한 내용")
                .build();

        postRepository.save(post);

        PostSearch postSearch = new PostSearch();
        postSearch.setContent("특이한");

        // when
        List<PostResponse> postResponseList = postService.findBySearch(postSearch);

        // then
        assertThat(postResponseList.size()).isEqualTo(1);
        assertThat(postResponseList.get(0).getTitle()).isEqualTo("특이한 제목");
    }

    @Test
    @DisplayName("글 검색 : 검색 입력 안함")
    public void testV8() {
        // given
        for (int i = 0; i < 5; i++) {
            Post post = Post.builder()
                    .title("제목 : " + i )
                    .content("내용 : " + i)
                    .build();

            postRepository.save(post);
        }

        PostSearch postSearch = new PostSearch();

        // when
        List<PostResponse> postResponseList = postService.findBySearch(postSearch);

        // then
        assertThat(postResponseList.size()).isEqualTo(5);
        assertThat(postResponseList.get(0).getTitle()).isEqualTo("제목 : 0");
        assertThat(postResponseList.get(4).getTitle()).isEqualTo("제목 : 4");
    }





}