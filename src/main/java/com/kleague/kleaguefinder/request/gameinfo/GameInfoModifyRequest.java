package com.kleague.kleaguefinder.request.gameinfo;

import lombok.Builder;
import lombok.Getter;

@Getter
public class GameInfoModifyRequest {

    private String name;

    private String date;

    private String location;

    @Builder
    public GameInfoModifyRequest(String name, String date, String location) {
        this.name = name;
        this.date = date;
        this.location = location;
    }
}
