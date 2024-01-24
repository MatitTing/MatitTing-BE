package com.kr.matitting.service;

import com.kr.matitting.constant.*;
import com.kr.matitting.entity.Party;
import com.kr.matitting.entity.User;
import com.kr.matitting.repository.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.UUID;

@Transactional
@SpringBootTest
class ChatServiceTest {
    @Autowired
    ChatRoomRepository chatRoomRepository;
    @Autowired
    ChatRepository chatRepository;
    @Autowired
    ChatUserRepository chatUserRepository;
    @Autowired
    ChatService chatService;
    @PersistenceContext
    EntityManager em;
    @Autowired
    UserRepository userRepository;
    @Autowired
    PartyRepository partyRepository;

    User user;
    Party party;

    @BeforeEach
    void beforeEach() {
        user = createStubUser();
        party = createStubParty(user);
        flushAndClearPersistence();
        System.out.println("==============================");
    }

    private Party createStubParty(User user) {
        Random r = new Random();
        Party party = Party.builder()
                .partyTitle(createUUID().substring(0, 30))
                .partyContent(createUUID())
                .partyTime(LocalDateTime.now().plusDays(r.nextLong(20)))
                .address(createUUID())
                .hit(r.nextInt(10))
                .gender(Gender.ALL)
                .latitude(r.nextDouble(100))
                .longitude(r.nextDouble(100))
                .category(PartyCategory.ETC)
                .menu(createUUID())
                .thumbnail(createUUID())
                .age(PartyAge.ALL)
                .user(user)
                .totalParticipant(r.nextInt(10))
                .build();
        return partyRepository.save(party);
    }

    private User createStubUser() {
        Random random = new Random();
        User user = User.builder()
                .age(random.nextInt(100))
                .gender(Gender.ALL)
                .imgUrl(null)
                .socialId(createUUID().substring(0, 30))
                .email(createUUID().substring(0, 20))
                .nickname(createUUID().substring(0, 20))
                .socialType(SocialType.NAVER)
                .role(Role.USER)
                .build();
        return userRepository.save(user);
    }

    private String createUUID() {
        return UUID.randomUUID().toString();
    }

    private void flushAndClearPersistence() {
        em.flush();
        em.clear();
    }
}