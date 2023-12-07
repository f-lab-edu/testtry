package com.kleague.kleaguefinder.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "gameInfo", cascade = CascadeType.ALL)
    private List<Seat> seatList = new ArrayList<>();

    @Builder
    public GameInfo(String name, String date, String location) {
        this.name = name;
        this.date = date;
        this.location = location;
    }
}
