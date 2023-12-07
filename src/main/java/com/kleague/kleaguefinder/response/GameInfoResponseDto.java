package com.kleague.kleaguefinder.response;

import com.kleague.kleaguefinder.domain.GameInfo;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class GameInfoResponseDto {

    private String name;

    private String date;

    private String location;

    private List<SeatResponseDto> seatList;

    public GameInfoResponseDto(GameInfo gameInfo) {
        this.name = gameInfo.getName();
        this.date = gameInfo.getDate();
        this.location = gameInfo.getLocation();
        this.seatList = gameInfo.getSeatList().stream()
                .map(SeatResponseDto::new).collect(Collectors.toList());
    }
}
