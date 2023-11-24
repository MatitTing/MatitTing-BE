package com.kr.matitting.controller;

import com.kr.matitting.constant.Orders;
import com.kr.matitting.dto.*;
import com.kr.matitting.entity.User;
import com.kr.matitting.oauth2.CustomOauth2User;
import com.kr.matitting.service.SearchService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/search")
public class SearchController {
    private final SearchService searchService;

    @Operation(summary = "파티 검색", description = "파티방을 검색하는 메소드")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "파티방 검색 리스트 반환", content = @Content(schema = @Schema(implementation = PartyCreateDto.class)))
    })
    @GetMapping
    public ResponseEntity<ResponseSearchPageDto> partySearch(
            @ModelAttribute @Valid PartySearchCondDto partySearchCondDto,
            @RequestParam(value = "size", required = false, defaultValue = "5") Integer size,
            @RequestParam(value = "lastPartyId", required = false) Long lastPartyId
    ) {
        ResponseSearchPageDto partyPageTest = searchService.getPartyPage(partySearchCondDto, size, lastPartyId);
        return ResponseEntity.ok().body(partyPageTest);
    }

    @Operation(summary = "인기 검색어", description = "인기 검색어 TOP 10")
    @ApiResponse(responseCode = "200", description = "검색어 TOP 10 성공", content = @Content(schema = @Schema(implementation = ResponseRankingDto.class)))
    @GetMapping("/rank")
    public ResponseEntity<List<ResponseRankingDto>> searchRankList() {
        List<ResponseRankingDto> responseRankingDtoList = searchService.searchRankList();
        return ResponseEntity.ok().body(responseRankingDtoList);
    }
}
