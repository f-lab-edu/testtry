package com.kleague.kleaguefinder.repository.seat;

import com.kleague.kleaguefinder.domain.Category;
import com.kleague.kleaguefinder.domain.Seat;
import com.kleague.kleaguefinder.request.seat.SeatCreateRequest;
import com.kleague.kleaguefinder.request.seat.SeatSearchRequest;

import java.util.List;

public interface SeatCustomRepository {

    List<Seat> findBySearchRequest(SeatSearchRequest request);

    List<Seat> findByNumberAndCategory(String seatNumber, Category category);

}
