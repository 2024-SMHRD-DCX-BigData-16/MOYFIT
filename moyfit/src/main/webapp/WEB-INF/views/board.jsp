<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.ArrayList" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
	<head>
		<title>MOYFIT</title>
			 <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/board.css">
	
	</head>
        <body>
         <%@ include file="header.jsp" %>
            
   <div class="community-container">
      <div class="board-header">
         <h2>게시판</h2>
         <a href="board_write" class="write-btn" style="background-color: #5882FA;">글쓰기</a>
      </div>

      <table class="board-table">
         <thead>
            <tr>
               <th width="7%">번호</th>
               <th width="10%">카테고리</th>
               <th width="15%">제목</th>
               <th width="30%">내용</th>
               <th width="10%">조회수</th>
               <th width="10%">작성자</th>
               <th width="14%">작성일자</th>
            </tr>
         </thead>
         <tbody>
            <c:forEach items="${boardList }" var="b" varStatus="s">
               <tr>
                  <td>${s.count }</td>
                  <td>${b.b_category }</td>
                  <td><a href="board_detail?idx=${b.b_idx }">${b.b_title }</a></td>
                  <td>${b.b_content }</td>
                  <td>${b.b_views }</td>
                  <td>${b.m_id }</td>
                  <td>${b.created_at }</td>
               </tr>
            </c:forEach>
         </tbody>
      </table>


   </div>

   <script>
               function handleImageUpload(event) {
                  const preview = document.getElementById('image-preview');
                  const files = event.target.files;

                  if (files.length > 5) {
                     alert('최대 5개까지만 첨부 가능합니다.');
                     event.target.value = '';
                     return;
                  }

                  for (const file of files) {
                     if (file.size > 5 * 1024 * 1024) {
                        alert('파일 크기는 5MB를 초과할 수 없습니다.');
                        continue;
                     }

                     const reader = new FileReader();
                     reader.onload = function (e) {
                        const div = document.createElement('div');
                        div.className = 'preview-item';
                        div.innerHTML = `
                                <img src="${e.target.result}" alt="미리보기">
                                <span class="remove-image" onclick="this.parentElement.remove()">×</span>
                            `;
                        preview.appendChild(div);
                     }
                     reader.readAsDataURL(file);
                  }
               }
            </script>
</body>
