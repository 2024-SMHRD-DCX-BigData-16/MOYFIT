<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>MOYFIT - 거리 기반 운동 파트너 매칭</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
            padding: 20px;
            line-height: 1.6;
        }
        h1, h2, h3 {
            color: #2c3e50;
        }
        ul {
            list-style-type: none;
            padding: 0;
        }
        li {
            background: #ecf0f1;
            margin: 5px 0;
            padding: 10px;
            border-radius: 5px;
        }
        .section {
            margin-bottom: 30px;
        }
    </style>
</head>
<body>

    <h1>🚀 MOYFIT - 거리 기반 운동 파트너 매칭</h1>

    <div class="section">
        <h2>📌 프로젝트 소개</h2>
        <p>MOYFIT은 <strong>카카오 API</strong>를 활용한 거리 기반 매칭 시스템을 통해, 가장 가까운 <strong>운동 파트너 및 모임</strong>을 추천하는 서비스입니다.</p>
        <ul>
            <li>운동 유형 및 목표 설정 시 비슷한 목표를 가진 사용자와 자동 매칭</li>
            <li>프로필 지역 설정 후 거리 기반 운동 파트너 추천</li>
            <li>매칭 요청 및 수락 시스템을 통한 연결</li>
            <li>사용자 간 평점 평가 및 활동 랭킹 시스템 제공</li>
            <li>게시글 작성 및 후기 공유를 통한 운동 정보 교류</li>
            <li>1:1 메신저 기능으로 원활한 소통 가능</li>
        </ul>
        <p><strong>운동을 더욱 즐겁고, 효율적으로!</strong> 함께 성장하는 운동 커뮤니티를 목표로 합니다. 🏆</p>
    </div>

    <div class="section">
        <h2>📅 개발 기간 및 인원</h2>
        <ul>
            <li>⏳ 개발 기간: 25.2.14 ~ 25.3.5 (3주)</li>
            <li>👨‍💻 개발 인원: 3명</li>
        </ul>
    </div>

    <div class="section">
        <h2>⚙️ 개발 환경</h2>
        <ul>
            <li>언어/프레임워크: Java, Python, SQL / Spring, MyBatis</li>
            <li>DB/API: MySQL / Kakao Maps API</li>
            <li>도구: VS Code, STS, Git, Notion, Apache Tomcat</li>
        </ul>
    </div>

    <div class="section">
        <h2>🏆 주요 기능</h2>
        
        <h3>📌 회원 관리 시스템</h3>
        <ul>
            <li>회원가입 및 로그인</li>
            <li>프로필 수정 및 삭제</li>
            <li>지역 설정 기능 제공</li>
        </ul>

        <h3>📌 매칭 시스템</h3>
        <ul>
            <li>거리 기반 운동 파트너 & 모임 매칭</li>
        </ul>

        <h3>📌 알림 시스템</h3>
        <ul>
            <li>매칭 요청 & 수락 여부 실시간 알림</li>
            <li>매칭 상태 업데이트 즉시 확인</li>
        </ul>

        <h3>📌 랭킹 & 평가 시스템</h3>
        <ul>
            <li>파트너 및 모임 활동 랭킹 시스템</li>
            <li>사용자 별점 평가 기능 제공</li>
        </ul>

        <h3>📌 게시판 & 메신저 시스템</h3>
        <ul>
            <li>게시글 및 후기 작성</li>
            <li>운동 정보 & 지식 공유 공간</li>
            <li>1:1 메신저 기능 제공</li>
        </ul>
    </div>

    <div class="section">
        <h2>👥 팀원 역할 분담</h2>
        <h3>🔹 팀장 | 문선웅</h3>
        <ul>
            <li>회원가입 및 프로필 관리 (입력/삭제)</li>
            <li>운동 파트너 및 모임 매칭 기능 개발</li>
            <li>사용자 간 거리 계산 로직 구현</li>
        </ul>

        <h3>🔹 팀원 | 문채은</h3>
        <ul>
            <li>데이터 크롤링 및 머신러닝(ML) 적용</li>
            <li>데이터베이스(DB) 설계 및 관리</li>
            <li>전체적인 Front-end 개발</li>
            <li>별점 평가 및 랭킹 시스템 구축</li>
            <li>알림 및 메신저 기능 개발</li>
        </ul>

        <h3>🔹 팀원 | 박태영</h3>
        <ul>
            <li>로그인 및 로그아웃 기능 개발</li>
            <li>회원가입 시 ID 및 닉네임 중복 체크</li>
            <li>커뮤니티 기능 구현</li>
            <li>사진 업로드 및 출력 로직 개선</li>
        </ul>
    </div>

    <div class="section">
        <h2>🚀 트러블 슈팅</h2>

        <h3>🔹 거리 계산 이슈</h3>
        <ul>
            <li><strong>문제:</strong> 카카오 API를 이용하여 주소를 위도·경도로 변환하여 저장했으나, 거리 계산 시 문제가 발생</li>
            <li><strong>해결:</strong> 위도·경도를 불러와 <code>getDistance</code> 메소드를 사용하여 거리 계산을 정확하게 수행</li>
        </ul>

        <h3>🔹 사진 출력 문제</h3>
        <ul>
            <li><strong>문제:</strong> 게시글 작성 시 사진 첨부는 되었으나 페이지에서 출력되지 않는 이슈 발생</li>
            <li><strong>해결:</strong> 사진 출력 방식을 수정하여 정상적으로 이미지가 표시되도록 코드 재작성</li>
        </ul>

        <h3>🔹 CSS 구분 문제</h3>
        <ul>
            <li><strong>문제:</strong> CSS가 구분되지 않아 개발 시 유지보수와 협업이 어려움</li>
            <li><strong>해결:</strong> 공통 레이아웃과 파트별 스타일을 분리하여 유지보수가 쉬운 구조로 개선</li>
        </ul>
    </div>

    <div class="section">
        <h2>🎥 시연 영상</h2>
        <p>📌 (추후 추가 예정)</p>
    </div>

</body>
</html>
