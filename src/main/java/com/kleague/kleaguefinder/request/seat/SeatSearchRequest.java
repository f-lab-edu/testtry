package com.kleague.kleaguefinder.request.seat;

import static com.kleague.kleaguefinder.exception.ErrorCode.AnnotationMsg.NOT_NULL;
import static java.lang.Math.max;
import static java.lang.Math.min;

import com.kleague.kleaguefinder.domain.Category;
import com.kleague.kleaguefinder.domain.Seat;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
public class SeatSearchRequest {

  private static final int MAX_SIZE = 10;

  private String seatNumber = "";

  @NotNull(message = NOT_NULL)
  private Category category;

  private int page = 0;

  private int size = 20;

  @Builder
  private SeatSearchRequest(String seatNumber, Category category, int page, int size) {
    this.seatNumber = (seatNumber == null) ? this.seatNumber : seatNumber;
    this.category = category;
    this.page = page;
    this.size = (size <= 0) ? this.size : size;
  }

  public Seat toEntity() {
    return Seat.builder()
        .seatNumber(seatNumber)
        .category(category)
        .build();
  }

  public int getOffset() {
    return (max(page, 0) * min(size, MAX_SIZE));
  }
}
