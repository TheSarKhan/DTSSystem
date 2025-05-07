package com.sarkhan.dtssystem.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class RedisService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    private static final String TOKEN_PREFIX = "token:";
    private static final String RESET_TOKEN_PREFIX = "reset-token:";
    private static final String REFRESH_TOKEN_PREFIX = "refresh-token:";

    // 🔹 Kullanıcı için JWT token'ı Redis'e kaydetme
    public void saveAccessTokenToRedis(String token, String username) {
        redisTemplate.opsForValue().set(TOKEN_PREFIX + username, token);
    }

    // 🔹 Kullanıcının Redis'te saklanan token'ını alma
    public String getAccessTokenFromRedis(String username) {
        return redisTemplate.opsForValue().get(TOKEN_PREFIX + username);
    }

    // 🔹 Kullanıcının token'ını Redis'ten silme (Logout vb. için)
    public void deleteAccessTokenFromRedis(String username) {
        redisTemplate.delete(TOKEN_PREFIX + username);
    }

    // 🔹 Şifre sıfırlama için geçici token'ı kaydetme (10 dakika geçerli)
    public void saveResetToken(String email, String token, int timeout) {
        redisTemplate.opsForValue().set(RESET_TOKEN_PREFIX + email, token, timeout, TimeUnit.MINUTES);
    }

    // 🔹 Kullanıcının şifre sıfırlama token'ını alma
    public String getResetToken(String email) {
        return redisTemplate.opsForValue().get(RESET_TOKEN_PREFIX + email);
    }

    // 🔹 Şifre sıfırlama token'ını silme (Kullanıldıktan sonra)
    public void deleteResetToken(String email) {
        redisTemplate.delete(RESET_TOKEN_PREFIX + email);
    }

    // 🔹 Refresh token'ı kaydetme (örneğin 7 gün süreyle)
    public void saveRefreshToken(String username, String refreshToken, int timeoutInDays) {
        redisTemplate.opsForValue().set(REFRESH_TOKEN_PREFIX + username, refreshToken, timeoutInDays, TimeUnit.DAYS);
    }

    // 🔹 Refresh token'ı alma
    public String getRefreshToken(String username) {
        return redisTemplate.opsForValue().get(REFRESH_TOKEN_PREFIX + username);
    }

    // 🔹 Refresh token'ı silme
    public void deleteRefreshToken(String username) {
        redisTemplate.delete(REFRESH_TOKEN_PREFIX + username);
    }
}
