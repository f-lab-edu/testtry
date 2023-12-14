package com.kleague.kleaguefinder.service;

import com.kleague.kleaguefinder.domain.Seat;
import com.kleague.kleaguefinder.exception.DuplicatedValueException;
import com.kleague.kleaguefinder.exception.NoValueException;
import com.kleague.kleaguefinder.repository.seat.SeatRepository;
import com.kleague.kleaguefinder.request.seat.SeatCreateRequest;
import com.kleague.kleaguefinder.response.SeatResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class SeatService {

    private final SeatRepository seatRepository;

    @Transactional
    public Long save(SeatCreateRequest request) {
        validationCheck(request);

        Seat seat = seatRepository.save(request.toEntity());

       return seat.getId();
    }

    private void validationCheck(SeatCreateRequest request) {
        List<Seat> seats = seatRepository.findByNumberAndCategory(request.getSeatNumber(), request.getCategory());

        if(!seats.isEmpty()){
            throw new DuplicatedValueException("Seat", "seatNumber & category");
        }
    }

    @Transactional(readOnly = true)
    public SeatResponse findById(Long seatId) {
        Seat seat = seatRepository.findById(seatId).orElseThrow(() -> new NoValueException("Seat", "id"));
        return SeatResponse.createSeatResponse(seat);
    }
}
