package com.kleague.kleaguefinder.repository.gameinfo;

import com.kleague.kleaguefinder.domain.GameInfo;
import com.kleague.kleaguefinder.request.gameinfo.GameInfoSearchRequest;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.kleague.kleaguefinder.domain.QGameInfo.*;

@RequiredArgsConstructor
public class GameInfoCustomRepositoryImpl implements GameInfoCustomRepository{

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<GameInfo> findByRequest(GameInfoSearchRequest request) {
        return jpaQueryFactory.selectFrom(gameInfo)
                .where(gameInfo.date.contains(request.getDate()))
                .limit(request.getSize())
                .offset(request.getOffset())
                .orderBy(gameInfo.id.desc())
                .fetch();
    }
}
