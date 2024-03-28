package com.kleague.kleaguefinder.request.gameinfo;

import static com.kleague.kleaguefinder.exception.ErrorCode.AnnotationMsg.NOT_BLANK;

import javax.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;


@Getter
public class GameInfoModifyRequest {

  @NotBlank(message = NOT_BLANK)
  private String name;

  @NotBlank(message = NOT_BLANK)
  private String date;

  @NotBlank(message = NOT_BLANK)
  private String location;

  @Builder
  private GameInfoModifyRequest(String name, String date, String location) {
    this.name = name;
    this.date = date;
    this.location = location;
  }
}
