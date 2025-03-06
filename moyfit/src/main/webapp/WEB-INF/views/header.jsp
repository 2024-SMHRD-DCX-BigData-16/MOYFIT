<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<head>
<title>MOYFIT</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/main.css">
<script
	src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
</head>
<!-- 헤더 영역 -->
<header id="header" class="alt">
	<nav>
		<div>
			<a href="home" class="logo"> <span>MOYFIT</span>
			</a>
		</div>
		<div id="navbar-menu">
			<ul class="navbar-nav">
				<li><a href="home">Home</a></li>
				<li><a href="#" onclick="checkLogin('partner')">파트너 매칭</a></li>
				<li><a href="#" onclick="checkLogin('party')">모임 매칭</a></li>
				<li><a href="#" onclick="checkLogin('board')">게시판</a></li>
				<li><a href="#" onclick="checkLogin('rank')">랭킹</a></li>
				<li><a href="#" onclick="checkLogin('rating')">평가하기</a></li>
				
			</ul>
		</div>
	</nav>
</header>

<style>
    /* 전체 헤더 스타일 */
    .header-container {
        position: absolute; /* 페이지 최상단에 고정 */
        top: 0;
        left: 0;
        width: 95%;
        display: flex;
        justify-content: space-between; /* 양쪽 정렬 */
        align-items: center;       
        padding: 10px 20px;
    }

    /* 왼쪽 상단 (알림 & 메시지) */
    .left-icons {
        display: flex;
        gap: 15px;
    }

    /* 오른쪽 상단 (로그인/회원가입/마이/로그아웃) */
    .right-buttons {
        display: flex;
        gap: 15px;
    }

    /* 버튼 스타일 */
    .header-container a {
        text-decoration: none;
        color: black;
        font-size: 16px;
        font-weight: bold;
    }
    .welcome-text {
    font-weight: bold;  /* 글씨 굵게 */
    color: blue;  /* 글씨 색상 파란색 */
    text-align: right; /* 오른쪽 정렬 (필요시 조정 가능) */
}
    
    
</style>

<div class="header-container">
    <!-- 왼쪽: 알림 & 메시지 -->
    <div class="left-icons">
        <%
        if (session.getAttribute("loginUser") != null) {
        %>
        <a style="margin-left: 35px;" href="#" onclick="checkLogin('match')">🔔 상태</a>
        <a href="#" onclick="checkLogin('message')">💬 메시지</a>
        <%
        }%>
    </div>

    <!-- 오른쪽: 로그인 여부에 따른 버튼 -->
    <div class="right-buttons">
        <%
        if (session.getAttribute("loginUser") == null) {
        %>
        <a href="login">로그인</a>
        <a href="memberJoin">회원가입</a>
        <%
        } else {
        %>
		<div class="right-buttons" style="display: flex; flex-direction: column; align-items: flex-end;">
		    <div class="buttons" style="display: flex; gap: 10px;">
		        <a href="partner_profil">마이페이지</a>
		        <a href="logout">로그아웃</a>
		    </div>
		    <div class="welcome-text" style="font-size:20px;">${sessionScope.loginUser.m_id}님 반갑습니다!</div>
		</div>

        <%
        }
        %>
    </div>
</div>

<script>
	function checkLogin(targetPage) {
		var isLoggedIn =
<%=(session.getAttribute("loginUser") != null) ? "true" : "false"%>
	;

		if (!isLoggedIn) {
			alert("로그인해주세요!");
			location.href = "home"; // 로그인 안 된 경우 home으로 이동
		} else {
			location.href = targetPage; // 로그인 된 경우 해당 페이지로 이동
		}
	}
</script>