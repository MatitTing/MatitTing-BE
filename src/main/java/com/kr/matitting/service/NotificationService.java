package com.kr.matitting.service;

import com.kr.matitting.constant.NotificationType;
import com.kr.matitting.dto.NotificationDto;
import com.kr.matitting.entity.Notification;
import com.kr.matitting.entity.User;
import com.kr.matitting.repository.EmitterRepository;
import com.kr.matitting.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationService {
    // 기본 타임아웃 설정
    private static final Long DEFAULT_TIMEOUT = 60L * 1000 * 60;

    private final EmitterRepository emitterRepository;
    private final NotificationRepository notificationRepository;

    /**
     * SSE 연결
     * @param user
     * @param lastEventId
     * @return
     */
    public SseEmitter subscribe(User user, String lastEventId) {

        String emitterId = makeTimeIncludeId(user.getId());

        SseEmitter emitter = emitterRepository.save(emitterId, new SseEmitter(DEFAULT_TIMEOUT));

        log.info("new emitter added : {}", emitter);
        log.info("lastEventId : {}", lastEventId);

        emitter.onCompletion(() -> emitterRepository.deleteById(emitterId));
        emitter.onTimeout(() -> emitterRepository.deleteById(emitterId));
        emitter.onError((e) -> emitterRepository.deleteAllEmitterStartWithId(emitterId));

        //503 에러 방지를 위한 더미 이벤트 전송
        String eventId = makeTimeIncludeId(user.getId());
        sendNotification(emitter, eventId, emitterId, "EventStream Created. [userId=" + user.getId() + "]");

        if (hasLostData(lastEventId)) {
            sendLostData(lastEventId, user, emitterId, emitter);
        }

        return emitter;
    }

    /**
     * 고유 Key 값 생성 : emitterId, eventId 등
     * @param userId
     * @return
     */
    private String makeTimeIncludeId(Long userId) {
        return userId + "_" + System.currentTimeMillis();
    }

    /**
     * 알림 전송
     * @param emitter
     * @param eventId
     * @param emitterId
     * @param data
     */
    private void sendNotification(SseEmitter emitter, String eventId, String emitterId, Object data) {
        try {
            emitter.send(SseEmitter.event()
                    .id(eventId)
                    .name("sse")
                    .data(data)
            );
        } catch (IOException exception) {
//            emitterRepository.deleteById(emitterId);
            log.error("알림 전송에 실패했습니다.");
        }
    }

    /**
     * Lost된 Data 검사
     * @param lastEventId
     * @return
     */
    private boolean hasLostData(String lastEventId) {
        return !lastEventId.isEmpty();
    }

    /**
     * Lost Data 전송
     * @param lastEventId
     * @param user
     * @param emitterId
     * @param emitter
     */
    private void sendLostData(String lastEventId, User user, String emitterId, SseEmitter emitter) {
        Map<String, Object> eventCaches = emitterRepository.findAllEventCacheStartWithByMemberId(String.valueOf(user.getId()));
        System.out.println("eventCaches = " + eventCaches.size());

        /**
         * cache에 쌓인 데이터 send
         */
        eventCaches.entrySet().stream()
                .filter(entry -> lastEventId.compareTo(entry.getKey()) < 0)
                .forEach(entry -> sendNotification(emitter, entry.getKey(), emitterId, entry.getValue()));

        /**
         * cache 비우기
         */
        eventCaches.clear();
    }

    /**
     * 알림 cache 저장 및 전송
     * @param receiver
     * @param notificationType
     * @param title
     * @param content
     */
    public void send(User receiver, NotificationType notificationType, String title, String content){
        Notification notification = notificationRepository.save(new Notification(receiver, notificationType, title, content));

        Long userId = receiver.getId();
        String eventId = makeTimeIncludeId(userId);
        Map<String, SseEmitter> emitters = emitterRepository.findAllEmitterStartWithByMemberId(String.valueOf(userId));
        emitterRepository.saveEventCache(eventId, NotificationDto.Response.createResponse(notification, eventId));

        emitters.forEach(
                (key, emitter) -> {
                    sendNotification(emitter, eventId, key, NotificationDto.Response.createResponse(notification, eventId));
                }
        );
    }
}