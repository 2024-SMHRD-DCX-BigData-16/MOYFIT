<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<title>MOYFIT</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/board_write.css">
</head>
<body>
	<%@ include file="header.jsp"%>

	<div class="write-container">
		<h2>게시판 글쓰기</h2>
		<form action="board_update" class="write-form" method="post" enctype="multipart/form-data">
			<div class="form-group">
				<label for="category">카테고리</label> <select id="category"
					name="b_category">
					<option value="">카테고리 선택</option>
					<option value="문의">문의</option>
					<option value="일상">일상</option>
					<option value="운동">운동</option>
					<option value="식단">식단</option>
					<option value="후기">후기</option>
				</select>
			</div>

			<div>
				<label for="title">제목</label> 
				<input type="text" id="title" name="b_title" required placeholder="제목을 입력해주세요" style="width: 600px; height: 30px;">
			</div>

			<div class="form-group">
				<label for="content">내용</label>
				<textarea id="content" name="b_content" required
					placeholder="내용을 입력해주세요" style="height: 600px;"></textarea>
			</div>

			<div class="form-group">
				<label for="profileImg">첨부 파일</label> <input type="file"
					id="profileImg" name="b_file" accept="image/*">
			</div>
			
			<div style="text-align: center;">
			    <form action="board" method="post" style="display: flex; justify-content: center; gap: 10px;">
			        <input type="submit" class="submit-btn" value="수정하기" 
			               style="width: 250px; height: 50px; background: #ffc107; border: none; cursor: pointer; font-size: 16px;">
			        <button type="button" class="cancel-btn" onclick="location.href='board'" 
			                style="width: 250px; height: 50px; background: #ccc; border: none; cursor: pointer; font-size: 16px;">
			            취소
			        </button>
			    </form>
			</div>


		</form>
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
                                    <span class="remove-image" onclick="removeImage(this)">×</span>
                                `;
                        preview.appendChild(div);
                     }
                     reader.readAsDataURL(file);
                  }
               }

               function removeImage(element) {
                  element.parentElement.remove();
               }

               function cancelWrite() {
                  if (confirm('작성중인 내용이 저장되지 않습니다. 정말 취소하시겠습니까?')) {
                     location.href = '/community';
                  }
               }

               function submitWrite() {
                  const category = document.getElementById('category').value;
                  const title = document.getElementById('title').value.trim();
                  const content = document.getElementById('content').value.trim();

                  if (!category) {
                     alert('카테고리를 선택해주세요.');
                     document.getElementById('category').focus();
                     return;
                  }

                  if (!title) {
                     alert('제목을 입력해주세요.');
                     document.getElementById('title').focus();
                     return;
                  }

                  if (!content) {
                     alert('내용을 입력해주세요.');
                     document.getElementById('content').focus();
                     return;
                  }

                  if (confirm('게시글을 등록하시겠습니까?')) {
                     document.querySelector('.write-form').submit();
                  }
               }
            </script>
</body>


</html>