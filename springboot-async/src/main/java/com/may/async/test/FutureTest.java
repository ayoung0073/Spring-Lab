package com.may.async.test;

import java.time.LocalTime;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FutureTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executor // ExecutorService : Single Thread 생성
                = Executors.newSingleThreadExecutor();
        /*
         * Future : 비동기적인 연산의 결과를 표현하는 클래스
         * 멀티쓰레드 환경에서 처리된 어떤 데이터를 다른 쓰레드에 전달할 수 있다.
         *
         * submit()에 전달된 Callable이 어떤 값을 리턴하면 그 값을 Future에 설정한다.
         *
         * Callable은 인터페이스로, 인자를 받지 않지만 어떤 데이터를 리턴한다.
         * (Runnable은 인자도 없고 리턴되는 데이터도 없는 인터페이스이다.)
         * */
        Future<Integer> future = executor.submit(() -> {
            System.out.println(LocalTime.now() + " Starting runnable");
            Integer sum = 7 + 3;
            Thread.sleep(3000);
            return sum;
        });

        System.out.println(LocalTime.now() + " Waiting the task done");
        Integer result = future.get();
        System.out.println(LocalTime.now() + " Result : " + result);

        /*
        17:16:13.807533 Waiting the task done
        17:16:13.807595 Starting runnable
        17:16:16.823044 Result : 10
        */
    }

}
