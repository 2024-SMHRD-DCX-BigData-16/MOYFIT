<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<title>MOYFIT</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/partner_info.css">
</head>
<body>
	<%@ include file="header.jsp"%>

	<div class="main-content">
		<div class="profile-container">
			<div class="profile-header">
				<span>사용자 : ${partner.m_id}</span>
				<!-- 매칭 여부를 체크하는 플래그 초기화 -->
				<c:set var="isMatchFound" value="false" scope="page" />
				<c:forEach var="pr" items="${partnersRating}">
					<c:if test="${partner.m_id eq pr.m_id}">
						<span><strong>⭐</strong>${pr.partner_rating}</span>
						<!-- 매칭된 경우 플래그를 true로 변경 -->
						<c:set var="isMatchFound" value="true" scope="page" />
					</c:if>
				</c:forEach>
				<!-- 매칭이 하나도 없는 경우 처리 -->
				<c:if test="${!isMatchFound}">
					<span><strong>⭐</strong>0.0</span>
				</c:if>
			</div>

			<img src="${pageContext.request.contextPath}/uploads/${partner.m_photo}" alt="프로필 이미지" class="profile-img"
				name="m_photo">

			<div class="profile-info" name="m_nick" style="font-size: 25px;">${partner.m_nick}</div>

			<div class="profile-info" name="m_age">${partner.m_gender}|
				${partner.m_age}세</div>

			<div class="profile-info" name="m_sport">${partner.m_sport}|
				${partner.m_level}</div>

			<div class="bio" name="m_info" style="height: 150px;">
				${partner.m_info}</div>

			<!-- 버튼 컨테이너 -->
			<div class="button-container"
				style="display: flex; justify-content: center; align-items: center; gap: 20px;">
				<div style="display: flex; gap: 10px;">
					<button onclick="location.href='message_create?idx=${partner.m_id}'"
						class="btn chat-btn">메시지하기</button>

							<form action="${pageContext.request.contextPath}/partnerOffer"
								method="post" onsubmit="return sendOffer(this);"
								style="margin: 0;">
								<input type="hidden" name="m_id"
									value="${sessionScope.loginUser.m_id}"> <input
									type="hidden" name="partner_id" value="${partner.m_id}">
								<button type="submit" class="btn offer-btn">제의하기</button>
							</form>
				</div>
			</div>


			<div style="margin-top: 20px; text-align: center; width: 100%;">
				<button onclick="window.location.href='partner'"
					class="btn back-btn">뒤로가기</button>
			</div>
		</div>
	</div>
	<script>
        function sendOffer(form) {
            if (!confirm("이 사용자에게 운동 제의를 보내시겠습니까?")) {
                return false;
            }
            setTimeout(() => alert("운동 제의가 전송되었습니다!"), 500);
            return true;
        }
    </script>

</body>
</html>
