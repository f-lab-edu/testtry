package com.kleague.kleaguefinder.response;

import com.kleague.kleaguefinder.domain.GameInfo;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class GameInfoResponse
{

    private String name;

    private String date;

    private String location;

    @Builder
    private GameInfoResponse(String name, String date, String location) {
        this.name = name;
        this.date = date;
        this.location = location;
    }

    public static GameInfoResponse createGameInfoResponse(GameInfo gameInfo) {
        return GameInfoResponse.builder()
                .name(gameInfo.getName())
                .date(gameInfo.getDate())
                .location(gameInfo.getLocation())
                .build();
    }
}
