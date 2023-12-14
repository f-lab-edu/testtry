package com.kleague.kleaguefinder.service;

import com.kleague.kleaguefinder.domain.Category;
import com.kleague.kleaguefinder.domain.Seat;
import com.kleague.kleaguefinder.exception.DuplicatedValueException;
import com.kleague.kleaguefinder.exception.NoValueException;
import com.kleague.kleaguefinder.repository.seat.SeatRepository;
import com.kleague.kleaguefinder.request.seat.SeatCreateRequest;
import com.kleague.kleaguefinder.response.SeatResponse;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static com.kleague.kleaguefinder.domain.Category.*;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
public class SeatServiceTest {

    @Autowired
    SeatRepository seatRepository;

    @Autowired
    SeatService seatService;

    Seat seatNo1;

    @BeforeEach
    public void setup() {
        seatNo1 = Seat.builder()
                .seatNumber("1")
                .category(A)
                .build();

        seatRepository.save(seatNo1);
    }

    @Test
    @DisplayName("Seat 저장 성공")
    public void saveSuccessV1() {
        // given
        SeatCreateRequest request = SeatCreateRequest.builder()
                .seatNumber("2")
                .category(A)
                .build();

        // when
        Long seatId = seatService.save(request);

        // then
        assertThat(seatId).isEqualTo(2L);
    }

    @Test
    @DisplayName("Seat 저장 실패 - 중복 값으로 인해 예외 발생")
    public void saveFail() {

        // when
        SeatCreateRequest request = SeatCreateRequest.builder()
                .seatNumber("1")
                .category(A)
                .build();

        // then
        assertThatThrownBy(() -> seatService.save(request)).isInstanceOf(DuplicatedValueException.class);
    }

    @Test
    @DisplayName("Seat 저장 성공 - 필드 중 한개 만 중복")
    public void saveSuccessV2() {

        // when
        SeatCreateRequest request = SeatCreateRequest.builder()
                .seatNumber("1")
                .category(B)
                .build();

        Long seatId = seatService.save(request);

        // then
        assertThat(seatId).isEqualTo(2L);

    }

    @Test
    @DisplayName("Seat Id 로 찾기 - 성공")
    public void findSeatById() {
        // given
        Long seatId = seatNo1.getId();

        // when
        SeatResponse seatResponse = seatService.findById(seatId);

        // then
        assertThat(seatResponse.getSeatNumber()).isEqualTo("1");
        assertThat(seatResponse.getCategory()).isEqualTo(A);
    }

    @Test
    @DisplayName("Seat Id 로 찾기 - 실패")
    public void findSeatByIdFail() {
        // given
        Long seatId = seatNo1.getId();

        // expected
        assertThatThrownBy(() -> seatService.findById(seatId + 30L)).isInstanceOf(NoValueException.class);
    }
}
