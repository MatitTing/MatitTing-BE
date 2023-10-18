package com.kr.matitting.service;

import com.kr.matitting.constant.ChatRoomRole;
import com.kr.matitting.constant.ChatRoomType;
import com.kr.matitting.constant.PartyCategory;
import com.kr.matitting.constant.PartyJoinStatus;
import com.kr.matitting.constant.PartyStatus;
import com.kr.matitting.constant.Role;
import com.kr.matitting.dto.PartyCreateDto;
import com.kr.matitting.dto.PartyJoinDto;
import com.kr.matitting.entity.Party;
import com.kr.matitting.entity.PartyJoin;
import com.kr.matitting.entity.Team;
import com.kr.matitting.entity.User;
import com.kr.matitting.exception.party.PartyException;
import com.kr.matitting.exception.party.PartyExceptionType;
import com.kr.matitting.exception.partyjoin.PartyJoinException;
import com.kr.matitting.exception.partyjoin.PartyJoinExceptionType;
import com.kr.matitting.exception.user.UserException;
import com.kr.matitting.exception.user.UserExceptionType;
import com.kr.matitting.repository.ChatRoomRepository;
import com.kr.matitting.repository.PartyJoinRepository;
import com.kr.matitting.repository.PartyRepository;
import com.kr.matitting.repository.PartyTeamRepository;
import com.kr.matitting.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;


@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class PartyService {
    @Value("${cloud.aws.s3.url}")
    private String url;

    private final PartyJoinRepository partyJoinRepository;
    private final PartyTeamRepository teamRepository;
    private final PartyRepository partyRepository;
    private final UserRepository userRepository;
    private final MapService mapService;
    private final ChatRoomRepository chatRoomRepository;

    public ResponsePartyDto getPartyInfo(Long partyId) {
        Party party = partyRepository.findById(partyId).orElseThrow(() -> new PartyException(PartyExceptionType.NOT_FOUND_PARTY));
        ResponsePartyDto responsePartyDto = ResponsePartyDto.toDto(party);
        return responsePartyDto;
    }

    public void createParty(PartyCreateDto request) {
        log.info("=== createParty() start ===");



        Long user_id = request.getUser_id();
        User user = userRepository.findById(user_id).orElseThrow(() -> new UserException(UserExceptionType.NOT_FOUND_USER));

        // address 변환, deadline, thumbnail이 null일 경우 처리하는 로직 처리 후 생성
        Party party = createBasePartyBuilder(request, user);

        partyRepository.save(party);
    }

    private String getThumbnail(PartyCategory category, String thumbnail) {

        if (thumbnail == null) {
            switch (category) {
                case KOREAN -> thumbnail = url + "korean.jpeg";
                case WESTERN -> thumbnail = url + "western.jpeg";
                case CHINESE -> thumbnail = url + "chinese.jpeg";
                case JAPANESE -> thumbnail = url + "japanese.jpeg";
                case ETC -> thumbnail = url + "etc.jpeg";
            }
        }
        return thumbnail;
    }

    }

    public void deleteParty(Long partyId) {
        Party party = partyRepository.findById(partyId).orElseThrow(() -> new PartyException(PartyExceptionType.NOT_FOUND_PARTY));
        partyRepository.delete(party);
    }

    // address, deadline, thumbnail와 같이 변환이나 null인 경우 처리가 필요한 필드는 제외하고 나머지 필드는 빌더패턴으로 생성
    private Party createBasePartyBuilder(PartyCreateDto request, User user) {
        return Party.builder()

    }

    public void joinParty(PartyJoinDto partyJoinDto) {
        log.info("=== joinParty() start ===");


            log.error("=== JoinParty:Request Data is null ===");
            throw new PartyJoinException(PartyJoinExceptionType.NULL_POINT_PARTY_JOIN);
        }

        Party party = partyRepository.findById(partyJoinDto.partyId()).orElseThrow(() -> new PartyJoinException(PartyJoinExceptionType.NOT_FOUND_PARTY_JOIN));
        PartyJoin partyJoin = PartyJoin.builder().party(party).leaderId(partyJoinDto.leaderId()).userId(partyJoinDto.userId()).build();
        partyJoinRepository.save(partyJoin);
    }

    public String decideUser(PartyJoinDto partyJoinDto) {
        log.info("=== decideUser() start ===");

        if (!(partyJoinDto.status().get() == PartyJoinStatus.ACCEPT || partyJoinDto.status().get() == PartyJoinStatus.REFUSE)) {
            log.error("=== Party Join Status was requested incorrectly ===");
            throw new PartyJoinException(PartyJoinExceptionType.WRONG_STATUS);
        }

        PartyJoin findPartyJoin = partyJoinRepository.findByPartyIdAndLeaderIdAndUserId(

        partyJoinRepository.delete(findPartyJoin);

        if (partyJoinDto.status().get() == PartyJoinStatus.ACCEPT) {
            log.info("=== ACCEPT ===");
            User user = userRepository.findById(partyJoinDto.userId()).orElseThrow(() -> new UserException(UserExceptionType.NOT_FOUND_USER));
            Party party = partyRepository.findById(partyJoinDto.partyId()).orElseThrow(() -> new PartyException(PartyExceptionType.NOT_FOUND_PARTY));
            Team member = Team.builder().user(user).party(party).role(Role.VOLUNTEER).build();
            teamRepository.save(member);

            // 채팅방 추가 로직
            // 각 파티당 채팅방은 한 개만 만들어 져야 함
            // 예외 처리 추가 해야함
            ChatRoom chatRoom = chatRoomRepository.findByPartyIdAndChatRoomType(
                partyJoinDto.getPartyId(), ChatRoomType.GROUP).orElseThrow();

            chatRoom.addParticipant(chatRoom, user, ChatRoomRole.MEMBER);

            // 클라이언트측에서 구독을 해줘야 하기 때문에 리스폰스 값을 줘야 한다.
            

            return "Accept Request Completed";
        } else if (partyJoinDto.status().get() == PartyJoinStatus.REFUSE) {
            log.info("=== REFUSE ===");
            return "Refuse Request Completed";
        }
        return null;
    }

}
