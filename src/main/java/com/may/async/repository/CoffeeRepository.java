package com.may.async.repository;

import com.may.async.domain.Coffee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Repository
public class CoffeeRepository {

    private Map<String, Coffee> coffeeMap = new HashMap<>();

    @PostConstruct // 의존성 주입이 이루어진 후 초기화를 수행하는 메서드
    public void init() {
        coffeeMap.put("americano", Coffee.builder().name("americano").price(4100).build());
        coffeeMap.put("icetea", Coffee.builder().name("icetea").price(3500).build());
        coffeeMap.put("greentealatte", Coffee.builder().name("greentealatte").price(5000).build());
    }

    public int getPriceByName(String name) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            log.info(e.getMessage());
        }
        return coffeeMap.get(name).getPrice();
    }
}
