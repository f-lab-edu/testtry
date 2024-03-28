package com.kleague.kleaguefinder.request.post;

import javax.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;

@Getter
public class PostModifyRequest {

  @NotEmpty
  private String title;
  @NotEmpty
  private String content;

  @Builder
  private PostModifyRequest(String title, String content) {
    this.title = title;
    this.content = content;
  }
}
