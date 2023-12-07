package com.kleague.kleaguefinder.request;

import lombok.Builder;
import lombok.Getter;

@Getter
public class GameInfoRequestDto {

    private String name;

    private String date;

    private String location;

    @Builder
    public GameInfoRequestDto(String name, String date, String location) {
        this.name = name;
        this.date = date;
        this.location = location;
    }
}
