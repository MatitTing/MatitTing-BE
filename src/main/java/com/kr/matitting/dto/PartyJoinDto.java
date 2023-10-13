package com.kr.matitting.dto;

import com.kr.matitting.constant.PartyJoinStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PartyJoinDto {
    private Long partyId;

    private Long leaderId;
    private Long userId;
    private PartyJoinStatus status;
    public PartyJoinDto() {
        this.status = PartyJoinStatus.WAIT;
    }

    public PartyJoinDto(Long partyId, Long leaderId, Long userId) {
        this.partyId = partyId;
        this.leaderId = leaderId;
        this.userId = userId;
        this.status = PartyJoinStatus.WAIT;
    }

    public void Accept() {
        this.status = PartyJoinStatus.ACCEPT;
    }
    public void Refuse() {
        this.status = PartyJoinStatus.REFUSE;
    }
}