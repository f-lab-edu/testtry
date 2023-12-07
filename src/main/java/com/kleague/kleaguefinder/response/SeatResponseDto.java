package com.kleague.kleaguefinder.response;

import com.kleague.kleaguefinder.domain.Category;
import com.kleague.kleaguefinder.domain.Seat;
import lombok.Builder;

public class SeatResponseDto {

    private String name;

    private String status;

    private Category category;

    public SeatResponseDto(Seat seat) {
        this.name = seat.getName();
        this.status = seat.getStatus();
        this.category = seat.getCategory();
    }
}
