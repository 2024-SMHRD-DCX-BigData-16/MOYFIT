<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
				<span>모임장 : ${party.m_id}</span>
				<input type="hidden" name="m_id" value="${party.m_id}">
				<!-- 매칭 여부를 체크하는 플래그 초기화 -->
				<c:set var="isMatchFound" value="false" scope="page" />
				<c:forEach var="pr" items="${partysRating}">
					<script type="text/javascript">
						console.log("${pr.p_idx}")
						console.log("${party.p_idx}")
						console.log("${pr.p_rating}")
					</script>
					<c:if test="${party.p_idx eq pr.p_idx}">
						<span><strong>⭐</strong>
						<fmt:formatNumber value="${pr.p_rating}" maxFractionDigits="1" /></span>
						<!-- 매칭된 경우 플래그를 true로 변경 -->
						<c:set var="isMatchFound" value="true" scope="page" />
					</c:if>
				</c:forEach>
				<!-- 매칭이 하나도 없는 경우 처리 -->
				<c:if test="${!isMatchFound}">
					<span><strong>⭐</strong>0.0</span>
				</c:if>
			</div>

			<div class="profile-info" name="p_name" style="font-size: 25px;">
				<label>모임명: </label> ${party.p_name}
			</div>
			<br>

			<c:if test="${not empty party.p_photo}">
				<div class="image-container">
					<img
						src="${pageContext.request.contextPath}/uploads/${party.p_photo}"
						alt="첨부 이미지" class="profile-img">
				</div>
			</c:if>

			<div class="profile-info" name="p_sport">${party.p_sport} |
				${party.p_addr}</div>

			<div class="bio" name="p_info" style="height: 150px;">
				<label></label> <br> ${party.p_info}
			</div>

			<div class="profile-info">
				<label>현재 인원: </label>${party.p_personnel} / ${party.p_limit}
			</div>

			<!-- 버튼 컨테이너 -->
			<div class="button-container"
				style="display: flex; justify-content: center; align-items: center; gap: 20px;">
				<div style="display: flex; gap: 10px;">
					<button onclick="location.href='message_create?idx=${party.m_id}'"
						class="btn chat-btn" >메시지하기</button>

					<form action="${pageContext.request.contextPath}/partyOffer"
						method="post" onsubmit="return sendOffer(this);"
						style="margin: 0; display: inline-block;">
						<input type="hidden" name="m_id"
							value="${sessionScope.loginUser.m_id}"> <input
							type="hidden" name="partner_id" value="${party.m_id}">
						<button type="submit" class="btn offer-btn">제의하기</button>
					</form>
				</div>
			</div>

        <div style="margin-top: 20px; text-align: center; width: 100%;">
            <button onclick="window.location.href='party'"
               class="btn back-btn">뒤로가기</button>
         </div>
			
				
			
			
		</div>
	</div>

	<script>
        function sendOffer(form) {
            if (!confirm("이 모임에 참여 요청을 보내시겠습니까?")) {
                return false;
            }
            setTimeout(() => alert("참여 요청이 전송되었습니다!"), 500);
            return true;
        }
    </script>

</body>
</html>
