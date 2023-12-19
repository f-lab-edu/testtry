package com.kleague.kleaguefinder.response;

import com.kleague.kleaguefinder.domain.Category;
import com.kleague.kleaguefinder.domain.Seat;
import lombok.Builder;
import lombok.Getter;

@Getter
public class SeatResponse {

  private String seatNumber;

  private Category category;

  @Builder
  public SeatResponse(String seatNumber, Category category) {
    this.seatNumber = seatNumber;
    this.category = category;
  }

  public static SeatResponse createSeatResponse(Seat seat) {
    return SeatResponse.builder()
        .seatNumber(seat.getSeatNumber())
        .category(seat.getCategory())
        .build();
  }
}
