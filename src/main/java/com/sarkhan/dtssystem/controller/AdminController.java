package com.sarkhan.dtssystem.controller;

import com.sarkhan.dtssystem.dto.request.CompleteRequest;
import com.sarkhan.dtssystem.dto.request.UserRequest;
import com.sarkhan.dtssystem.jwt.JwtUtil;
import com.sarkhan.dtssystem.model.user.User;
import com.sarkhan.dtssystem.repository.user.UserRepository;
import com.sarkhan.dtssystem.service.AdminService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin")
public class AdminController {
    private final AdminService adminService;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    @PostMapping("/complete")
    @Operation(description = "Admin hesabi yaradilib giris etdikden sonra melumatlari elave ederek hesabi tamamlamaq (admin)")

    public ResponseEntity<?> completeAdmin(@Valid @RequestHeader("Authorization") String token, @RequestBody CompleteRequest completeRequest) {
        token = token.substring(7);
        if (completeRequest.getPassword().equals(completeRequest.getConfirmPassword())) {
            User completedUser = adminService.completeAdminData(completeRequest, token);
            return ResponseEntity.status(201).body(completedUser);
        }
        if (completeRequest.getPassword().length() < 8) {
            return ResponseEntity.status(400).body("Password length must be between 8  characters");
        }
        return ResponseEntity.status(401).body("Wrong password");
    }

    @GetMapping("/checkAdminEmail")
    public String checkAdminEmail(@RequestHeader("Authorization") String token) {
        token = token.substring(7);
        String username = jwtUtil.extractUsername(token);
        Optional<User> user = userRepository.findByUsername(username);

        return user.map(User::getEmail).orElse(null);
    }

    @PutMapping("/editAdminData")
    public User editAdminData(@Valid @RequestHeader("Authorization") String token, @RequestBody UserRequest userRequest) {
        token = token.substring(7);
        String username = jwtUtil.extractUsername(token);
        Optional<User> user = userRepository.findByUsername(username);
        user.get().setEmail(userRequest.getEmail());
        user.get().setUpdatedDate(LocalDateTime.now());
        user.get().setPhoneNumber(userRequest.getPhoneNumber());
        user.get().setUsername(userRequest.getUsername());
        user.get().setNameSurname(userRequest.getNameAndSurname());
        userRepository.save(user.get());
        return user.orElse(null);
    }
}
