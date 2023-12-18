package com.kleague.kleaguefinder.repository.seat;

import com.kleague.kleaguefinder.domain.GameInfo;
import com.kleague.kleaguefinder.domain.Seat;
import com.kleague.kleaguefinder.repository.gameinfo.GameInfoCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatRepository extends JpaRepository<Seat, Long>, SeatCustomRepository{

}
