package com.kleague.kleaguefinder.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostModifier {

    private String title;
    private String content;


    public PostModifier(String title, String content) {
        this.title = title;
        this.content = content;
    }

    /**
     *  Modify(null Check) 를 위한 Builder 수동 구현
     */

    public static PostModifierBuilder builder() {
        return new PostModifierBuilder();
    }

    public static class PostModifierBuilder {
        private String title;
        private String content;

        PostModifierBuilder() {
        }

        public PostModifierBuilder title(final String title) {
            if(title != null) this.title = title;
            return this;
        }

        public PostModifierBuilder content(final String content) {
            if(content != null) this.content = content;
            return this;
        }

        public PostModifier build() {
            return new PostModifier(this.title, this.content);
        }

        public String toString() {
            return "PostModifier.PostModifierBuilder(title=" + this.title + ", content=" + this.content + ")";
        }
    }
}
