<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<title>MOYFIT</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/partner_rating.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">
</head>
<body>
	<%@ include file="header.jsp"%>
	<br>
	<div class="container">
		<h2>파트너 평가</h2>
		<div class="rating-section">
			<h3>별점 평가하기</h3>
			<form action="${pageContext.request.contextPath}/submitRating"
				method="POST">
				<!-- 평가자 ID 입력 필드 -->
				<div class="form-group">
					<label for="evaluatorId">평가할 ID :</label> <input type="text"
						name="partner_id" id="partner_id">
				</div>

				<!-- 로그인한 회원 아이디 정보 -->
				<input type="hidden" id="m_id" name="m_id" value="${loginUser.m_id}">

				<!-- 별점 선택 UI -->
				<div class="stars">
					<i class="far fa-star" data-rating="1.0"></i> <i
						class="far fa-star" data-rating="2.0"></i> <i class="far fa-star"
						data-rating="3.0"></i> <i class="far fa-star" data-rating="4.0"></i>
					<i class="far fa-star" data-rating="5.0"></i>
				</div>
				<input type="hidden" name="rating" id="selectedRating" value="0">
				<button type="submit" id="submitRating"
					style="background-color: black;">평가 제출</button>
			</form>
		</div>
	</div>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script>
	$(document).ready(function() {
	    // ⭐ 별점 평가 기능 ⭐
	    $('.stars i').hover(function() {
	        var rating = $(this).data('rating');
	        highlightStars(rating);
	    }, function() {
	        var selectedRating = $('#selectedRating').val();
	        if (selectedRating > 0) {
	            highlightStars(selectedRating);
	        } else {
	            $('.stars i').removeClass('fas').addClass('far');
	        }
	    });

	    $('.stars i').click(function() {
	        var rating = $(this).data('rating');
	        $('#selectedRating').val(rating);
	        highlightStars(rating);
	    });

	    function highlightStars(rating) {
	        $('.stars i').each(function() {
	            var starRating = $(this).data('rating');
	            if (starRating <= rating) {
	                $(this).removeClass('far').addClass('fas');
	            } else {
	                $(this).removeClass('fas').addClass('far');
	            }
	        });
	    }

	    // ⭐ 평가 제출 후 alert 띄우기 ⭐
	    const urlParams = new URLSearchParams(window.location.search);
	    const success = urlParams.get('success');

	    if (success === 'true') {
	        alert('평가 제출 완료했습니다.');
	    } else if (success === 'false') {
	        alert('평가 제출에 실패했습니다. 다시 시도해주세요.');
	    }
	});

		
	</script>
</body>
</html>
