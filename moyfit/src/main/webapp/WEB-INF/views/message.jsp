<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.ArrayList" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <title>메시지 목록</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/message.css">

    <script>
        function confirmDelete(msgIdx) {
            if (confirm("정말 이 메시지를 삭제하시겠습니까?")) {
                location.href = "message/delete?msg_idx=" + msgIdx;
            }
        }

        // 📌 ✅ 모든 메시지 삭제 요청
        function confirmDeleteAll(receiverId) {
            if (confirm("정말 모든 메시지를 삭제하시겠습니까?")) {
                fetch("${pageContext.request.contextPath}/message/deleteAll", {
                    method: "POST",
                    headers: { "Content-Type": "application/x-www-form-urlencoded" }
                })
                .then(response => response.text())
                .then(message => {
                	alert("✅ 모든 메시지를 삭제 처리했습니다.");
                    location.reload();
                })
                .catch(error => {
                    console.error("🚨 삭제 실패:", error);
                    alert("❌ 메시지 삭제 실패!");
                });
            }
        }

        function markAllAsRead(receiverId) {
            fetch("message/read-all", {
                method: "POST",
                headers: { "Content-Type": "application/x-www-form-urlencoded" },
                body: "receiver_id=" + encodeURIComponent(receiverId)
            })
            .then(response => {
                if (!response.ok) throw new Error("읽음 처리 실패");
                alert("✅ 모든 메시지를 읽음 처리했습니다.");
                location.reload();
            })
            .catch(error => {
                console.error("🚨 읽음 처리 실패:", error);
                alert("❌ 메시지 읽음 처리 실패!");
            });
        }

        function refreshUnreadCount(receiverId) {
            fetch("message/unread-count?receiver_id=" + receiverId)
                .then(response => response.text())
                .then(count => {
                    document.getElementById("unreadCount").innerText = count;
                });
        }
    </script>
</head>
<body style="margin-left: 20px; margin-right: 20px;">
    <%@ include file="header.jsp" %>
   <h4>   </h4>

    <h2 style="display: flex; justify-content: space-between; align-items: center; padding-left:60px;">
         💬 메시지 목록
    
    <button onclick="location.href='message_create'" class="btn btn-primary" style="margin-right: 100px; margin-top: 50px; font-size:20px;">🛩️ 메시지 보내기</button>

    </h2>
    <h4>   </h4>
    <!-- ✅ 읽지 않은 메시지 개수 -->
    <h3 style="padding-left:60px;">📊 읽지 않은 메시지 개수: <span id="unreadCount">${unreadCount}</span></h3>
    <button style="margin-left: 60px;" onclick="refreshUnreadCount('${loginUser.m_id}')" class="btn btn-info">새로고침</button>
    <button onclick="markAllAsRead('${loginUser.m_id}')" class="btn btn-success">📖 모든 메시지 읽음 처리</button>
    <button onclick="confirmDeleteAll('${loginUser.m_id}')" class="btn btn-danger">🗑 모든 메시지 삭제</button>

    <br><br>

    <!-- ✅ 내가 받은 메시지 목록 -->
    <h3 style="padding-left:60px;">🔽 내가 받은 메시지</h3>
    <table>
        <thead>
            <tr>
                <th>번호</th>
                <th>발신 날짜</th>
                <th>메시지 내용</th>
                <th>보낸 사람</th>
                <th>상태</th>
                <th>삭제</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="message" items="${messages}">
                <tr class="${message.receive_yn == 'N' ? 'unread' : 'read'}">
                    <td>${message.msg_idx}</td>
                    <td>${message.sended_at}</td>
                    <td>${message.msg_content}</td> 
                    <td>${message.m_id}</td> <!-- 보낸 사람 ID 표시 -->
                    <td>
                        <c:choose>
                            <c:when test="${message.receive_yn == 'Y'}">✔️ 읽음</c:when>
                            <c:otherwise>
                                <a href="message/read?msg_idx=${message.msg_idx}&receiver_id=${message.receiver_id}" class="btn btn-warning">읽지 않음</a>
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td><button onclick="confirmDelete(${message.msg_idx})" class="btn btn-danger">🗑 삭제</button></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <br>

    <!-- ✅ 내가 보낸 메시지 목록 -->
    <h3 style="padding-left:60px;">🔼 내가 보낸 메시지</h3>
    <table>
        <thead>
            <tr>
                <th>번호</th>
                <th>발신 날짜</th>
                <th>메시지 내용</th>
                <th>받는 사람</th>
                <th>상태</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="message" items="${sentMessages}">
                <tr class="${message.receive_yn == 'N' ? 'unread' : 'read'}">
                    <td>${message.msg_idx}</td>
                    <td>${message.sended_at}</td>
                    <td>${message.msg_content}</td> 
                    <td>${message.receiver_id}</td> <!-- 받는 사람 ID 표시 -->
                    <td>${message.receive_yn == 'Y' ? '✔️ 읽음' : '❗ 상대가 아직 읽지 않음'}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>


</body>
</html>
