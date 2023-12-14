package com.kleague.kleaguefinder.controller;

import com.kleague.kleaguefinder.request.gameinfo.GameInfoCreateRequest;
import com.kleague.kleaguefinder.request.seat.SeatCreateRequest;
import com.kleague.kleaguefinder.response.GameInfoResponse;
import com.kleague.kleaguefinder.response.SeatResponse;
import com.kleague.kleaguefinder.service.SeatService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Slf4j
@RequiredArgsConstructor
public class SeatController {

    private final SeatService seatService;

    @PostMapping("api/v1/seat/save")
    public Long save(@RequestBody SeatCreateRequest request) {
        return seatService.save(request);
    }

    @GetMapping("api/v1/seat/{seatId}")
    public SeatResponse findOne(@PathVariable("seatId") Long seatId) {
        return seatService.findById(seatId);
    }
}
