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
  <h2>⭐ 평가하기</h2>
  </div>
    <div class="rank-container">
 		<a href="partner_rating" style="background-color: black; color: white; padding: 15px 20px; border-radius: 10px; text-decoration: none;">파트너</a>
 		<a href="party_rating" style="background-color: black; color: white; padding: 15px 20px; border-radius: 10px; text-decoration: none;">모임</a>
	</div>
	
	<%-- rating.jsp --%>
<% if (request.getAttribute("message") != null) { %>
    <script type="text/javascript">
        alert('<%= request.getAttribute("message") %>');
    </script>
<% } %>
</body>
</html>
        