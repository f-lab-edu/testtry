package com.kleague.kleaguefinder.service;

import com.kleague.kleaguefinder.domain.GameInfo;
import com.kleague.kleaguefinder.repository.GameInfoRepository;
import com.kleague.kleaguefinder.request.GameInfoRequest;
import com.kleague.kleaguefinder.response.GameInfoResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class GameInfoServiceTest {

    @Autowired
    GameInfoService gameInfoService;

    @Autowired
    GameInfoRepository gameInfoRepository;

    GameInfoRequest request1;
    GameInfoRequest request2;


    @BeforeEach
    public void setUp() {
        request1 = GameInfoRequest.builder()
                .name("부산 vs 수원")
                .date("12월 7일")
                .location("부산 아시아드")
                .build();

        request2 = GameInfoRequest.builder()
                .name("강원 vs 김포")
                .date("12월 8일")
                .location("강원 주 경기장")
                .build();
    }

    @Test
    @DisplayName("게임 정보 저장")
    public void saveGameInfo() {

        // when
        Long savedId = gameInfoService.save(request1);

        // then
        assertThat(savedId).isEqualTo(1L);

    }

    @Test
    @DisplayName("저장된 GameInfo 찾기")
    public void findGameInfo() {

        GameInfo gameInfo = request1.toEntity();

        gameInfoRepository.save(gameInfo);

        // when
        GameInfoResponse searchGameInfo = gameInfoService.findById(gameInfo.getId());

        // then
        assertThat(searchGameInfo.getDate()).isEqualTo("12월 7일");
    }

    @Test
    @DisplayName("Id로 GameInfo 찾기")
    public void findByGameInfoId() {

        // given
        Long Id1 = gameInfoService.save(request1);
        gameInfoService.save(request2);


        // when
        GameInfoResponse response = gameInfoService.findById(Id1);

        // then

        assertThat(response.getDate()).isEqualTo(request1.getDate());
        assertThat(response.getName()).isEqualTo(request1.getName());
        assertThat(response.getLocation()).isEqualTo(request1.getLocation());
    }
}