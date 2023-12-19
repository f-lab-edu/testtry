package com.kleague.kleaguefinder.request.post;

import com.kleague.kleaguefinder.domain.Post;
import javax.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;

@Getter
public class PostCreateRequest {

  @NotEmpty
  private String title;
  @NotEmpty
  private String content;

  @Builder
  private PostCreateRequest(String title, String content) {
    this.title = title;
    this.content = content;
  }

  public Post toEntity() {
    return Post.builder()
        .title(this.title)
        .content(this.content)
        .build();
  }
}
