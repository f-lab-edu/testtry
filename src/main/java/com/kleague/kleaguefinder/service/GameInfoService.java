package com.kleague.kleaguefinder.service;

import com.kleague.kleaguefinder.domain.GameInfo;
import com.kleague.kleaguefinder.repository.gameinfo.GameInfoRepository;
import com.kleague.kleaguefinder.request.gameinfo.GameInfoCreateRequest;
import com.kleague.kleaguefinder.request.gameinfo.GameInfoModifyRequest;
import com.kleague.kleaguefinder.request.gameinfo.GameInfoSearchRequest;
import com.kleague.kleaguefinder.response.GameInfoResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class GameInfoService {

    private final GameInfoRepository gameInfoRepository;

    public GameInfoService(GameInfoRepository gameInfoRepository) {
        this.gameInfoRepository = gameInfoRepository;
    }

    @Transactional
    public Long save(GameInfoCreateRequest requestDto) {

        GameInfo entity = requestDto.toEntity();
        gameInfoRepository.save(entity);
        return entity.getId();

    }

    @Transactional(readOnly = true)
    public GameInfoResponse findById(Long gameInfoId) {
        GameInfo gameInfo = gameInfoRepository.findById(gameInfoId).orElseThrow(IllegalStateException::new);
        return GameInfoResponse.createGameInfoResponse(gameInfo);
    }

    @Transactional(readOnly = true)
    public List<GameInfoResponse> findByRequest(GameInfoSearchRequest request) {
        return gameInfoRepository.findByRequest(request)
                .stream().map(GameInfoResponse::createGameInfoResponse).collect(Collectors.toList());
    }

    @Transactional
    public void modify(Long gameInfoId, GameInfoModifyRequest request) {
        GameInfo gameInfo = gameInfoRepository.findById(gameInfoId).orElseThrow(IllegalStateException::new);
        gameInfo.modify(request.getName(), request.getDate(), request.getLocation());
    }

    @Transactional
    public void delete(Long gameInfoId) {
        GameInfo gameInfo = gameInfoRepository.findById(gameInfoId).orElseThrow(IllegalStateException::new);
        gameInfoRepository.delete(gameInfo);
    }
}
