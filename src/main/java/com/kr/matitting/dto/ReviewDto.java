package com.kr.matitting.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDto {
    @Schema(description = "방장 ID", example = "11")
    @NotNull
    private Long userId;

    @Schema(description = "후기 내용", example = "너무 친절해요.")
    @NotBlank
    private String content;
}
