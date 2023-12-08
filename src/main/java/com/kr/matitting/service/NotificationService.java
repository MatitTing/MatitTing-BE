package com.kr.matitting.service;

import com.kr.matitting.exception.user.UserException;
import com.kr.matitting.exception.user.UserExceptionType;
import com.kr.matitting.repository.EmitterRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import com.kr.matitting.entity.User;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@Service
public class NotificationService {
    private final EmitterRepository emitterRepository;
    private static final Long DEFAULT_TIMEOUT = 60L * 1000 * 60;

    public SseEmitter subscribe(User user,Long userId) {
        checkUserAuthentication(user, userId);

        SseEmitter emitter = createEmitter(userId);
        sendToClient(userId, "EventStream Created. [userId=" + userId + "]");

        return emitter;
    }

    public void notify(User user, Long userId, Object event) {
        checkUserAuthentication(user, userId);
        sendToClient(userId, event);
    }

    private void sendToClient(Long userId, Object data) {
        SseEmitter emitter = emitterRepository.get(userId);
        if (emitter != null) {
            try {
                emitter.send(SseEmitter.event()
                        .id(String.valueOf(userId))
                        .name("sse")
                        .data(data));
            } catch (IOException exception) {
                emitterResetAndError(userId, exception, emitter);
            }
        }
    }

    private SseEmitter createEmitter(Long userId) {
        SseEmitter emitter = new SseEmitter(DEFAULT_TIMEOUT);
        emitterRepository.save(userId, emitter);

        emitter.onCompletion(() -> {
            log.info("onCompletion callback");
            emitterRepository.deleteById(userId);
        });
        emitter.onTimeout(() -> {
            log.info("onTimeout callback");
            emitterRepository.deleteById(userId);
            emitter.complete();
        });
        try {
            emitter.send(SseEmitter.event()
                    .name("sse")
                    .id(String.valueOf(userId))
                    .data("subscribe"));
        } catch (IOException exception) {
            emitterResetAndError(userId, exception, emitter);
        }
        return emitter;
    }

    private void emitterResetAndError(Long userId, IOException exception, SseEmitter emitter) {
        emitterRepository.deleteById(userId);
        emitter.completeWithError(exception);
    }

    private void checkUserAuthentication(User user, Long userId) {
        if (!user.getId().equals(userId)) {
            throw new UserException(UserExceptionType.NOT_PERMISSION_USER);
        }
    }
}
