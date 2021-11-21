package com.may.redispub;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.core.RedisTemplate;

@RequiredArgsConstructor
@SpringBootApplication
public class RedisPubApplication implements CommandLineRunner {

    private final RedisTemplate<String, String> redisTemplate;

    public static void main(String[] args) {
        SpringApplication.run(RedisPubApplication.class, args);
    }

    @Override
    public void run(String... args) {
        // 앱 실행되면 메시지를 바로 발신한다.
        redisTemplate.convertAndSend("ch1", "Redis Pub/Sub Message Test");
    }

}
