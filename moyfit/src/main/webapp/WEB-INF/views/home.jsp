<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.ArrayList" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
	<head>
		<title></title>
			 <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/home.css">
	</head>
        <body>
         <%@ include file="header.jsp" %>
        <br><br><br>
         <h1 style="text-align: center; font-size: 24px; font-weight: bold; margin-top: 20px;">
		    'Moyfit에서는 매달 상위 랭킹을 달성한 사용자에게 Moyfit 상장을 수여합니다.'
		 </h1>

            <!-- Why Section -->
            <section class="why_section" style="margin-top:-30px;">
               <div class="card-container">
                  <!-- Card 1 -->
                  <div class="box">
                     <div class="img-box">
                        <img src="${pageContext.request.contextPath}/resources/images/캡처1.PNG" alt="Fast Matching">
                     </div>
                     <div class="detail-box">
                        <h5>빠른 매칭</h5>
                        <p>MOYFIT은 빠른 매칭을 제공합니다.</p>
                     </div>
                  </div>

                  <!-- Card 2 -->
                  <div class="box">
                     <div class="img-box">
                        <img src="${pageContext.request.contextPath}/resources/images/캡처2.PNG" alt="Free Matching">
                     </div>
                     <div class="detail-box">
                        <h5>무료 매칭</h5>
                        <p>MOYFIT은 무료 매칭을 제공합니다.</p>
                     </div>
                  </div>

                  <!-- Card 3 -->
                  <div class="box">
                     <div class="img-box">
                        <img src="${pageContext.request.contextPath}/resources/images/캡처3.PNG" alt="Best Quality">
                     </div>
                     <div class="detail-box">
                        <h5>최고의 품질</h5>
                        <p>MOYFIT은 최고의 매칭을 제공합니다.</p>
                     </div>
                  </div>
               </div>
            </section>
         </body>

         </html>