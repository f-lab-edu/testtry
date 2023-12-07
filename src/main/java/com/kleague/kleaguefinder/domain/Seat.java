package com.kleague.kleaguefinder.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "seat")
@Getter
@NoArgsConstructor
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seat_id")
    private Long id;

    private String name;

    private String status;

    private Category category;

    // 필요할까 ?
    @OneToOne(mappedBy = "seat",fetch = FetchType.LAZY)
    private Reservation reservation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "game_info_id")
    private GameInfo gameInfo;

    @Builder
    public Seat(String name, String status, Category category, Reservation reservation, GameInfo gameInfo) {
        this.name = name;
        this.status = status;
        this.category = category;
        this.reservation = reservation;

        changeGameInfo(gameInfo);
    }

    public void changeGameInfo(GameInfo gameInfo) {
        this.gameInfo = gameInfo;
        gameInfo.getSeatList().add(this);
    }

    public void changeReservation(Reservation reservation) {
        this.reservation = reservation;
    }
}
