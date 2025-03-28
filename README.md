  # 🚀 MOYFIT - 거리 기반 운동 파트너 매칭

## 📌 프로젝트 소개

MOYFIT은 **카카오 API**를 활용한 거리 기반 매칭 시스템을 통해, 가장 가까운 **운동 파트너 및 모임**을 추천하는 서비스입니다.


- 운동 유형 및 목표를 설정하면, **비슷한 목표를 가진 사용자와 자동 매칭**
- 프로필에서 지역을 설정하면 **거리 기반 운동 파트너 추천**
- **매칭 요청 & 수락 시스템**을 통해 원하는 파트너와 연결
- **매칭 후 사용자 간 평점 평가 및 활동 랭킹 시스템 제공**
- **게시글 작성 및 후기 공유**로 운동 정보를 교류
- **1:1 메신저 기능**으로 원활한 소통 가능

> **🎯 운동을 더 즐겁고, 더 효율적으로!**  
> 함께 성장하는 운동 커뮤니티를 만드는 것이 목표입니다. 🏆
> 



---

## 🗓️ 개발 기간 및 인원
- ⏳ **개발 기간**: 25.2.14 ~ 25.3.5 (3주)
- 👨‍💻 **개발 인원**: 3명

---

## ⚙️ 개발 환경
- **언어/프레임워크**: Java, Python, SQL / Spring, MyBatis
- **DB/API**: MySQL / Kakao Maps API
- **도구**: VS Code, STS, Git, Notion, Apache Tomcat

---

## ER 다이어그램
<img src="https://github.com/user-attachments/assets/141baa7d-e93f-410f-ab4f-e23c1d29e9d8" width="700" style="max-width:100%">

---

## 유스케이스 다이어그램
<img src="https://github.com/user-attachments/assets/d56c440f-1bbd-475b-9d6a-7d0e18b65f29" width="700" style="max-width:100%">

---

## 시스템 아키텍처
<img src="https://github.com/user-attachments/assets/e4b06d45-8300-48bb-8f84-a05da55f3531" width="700" style="max-width:100%">


## 🏆 주요 기능

### 📌 회원 관리 시스템
- 회원가입 및 로그인
- 프로필 수정 및 삭제
- 지역 설정 기능 제공

### 📌 매칭 시스템
- 거리 기반 운동 파트너 & 모임 매칭

### 📌 알림 시스템
- 매칭 요청 & 수락 여부 실시간 알림
- 매칭 상태 업데이트 즉시 확인

### 📌 랭킹 & 평가 시스템
- 파트너 및 모임 활동 랭킹 시스템
- 사용자 별점 평가 기능 제공

### 📌 게시판 & 메신저 시스템
- 게시글 및 후기 작성
- 운동 정보 & 지식 공유 공간
- 1:1 메신저 기능 제공

---

## 👥 팀원 역할 분담

### 🔹 팀장 | 문선웅
- 회원가입 및 프로필 관리 (입력/삭제)
- 운동 파트너 및 모임 매칭 기능 개발
- 사용자 간 거리 계산 로직 구현

### 🔹 팀원 | 문채은
- 데이터 크롤링 및 머신러닝(ML) 적용
- 데이터베이스(DB) 설계 및 관리
- 전체적인 Front-end 개발
- 별점 평가 및 랭킹 시스템 구축
- 알림 및 메신저 기능 개발

### 🔹 팀원 | 박태영
- 로그인 및 로그아웃 기능 개발
- 회원가입 시 ID 및 닉네임 중복 체크
- 커뮤니티 기능 구현
- 사진 업로드 및 출력 로직 개선

---

## 🚀 트러블 슈팅

### 🔹 1. 거리 계산 이슈
- **문제**: 카카오 API를 이용하여 주소를 위도·경도로 변환하여 저장했으나, 거리 계산 시 문제가 발생
- **해결**: 위도·경도를 불러와 `getDistance` 메소드를 사용하여 거리 계산을 정확하게 수행

### 🔹 2. 사진 출력 문제
- **문제**: 게시글 작성 시 사진 첨부는 되었으나 페이지에서 출력되지 않는 이슈 발생
- **해결**: 사진 출력 방식을 수정하여 정상적으로 이미지가 표시되도록 코드 재작성

### 🔹 3. CSS 구분 문제
- **문제**: CSS가 구분되지 않아 개발 시 유지보수와 협업이 어려움
- **해결**: 공통 레이아웃과 파트별 스타일을 분리하여 유지보수가 쉬운 구조로 개선 → 코드 간결화 & 효율적인 개발 환경 조성

---

## 홈페이지 소개
### 메인페이지
<img src="https://github.com/user-attachments/assets/73fedfd8-8797-499d-a941-274988f7a904" width="1000" style="max-width:100%">

---

## 🌟 파트너 매칭 페이지


---

## 🌟 파트너 상세 페이지 및 알림 (요청 & 수락)


<div style="display: flex; justify-content: center; align-items: center; gap: 20px;">
  <img src="https://github.com/user-attachments/assets/8f5ad128-94fb-434a-90e8-8f79f5caf09d" width="300">
  <img src="https://github.com/user-attachments/assets/6e839f9b-b5f3-4e1e-8b8f-e9f173f2a06a" width="300">
</div>

---

## 🌟 메시지


<div style="display: flex; justify-content: center; align-items: center; gap: 20px;">
  <img src="https://github.com/user-attachments/assets/e354f5c9-751f-4ef1-b5fd-50bc71479510" width="300">
  <img src="https://github.com/user-attachments/assets/21bd5598-aaec-413e-8b4b-361dc6149fa2" width="300">  
</div>

---

## 🏆 랭킹 & 평점 시스템 페이지

<div style="display: flex; justify-content: center; align-items: center; gap: 20px;">
  <img src="https://github.com/user-attachments/assets/40a29317-4a2e-43c8-9370-97b8408459af" width="300">
  <img src="https://github.com/user-attachments/assets/89c65159-bc23-4a0c-8333-db88c8615797" width="300">
</div>

---

## 🌟커뮤니티 페이지
<div style="display: flex; justify-content: center; align-items: center; gap: 20px;">
  <img src="https://github.com/user-attachments/assets/308ca79b-b153-40e5-ba08-429671abeedf" width="700" style="max-width:100%">
</div>


---


## 🎥 시연 영상
<video src="https://github.com/user-attachments/assets/6fadef2b-94c4-47c6-9ae2-c67e2dc4ec34">

