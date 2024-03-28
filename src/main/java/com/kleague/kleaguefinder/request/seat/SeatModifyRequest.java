package com.kleague.kleaguefinder.request.seat;

import static com.kleague.kleaguefinder.exception.ErrorCode.AnnotationMsg.NOT_BLANK;
import static com.kleague.kleaguefinder.exception.ErrorCode.AnnotationMsg.NOT_NULL;

import com.kleague.kleaguefinder.domain.Category;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
public class SeatModifyRequest {

  @NotBlank(message = NOT_BLANK)
  private String seatNumber;

  @NotNull(message = NOT_NULL)
  private Category category;

  @Builder
  private SeatModifyRequest(String seatNumber, Category category) {
    this.seatNumber = seatNumber;
    this.category = category;
  }
}
