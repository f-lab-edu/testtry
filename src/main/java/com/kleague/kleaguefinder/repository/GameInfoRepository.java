package com.kleague.kleaguefinder.repository;

import com.kleague.kleaguefinder.domain.GameInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GameInfoRepository extends JpaRepository<GameInfo, Long> {
    List<GameInfo> findByDate(String date);
}
