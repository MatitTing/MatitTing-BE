package com.kr.matitting.controller;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.kr.matitting.constant.Gender;
import com.kr.matitting.constant.Role;
import com.kr.matitting.constant.SocialType;
import com.kr.matitting.entity.User;
import com.kr.matitting.jwt.service.JwtService;
import com.kr.matitting.repository.UserRepository;
import com.kr.matitting.service.NotificationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.Optional;

@RestController
@RequestMapping("/notifications")
@RequiredArgsConstructor
public class NotificationController {
    private final NotificationService notificationService;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    @GetMapping(value = "/subscribe/{userId}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter subscribe(@AuthenticationPrincipal User user, @PathVariable Long userId, HttpServletResponse response) {
        response.setHeader("X-Accel-Buffering", "no");
        return notificationService.subscribe(user, userId);
    }

    @PostMapping("/send-data/{userId}")
    public void sendDate(@AuthenticationPrincipal User user, @PathVariable Long userId) {
        notificationService.notify(user, userId, "data send");
    }

    @GetMapping("/dummy-test")
    public ResponseEntity<?> createDummyDate(HttpServletResponse response) {
        User user = User.builder()
                .socialId("123123")
                .socialType(SocialType.KAKAO)
                .email("test@naver.com")
                .nickname("닉네임")
                .gender(Gender.MALE)
                .age(26)
                .imgUrl("테스트.jpg")
                .role(Role.USER)
                .build();

        Optional<User> bySocialId = userRepository.findBySocialId("123123");
        if (bySocialId.isEmpty()) {
            userRepository.save(user);
        }

        String accessToken = jwtService.createAccessToken(user);
        String refreshToken = jwtService.createRefreshToken(user);

        response.addHeader(jwtService.getAccessHeader(), "Bearer "+accessToken);
        response.addHeader(jwtService.getRefreshHeader(), "Bearer " + refreshToken);

        return ResponseEntity.ok(user);
    }

    @GetMapping("/token-test")
    public ResponseEntity<?> verifyToken(HttpServletRequest request) {
        String tokenHeader = request.getHeader(jwtService.getAccessHeader());
        String token = tokenHeader.replace("Bearer ", "");
        DecodedJWT tokenValid = jwtService.isTokenValid(token);

        return ResponseEntity.ok("유효한 Token입니다.");
    }
}
