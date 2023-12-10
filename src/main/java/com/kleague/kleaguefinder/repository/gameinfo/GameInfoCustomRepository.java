package com.kleague.kleaguefinder.repository.gameinfo;

import com.kleague.kleaguefinder.domain.GameInfo;
import com.kleague.kleaguefinder.request.gameinfo.GameInfoSearchRequest;

import java.util.List;

public interface GameInfoCustomRepository {
    List<GameInfo> findByRequest(GameInfoSearchRequest request);
}
