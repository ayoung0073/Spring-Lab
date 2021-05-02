package com.may.async.service;

import java.util.concurrent.CompletableFuture;

// 서비스의 비즈니스 로직을 포함하고 있는 UseCase Interface
public interface CoffeeUseCase {
    int getPrice(String name) throws InterruptedException;                            // Sync
    CompletableFuture<Integer> getPriceAsync(String name) throws InterruptedException;           // Async
    CompletableFuture<Integer> getDiscountPriceAsync(Integer price); // Async
}
/*
 기능을 제공하는 쪽에서 동기, 비동기에 대한 개념을 포함하고 있다.
 블록킹으로 할지, 논블록킹으로 할지는 기능을 제공하는 클래스에서 제공하는 것이 나닌
 해당 메서드를 호출하는 곳(클라이언트)에서 선택한다.
 */

