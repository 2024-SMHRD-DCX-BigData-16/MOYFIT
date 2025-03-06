<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>매칭 상태</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/match.css">
</head>
<body>
    <%@ include file="header.jsp" %>

    <div class="main-content">
        <h2>운동 파트너 매칭 상태</h2>

        <!-- ✅ 파트너 요청 수락 완료 또는 승인 메시지 표시 -->
        <c:if test="${not empty message}">
            <div class="message-box">${message}</div>
        </c:if>

        <c:if test="${empty matches}">
            <p>현재 매칭 요청이 없습니다.</p>
        </c:if>

        <c:forEach var="match" items="${matches}">
            <div class="match-card">
                <p><strong>요청자:</strong> ${match.m_id}</p>
                <p><strong>수락자:</strong> ${match.partner_id}</p>
                <p><strong>승인 상태:</strong> 
                    <c:choose>
                        <c:when test="${match.accept_yn eq 'Y'}">✅ 승인됨</c:when>
                        <c:when test="${match.accept_yn eq 'N'}">⏳ 대기 중</c:when>
                        <c:otherwise>❌ 미정</c:otherwise>
                    </c:choose>
                </p>
                <p><strong>날짜:</strong> ${match.created_at}</p>

                <!-- ✅ 승인된 요청에 대해 "이미 승인 완료" 메시지 추가 -->
                <c:if test="${match.accept_yn eq 'Y'}">
                    <p class="approved-message">이미 승인 완료된 요청입니다.</p>
                </c:if>

                <!-- 현재 로그인한 사용자가 partner_id일 때만 수락 버튼 표시 -->
                <c:if test="${match.accept_yn eq 'N' and match.partner_id eq sessionScope.loginUser.m_id}">
                    <form action="${pageContext.request.contextPath}/partner_accept" method="post">
                        <input type="hidden" name="m_id" value="${match.m_id}">
                        <input type="hidden" name="partner_id" value="${match.partner_id}">
                        <c:if test="${not empty _csrf}">
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                        </c:if>
                        <button type="submit" class="accept-btn">매칭 수락</button>
                    </form>
                </c:if>
            </div>
        </c:forEach>
    </div>
</body>
</html>
