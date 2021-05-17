package com.may.async.test;

import java.time.LocalTime;
import java.util.concurrent.*;

public class CompletableFutureTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        // CompletionStage는 비동기 연산 Step을 제공해서 계속 체이닝 형태로 조합이 가능하다.
        // new CompletableFuture<Type>처럼 생성할 수 있다.
        // Type은 작업이 완료되어 저장되는 데이터의 타입을 의미

        CompletableFuture<Integer> future
                = new CompletableFuture<>(); // Future 객체 생성, <Integer> 저장하려는 데이터 타입 명시

        /*
         * Executors.newCachedThreadPool() : Thread Pool 생성
         * submit() : Callable 전달하면 대기 중인 Thread로부터 실행된다.
         * */
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.submit(() -> {
            System.out.println(LocalTime.now() + " Doing something");
            Integer sum = 7 + 3;
            Thread.sleep(3000);
            future.complete(sum); // 다른 쓰레드로 전달할 데이터 저장
            System.out.println(LocalTime.now() + " Exiting runnable");
            return null;
        });

        System.out.println(LocalTime.now() + " Waiting the task done");

        // Integer result = future.get(); // 데이터가 set될 때까지 기다린다.
        // timeout과 시간 단위 TimeUnit 전달
        Integer result;
        try {
            result = future.get(6000, TimeUnit.MILLISECONDS); // 데이터가 set 될 때까지 기다린다.
        } catch (TimeoutException e) {
            System.out.println(LocalTime.now() + " Timed out");
            result = 0;
        }

        System.out.println(LocalTime.now() + " Result : " + result);
        executorService.shutdown();
        /*
            스레드 풀은 데몬 스레드가 아니기 때문에 main 스레드가 종료되어도 실행 상태로 남아있다.
            애플리케이션을 종료하려면 스레드 풀을 종료시켜 스레드들이 종료 상태가 되도록 처리해줘야 한다.

            shutdown 메서드를 호출하지 않으면 main 쓰레드가 종료되어도 스레드 풀이 실행 상태로 남아있음
         */
    }
}
