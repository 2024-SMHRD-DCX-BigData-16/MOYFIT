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
      <h2>🏆 랭킹</h2>
    </div>
    <div class="rank-container">
      <a href="getAllRankings" style="background-color: black; text-decoration: none;">전체</a>
      <a href="getPartnerRankings" style="background-color: black; text-decoration: none;">파트너</a>
      <a href="getPartyRankings" style="background-color: black; text-decoration: none;">모임</a>
      
    </div>
    <div class="ranking-list">
      <c:forEach items="${rankings}" var="rank" varStatus="status">
        <div class="ranking-item">
          <div class="user-info">
            <div class="rank-number ${status.index == 0 ? 'first' : status.index == 1 ? 'second' : status.index == 2 ? 'third' : ''}">
              ${status.index + 1}
            </div>
            <div class="user-name"  style="padding-left:40px;">${rank.p_id != null ? rank.p_id : 'Unknown'}</div> <!-- Null 체크 추가 -->
          </div>
          <div class="rating">
            <span class="rating-number">${rank.p_rating != null ? rank.p_rating : '-'}</span> <!-- Null 체크 추가 -->
          </div>
        </div>
      </c:forEach>
    </div>
  </div>
</body>
</html>