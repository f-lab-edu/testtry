package com.kleague.kleaguefinder.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kleague.kleaguefinder.domain.Category;
import com.kleague.kleaguefinder.domain.Seat;
import com.kleague.kleaguefinder.exception.ErrorCode;
import com.kleague.kleaguefinder.repository.seat.SeatRepository;
import com.kleague.kleaguefinder.request.seat.SeatCreateRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static com.kleague.kleaguefinder.domain.Category.*;
import static com.kleague.kleaguefinder.exception.ErrorCode.*;
import static org.springframework.http.MediaType.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class SeatControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    SeatRepository seatRepository;

    @Autowired
    ObjectMapper objectMapper;

    Seat seatNo1;

    @BeforeEach
    public void setup() {
        seatNo1 = seatRepository.save(Seat.builder()
                .seatNumber("1")
                .category(B)
                .build());

        seatRepository.save(seatNo1);
    }

    @Test
    @DisplayName("저장 성공")
    public void saveSuccessV1() throws Exception {
        // given
        SeatCreateRequest request = SeatCreateRequest.builder()
                .seatNumber("1")
                .category(A)
                .build();

        // when
        String json = objectMapper.writeValueAsString(request);

        // then
        mockMvc.perform(post("/api/v1/seat/save")
                        .contentType(APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("저장 성공 - 필드 값 중 한 속성만 중복")
    public void saveSuccessV2() throws Exception {

        SeatCreateRequest request = SeatCreateRequest.builder()
                .seatNumber("1")
                .category(A)
                .build();

        // when
        String json = objectMapper.writeValueAsString(request);

        // then
        mockMvc.perform(post("/api/v1/seat/save")
                        .contentType(APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("저장 실패 - 중복 Seat 존재")
    public void saveFail() throws Exception {

        SeatCreateRequest request = SeatCreateRequest.builder()
                .seatNumber("1")
                .category(B)
                .build();

        // when
        String json = objectMapper.writeValueAsString(request);

        // then
        mockMvc.perform(post("/api/v1/seat/save")
                        .contentType(APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value(400))
                .andExpect(jsonPath("$.messages[0]")
                        .value(makeMessage("Seat", DUPLICATED_CODE.getMessage(), "seatNumber & category")))
                .andDo(print());
    }

    @Test
    @DisplayName("검색 성공 - Seat Id 로 검색")
    public void findSeatById() throws Exception {


        mockMvc.perform(get("/api/v1/seat/{seatId}", seatNo1.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.seatNumber").value("1"))
                .andExpect(jsonPath("$.category").value("B"))
                .andDo(print());
    }

    @Test
    @DisplayName("검색 실패 - Seat Id 로 검색 결과 없음")
    public void findSeatByIdFail() throws Exception {


        mockMvc.perform(get("/api/v1/seat/{seatId}", seatNo1.getId() + 20L))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.code").value(404))
                .andExpect(jsonPath("$.messages[0]")
                        .value(makeMessage("Seat", NO_VALUE_CODE.getMessage(), "id")))
                .andDo(print());
    }

    public String makeMessage(String type, String message, String field) {
        return ("[" + type + "] " + message + " { 필드 : " + field + " }" );
    }
}
