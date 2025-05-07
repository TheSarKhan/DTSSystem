package com.sarkhan.dtssystem.service.impl;

import com.sarkhan.dtssystem.dto.request.CompleteRequest;
import com.sarkhan.dtssystem.jwt.JwtUtil;
import com.sarkhan.dtssystem.model.user.User;
import com.sarkhan.dtssystem.repository.user.UserRepository;
import com.sarkhan.dtssystem.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User completeAdminData(CompleteRequest completeRequest, String token) {
        String username = jwtUtil.extractUsername(token);
        Optional<User> currentUser = userRepository.findByUsername(username);
        currentUser.get().setEmail(completeRequest.getEmail());
        currentUser.get().setPassword(passwordEncoder.encode(completeRequest.getPassword()));
        currentUser.get().setPhoneNumber(completeRequest.getPhoneNumber());
        currentUser.get().setNameSurname(completeRequest.getNameAndSurname());
        currentUser.get().setUpdatedDate(LocalDateTime.now());
        currentUser.get().setVerified(true);
        userRepository.save(currentUser.get());
        return currentUser.get();
    }
}
