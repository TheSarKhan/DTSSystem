package com.sarkhan.dtssystem.controller.home;

import com.sarkhan.dtssystem.dto.home.HomeDTO;
import com.sarkhan.dtssystem.service.home.service.HomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/home")
@RequiredArgsConstructor
public class HomeController {
    private final HomeService homeService;

    @GetMapping
    public ResponseEntity<HomeDTO> getHome() {
        return ResponseEntity.ok(homeService.getHomeData());
    }
}
