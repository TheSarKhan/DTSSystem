package com.sarkhan.dtssystem.controller;

import com.sarkhan.dtssystem.dto.request.LoginRequest;
import com.sarkhan.dtssystem.dto.response.TokenResponse;
import com.sarkhan.dtssystem.forgot.EmailService;
import com.sarkhan.dtssystem.forgot.OTPService;
import com.sarkhan.dtssystem.jwt.JwtUtil;
import com.sarkhan.dtssystem.model.user.User;
import com.sarkhan.dtssystem.redis.RedisService;
import com.sarkhan.dtssystem.repository.user.UserRepository;
import com.sarkhan.dtssystem.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthorizationController {
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthService authService;
    private final RedisService redisService;
    private final OTPService otpService;
    private final EmailService emailService;

    @PostMapping("/login")
    public TokenResponse login(@RequestBody LoginRequest request) {
        Optional<User> userOptional = userRepository.findByUsername(request.getUsername());

        if (userOptional.isEmpty()) {
            throw new RuntimeException("User not found with username: " + request.getUsername());
        }

        User user = userOptional.get();

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        String accessToken = jwtUtil.generateAccessToken(user.getUsername(), null);
        String refreshToken = jwtUtil.generateRefreshToken(user.getUsername());
        boolean isVerified = user.isVerified();
        return TokenResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .isVerified(isVerified)
                .build();
    }


    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestParam String email) {
        String otp = otpService.generateOTP(email);
        emailService.sendEmail(email, "Password Reset OTP", "Your OTP is: " + otp);
        return ResponseEntity.ok("OTP sent to your email.");
    }

    @PostMapping("/validate-otp")
    public ResponseEntity<String> validateOTP(@RequestParam String email, @RequestParam String otp) {
        if (otpService.validateOTP(email, otp)) {
            otpService.clearOTP(email);

            // Geçici Reset Token Üret ve Redis'e Kaydet
            String resetToken = UUID.randomUUID().toString();
            redisService.saveResetToken(email, resetToken, 10);

            return ResponseEntity.ok(resetToken);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid OTP.");
        }
    }

    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(
            @RequestParam String email,
            @RequestParam String newPassword,
            @RequestParam String verifyPassword,
            @RequestParam String resetToken) {

        String storedToken = redisService.getResetToken(email);

        if (storedToken == null || !storedToken.equals(resetToken)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid or expired reset token.");
        }

        if (!newPassword.equals(verifyPassword)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Passwords do not match.");
        }

        authService.resetPass(email, newPassword);
        redisService.deleteResetToken(email);

        return ResponseEntity.ok("Password reset successfully.");
    }
}
