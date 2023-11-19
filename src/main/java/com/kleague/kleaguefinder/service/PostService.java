package com.kleague.kleaguefinder.service;

import com.kleague.kleaguefinder.domain.Post;
import com.kleague.kleaguefinder.exception.NoValueException;
import com.kleague.kleaguefinder.repository.PostRepository;
import com.kleague.kleaguefinder.request.PostCreate;
import com.kleague.kleaguefinder.request.PostModifier;
import com.kleague.kleaguefinder.request.PostModify;
import com.kleague.kleaguefinder.request.PostSearch;
import com.kleague.kleaguefinder.response.PostResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostService {
    /**
     * 글 추가
     * 수정
     * 조회
     * 삭제
     */

    private final PostRepository postRepository;

    @Transactional
    public Long write(PostCreate postCreate) {
        Post post = Post.builder()
                .title(postCreate.getTitle())
                .content(postCreate.getContent())
                .build();

        postRepository.save(post);

        return post.getId();
    }

    public PostResponse findOne(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(NoValueException::new);

        return getPostResponse(post);
    }

    public List<PostResponse> findAll() {
        return postRepository.findAll().stream()
                .map(PostService::getPostResponse).collect(Collectors.toList());
    }

    public List<PostResponse> findBySearch(PostSearch postSearch) {
        return postRepository.getList(postSearch).stream()
                .map(post -> PostResponse.builder()
                        .title(post.getTitle())
                        .content(post.getContent())
                        .build()).collect(Collectors.toList());
    }

    @Transactional
    public void modify(Long postId, PostModify postModify) {
        Post post = postRepository.findById(postId).orElseThrow(NoValueException::new);

        PostModifier postModifier = post.modifierBuilder()
                .title(postModify.getTitle())
                .content(postModify.getContent())
                .build();

        post.modify(postModifier);

    }

    @Transactional
    public void delete(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(NoValueException::new);
        postRepository.delete(post);
    }

    private static PostResponse getPostResponse(Post post) {
        return PostResponse.builder()
                .title(post.getTitle())
                .content(post.getContent())
                .build();
    }
}
