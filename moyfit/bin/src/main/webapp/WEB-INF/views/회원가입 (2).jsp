<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<title>MOYFIT</title>
<meta charset="utf-8" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<style type="text/css">
.navbar-nav {
	display: flex;
	justify-content: center;
	align-items: center;
	list-style: none;
	padding: 0;
	margin: 20px 0 0 0;
}

.navbar-nav li {
	margin: 0 15px;
}

.navbar-nav a {
	text-decoration: none;
	font-size: 16px;
	font-weight: bold;
	color: #333;
	padding: 10px 15px;
	transition: color 0.3s ease-in-out;
}

.navbar-nav a:hover {
	color: #007bff;
}

.logo {
	display: block;
	text-align: center;
	font-size: 50px;
	font-weight: bold;
	color: #333;
	text-decoration: none;
	margin: 0 auto;
	margin-bottom: 20px;
}

.user-option {
	position: absolute;
	top: 10px;
	right: 20px;
	display: flex;
	align-items: center;
	gap: 15px;
}

.user-option a {
	text-decoration: none;
	font-size: 16px;
	font-weight: bold;
	color: #333;
}

.user-option a:hover {
	color: #007bff;
}

/* 회원가입 폼 스타일 */
.join-form {
	max-width: 400px;
	margin: 50px auto;
	padding: 30px;
	background-color: #ffffff;
	border-radius: 10px;
	box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
}

.join-form h5 {
	text-align: center;
	margin-bottom: 30px;
	font-size: 24px;
	color: #333;
}

.form-group {
	margin-bottom: 20px;
}

.form-group input {
	width: 100%;
	padding: 12px 15px;
	border: 1px solid #ddd;
	border-radius: 5px;
	font-size: 16px;
	transition: border-color 0.3s ease;
}

.form-group input:focus {
	border-color: #007bff;
	outline: none;
	box-shadow: 0 0 5px rgba(0, 123, 255, 0.3);
}

.submit-btn {
	width: 100%;
	padding: 12px;
	background-color: #007bff;
	color: white;
	border: none;
	border-radius: 5px;
	font-size: 16px;
	font-weight: bold;
	cursor: pointer;
	transition: background-color 0.3s ease;
}

.submit-btn:hover {
	background-color: #0056b3;
}
</style>

</head>

<body>
	<div id="wrapper">
		<!-- Header -->
		<header id="header" class="alt"> <nav>
		<button>
			<span>모이핏 그림</span>
		</button>
		<a href="home" class="logo"> <span>MOYFIT</span>
		</a>

		<div>
			<ul class="navbar-nav">
				<li><a href="home">Home</a></li>
				<li><a href="partner">파트너 매칭</a></li>
				<li><a href="gather">모임 매칭</a></li>
				<li><a href="board">커뮤니티</a></li>
				<li><a href="rank">랭킹</a></li>
				<li><a href="qna">문의</a></li>
			</ul>
			<div class="user-option">
				<a href="login"><i class="fa fa-user"></i> <span>Login</span></a> <a
					href="profil"><span>프로필</span></a>
			</div>
		</div>
		</nav> </header>
	</div>



	<!-- Menu -->
	<nav id="menu" align="center">
	<div class="join-form">
		<h5>회원가입</h5>
		<form action="login" method="post">
			<div class="form-group">
				<input type="text" placeholder="아이디를 입력하세요" name="id">
			</div>
			<div class="form-group">
				<input type="password" placeholder="비밀번호를 입력하세요" name="pw">
			</div>
			<div class="form-group">
				<input type="text" placeholder="성별을 입력하세요" name="gender">
			</div>
			<div class="form-group">
				<input type="number" placeholder="나이를 입력하세요" name="age">
			</div>
			<div class="form-group">
				<input type="text" placeholder="주소를 입력하세요" name="address">
			</div>
			<div class="form-group">
				<input type="submit" value="가입하기" class="submit-btn">
			</div>
		</form>
	</div>
	</nav>

	</script>

</body>

</html>