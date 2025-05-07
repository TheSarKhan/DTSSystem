package com.sarkhan.dtssystem.forgot;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class OTPService {

    private final StringRedisTemplate redisTemplate;
    private static final long OTP_EXPIRATION_MINUTES = 5; // OTP'nin geçerlilik süresi (5 dakika)

    public String generateOTP(String email) {
        String otp = String.valueOf((int) (Math.random() * 900000) + 100000); // 6 haneli OTP
        redisTemplate.opsForValue().set(email+"_fotgot", otp, OTP_EXPIRATION_MINUTES, TimeUnit.MINUTES);
        return otp;
    }

    public boolean validateOTP(String email, String otp) {
        String storedOtp = redisTemplate.opsForValue().get(email+"_fotgot");
        return storedOtp != null && storedOtp.equals(otp);
    }

    public void clearOTP(String email) {
        redisTemplate.delete(email+"_fotgot");
    }
}
