package com.kr.matitting.entity;

import com.kr.matitting.constant.PartyCategory;
import com.kr.matitting.constant.PartyGender;
import com.kr.matitting.constant.PartyStatus;
import com.kr.matitting.dto.CreatePartyRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "PARTY")
public class Party extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "party_id")
    private Long id;
    @Column(nullable = false, name = "title")
    private String title; // 파티 모집 제목
    @Column(nullable = false, name = "content", length = 500)
    private String content; // 파티 모집 글
    @Column(nullable = false, name = "menu")
    private String menu; // 메뉴
    @Column(nullable = false, name = "address")
    private String address; // 주소
    @Enumerated(EnumType.STRING)
    private PartyStatus status; // 파티 상태
    @Column(name = "deadline")
    private LocalDateTime deadline; // 파티 모집 시간
    @Column(nullable = false, name = "party_time")
    private LocalDateTime partyTime; // 파티 시작 시간
    @Column(nullable = false, name = "total_participant")
    private int totalParticipant; // 모집 인원
    @Column(name = "participant_count")
    @ColumnDefault("0")
    private int participantCount; // 참가자 수
    @Column(nullable = false, name = "category")
    @Enumerated(EnumType.STRING)
    private PartyCategory category; // 음식 카테고리
    @Column(nullable = false, name = "gender")
    @Enumerated(EnumType.STRING)
    private PartyGender gender; // 참여 가능 성별

    @Column(nullable = true, name = "thumbnail")
    private String thumbnail; //파티 썸네일
    @Column(name = "hit")
    @ColumnDefault("0")
    private int hit;
    @JoinColumn(nullable = false, name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    public static Party create(CreatePartyRequest request, User user, String address) {
        return Party.builder()
                .title(request.getPartyTitle())
                .content(request.getPartyContent())
                .menu("메뉴")
                .address(address)
                .status(PartyStatus.ON)
                .deadline(request.getPartyDeadline())
                .partyTime(request.getPartyTime())
                .totalParticipant(request.getTotalParticipant())
                .user(user)
                .gender(request.getGender())
                .category(request.getCategory())
                .build();
    }
}