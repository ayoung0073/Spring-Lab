package com.may.async.service;


import com.may.async.repository.CoffeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Component
@RequiredArgsConstructor
public class CoffeeComponent implements CoffeeUseCase {

    private final CoffeeRepository coffeeRepository;
    // Executor executor = Executors.newFixedThreadPool(10);
    private final ThreadPoolTaskExecutor executor;

    @Override
    public int getPrice(String name) throws InterruptedException {
        log.info("Sync 방식으로 가격 조회 시작");
        return coffeeRepository.getPriceByName(name);
    }

    @Override
    public CompletableFuture<Integer> getPriceAsync(String name) {
        log.info("Async 방식으로 가격 조회 시작");

//        return CompletableFuture.supplyAsync(() -> coffeeRepository.getPriceByName(name));
        return CompletableFuture.supplyAsync(() -> { // 로그 찍어보기 위해 추가
                    log.info("supplyAsync");
                    return coffeeRepository.getPriceByName(name);
                },
                executor // CommonPool이 아닌 스레드 풀 사용
        );

//        CompletableFuture<Integer> future = new CompletableFuture<>();
//
//        new Thread(() -> {
//            log.info("새로운 스레드로 작업 시작");
//            Integer price = coffeeRepository.getPriceByName(name);
//            future.complete(price); // 다른 스레드에 데이터를 전
//        }).start();
//
//        return future;
//        // CompletableFuture 반환
    }

    @Override
    public CompletableFuture<Integer> getDiscountPriceAsync(Integer price) {
        return null;
    }
}
