package com.kleague.kleaguefinder.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "post")
@NoArgsConstructor
public class Post {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "title")
  @NotEmpty
  private String title;

  @Column(name = "content")
  @NotEmpty
  private String content;

  @Builder
  public Post(Long id, String title, String content) {
    this.id = id;
    this.title = title;
    this.content = content;
  }

  public void modify(String title, String content) {
    this.title = title;
    this.content = content;
  }


}
