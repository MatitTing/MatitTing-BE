package com.kr.matitting.controller;

import com.kr.matitting.dto.CreatePartyRequest;
import com.kr.matitting.dto.PartyJoinDto;
import com.kr.matitting.service.PartyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/party")
public class PartyController {
    private final PartyService partyService;

    // 파티 모집 글 생성
    @PostMapping
    public ResponseEntity createParty(
            @RequestBody @Valid CreatePartyRequest request
    ) {
        partyService.createParty(request);
        return ResponseEntity.status(HttpStatus.CREATED).body("파티 글이 생성되었습니다.");
    }

    //유저가 파티방에 참가를 요청하는 logic
    @PostMapping("/participation")
    public ResponseEntity JoinParty(PartyJoinDto partyJoinDto) throws Exception {
        partyService.joinParty(partyJoinDto);
        return ResponseEntity.ok().body("Success join request!");
    }

    //방장이 파티방에 대한 수락/거절을 하는 logic
    @PostMapping("/decision")
    public ResponseEntity AcceptRefuseParty(PartyJoinDto partyJoinDto) throws Exception {
        String result = partyService.decideUser(partyJoinDto);
        return ResponseEntity.ok().body(result);
    }

}