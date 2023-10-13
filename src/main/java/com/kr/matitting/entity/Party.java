package com.kr.matitting.entity;

import com.kr.matitting.constant.PartyAge;
import com.kr.matitting.constant.PartyGender;
import com.kr.matitting.constant.PartyStatus;
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
    private String partyTitle; // 파티 모집 제목
    @Column(nullable = false, name = "content", length = 500)
    private String partyContent; // 파티 모집 글
    @Column(nullable = false, name = "address")
    private String address; // 주소
    @Column(name = "longitude")
    private String longitude;
    @Column(name = "latitude")
    private String latitude;
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
    @Column(nullable = false, name = "gender")
    @Enumerated(EnumType.STRING)
    private PartyGender gender; // 참여 가능 성별
    @Column(nullable = false, name = "age")
    @Enumerated(EnumType.STRING)
    private PartyAge age; // 연령대
    @Column(name = "hit")
    @ColumnDefault("0")
    private int hit;
    @JoinColumn(nullable = false, name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
    @JoinColumn(nullable = false, name = "menu_id")
    @OneToOne(fetch = FetchType.LAZY)
    private Menu menu;

}