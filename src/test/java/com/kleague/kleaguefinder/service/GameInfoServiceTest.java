package com.kleague.kleaguefinder.service;

import com.kleague.kleaguefinder.domain.GameInfo;
import com.kleague.kleaguefinder.repository.GameInfoRepository;
import com.kleague.kleaguefinder.request.GameInfoRequestDto;
import com.kleague.kleaguefinder.response.GameInfoResponseDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class GameInfoServiceTest {

    @Autowired
    GameInfoService gameInfoService;

    @Autowired
    GameInfoRepository gameInfoRepository;

    @Test
    @DisplayName("게임 정보 저장")
    public void test() {

        // given
        GameInfoRequestDto dto = GameInfoRequestDto.builder()
                .name("부산 vs 수원")
                .date("12월 7일")
                .location("부산 아시아드")
                .build();

        // when
        Long savedId = gameInfoService.save(dto);

        // then
        assertThat(savedId).isEqualTo(1L);

    }

    @Test
    @DisplayName("저장된 GameInfo 찾기")
    public void test2() {
        // given
        GameInfo gameInfo = GameInfo.builder()
                .name("부산 vs 수원")
                .date("12월 7일")
                .location("부산 아시아드")
                .build();

        gameInfoRepository.save(gameInfo);

        // when
        GameInfoResponseDto searchGameInfo = gameInfoService.findById(gameInfo.getId());

        // then
        assertThat(searchGameInfo.getDate()).isEqualTo("12월 7일");
    }


}