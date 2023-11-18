package com.kleague.kleaguefinder.service;

import com.kleague.kleaguefinder.domain.Post;
import com.kleague.kleaguefinder.exception.NoValueException;
import com.kleague.kleaguefinder.repository.PostRepository;
import com.kleague.kleaguefinder.request.PostCreate;
import com.kleague.kleaguefinder.request.PostSearch;
import com.kleague.kleaguefinder.response.PostResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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

        PostSearch postSearch = PostSearch.builder().title("제목").build();

        // when
        List<PostResponse> postResponseList = postService.findBySearch(postSearch);

        // then
        assertThat(postResponseList.size()).isEqualTo(5);
        assertThat(postResponseList.get(0).getContent()).isEqualTo("내용 : 4");
        assertThat(postResponseList.get(4).getContent()).isEqualTo("내용 : 0");
    }

    @Test
    @DisplayName("글 검색 - 내용만")
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

        PostSearch postSearch = PostSearch.builder().content("특이한").build();


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

        PostSearch postSearch = PostSearch.builder().build();

        // when
        List<PostResponse> postResponseList = postService.findBySearch(postSearch);

        // then
        assertThat(postResponseList.size()).isEqualTo(5);
        assertThat(postResponseList.get(0).getTitle()).isEqualTo("제목 : 4");
        assertThat(postResponseList.get(4).getTitle()).isEqualTo("제목 : 0");
    }


    @Test
    @DisplayName("글 검색 : 제목 - 페이징 확인 ( Default )")
    public void testV9() {
        // given

        List<Post> posts = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            posts.add(Post.builder()
                    .title("제목 : " + i )
                    .content("내용 : " + i)
                    .build());
        }

        postRepository.saveAll(posts);

        PostSearch postSearch = PostSearch.builder()
                .title("제목")
                .build();

        // when
        List<PostResponse> postResponseList = postService.findBySearch(postSearch);

        // then
        assertThat(postResponseList.size()).isEqualTo(10);
        assertThat(postResponseList.get(0).getTitle()).isEqualTo("제목 : 19");
        assertThat(postResponseList.get(9).getTitle()).isEqualTo("제목 : 10");
    }


    @Test
    @DisplayName("글 검색 : 제목 - 페이징 확인 ( Page  )")
    public void testV10() {
        // given

        List<Post> posts = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            posts.add(Post.builder()
                    .title("제목 : " + i )
                    .content("내용 : " + i)
                    .build());
        }

        postRepository.saveAll(posts);

        PostSearch postSearch = PostSearch.builder()
                .title("제목")
                .page(1)
                .build();

        // when
        List<PostResponse> postResponseList = postService.findBySearch(postSearch);

        // then
        assertThat(postResponseList.size()).isEqualTo(10);
        assertThat(postResponseList.get(0).getTitle()).isEqualTo("제목 : 9");
        assertThat(postResponseList.get(9).getTitle()).isEqualTo("제목 : 0");
    }

    @Test
    @DisplayName("글 검색 : 제목 - 페이징 확인 ( Page 설정을 초과 )")
    public void testV11() {
        // given
        List<Post> posts = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            posts.add(Post.builder()
                    .title("제목 : " + i )
                    .content("내용 : " + i)
                    .build());
        }

        postRepository.saveAll(posts);

        PostSearch postSearch = PostSearch.builder()
                .title("제목")
                .page(3)
                .build();

        // when
        List<PostResponse> postResponseList = postService.findBySearch(postSearch);

        // then
        assertThat(postResponseList.size()).isEqualTo(0);
        assertThatThrownBy(() -> postResponseList.get(0)).isInstanceOf(IndexOutOfBoundsException.class);

    }

    @Test
    @DisplayName("글 검색 : 내용 - 페이징 확인 ( Page  )")
    public void testV12() {
        // given

        List<Post> posts = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            posts.add(Post.builder()
                    .title("제목 : " + i )
                    .content("내용 : " + i)
                    .build());
        }
        postRepository.saveAll(posts);

        for (int i = 0; i < 15; i++) {
            Post post = Post.builder()
                    .title("제목 : " + i)
                    .content("특이한 내용")
                    .build();
            postRepository.save(post);
        }


        PostSearch postSearch = PostSearch.builder()
                .content("특이")
                .page(1)
                .build();

        // when
        List<PostResponse> postResponseList = postService.findBySearch(postSearch);

        // then
        assertThat(postResponseList.size()).isEqualTo(5);
        assertThat(postResponseList.get(0).getTitle()).isEqualTo("제목 : 4");
        assertThat(postResponseList.get(0).getContent()).isEqualTo("특이한 내용");
    }

    @Test
    @DisplayName("글 검색 : 조건은 없고 페이징만 적용")
    public void testV13() {
        // given

        List<Post> posts = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            posts.add(Post.builder()
                    .title("제목 : " + i )
                    .content("내용 : " + i)
                    .build());
        }
        postRepository.saveAll(posts);

        for (int i = 0; i < 15; i++) {
            Post post = Post.builder()
                    .title("제목 : " + i)
                    .content("특이한 내용")
                    .build();
            postRepository.save(post);
        }


        PostSearch postSearch = PostSearch.builder()
                .page(1)
                .build();

        // when
        List<PostResponse> postResponseList = postService.findBySearch(postSearch);

        // then
        assertThat(postResponseList.size()).isEqualTo(10);
        assertThat(postResponseList.get(5).getTitle()).isEqualTo("제목 : 19");
        assertThat(postResponseList.get(5).getContent()).isEqualTo("내용 : 19");
    }





}