<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<title>MOYFIT</title>
 <style>
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }
        .main-container {
            display: flex;
            max-width: 250px;
            background: white;
            border-radius: 10px;
            box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.1);
            padding: 15px;
        }
        .left_content {
            width: 200px;
            background-color: #f8f9fa;
            padding: 10px;
            border-right: 1px solid #ddd;
        }
        .title_box {
            font-size: 20px;
            font-weight: bold;
            color: white;
            text-align: center;
            padding: 12px;
            margin-bottom: 10px;
            background-color: black;
            border-radius: 5px;
        }
        .left_menu {
            list-style: none;
            padding: 0;
            margin: 0;
        }
        .left_menu li {
            margin: 5px 0;
            font-weight: bold;
            background: #e9ecef;
            padding: 10px;
            border-radius: 5px;
            cursor: pointer;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }
        .left_menu li:hover {
            background: #dee2e6;
        }
        .left_menu li a {
            text-decoration: none;
            font-weight: bold;
            color: #007bff;
            display: block;
            width: 100%;
        }
        .left_menu li a:hover {
            text-decoration: underline;
        }
        .details {
            display: none;
            padding-left: 10px;
            border-left: 3px solid #007bff;
            overflow: hidden;
            transition: max-height 0.3s ease-in-out;
            max-height: 0;
        }
        .details li {
            background: none;
            font-size: 14px;
            padding: 5px 10px;
            border-radius: 0;
            margin: 2px 0;
        }
        .details li a {
            font-weight: normal;
            color: #343a40;
        }
        .details li a:hover {
            color: #007bff;
        }
    </style>
     <script>
        function toggleDetails(category) {
            let details = document.querySelector('.details-' + category);
            let icon = document.getElementById('icon-' + category);
            
            if (details.style.maxHeight === "0px" || details.style.maxHeight === "") {
                details.style.display = "block";
                details.style.maxHeight = details.scrollHeight + "px";
                icon.innerHTML = "▲";
            } else {
                details.style.maxHeight = "0px";
                setTimeout(() => details.style.display = "none", 300);
                icon.innerHTML = "▼";
            }
        }
    </script>

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/partner.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/페이지 넘기기.css">
</head>
<body>
	<%@ include file="header.jsp"%>
	<div class="main-container">
		<div class="left_content">
			<div class="title_box" style="background-color: black;">운동 종목</div>
			<ul class="left_menu">		 
            <li onclick="toggleDetails('전체')">전체 <span id="icon-전체">▼</span></li>
            <ul class="details details-전체">
                <li><a href="<c:url value='/party'/>?p_sport=축구">축구</a></li>
                <li><a href="<c:url value='/party'/>?p_sport=농구">농구</a></li>
                <li><a href="<c:url value='/party'/>?p_sport=배드민턴">배드민턴</a></li>
                <li><a href="<c:url value='/party'/>?p_sport=헬스">헬스</a></li>
                <li><a href="<c:url value='/party'/>?p_sport=크로스핏">크로스핏</a></li>
                <li><a href="<c:url value='/party'/>?p_sport=러닝">러닝</a></li>
                <li><a href="<c:url value='/party'/>?p_sport=자전거">자전거</a></li>
                <li><a href="<c:url value='/party'/>?p_sport=등산">등산</a></li>
                <li><a href="<c:url value='/party'/>?p_sport=클라이밍">클라이밍</a></li>
            </ul>

            <li onclick="toggleDetails('구기종목')">구기 종목 <span id="icon-구기종목">▼</span></li>
            <ul class="details details-구기종목">
                <li><a href="<c:url value='/party'/>?p_sport=축구">축구</a></li>
                <li><a href="<c:url value='/party'/>?p_sport=농구">농구</a></li>
                <li><a href="<c:url value='/party'/>?p_sport=배드민턴">배드민턴</a></li>
            </ul>

            <li onclick="toggleDetails('헬스')">헬스 & 피트니스 <span id="icon-헬스">▼</span></li>
            <ul class="details details-헬스">
                <li><a href="<c:url value='/party'/>?p_sport=헬스">헬스</a></li>
                <li><a href="<c:url value='/party'/>?p_sport=크로스핏">크로스핏</a></li>
            </ul>

            <li onclick="toggleDetails('유산소')">유산소 운동 <span id="icon-유산소">▼</span></li>
            <ul class="details details-유산소">
                <li><a href="<c:url value='/party'/>?p_sport=러닝">러닝</a></li>
                <li><a href="<c:url value='/party'/>?p_sport=자전거">자전거</a></li>
            </ul>

            <li onclick="toggleDetails('아웃도어')">아웃도어 & 익스트림 <span id="icon-아웃도어">▼</span></li>
            <ul class="details details-아웃도어">
                <li><a href="<c:url value='/party'/>?p_sport=등산">등산</a></li>
                <li><a href="<c:url value='/party'/>?p_sport=클라이밍">클라이밍</a></li>
            </ul>
        </ul>
    </div>


		<!-- 중앙 컨텐츠 -->
		<div class="center_content">
			<div class="profile-container">
				<c:forEach var="party" items="${PartyList}" varStatus="status">
					<a href="party_info?idx=${party.p_idx}"
						style="text-decoration: none; color: inherit;">
						<div class="prod_box">
							<!-- 상단: 고유번호 & 평점 -->
							<div class="profile-header"
								style="display: flex; justify-content: space-between;">
								<span class="idx"
									style="background: black; color: white; border-radius: 50%; width: 25px;">${status.count}</span>
								<!-- 매칭 여부를 체크하는 플래그 초기화 -->
								<c:set var="isMatchFound" value="false" scope="page" />
								<c:forEach var="pr" items="${partysRating}">
									<c:if test="${party.p_idx eq pr.p_idx}">
										<span><strong>⭐</strong><fmt:formatNumber value="${pr.p_rating}" maxFractionDigits="1" /></span>
										<!-- 매칭된 경우 플래그를 true로 변경 -->
										<c:set var="isMatchFound" value="true" scope="page" />
									</c:if>
								</c:forEach>
								<!-- 매칭이 하나도 없는 경우 처리 -->
								<c:if test="${!isMatchFound}">
									<span><strong>⭐</strong>0.0</span>
								</c:if>
							</div>
							<!-- 프로필 정보 -->
							<div class="profile-info">
								<strong>${party.p_name}</strong>
							</div>

							<div class="profile-info">
                        <img src="${pageContext.request.contextPath}/uploads/${party.p_photo}" alt="프로필 이미지" class="profile-img"
                           name="p_photo" />
                     </div>

							<div class="profile-info">
								<span>${party.p_sport}</span>
							</div>
							<div class="profile-info">
								<span> ${party.p_personnel} / ${party.p_limit}명</span>
							</div>
							<!-- 거리 표시 -->
							<div class="profile-info">
								<c:choose>
									<c:when test="${party.distance < 1}">
										<span style="font-size: 18px; font-weight: bold;"> <fmt:formatNumber
												value="${party.distance * 1000}" maxFractionDigits="0" />m
										</span>
									</c:when>
									<c:otherwise>
										<span style="font-size: 18px; font-weight: bold;"> <fmt:formatNumber
												value="${party.distance}" maxFractionDigits="0" />km
										</span>
									</c:otherwise>
								</c:choose>
							</div>
						</div>
					</a>
				</c:forEach>
			</div>
		</div>

		<!-- 오른쪽 파티 프로필 -->
		<div>
			<button onclick="location.href='party_profil'"
				style="padding: 10px 20px; background-color: #5882FA; color: white; border: none; cursor: pointer; border-radius: 5px; text-decoration: none; font-size: 20px; top: -20px; right: 20px;">
				모임 만들기</button>

		</div>
	</div>
	<!-- main-container 닫기 -->

</body>
</html>
