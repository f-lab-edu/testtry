package com.kleague.kleaguefinder.service;

import com.kleague.kleaguefinder.domain.Post;
import com.kleague.kleaguefinder.exception.NoIdValueException;
import com.kleague.kleaguefinder.repository.PostRepository;
import com.kleague.kleaguefinder.request.PostCreateRequest;
import com.kleague.kleaguefinder.domain.PostModifier;
import com.kleague.kleaguefinder.request.PostModifyRequest;
import com.kleague.kleaguefinder.request.PostSearchRequest;
import com.kleague.kleaguefinder.response.PostResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.kleague.kleaguefinder.response.PostResponse.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostService {
    /**
     * 글 추가
     * 수정
     * 조회
     * 삭제
     */

    private final PostRepository postRepository;

    @Transactional
    public Long write(PostCreateRequest postCreateRequest) {
        Post post = Post.builder()
                .title(postCreateRequest.getTitle())
                .content(postCreateRequest.getContent())
                .build();

        postRepository.save(post);

        return post.getId();
    }

    @Transactional(readOnly = true)
    public PostResponse findOne(Long postId) {

        Post post = postRepository.findById(postId).orElseThrow(() -> new NoIdValueException(postId, "Post"));

        return createPostResponse(post);
    }

    @Transactional(readOnly = true)
    public List<PostResponse> findBySearch(PostSearchRequest postSearchRequest) {
        return postRepository.getList(postSearchRequest).stream()
                .map(post -> builder()
                        .title(post.getTitle())
                        .content(post.getContent())
                        .build()).collect(Collectors.toList());
    }

    @Transactional
    public void modify(Long postId, PostModifyRequest postModifyRequest) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new NoIdValueException(postId, "Post"));

        PostModifier postModifier = post.modifierBuilder()
                .title(postModifyRequest.getTitle())
                .content(postModifyRequest.getContent())
                .build();

        post.modify(postModifier);

    }

    @Transactional
    public void delete(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new NoIdValueException(postId, "Post"));
        postRepository.delete(post);
    }

}
