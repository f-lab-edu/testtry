package com.kleague.kleaguefinder.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "game_info")
@NoArgsConstructor
public class GameInfo {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "game_info_id")
  private Long id;

  private String name;

  private String date;

  private String location;

  @Builder
  public GameInfo(String name, String date, String location) {
    this.name = name;
    this.date = date;
    this.location = location;
  }

  public void modify(String name, String date, String location) {
    this.name = name;
    this.date = date;
    this.location = location;
  }

}
