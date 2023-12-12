package com.kleague.kleaguefinder.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kleague.kleaguefinder.domain.GameInfo;
import com.kleague.kleaguefinder.repository.gameinfo.GameInfoRepository;
import com.kleague.kleaguefinder.request.gameinfo.GameInfoCreateRequest;
import com.kleague.kleaguefinder.request.gameinfo.GameInfoSearchRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
@Transactional
public class GameInfoControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    GameInfoRepository gameInfoRepository;

    GameInfo gameInfo;

    GameInfoSearchRequest searchRequest;

    @BeforeEach
    public void setUp() {
        List<GameInfo> gameInfoList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            gameInfoList.add(GameInfo.builder()
                    .name("경기 이름 " + i)
                    .date("12월 12일")
                    .location("경기 위치 " + i)
                    .build());
        }
        gameInfoRepository.saveAll(gameInfoList);

        for (int i = 10; i < 15; i++) {
            gameInfoRepository.save(GameInfo.builder()
                    .name("경기 이름 " + i)
                    .date("12월 11일")
                    .location("경기 위치 " + i)
                    .build());
        }

        gameInfo  = GameInfo.builder()
                .name("서울 vs 부산")
                .date("12월 12일")
                .location("경기장")
                .build();

        gameInfoRepository.save(gameInfo);

        searchRequest = GameInfoSearchRequest.builder()
                .name("경기 이름")
                .build();
    }

    @Test
    @DisplayName("GameInfo 저장 성공")
    public void saveSuccess() throws Exception {
        GameInfoCreateRequest request = GameInfoCreateRequest.builder()
                .name("경기 내용")
                .date("12월 12일")
                .location("경기장")
                .build();

        String json = objectMapper.writeValueAsString(request);

        mockMvc.perform(post("/api/v1/gameInfo/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andDo(print());

    }

    @Test
    @DisplayName("GameInfo 저장 실패")
    public void saveFail() throws Exception {
        // given
        GameInfoCreateRequest request = GameInfoCreateRequest.builder()
                .name("경기 이름 0")
                .date("12월 12일")
                .location("경기 위치 0")
                .build();

        // when
        String json = objectMapper.writeValueAsString(request);

        // then
        mockMvc.perform(post("/api/v1/gameInfo/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isNotFound())
                .andDo(print());
    }

    @Test
    @DisplayName("Id 로 검색")
    public void findById() throws Exception {

        mockMvc.perform(get("/api/v1/gameInfo/{Id}", gameInfo.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("서울 vs 부산"))
                .andExpect(jsonPath("$.date").value("12월 12일"))
                .andExpect(jsonPath("$.location").value("경기장"))
                .andDo(print());
    }


    @Test
    @DisplayName("Id 로 검색 - 실패")
    public void findByIdFail() throws Exception {

        mockMvc.perform(get("/api/v1/gameInfo/{Id}", gameInfo.getId() + 10L))
                .andExpect(status().isNotFound())
                .andDo(print());
    }

    @Test
    @DisplayName("검색 - 경기 이름")
    public void searchByName() throws Exception {
        String json = objectMapper.writeValueAsString(searchRequest);

        mockMvc.perform(post("/api/v1/gameInfo/search")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("경기 이름 14"))
                .andExpect(jsonPath("$[0].date").value("12월 11일"))
                .andExpect(jsonPath("$[0].location").value("경기 위치 14"))
                .andDo(print());
    }


}
