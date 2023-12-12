package com.kleague.kleaguefinder.request.gameinfo;

import com.kleague.kleaguefinder.domain.GameInfo;
import lombok.Builder;
import lombok.Getter;

import static java.lang.Math.*;

@Getter
@Builder
public class GameInfoSearchRequest {

    private static final int MAX_SIZE = 30;

    @Builder.Default
    private String name = "";
    @Builder.Default
    private String date = "";
    @Builder.Default
    private String location = "";

    @Builder.Default
    private int page = 0;

    @Builder.Default
    private int size = 10;

    private GameInfoSearchRequest(String name, String date, String location, int page, int size) {
        this.name = name;
        this.date = date;
        this.location = location;
        this.page = page;
        this.size = size;
    }

    public GameInfo toEntity() {
        return GameInfo.builder()
                .name(this.getName())
                .date(this.getDate())
                .location(this.getLocation())
                .build();
    }

    public int getOffset() {
        return (max(page, 0) * min(size, MAX_SIZE));
    }
}
