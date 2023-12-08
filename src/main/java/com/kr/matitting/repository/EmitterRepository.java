package com.kr.matitting.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Repository
public class EmitterRepository {
    private final Map<Long, SseEmitter> emitters = new ConcurrentHashMap<>();

    public void save(Long userId, SseEmitter emitter) {
        emitters.put(userId, emitter);
    }

    public void deleteById(Long userId) {
        emitters.remove(userId);
    }

    public SseEmitter get(Long userId) {
        return emitters.get(userId);
    }

    public List<SseEmitter> getListByKeyPrefix(String keyPrefix) {
        return emitters.keySet().stream()
                .filter(key -> key.equals(Long.valueOf(keyPrefix)))
                .map(emitters::get)
                .collect(Collectors.toList());
    }

    public List<Long> getKeyListByKeyPrefix(String keyPrefix) {
        return emitters.keySet().stream()
                .filter(key -> key.equals(Long.valueOf(keyPrefix)))
                .collect(Collectors.toList());
    }
}
