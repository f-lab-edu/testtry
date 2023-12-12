package com.kleague.kleaguefinder.controller;

import com.kleague.kleaguefinder.request.gameinfo.GameInfoCreateRequest;
import com.kleague.kleaguefinder.request.gameinfo.GameInfoModifyRequest;
import com.kleague.kleaguefinder.request.gameinfo.GameInfoSearchRequest;
import com.kleague.kleaguefinder.response.GameInfoResponse;
import com.kleague.kleaguefinder.service.GameInfoService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class GameInfoController {

    private final GameInfoService gameInfoService;

    @PostMapping("api/v1/gameInfo/save")
    public Long save(@RequestBody GameInfoCreateRequest request) {
        log.info("controller request ={}", request.getDate());
        return gameInfoService.save(request);
    }

    @GetMapping("api/v1/gameInfo/{Id}")
    public GameInfoResponse findOne(@PathVariable("Id") Long id) {
        return gameInfoService.findById(id);
    }

    @PostMapping("api/v1/gameInfo/search")
    public List<GameInfoResponse> search(@RequestBody GameInfoSearchRequest request) {
        return gameInfoService.findByRequest(request);
    }

    @PutMapping("api/v1/gameInfo/{Id}")
    public void modify(@PathVariable("Id") Long id, @RequestBody GameInfoModifyRequest request) {
        gameInfoService.modify(id,request);
    }

    @DeleteMapping("api/v1/gameInfo/{id}")
    public void delete(@PathVariable("Id") Long id) {
        gameInfoService.delete(id);
    }

}
