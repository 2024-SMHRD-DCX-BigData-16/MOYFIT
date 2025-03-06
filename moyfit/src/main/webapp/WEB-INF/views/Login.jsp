<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.ArrayList" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
	<head>
		<title>MOYFIT</title>
			 <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/Login.css">
	</head>
        <body>
         <%@ include file="header.jsp" %>
			<!-- 로그인 메뉴 -->
				<nav id="menu">
					<h5>로그인하세요</h5>
					<form action="memberLogin" method="post">
						<input type="id" placeholder="ID을 입력하세요" name="m_id"> <input
							type="password" placeholder="PW를 입력하세요" name="m_pw">
						<div class="button-group">
							<input type="submit" value="로그인"> <a href="memberJoin">회원가입</a>
						</div>
					</form>
				</nav>
        </body>

        </html>