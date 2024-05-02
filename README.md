![matitting](https://github.com/MatitTing/MatitTing-BE/assets/55770741/c20b6e10-1542-406c-9462-0fb0bf45dbae)

---
## 혼밥러들을 위한 식사 매칭 플랫폼
    
    맛있팅은 외로운 혼밥러들을 위해 식사 메이트를 구해 즐거운 식사 자리를 가질 수 있도록 도와주는 웹 서비스입니다.
    
    개인의 거주지에서 가까운 파티부터 시작하여 먹고싶은 메뉴, 가고싶은 가게 등을 고려하여 식사 메이트를 매칭할 수 있습니다.
    
    방장은 파티 신청한 사람에 대해 수락/거절 여부를 결정할 수 있고, 파티에 참여하는 파티원들은 채팅방에서 함께 식사할 파티원들과 채팅방에서 대화를 나눌 수 있습니다.
    
    파티를 생성한 방장은 즐거운 식사 자리를 마친 후, 팀원들로부터 후기를 전달받게 되고, 사람들은 방장의 후기를 살펴보며 본인과 맞는 사람들을 선정하여 파티에 참여 신청을 할 수 있습니다.
    
### 개발 기간
- 2023.10 ~ 2024.05

## 디자인
![image](https://github.com/MatitTing/FE/assets/90169703/69e3a033-edfc-4fbd-bcf3-f293d8888163)

[피그마 링크](https://www.figma.com/file/SdRnUqQw1HCGpNNCflXCY7/%EB%A7%9B%EC%9E%87%ED%8C%85?type=design&node-id=740%3A121&mode=design&t=VlO5bikHiw1heSzy-1)

## 팀 구성

<프론트 엔드>

<img src="https://user-images.githubusercontent.com/90169703/152270453-d84bbe12-ce24-4b7a-94a2-319125ee3f11.jpg" width="50px" height="50px"/> 강민수 &nbsp; [기술블로그](https://velog.io/@minsu8834), [깃허브](https://github.com/minchodang)
</br>
</br>
<img src="https://avatars.githubusercontent.com/u/86722178?v=4" width="50px" height="50px"/> 고현석 &nbsp; [깃허브](https://github.com/sukpo61) 
</br>
</br>
<img src="https://avatars.githubusercontent.com/u/100080949?v=4" width="50px" height="50px"/> 김소연 &nbsp; [깃허브](https://github.com/xo-yeon) 

<br>

<백엔드>

<img src="https://avatars.githubusercontent.com/u/55770741?v=4" width="50px" height="50px"/> 박종두 &nbsp; [깃허브](https://github.com/dujong)
</br>
</br>
<img src="https://avatars.githubusercontent.com/u/115761352?v=4" width="50px" height="50px"/> 이수빈 &nbsp; [깃허브](https://github.com/esubine) 
</br>

## 기술 스택
### Development
<img src="https://img.shields.io/badge/java-007396?style=for-the-badge&logo=OpenJDK&logoColor=white"> <img src="https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=Spring&logoColor=white"> <img src="https://img.shields.io/badge/Spring%20Boot-6DB33F?style=for-the-badge&logo=Spring%20Boot&logoColor=white"/> <img src="https://img.shields.io/badge/Spring Security-6DB33F?style=for-the-badge&logo=Spring Security&logoColor=white">
<img src="https://img.shields.io/badge/JUnit5-25A162?style=for-the-badge&logo=JUnit5&logoColor=white">

### DB
<img src="https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=MySQL&logoColor=white"> <img src="https://img.shields.io/badge/Redis-DC382D?style=for-the-badge&logo=Redis&logoColor=white"> <img src="https://img.shields.io/badge/Amazon%20S3-569A31?style=for-the-badge&logo=Amazon%20S3&logoColor=white">

### CI/CD
<img src="https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white">  <img src="https://img.shields.io/badge/swagger-%85EA2D.svg?style=for-the-badge&logo=swagger&logoColor=white">

### Environment
<img src="https://img.shields.io/badge/IntellijIDEA-000000?style=for-the-badge&logo=Redis&logoColor=white"> <img src="https://img.shields.io/badge/Github-181717?style=for-the-badge&logo=Redis&logoColor=white"> <img src="https://img.shields.io/badge/Sourcetree-0052CC?style=for-the-badge&logo=Redis&logoColor=white">

## API 명세서(Swagger 포함)
- &nbsp; [API 명세서](https://productive-ground-f7e.notion.site/1e39b0ec6c5641bd88295c8e2eb01814?v=f9d300c0cfee405cafa19fac78590ea0&pvs=4) 

## 개발 기능
### 이수빈 개발자
- 파티 생성 기능(카카오맵 API)
    - 카카오 지도 API를 활용하여 좌표값에 따른 주소를 호출하는 로직을 구현
- 이미지 s3 업로드
    - 입력한 이미지를 AWS S3에 업로드하고 데이터베이스에 저장하는 기능을 개발했습니다.
- 메인 페이지 조회 기능
    - 위치기반 서비스
    - 두 지점 간의 거리를 계산하는 Haversine 공식을 사용하여 사용자의 위치정보와 파티글에 저장된 파티 장소 위치 정보를 비교했습니다. 사용자 위치에 가까운 순으로 파티글을 조회할 수 있도록 구현
    - ~~메인페이지 조회 시 No-offset 기반 페이지네이션으로 구현하여 무한 스크롤을 통해 사용자 경험을 개선하였습니다.~~
- 파티 일정시간 경과 시 파티 상태 변경 스케줄러
    - **@Scheduled 사용을 통한 파티 상태 변경 작업 주기적 자동 실행**
    - 파티 시작시간으로부터 일정시간이 지난 경우 파티 상태를 완료로 변경하는 작업을 스케줄러를 통해 cron 표현식에 따라 상태 변경 메서드를 주기적, 자동적으로 실행되도록 하였습니다.
- 채팅 메시지 전송(웹 소켓 연결)
    - Spring boot의 **`@EnableWebSocketMessageBroker`** 어노테이션을 사용하여 웹소켓 메시지 브로커를 설정했습니다. 이를 통해 클라이언트와 서버 간의 양방향 통신이 가능하도록 하였습니다.
- 채팅 기록 조회
- 채팅방 유저 강퇴
- 내 채팅방 리스트 조회
- 채팅방 내 유저정보 조회

### 박종두 개발자
- 소셜 로그인 기능
    - 네이버, 카카오 두 가지 소셜 로그인 서비스를 제공
    
- jwt 토큰 재발급 기능
    - 인증/인가는 spring security+ jwt로 유지하며, accessToken이 expired 되었을 때 refreshToken으로 재발급 서비스 제공, refreshToken은 DB가 아닌 redis cache로 관리
- 마이 프로필 조회 기능
- 마이 프로필 업데이트 기능
- 파티 업데이트 기능
- 파티방 조회 기능
- 파티 참가 신청 기능
    - 특정 파티방에 참가 요청을 보내는 기능   
- 파티 참가 수락/거절 기능
  - 파티방의 방장이 받은 참가 요청에 대해서 수락 or 거절을 결정하는 기능
- 내 파티 현황 리스트 조회 기능
- 내 파티 신청 현황 리스트 조회 기능
- 파티방 검색 기능
  - 사용자가 관심 있는 키워드를 활용하여 파티방 제목, 내용, 주소 등으로 검색할 수 있는 기능, query dsl을 활용하여 동적으로 쿼리문을 활용하였습니다.
- 인기검색어 기능
    - 사용자들이 실시간으로 많이 검색한 TOP10을 소개해주는 기능, 사용자들의 검색은 실시간으로 많이 활용되는 기능으로써 발생하는 데이터가 단순하다는 특징을 가지고 있다. 이런 특징을 활용하여 redis의 cache 기능을 활용해서 서버의 부하를 감소시키고 빠른 처리성능을 이점으로 가져갔다.
- 알림 기능
  - 파티방에 참가요청을 보내거나, 보낸 참가요청이 결정났을 때 사용자들에게 알림을 보내주는 서비스 sse 통신을 활용하여 개발의 용이성과 실시간성에 중점을 두었다.

## 아키텍처
- &nbsp; [ERD](https://app.quickdatabasediagrams.com/#/d/Rgmcl4)
</br>

![Untitled (3)](https://github.com/MatitTing/FE/assets/55770741/420551ad-785b-4b9f-a464-ecfa4f79db80)

## 삽질 리스트
<details>
  <summary><b>[소셜 로그인의 방식!! (only backend → front+backend)]</b></summary>
  <div markdown="1">
    <ul>
      <li>소셜 로그인의 방식에는 2가지가 있다. Front + Back, Back 맛있팅 프로젝트의 소셜 로그인 1차 버전은 Front에서는 소셜 로그인 아이콘만 띄워주고 클릭 시 Backend로 요청을 보내서 Backend ↔ Social Server의 통신을 통해서 code, token, userinfo를 가져오는 로직으로 구성되었다. 이 때 oauth-client 라이브러리를 활용하여 코드를 구성하였다. 중간에 Front 분들에게로부터 로직 변경 요청이 들어왔고 나는 이를 자세하게 조사하기 시작하였다. 조사 결과 Only Backend 로직은 Front + Back 로직에 비해서 치명적인 보안적 단점을 가지고 있었다. 이는 소셜 로그인 이후 사용자에게 지급되는 accessToken, refreshToken이 쿼리 파라미터로 리다이렉트 되게 되고, 이는 보안적인 측명에서 치명적인 오류를 발생시킬 수 있다. 그래서 맛있팅 프로젝트는 Front에서 Social Server와 통신하여 인증 code를 획득하고 이를 Backend로 전송하여 token, userinfo를 가져오는 로직으로 리팩토링 하였다.</li>
    </ul>
  </div>
</details>

<details>
  <summary>[CORS 설정 시 exposedHeader 값 직접 명시!]</summary>
  <div markdown="2">
    <img src="https://github.com/MatitTing/FE/assets/55770741/b9d99beb-c31d-4990-83bb-a17b9ebe4a2e" alt="코드"/></br>
    <ul>
      <li>프로젝트를 진행하면서 CORS 설정에서 setExposedHeaders를 *로 설정함으로써 모든 값에 대해서 접근 제한을 해제하였다고 생각하였지만, FrontEnd분들께서 테스트 결과 Authorization, Authorization-Refresh에 접근이 제한되고 있다고 하셨다. 여러가지 카페나 블로그를 검색해보며 코드를 살펴본 결과 모두 공통점을 가지고 있었다. *을 쓰기보다는 접근 제한을 해제하고 싶은 Header값을 직접적으로 명시하고 있었다. 그래서 우리의 CORS 설정 또한 아래처럼 수정 후 정상적으로 작동하였다.</li>
    </ul>
    <img src="https://github.com/MatitTing/FE/assets/55770741/5abed949-96d9-411d-aa22-bb0ae7d93774" alt="코드">
  </div>
</details>

<details>
  <summary>[jpa delete시 삭제가 안되는 이슈!]</summary>
  <div markdown="3">
    <ul>
      <li>delete 쿼리는 flush 이후에 나가는데 partyJoin을 불러올 때 Party도 꺼내오기 때문에 영속성 컨텍스트가 유지되어서 delete문이 나가지 않는다. 이를 해결하기 위해서 deleteById를 사용하여 삭제하였다.</li>
    </ul>
  </div>
</details>

<details>
  <summary>[sse 통신을 활용한 알림 기능 시 연결이 안됐을 때 알림 miss]</summary>
  <div markdown="4">
    <ul>
      <li>알림 서비스를 개발할 때의 이야기이다. 나는 서치를 통해서 대략적인 알림 코드들을 살펴보며 흐름을 파악하고 중점을 파악하기 위해서 노력하였다. 이후 코드를 작성해서 테스트 해봄으로써 결과 값을 확인하려고 했는데 2가지 문제를 발견하였다.</li>
      <ui>
        <li>sse 연결이 되지 않았을 때 miss 될 수 있는 알림들을 cache에 저장할 때 이전에 생성된 emitter id를 사용하여 cache key값에 저장하고 있었다. 재 연결이 되었을 때 Last-Event-Id가 있을 때 해당 Id 이후에 알림온 것들을 재전송 해줘야 하는데 이전에 생성된 emitter id를 사용하고 있어 무조건 시간이 이전으로 측정되어 다 무시되고 있었다.</li>
        <li>sse 연결이 되지 않았을 때 send시 error가 발생되면 emitter를 삭제한다! 그런데 2개 이상의 알림이 쌓였을 때 첫 번째 알림전송 시 sse 연결이 안되어 있어 발생하는 error로 emitter가 삭제되고 두 번째 알림은 emitter가 없어 cache에도 저장되지 못하여 사라지고 만다.</li>
      </ui>
      <li>이 2가지 이슈에 대해서는 모든 코드들이 동일한 코드로 대답하고 있다!! 하지만 내가 테스트를 진행한 결과 sse 연결이 끊어졌을 때 발생하는 알림들에 대해서 보장을 못하고 있다..!! 이는 조금 더 조사 후 리팩토링이 필요하다고 생각한다.</li>
    </ul>
  </div>
</details>


<details>
  <summary><b>docker 멀티 플랫폼 배포 이슈!</b></summary>
  <div markdown="5">
    <ul>
    <li> 프론트분들에게 API Server Test를 위해서 docker로 공유할 때 윈도우 환경과 MAC환경을 동시에 고려해야 했다. 처음에는 윈도우 버전, MAC 버전 두 가지를 각각 따로 올려야 하나 했지만 buildx를 통해서 멀티플랫폼으로 빌드하여 docker hub에 공유할 수 있다는 사실을 알아냈다.</li>
    </ul>
  </div>
</details>

<details>
  <summary><b>docker 배포를 위한 환경변수 값 설정</b></summary>
  <div markdown="6">
    <ul>
    <li> github에 올릴 수 없는 spring application.yml에서의 환경변수 값들은 .env파일로 관리하며 외부 환경변수에서의 값 주입으로 docker 배포를 진행하였다.</li>
    </ul>
  </div>
</details>


<details>
  <summary><b>CORS 에러 설정</b></summary>
  <div markdown="7">
    <ul>
    <li> CorsConfigurationSource를 SecurityConfig에서 빈으로 등록하고 .cors(withDefaults());으로 cors 설정을 활성화하여 CorsConfigurationSource에 해당하는 부분을 허용하도록 했다.</li>
    </ul>
  </div>
</details>

<details>
  <summary><b>Request로 시간을 입력받았을때 포맷 설정</b></summary>
  <div markdown="1">
    <ul>
    <li> @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") 으로 요청 파라미터 형식을 설정하였다. 해당 포맷 설정이 없을 시 “MethodArgumentNotValidException”이 발생한다.</li>
    </ul>
  </div>
</details>

## 프로젝트 진행 기록

- [프로젝트 전체 회의록](https://www.notion.so/07fa8acfa5304a6c93f07561f4a90bc7?pvs=21)
- [프로젝트 전체 회의록2](https://scalloped-yew-edd.notion.site/a80cd087ce294bde88b4689ec5423df3?v=2ecc91a065294a3daa3ce742a2fcd7d8&pvs=4)
- [프로젝트 BE 회의록](https://carnelian-astronomy-233.notion.site/a6c4e632b4f1483a9db8fecf9b819d69?v=651ec337b1b64c49988bb3c78880c745&pvs=4)
- [Git Commit Message Convention](https://www.notion.so/Git-Commit-Message-Convention-ab30907d00d741c4807c00bd808b282c?pvs=21)
- [BE 메소드](https://productive-ground-f7e.notion.site/3d7c424248644f4c8e434a2f39814d9a?v=7d121e11a9e64b55acfd4f8a6a5a714b&pvs=4)
- [ENUM 설명](https://productive-ground-f7e.notion.site/ENUM-a406235f21764eefa6226f128a76c43b?pvs=4)
- [Docker Version 관리](https://productive-ground-f7e.notion.site/Docker-Version-61a15321fcd447599b867b6989b50f19?pvs=4)
