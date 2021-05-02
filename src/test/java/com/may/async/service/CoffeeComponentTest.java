package com.may.async.service;

import com.may.async.repository.CoffeeRepository;
import net.minidev.json.writer.CompessorMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.CompletableFuture;

import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(
        classes = {
                CoffeeComponent.class,
                CoffeeRepository.class
        }
)
public class CoffeeComponentTest {
    @Autowired
    private CoffeeComponent coffeeComponent;

    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Test
    public void 가격조회_Sync_블록킹_호출_테스트() throws InterruptedException {

        int expectedPrice = 4100;

        int resultPrice = coffeeComponent.getPrice("americano");
        logger.info("최종 가격 전달 받음");
        assertThat(resultPrice).isEqualTo(expectedPrice);
    }

    @Test
    public void 가격조회_Async_블록킹_호출_테스트() {

        int expectedPrice = 4100;

        CompletableFuture<Integer> future = coffeeComponent.getPriceAsync("americano");
        logger.info("아직 최종 가격 전달 받진 않았지만, 다른 작업 수행 가능");

        // CompletableFuture의 join 은 Future의 get과 비슷하다. 하지만 예외처리가 내부적으로 되어 있다는 차이가 있다.
        // 데이터가 계산되지 않았다면 될 때까지 기다렸다가 결과를 전달받는다.
        int resultPrice = future.join();
        logger.info("최종 가격 전달 받음");

        assertThat(resultPrice).isEqualTo(expectedPrice);

        /*
        19:16:40.958 [Test worker] INFO com.may.async.service.CoffeeComponent - Async 방식으로 가격 조회 시작
        19:16:40.960 [Test worker] INFO com.may.async.service.CoffeeComponentTest - 아직 최종 가격 전달 받진 않았지만, 다른 작업 수행 가능
        19:16:40.960 [Thread-3] INFO com.may.async.service.CoffeeComponent - 새로운 스레드로 작업 시작
        19:16:41.965 [Test worker] INFO com.may.async.service.CoffeeComponentTest - 최종 가격 전달 받음
         */

        /* supplyAsync
        22:55:26.676 [Test worker] INFO com.may.async.service.CoffeeComponent - Async 방식으로 가격 조회 시작
        22:55:26.680 [Test worker] INFO com.may.async.service.CoffeeComponentTest - 아직 최종 가격 전달 받진 않았지만, 다른 작업 수행 가능
        22:55:26.681 [ForkJoinPool.commonPool-worker-3] INFO com.may.async.service.CoffeeComponent - supplyAsync
        22:55:27.690 [Test worker] INFO com.may.async.service.CoffeeComponentTest - 최종 가격 전달 받음
         */

    }
    
}
