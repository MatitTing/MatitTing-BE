package com.kr.matitting.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
@Schema(description = "메인페이지 페이지 정보 Response")
@Getter
public class ResponseChatPageInfoDto {
    @Schema(description = "이전에 조회한 party id", nullable = false, example = "10")
    private Long lastPartyId;
    @Schema(description = "다음 페이지 유무", nullable = false, example = "true")
    private boolean hasNext;

    public ResponseChatPageInfoDto(Long newLastPartyId, boolean hasNext) {
        this.lastPartyId = newLastPartyId;
        this.hasNext = hasNext;
    }
}