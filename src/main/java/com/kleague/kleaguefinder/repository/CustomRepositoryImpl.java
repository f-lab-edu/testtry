package com.kleague.kleaguefinder.repository;

import com.kleague.kleaguefinder.domain.Post;
import com.kleague.kleaguefinder.domain.QPost;
import com.kleague.kleaguefinder.request.PostSearch;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import java.util.List;
import static com.kleague.kleaguefinder.domain.QPost.*;


@RequiredArgsConstructor
public class CustomRepositoryImpl implements CustomRepository{

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Post> getList(PostSearch postSearch) {
        return jpaQueryFactory.selectFrom(post)
                .where(post.title.contains(postSearch.getTitle())
                        .and(post.content.contains(postSearch.getContent())))
                .limit(postSearch.getSize())
                .offset(postSearch.getOffSet())
                .orderBy(post.id.desc())
                .fetch();
    }
}
