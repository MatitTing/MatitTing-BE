package com.kr.matitting.controller;

import com.kr.matitting.dto.InvitationRequestDto;
import com.kr.matitting.dto.ReviewDto;
import com.kr.matitting.entity.User;
import com.kr.matitting.exception.user.UserExceptionType;
import com.kr.matitting.service.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/review")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @Operation(summary = "후기 작성", description = "참여자가 방장에게 후기를 남기는 API \n\n" +
            "파티 시작시간이 특정 시간 지나서 Scheduler에 의해서 파티 상태가 FINISH로 변경 될 때 참여자들에게 후기 알림 요청 전송 \n\n \n\n" +
            "로직 설명 \n\n" +
            "1. 참여자들은 후기 알림을 받아서 후기를 작성 \n\n" +
            "2. 전송 \n\n" +
            "Response : null, state code : 200")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "파티 신청 현황 불러오기 성공"),
            @ApiResponse(responseCode = "600", description = "사용자 정보가 없습니다.", content = @Content(schema = @Schema(implementation = UserExceptionType.class)))
    })
    @PostMapping
    public ResponseEntity<?> setReview(@AuthenticationPrincipal User user, @Valid @RequestBody ReviewDto reviewDto) {
        reviewService.setReview(user, reviewDto);
        return ResponseEntity.ok(null);
    }


}
