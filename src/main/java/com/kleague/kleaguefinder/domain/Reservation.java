package com.kleague.kleaguefinder.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "reservation")
    private List<ReservedSeat> reservedSeatList = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gameInfo_id")
    private GameInfo gameInfo;

    private LocalDateTime reservedTime;

    private ReservationStatus reservationStatus;



}
