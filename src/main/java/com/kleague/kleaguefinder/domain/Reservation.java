package com.kleague.kleaguefinder.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "reservation")
@NoArgsConstructor
public class Reservation {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_id")
    private Long id;

    private String name;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "seat_id")
    private Seat seat;

    private LocalDateTime localDateTime;

    private ReservationStatus reservationStatus;

    @Builder
    public Reservation(String name, Seat seat, LocalDateTime localDateTime, ReservationStatus reservationStatus) {
        this.name = name;
        this.seat = seat;
        this.localDateTime = localDateTime;
        this.reservationStatus = reservationStatus;
    }

    public void changeSeat(Seat seat) {
        this.seat = seat;
        seat.changeReservation(this);
    }
}
