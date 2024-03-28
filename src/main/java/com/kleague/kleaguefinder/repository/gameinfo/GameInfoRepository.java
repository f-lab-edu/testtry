package com.kleague.kleaguefinder.repository.gameinfo;

import com.kleague.kleaguefinder.domain.GameInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameInfoRepository extends JpaRepository<GameInfo, Long>,
    GameInfoCustomRepository {

}
