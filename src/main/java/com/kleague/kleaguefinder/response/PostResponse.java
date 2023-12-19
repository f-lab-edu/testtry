package com.kleague.kleaguefinder.response;

import com.kleague.kleaguefinder.domain.Post;
import lombok.Builder;
import lombok.Data;

@Data
public class PostResponse {

  private String title;
  private String content;

  @Builder
  public PostResponse(String title, String content) {
    this.title = title;
    this.content = content;
  }

  public static PostResponse createPostResponse(Post post) {
    return PostResponse.builder()
        .title(post.getTitle())
        .content(post.getContent())
        .build();
  }
}
