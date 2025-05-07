package com.sarkhan.dtssystem.service.impl;

import com.sarkhan.dtssystem.model.user.User;
import com.sarkhan.dtssystem.repository.user.UserRepository;
import com.sarkhan.dtssystem.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void resetPass(String email, String newPassword) {
        User user = userRepository.findByEmail(email);
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
        System.out.println("Reset password: " + user.getPassword());
    }

}
