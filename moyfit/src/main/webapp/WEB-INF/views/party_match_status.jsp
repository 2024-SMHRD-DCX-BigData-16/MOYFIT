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
    <%@ include file="header.jsp"%>

    <div class="main-content">
        <h2>운동 모임 매칭 상태</h2>

        <!-- ✅ 매칭 요청이 없는 경우 -->
        <c:if test="${empty matches}">
            <p>현재 매칭 요청이 없습니다.</p>
        </c:if>

        <!-- ✅ 매칭 요청 목록 -->
        <c:forEach var="mt" items="${matches}">
            <div class="match-card">
                <p><strong>요청자:</strong> ${mt.m_id}</p>
                <p><strong>상대방:</strong> ${mt.partner_id}</p>
                <p><strong>승인 상태:</strong> 
                    <c:choose>
                        <c:when test="${mt.approve_yn eq 'Y'}">✅ 승인됨</c:when>
                        <c:when test="${mt.approve_yn eq 'N'}">⏳ 대기 중</c:when>
                        <c:otherwise>❌ 미정</c:otherwise>
                    </c:choose>
                </p>
                 <p><strong>날짜:</strong>  ${mt.created_at}</p>

                <!-- ✅ 승인된 요청에 대한 메시지 표시 -->
                <c:if test="${mt.approve_yn eq 'Y'}">
                    <p class="approved-message">이미 승인 완료된 요청입니다.</p>
                </c:if>

                <!-- ✅ 현재 로그인한 사용자가 상대방(`partner_id`)인 경우에만 "매칭 수락" 버튼 표시 -->
                <c:if test="${mt.approve_yn eq 'N' and mt.partner_id eq sessionScope.loginUser.m_id}">
                    <form action="${pageContext.request.contextPath}/party_accept" method="post">
                        <input type="hidden" name="m_id" value="${mt.m_id}">
                        <input type="hidden" name="partner_id" value="${mt.partner_id}">
                        
                        <!-- ✅ Spring Security CSRF 토큰 추가 (보안 강화) -->
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
