package com.kleague.kleaguefinder.request;

import com.kleague.kleaguefinder.domain.GameInfo;
import lombok.Builder;
import lombok.Getter;

@Getter
public class GameInfoRequest {

    private String name;

    private String date;

    private String location;

    @Builder
    public GameInfoRequest(String name, String date, String location) {
        this.name = name;
        this.date = date;
        this.location = location;
    }

    public GameInfo toEntity() {
        return GameInfo.builder()
                .name(this.getName())
                .date(this.getDate())
                .location(this.getLocation())
                .build();
    }
}
