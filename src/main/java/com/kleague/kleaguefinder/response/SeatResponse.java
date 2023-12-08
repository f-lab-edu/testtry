package com.kleague.kleaguefinder.response;

import com.kleague.kleaguefinder.domain.Category;
import com.kleague.kleaguefinder.domain.Seat;

public class SeatResponse {

    private String name;

    private Category category;

    public SeatResponse(Seat seat) {
        this.name = seat.getName();
        this.category = seat.getCategory();
    }
}
