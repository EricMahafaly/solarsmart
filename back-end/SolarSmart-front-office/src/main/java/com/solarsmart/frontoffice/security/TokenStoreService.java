package com.solarsmart.frontoffice.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.concurrent.ConcurrentHashMap;

@RequiredArgsConstructor
@Component
@Slf4j
public class TokenStoreService {

//    private final RedisTemplate<String, String> redisTemplate;
    private final TokenService tokenService;
    private final ConcurrentHashMap<String, String> tokenStore = new ConcurrentHashMap<>();
    public void saveToken(String token) {
        String key = this.createKey(token);

        this.tokenStore.put(key, token);
//        redisTemplate.opsForValue().set(key, token);
    }

    private String createKey(String token){
        String key = "user_token:" + this.tokenService.extractUsername(token)+"_"+this.tokenService.extractId(token);
        return key;
    }

    public boolean isExist(String token) {
        String key = this.createKey(token);

//        log.info("key for the token: {} is {}", token, key);
        String existingToken = tokenStore.get(key);

        return existingToken != null && existingToken.equals(token);

    }

    public boolean deleteToken(String token) {
        boolean deleted = tokenService.deleteToken(token);
        // Traitez la logique selon la suppression du jeton
        return deleted;
    }
}
