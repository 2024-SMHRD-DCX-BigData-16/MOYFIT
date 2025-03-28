<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<title>MOYFIT</title>
<link rel="stylesheet"
   href="${pageContext.request.contextPath}/resources/css/Join.css">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
   <%@ include file="header.jsp"%>

   <script type="text/javascript"
      src="//dapi.kakao.com/v2/maps/sdk.js?appkey=932327c47972904f1754ba8c9dc64eb2&libraries=services"></script>
   <script type="text/javascript">
      function nickCheck() {
         var m_nick = document.getElementById('m_nick').value;
         console.log('m_nick : ', m_nick);
         if (m_nick == '') {
            alert('닉네임을 입력해주세요');
            return false;
         }
         $.ajax({
            url : 'nickCheck',
            type : 'POST',
            data : {
               m_nick : m_nick
            },
            success : function(nickCheck) {
               console.log('nickCheck : ', nickCheck);
               if (nickCheck == 'true') {
                  $('#nickResult').text('사용할 수 없는 닉네임입니다.');
               } else if (nickCheck == 'false') {
                  $('#nickResult').text('사용할 수 있는 닉네임입니다.');
               } else {
                  $('#nickResult').text('서버 응답 오류: ' + nickCheck);
               }
            }
         });
      }
   </script>

   <!-- 회원가입 폼 -->
   <nav id="menu">
      <div class="join-form">
         <h5>내 프로필</h5>
         <form action="profil_update" method="post"
            enctype="multipart/form-data">
            <!-- 아이디/비밀번호 섹션 -->
            <!-- 닉네임 입력 및 중복 확인 -->
            <div class="form-group">
               <label for="nickname">[닉네임]</label>
               <div class="input-group" style="display: flex;">
                  <input type="text" id="m_nick" name="m_nick"
                     placeholder="닉네임을 입력하세요" style="margin-right: 10px;" required>
                  <input type="button" class="check-btn" value="중복확인"
                     onclick="nickCheck()">
               </div>
               <div>
                  <span id="nickResult"></span>
               </div>
            </div>

            <!-- 성별 선택 -->
            <div class="form-group">
               <label for="gender">[성별]</label> <select name="m_gender"
                  id="gender" style="width: 600px; font-size: 17px">
                  <option value="M" style="width: 600px; font-size: 17px">남성</option>
                  <option value="F" style="width: 600px; font-size: 17px">여성</option>
               </select>
            </div>

            <!-- 나이 선택 -->
            <div class="form-group">
               <label for="age">[나이]</label> <select name="m_age" id="age"
                  style="width: 600px; font-size: 17px">
                  <%
                  for (int i = 20; i <= 80; i++) {
                  %>
                  <option value="<%=i%>" style="width: 600px; font-size: 17px">
                     <%=i%>세
                  </option>
                  <%
                  }
                  %>
               </select>
            </div>

            <!-- 주소 입력 -->
            <div class="form-group">
               <label for="address">[주소]</label>
               <div class="address-input-group">
                  <input type="text" id="postcode" name="postcode"
                     placeholder="우편번호">
                  <button type="button" class="address-search-btn"
                     onclick="openPostcode()">주소</button>
               </div>
               <div>
                  <input type="text" id="address" name="m_addr" placeholder="주소"
                     readonly style="margin-bottom: 10px;">
               </div>
               <input type="text" id="detailAddress" name="detailAddress"
                  placeholder="상세주소를 입력하세요">


               <!-- 위도, 경도 값을 저장할 hidden input -->
               <input type="hidden" id="latitude" name="m_lat"> <input
                  type="hidden" id="longitude" name="m_lon">

               <!-- 디버그 용 결과 출력 영역 -->
            </div>

            <script>
               // Daum 우편번호 검색 팝업 열기 함수
               function openPostcode() {
                  new daum.Postcode(
                        {
                           oncomplete : function(data) {
                              // 사용자가 선택한 주소를 변수에 저장
                              var selectedAddress = data.address; // 도로명주소 또는 지번주소

                              // 주소 입력 필드에 자동 입력
                              document.getElementById('postcode').value = data.zonecode;
                              document.getElementById("address").value = selectedAddress;

                              // 주소를 위도/경도로 변환하는 함수 호출
                              convertAddressToCoords(selectedAddress);
                           }
                        }).open();
               }

               // 입력된 주소를 위도/경도로 변환하는 함수
               function convertAddressToCoords(address) {
                  // Geocoder 객체 생성
                  var geocoder = new kakao.maps.services.Geocoder();

                  geocoder
                        .addressSearch(
                              address,
                              function(result, status) {
                                 if (status === kakao.maps.services.Status.OK) {
                                    // 첫번째 검색 결과의 위도와 경도 추출
                                    var lat = result[0].y;
                                    var lng = result[0].x;

                                    // 숨겨진 input 태그에 위도와 경도 저장
                                    document
                                          .getElementById("latitude").value = lat;
                                    document
                                          .getElementById("longitude").value = lng;

                                    // 결과 출력 (디버그용)
                                    console
                                          .log("변환된 좌표:", lat,
                                                lng);
                                 }
                              });
               }
            </script>

            <!-- 자기소개 입력 -->
            <div class="form-group">
               <label for="bio">[자기소개]</label>
               <textarea name="m_info" id="bio" placeholder="자기소개를 입력하세요" rows="4"></textarea>
            </div>

            <!-- 프로필 사진 업로드 -->
            <div class="form-group">
               <label for="profileImg">[프로필 사진]</label> <input type="file"
                  id="profileImg" name="m_photo" accept="image/*">
            </div>

            <!-- 관심 운동 선택 -->
            <div class="form-group">
               <label for="m_sport">[운동 종목]</label> <select id="m_sport"
                  name="m_sport" style="width: 600px; font-size: 17px">
                  <option value="헬스" style="width: 500px; font-size: 17px">헬스</option>
                  <option value="농구" style="width: 600px; font-size: 17px">농구</option>
                  <option value="배드민턴" style="width: 600px; font-size: 17px">배드민턴</option>
                  <option value="헬스" style="width: 600px; font-size: 17px">헬스</option>
                  <option value="크로스핏" style="width: 600px; font-size: 17px">크로스핏</option>
                  <option value="러닝" style="width: 600px; font-size: 17px">러닝</option>
                  <option value="자전거" style="width: 600px; font-size: 17px">자전거</option>
                  <option value="등산" style="width: 600px; font-size: 17px">등산</option>
                  <option value="클라이밍" style="width: 600px; font-size: 17px">클라이밍</option>
               </select>
            </div>



            <!-- 운동 레벨 선택 -->
            <div class="form-group">
               <label>[운동 레벨]</label>
               <div class="radio-group" align="center"
                  style="width: 600px; font-size: 17px">
                  <label style="width: 600px; font-size: 17px">왕초보 <input
                     type="radio" name="m_level" value="왕초보" required></label> <label
                     style="width: 600px; font-size: 17px">초보 <input
                     type="radio" name="m_level" value="초보"></label style="width: 600px; font-size: 17px"> <label
                     style="width: 600px; font-size: 17px">중급 <input
                     type="radio" name="m_level" value="중급">
                  </label> <label style="width: 600px; font-size: 17px">고급 <input
                     type="radio" name="m_level" value="고급">
                  </label>
               </div>
            </div>


<div class="button-wrapper" style="display: flex; justify-content: center; gap: 10px; margin-top: 20px;">
   <button type="submit" class="btn-action submit-btn">수정하기</button>
   <a href="memberDelete">
      <button type="button" class="btn-action submit-btn"  style="background-color:red;">탈퇴하기</button>
   </a>
</div>


   </nav>

</body>

</html>