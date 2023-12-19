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
@Table(name = "seat")
@Getter
@NoArgsConstructor
public class Seat {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "seat_id")
  private Long id;

  private String seatNumber;

  private Category category;

  @Builder
  public Seat(String seatNumber, Category category) {
    this.seatNumber = seatNumber;
    this.category = category;
  }

  public void modify(String seatNumber, Category category) {
    this.seatNumber = seatNumber;
    this.category = category;
  }
}
