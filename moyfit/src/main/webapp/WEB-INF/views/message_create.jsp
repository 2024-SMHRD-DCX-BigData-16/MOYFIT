<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="header.jsp" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <title>🔼 메시지 보내기</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/message_create.css">
    
</head>
<body>
    <br><br><br><br><br>
    <div class="message-container">
        <h2>🔼 메시지 보내기</h2>

        <!-- 에러 메시지 표시 -->
        <c:if test="${not empty param.error}">
            <p class="error-message">
                <c:choose>
                    <c:when test="${param.error == 'receiver_id_missing'}">⚠️ 받는 사람 ID를 입력하세요.</c:when>
                    <c:when test="${param.error == 'msg_content_missing'}">⚠️ 메시지 내용을 입력하세요.</c:when>
                    <c:otherwise>⚠️ 알 수 없는 오류가 발생했습니다.</c:otherwise>
                </c:choose>
            </p>
        </c:if>

        <form action="${pageContext.request.contextPath}/message/send" method="post">
        	<script type="text/javascript">
        		console.log(m_id)
        	</script>
            <label for="receiver_id">받는 사람 ID:</label>
            <input type="text" id="receiver_id" name="receiver_id" readonly value="${m_id}">

            <label for="msg_content">메시지 내용:</label>
            <textarea id="msg_content" name="msg_content" required placeholder="메시지를 입력하세요."></textarea>

            <div class="btn-container">
                <button type="submit" class="btn btn-success" style="background-color: #eaeaea; color: black;">보내기</button>
                <button type="button" class="btn btn-secondary" onclick="history.back()" style="background-color: red; ">취소</button>
            </div>
        </form>
    </div>
</body>
</html>
