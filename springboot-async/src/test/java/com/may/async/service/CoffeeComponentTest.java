package com.may.async.service;

import com.may.async.config.TaskConfig;
import com.may.async.repository.CoffeeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(
        classes = {
                TaskConfig.class,
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

    /*
         thenAccept 메서드를 사용한 테스트 코드

         getPriceAsync는 Completable<Integer>을 반환하는데, 이때 thenAccept 메서드를 정의하면 콜백함수를 선언할 수 있다.
         CompletableFuture가 complete가 되면(가격 조회 완성) thenAccept를 수행하게 될 것이다.
     */
    @Test
    public void 가격조회_Async_호출_테스트_반환X_테스트() {
        Integer expectedPrice = 4100;

        CompletableFuture<Void> future = coffeeComponent
                .getPriceAsync("americano")
                .thenAccept(p -> { // p : 파라미터
                    logger.info("콜백, 가격은 " + p + "원, 하지만 데이터를 반환하지 않음");
                    assertThat(p).isEqualTo(expectedPrice);
                });
        logger.info("아직 최종 가격 전달 받진 않았지만, 다른 작업 수행 가능");

        // 메인스레드가 종료되지 않도록 블록킹으로 대기하기 위한 코드, future가 complet가 되면 위에 작성한 thenAccept 코드가 실행된다.
        assertNull(future.join());

        /*
            23:39:05.498 [Test worker] INFO com.may.async.service.CoffeeComponent - Async 방식으로 가격 조회 시작
            23:39:05.501 [pool-1-thread-1] INFO com.may.async.service.CoffeeComponent - supplyAsync
            23:39:05.509 [Test worker] INFO com.may.async.service.CoffeeComponentTest - 아직 최종 데이터를 전달 받지는 않았지만, 다른 작업 수행 가능
            23:39:06.509 [pool-1-thread-1] INFO com.may.async.service.CoffeeComponentTest - 같은 스레드로 동작
            23:39:06.528 [pool-1-thread-1] INFO com.may.async.service.CoffeeComponentTest - 콜백, 가격은 4600원, 하지만 데이터를 반환하지 않음
         */
    }

    @Test
    public void 가격조회_Async_호출_테스트_콜백_반환O_같은스레드_테스트() {

        Integer expectedPrice = 4100 + 500;

        CompletableFuture<Void> future = coffeeComponent
                .getPriceAsync("americano")
                .thenApply(p -> {
                    logger.info("같은 스레드로 동작");
                    return p + 500;
                })
                .thenAccept(p -> {
                    logger.info("콜백, 가격은 " + p + "원, 하지만 데이터를 반환하지 않음");
                    assertThat(p).isEqualTo(expectedPrice);
                });

        logger.info("아직 최종 데이터를 전달 받지는 않았지만, 다른 작업 수행 가능");
        assertNull(future.join());

        /*
            23:39:05.498 [Test worker] INFO com.may.async.service.CoffeeComponent - Async 방식으로 가격 조회 시작
            23:39:05.501 [pool-1-thread-1] INFO com.may.async.service.CoffeeComponent - supplyAsync
            23:39:05.509 [Test worker] INFO com.may.async.service.CoffeeComponentTest - 아직 최종 데이터를 전달 받지는 않았지만, 다른 작업 수행 가능
            23:39:06.509 [pool-1-thread-1] INFO com.may.async.service.CoffeeComponentTest - 같은 스레드로 동작
            23:39:06.528 [pool-1-thread-1] INFO com.may.async.service.CoffeeComponentTest - 콜백, 가격은 4600원, 하지만 데이터를 반환하지 않음
         */
    }

    @Test
    public void 가격조회_Async_호출_테스트_콜백_반환O_다른스레드_테스트() {

        Integer expectedPrice = 4100 + 500;
        Executor executor = Executors.newFixedThreadPool(3);

        CompletableFuture<Void> future = coffeeComponent
                .getPriceAsync("americano")
                .thenApplyAsync(p -> {
                    logger.info("다른 스레드로 동작");
                    return p + 500;
                }, executor)
                .thenAcceptAsync(p -> {
                    logger.info("콜백, 가격은 " + p + "원, 하지만 데이터를 반환하지 않음");
                    assertThat(p).isEqualTo(expectedPrice);
                }, executor);

        logger.info("아직 최종 데이터를 전달 받지는 않았지만, 다른 작업 수행 가능");
        assertNull(future.join());

        /* executor 파라미터 추가 X
            23:46:41.668 [Test worker] INFO com.may.async.service.CoffeeComponent - Async 방식으로 가격 조회 시작
            23:46:41.671 [pool-1-thread-1] INFO com.may.async.service.CoffeeComponent - supplyAsync
            23:46:41.680 [Test worker] INFO com.may.async.service.CoffeeComponentTest - 아직 최종 데이터를 전달 받지는 않았지만, 다른 작업 수행 가능
            23:46:42.680 [ForkJoinPool.commonPool-worker-3] INFO com.may.async.service.CoffeeComponentTest - 다른 스레드로 동작
            23:46:42.702 [ForkJoinPool.commonPool-worker-5] INFO com.may.async.service.CoffeeComponentTest - 콜백, 가격은 4600원, 하지만 데이터를 반환하지 않음
         */

        /* executor 파라미터 추가 O
            23:48:16.586 [Test worker] INFO com.may.async.service.CoffeeComponent - Async 방식으로 가격 조회 시작
            23:48:16.588 [pool-1-thread-1] INFO com.may.async.service.CoffeeComponent - supplyAsync
            23:48:16.596 [Test worker] INFO com.may.async.service.CoffeeComponentTest - 아직 최종 데이터를 전달 받지는 않았지만, 다른 작업 수행 가능
            23:48:17.596 [pool-2-thread-1] INFO com.may.async.service.CoffeeComponentTest - 다른 스레드로 동작
            23:48:17.613 [pool-2-thread-2] INFO com.may.async.service.CoffeeComponentTest - 콜백, 가격은 4600원, 하지만 데이터를 반환하지 않음
         */
    }

}
