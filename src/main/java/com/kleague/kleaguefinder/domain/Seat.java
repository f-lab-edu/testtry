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

    private Category category;

    @Builder
    public Seat(String name, Category category) {
        this.name = name;
        this.category = category;
    }
}
