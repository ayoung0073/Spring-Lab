package com.may.redissub;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RedisMessageStringSubscriber implements MessageListener {

    // 실제 메시지를 수신하면 처리하는 로직
    @Override
    public void onMessage(Message message, byte[] pattern) {
        log.info("Message: " + message.toString());
    }
}
