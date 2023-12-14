package com.kleague.kleaguefinder.request.seat;

import com.kleague.kleaguefinder.domain.Category;
import com.kleague.kleaguefinder.domain.Seat;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class SeatCreateRequest {

    @NotBlank
    private String seatNumber;

    @NotBlank
    private Category category;

    @Builder
    public SeatCreateRequest(String seatNumber, Category category) {
        this.seatNumber = seatNumber;
        this.category = category;
    }

    public Seat toEntity() {
        return Seat.builder()
                .seatNumber(seatNumber)
                .category(category)
                .build();
    }

}
