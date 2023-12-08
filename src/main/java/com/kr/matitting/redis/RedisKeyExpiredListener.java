package com.kr.matitting.redis;

import com.kr.matitting.repository.EmitterRepository;
import com.kr.matitting.service.NotificationService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.SessionException;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class RedisKeyExpiredListener implements MessageListener {
    private final NotificationService notificationService;
    private final EmitterRepository emitterRepository;
    public void onMessage(Message message, byte[] pattern) {
        log.info("message:[" + message + "]");
//        log.info("Redis Expired Event");
//        String userId = message.toString();
//
//        emitterRepository.getKeyListByKeyPrefix(userId).forEach(key -> {
//            SseEmitter emitter = emitterRepository.get(key);
//            try {
//                emitter.send(SseEmitter.event()
//                        .id(userId)
//                        .name("sse")
//                        .data("data send"));
//            } catch (IOException exception) {
//                emitterRepository.deleteById(key);
//                log.error("SSE send error", exception);
//                throw new SessionException("SSE send error");
//            }
//        });

    }
}
