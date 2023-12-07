package com.kleague.kleaguefinder.service;

import com.kleague.kleaguefinder.domain.GameInfo;
import com.kleague.kleaguefinder.repository.GameInfoRepository;
import com.kleague.kleaguefinder.request.GameInfoRequestDto;
import com.kleague.kleaguefinder.response.GameInfoResponseDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GameInfoService {

    private final GameInfoRepository gameInfoRepository;

    public GameInfoService(GameInfoRepository gameInfoRepository) {
        this.gameInfoRepository = gameInfoRepository;
    }

    @Transactional
    public Long save(GameInfoRequestDto requestDto) {
        GameInfo gameInfo = GameInfo.builder()
                .name(requestDto.getName())
                .date(requestDto.getDate())
                .location(requestDto.getLocation())
                .build();

        gameInfoRepository.save(gameInfo);

        return gameInfo.getId();
    }

    @Transactional(readOnly = true)
    public GameInfoResponseDto findById(Long gameInfoId) {
        GameInfo gameInfo = gameInfoRepository.findById(gameInfoId).orElseThrow(IllegalStateException::new);
        return new GameInfoResponseDto(gameInfo);
    }
}
