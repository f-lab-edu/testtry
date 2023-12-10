package com.kleague.kleaguefinder.service;

import com.kleague.kleaguefinder.domain.GameInfo;
import com.kleague.kleaguefinder.repository.gameinfo.GameInfoRepository;
import com.kleague.kleaguefinder.request.gameinfo.GameInfoCreateRequest;
import com.kleague.kleaguefinder.request.gameinfo.GameInfoModifyRequest;
import com.kleague.kleaguefinder.request.gameinfo.GameInfoSearchRequest;
import com.kleague.kleaguefinder.response.GameInfoResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class GameInfoServiceTest {

    @Autowired
    GameInfoService gameInfoService;

    @Autowired
    GameInfoRepository gameInfoRepository;

    GameInfoCreateRequest createRequest1;
    GameInfoCreateRequest createRequest2;


    @BeforeEach
    public void setUp() {
        createRequest1 = GameInfoCreateRequest.builder()
                .name("부산 vs 수원")
                .date("12월 7일")
                .location("부산 아시아드")
                .build();

        createRequest2 = GameInfoCreateRequest.builder()
                .name("강원 vs 김포")
                .date("12월 8일")
                .location("강원 주 경기장")
                .build();

    }

    @Test
    @DisplayName("게임 정보 저장")
    public void saveGameInfo() {

        // when
        Long savedId = gameInfoService.save(createRequest1);

        // then
        assertThat(savedId).isEqualTo(1L);

    }

    @Test
    @DisplayName("저장된 GameInfo 찾기")
    public void findGameInfo() {

        GameInfo gameInfo = createRequest1.toEntity();

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
        Long Id1 = gameInfoService.save(createRequest1);
        gameInfoService.save(createRequest2);


        // when
        GameInfoResponse response = gameInfoService.findById(Id1);

        // then

        assertThat(response.getDate()).isEqualTo(createRequest1.getDate());
        assertThat(response.getName()).isEqualTo(createRequest1.getName());
        assertThat(response.getLocation()).isEqualTo(createRequest1.getLocation());
    }

    @Test
    @DisplayName("Date 로 GameInfo 찾기")
    public void findByRequestV1() {

        // given
        gameInfoService.save(createRequest1);
        gameInfoService.save(createRequest2);

        // when
        GameInfoSearchRequest searchRequest = GameInfoSearchRequest.builder()
                .name("경기 이름")
                .date("12월 7일")
                .location("경기 위치")
                .build();

        List<GameInfoResponse> gameInfoResponses = gameInfoService.findByRequest(searchRequest);

        //then
        assertThat(gameInfoResponses.size()).isEqualTo(1);
        assertThat(gameInfoResponses.get(0).getDate()).isEqualTo("12월 7일");

    }

    @Test
    @DisplayName("Date 로 GameInfo 찾기")
    public void findByRequestV2() {

        // given
        for (int i = 0; i < 23; i++) {
            gameInfoRepository.save(
                    GameInfo.builder()
                            .name("경기 이름 " + i)
                            .date("12월 7일")
                            .location("경기 위치 " + i)
                            .build()
            );
        }

        // when
        GameInfoSearchRequest searchRequest = GameInfoSearchRequest.builder()
                .name("경기 이름")
                .date("12월 7일")
                .location("경기 위치")
                .build();

        List<GameInfoResponse> gameInfoResponses = gameInfoService.findByRequest(searchRequest);

        //then
        assertThat(gameInfoResponses.size()).isEqualTo(10);
        assertThat(gameInfoResponses.get(0).getName()).isEqualTo("경기 이름 22");

    }

    @Test
    @DisplayName("GameInfo 수정 - 전체 수정 요청")
    public void modifyGameInfoV1() {
        // given
        GameInfo gameInfo = gameInfoRepository.save(createRequest1.toEntity());
        GameInfoModifyRequest request = GameInfoModifyRequest.builder()
                .name("부산 vs 청주")
                .date("12월 10일")
                .location("부산 구덕운동장")
                .build();

        // when
        gameInfoService.modify(gameInfo.getId(), request);

        // then
        assertThat(gameInfo.getName()).isEqualTo("부산 vs 청주");
        assertThat(gameInfo.getDate()).isEqualTo("12월 10일");
        assertThat(gameInfo.getLocation()).isEqualTo("부산 구덕운동장");
    }


    @Test
    @DisplayName("GameInfo 수정 - 이름만")
    public void modifyGameInfoV2() {
        // given
        GameInfo gameInfo = gameInfoRepository.save(createRequest1.toEntity());
        GameInfoModifyRequest request = GameInfoModifyRequest.builder()
                .name("부산 vs 청주")
                .build();

        // when
        gameInfoService.modify(gameInfo.getId(), request);

        // then
        assertThat(gameInfo.getName()).isEqualTo("부산 vs 청주");
        assertThat(gameInfo.getDate()).isEqualTo("12월 7일");
        assertThat(gameInfo.getLocation()).isEqualTo("부산 아시아드");
    }

    @Test
    @DisplayName("GameInfo 수정시도 - 실패")
    public void modifyGameInfoV3() {
        // given
        GameInfo gameInfo = gameInfoRepository.save(createRequest1.toEntity());
        GameInfoModifyRequest request = GameInfoModifyRequest.builder()
                .name("부산 vs 청주")
                .build();

        // expected
        assertThatThrownBy(() -> gameInfoService.modify(gameInfo.getId() + 1L, request))
                .isInstanceOf(IllegalStateException.class);

    }

    @Test
    @DisplayName("GameInfo 삭제 - 성공 ")
    public void deleteSuccess() {
        // given
        GameInfo gameInfo = gameInfoRepository.save(createRequest1.toEntity());

        // when
        gameInfoService.delete(gameInfo.getId());

        // then
        assertThat(gameInfoRepository.count()).isEqualTo(0);
    }

    @Test
    @DisplayName("GameInfo 삭제 - 실패 ")
    public void deleteFail() {
        // given
        GameInfo gameInfo = gameInfoRepository.save(createRequest1.toEntity());

        // expected
        assertThatThrownBy(() -> gameInfoService.delete(gameInfo.getId() + 1L))
                .isInstanceOf(IllegalStateException.class);

        assertThat(gameInfoRepository.count()).isEqualTo(1);

    }
}