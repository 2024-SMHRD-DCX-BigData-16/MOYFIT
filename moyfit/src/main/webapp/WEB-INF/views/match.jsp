<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.ArrayList" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
	<head>
        <%@ include file="header.jsp" %>
		<title>MOYFIT</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/rank.css">
	</head>
   <body>    
  <div class="ranking-container">
    <div class="ranking-header">
  <h2>매칭 상태</h2>
  </div>
    <div class="rank-container">
  <a href="checkMatching"  style="background-color: black; text-decoration: none;">파트너</a>
  <a href="match_status?m_id=${sessionScope.loginUser.m_id}"  style="background-color: black; text-decoration: none;">모임</a>
</div>
</body>
</html>
        