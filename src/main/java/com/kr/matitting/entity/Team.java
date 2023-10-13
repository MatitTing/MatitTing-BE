package com.kr.matitting.entity;

import com.kr.matitting.constant.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_id")
    private Long id; //식사팀 id

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user; //식사팀 user

    @ManyToOne
    @JoinColumn(name = "party_id")
    private Party party; //파티 id

    @Enumerated(EnumType.STRING)
    private Role role; //방장 or 참여자
}