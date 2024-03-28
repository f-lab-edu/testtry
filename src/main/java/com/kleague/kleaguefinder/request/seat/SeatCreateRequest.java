package com.kleague.kleaguefinder.request.seat;

import static com.kleague.kleaguefinder.exception.ErrorCode.AnnotationMsg.NOT_BLANK;
import static com.kleague.kleaguefinder.exception.ErrorCode.AnnotationMsg.NOT_NULL;

import com.kleague.kleaguefinder.domain.Category;
import com.kleague.kleaguefinder.domain.Seat;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
public class SeatCreateRequest {

  @NotBlank(message = NOT_BLANK)
  private String seatNumber;

  @NotNull(message = NOT_NULL)
  private Category category;

  @Builder
  private SeatCreateRequest(String seatNumber, Category category) {
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
