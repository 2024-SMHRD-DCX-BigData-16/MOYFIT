<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<title>게시글 상세보기</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/board_write.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/board_detail.css">
</head>
<body>
	<%@ include file="header.jsp"%>

	<div class="detail-container">
		<div class="post-card">

			<h2 class="post-title">${boardDetail.b_title}</h2>

			<!-- 게시글 정보 -->
			<div class="post-info">
				<span class="post-category">🏷 카테고리:${boardDetail.b_category}</span>
				<span class="post-date">🕒 작성일:${boardDetail.created_at}</span>
				<span class="post-views">👁 조회수:${boardDetail.b_views}</span>
			</div>

			<!-- 게시글 내용 (여백 증가) -->
			<div class="post-content">
				<p style="height: 300px;">${boardDetail.b_content}</p>
			</div>

			<!-- 이미지 -->
			<p style="text-align: left;">첨부 파일 : ${boardDetail.b_file}</p>
			<c:if test="${not empty boardDetail.b_file}">
                <div class="image-container">
                    <img src="${pageContext.request.contextPath}/uploads/${boardDetail.b_file}" alt="첨부 이미지">
                </div>
            </c:if>

			<!-- 버튼 그룹 -->
			<div class="button-container">
				<!-- 왼쪽 하단: 목록 버튼 -->
				<button class="btn btn-back" onclick="location.href='board'">목록으로</button>

				<%-- <a href="boardDetail?idx=${b.b_idx }">${b.b_title }</a> --%>


				<!-- 오른쪽 하단: 수정/삭제 버튼 그룹 -->
				<c:if test="${loginUser.m_id eq boardDetail.m_id}">
					<div class="edit-delete-group">
						<%-- <input type="hidden" name="b_id" value="${board.b_id}"> --%>
						<a href="modify?idx=${boardDetail.b_idx }">
						<button class="btn btn-edit">수정하기</button></a>
						<form action="board_delete" method="post">
							<%-- <input type="hidden" name="b_id" value="${board.b_id}"> --%>
							<input type="submit" class="btn btn-delete" value="삭제"
								onclick="return confirm('정말 삭제하시겠습니까?');">
						</form>
					</div>
				</c:if>
			</div>
		</div>
	</div>

	<script>
        function toggleLike(m_id) {
            fetch(`board_like?m_id=${m_id}`, {
                method: 'POST'
            })
            .then(response => response.json())
            .then(data => {
                if (data.success) {
                    document.getElementById("like-count").innerText = data.likes;
                    document.getElementById("like-heart").classList.toggle("liked");
                } else {
                    alert("좋아요 처리 중 오류가 발생했습니다.");
                }
            })
            .catch(error => console.error("Error:", error));
        }
    </script>


</body>
</html>
