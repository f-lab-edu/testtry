package com.kleague.kleaguefinder.domain;

import com.kleague.kleaguefinder.request.PostModifier;
import com.kleague.kleaguefinder.request.PostModify;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;

    public Post() {

    }

    @Builder
    public Post(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public PostModifier.PostModifierBuilder modifierBuilder() {
        return PostModifier.builder()
                .title(this.title)
                .content(this.content);
    }

    public void modify(PostModifier postModifier) {
        this.title = postModifier.getTitle();
        this.content = postModifier.getContent();
    }


}
