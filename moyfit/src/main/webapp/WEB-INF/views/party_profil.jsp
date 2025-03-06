<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<title>MOYFIT - 모임 프로필</title>
<link rel="stylesheet"
   href="${pageContext.request.contextPath}/resources/css/Join.css">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>

<!-- 모임명 입력 -->
<script>
   function nameCheck() {
      var p_name = document.getElementById('p_name').value;
      console.log('p_name : ', p_name);
      if (p_name == '') {
         alert('모임명을 입력해주세요');
         return false;
      }
      $.ajax({
         url : 'nameCheck',
         type : 'POST',
         data : {
            p_name : p_name
         },
         success : function(nameCheck) {
            console.log('nameCheck : ', nameCheck);
            if (nameCheck == 'true') {
               $('#resultCheck').text('사용할 수 없는 이름입니다.');
            } else if (nameCheck == 'false') {
               $('#resultCheck').text('사용할 수 있는 이름입니다.');
            } else {
               $('#resultCheck').text('서버 응답 오류: ' + nameCheck);
            }
         }
      });
   }
</script>

<script type="text/javascript"
   src="//dapi.kakao.com/v2/maps/sdk.js?appkey=932327c47972904f1754ba8c9dc64eb2&libraries=services"></script>
<body>
   <%@ include file="header.jsp"%>

   <!-- 모임 프로필 폼 -->
   <nav id="menu">
      <div class="join-form">
         <h5>모임 프로필</h5>
         <form action="insertParty" method="post"
            enctype="multipart/form-data">


            <!-- 모임장 (현재 로그인한 사용자) -->
            <div class="form-group">
               <label for="m_id">[모임장]</label> <input type="text" id="m_id"
                  name="m_id" value="${loginUser.m_id}" readonly>
            </div>

            <!-- 모임명 입력 -->
            <div class="form-group">
               <label for="p_name">[모임명]</label>
               <div class="input-group" style="display: flex;">
                  <input type="text" id="p_name" name="p_name"
                     placeholder="모임 이름을 입력하세요" required style="margin-right: 10px;">
                  <input type="button" class="check-btn" value="중복확인"
                     onclick="nameCheck()">
               </div>
               <div>
                  <span id="resultCheck"></span>
               </div>
            </div>



            <!-- 주소 입력 -->
            <div class="form-group">
               <label for="p_addr">[모임 주소]</label>
               <div class="address-input-group">
                  <input type="text" id="postcode" name="postcode"
                     placeholder="우편번호">
                  <button type="button" class="check-btn" onclick="openPostcode()">주소</button>
               </div>
               <input type="text" id="address" name="p_addr" placeholder="주소"
                  readonly style="margin-bottom: 10px;">
                  <input type="text"
                  id="detailAddress" name="detailAddress" placeholder="상세주소를 입력하세요">
            </div>

            <input type="hidden" id="latitude" name="p_lat"> <input
               type="hidden" id="longitude" name="p_lon">

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

            <!-- 모임 소개 -->
            <div class="form-group">
               <label for="p_info">[모임 소개]</label>
               <textarea name="p_info" id="p_info" placeholder="모임을 소개해주세요"
                  rows="4"></textarea>
            </div>

            <!-- 모임 사진 업로드 -->
            <div class="form-group">
               <label for="p_photo">[모임 사진]</label> <input type="file"
                  id="p_photo" name="p_photo" accept="image/*">
            </div>

            <!-- 모임 인원수 제한 -->
            <div class="form-group">
               <label for="p_limit">[모임 인원]</label> <input type="number"
                  id="p_limit" name="p_limit" min="2" max="50" value="2"
                  style="width: 590px; font-size: 20px">
            </div>

            <!-- 관심 운동 선택 -->
            <div class="form-group">

               <label for="m_sport">[운동 종목]</label> <select id="p_sport"
                  name="p_sport" style="width: 600px; font-size: 17px">
                  <option value="헬스" style="width: 600px; font-size: 17px">헬스</option>
                  <option value="농구" style="width: 600px; font-size: 17px">농구</option>
                  <option value="축구" style="width: 600px; font-size: 17px">축구</option>
                  <option value="배드민턴" style="width: 600px; font-size: 17px">배드민턴</option>
                  <option value="러닝" style="width: 600px; font-size: 17px">러닝</option>
                  <option value="자전거" style="width: 600px; font-size: 17px">자전거</option>
                  <option value="등산" style="width: 600px; font-size: 17px">등산</option>
                  <option value="크로스핏" style="width: 600px; font-size: 17px">크로스핏</option>
                  <option value="클라이밍" style="width: 600px; font-size: 17px">클라이밍</option>
               </select>
            </div>


            <!-- 버튼 그룹 -->
            <div class="button-container">
               <div class="button-group">
                  <button type="submit" class="btn-action submit-btn">모임 생성</button>
                  <button href="partyList" class="btn-action submit-btn">목록으로</button>
               </div>
            </div>
         </form>
      </div>
   </nav>
</body>
</html>
