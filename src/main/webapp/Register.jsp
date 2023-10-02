<%@ page
	import="persist.ChessPlayerDM, domain.ChessPlayer, java.io.PrintWriter, java.util.ArrayList, java.util.List, java.util.Date, java.time.LocalDateTime, java.time.format.DateTimeFormatter"
	language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ChessBook | Register</title>
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script src='https://kit.fontawesome.com/a076d05399.js' crossorigin='anonymous'></script>
<style>
<%@include file="css/main.css"%>
<%@include file="css/LoginRegisterForm.css"%>
</style>
</head>
<body>
	<div class="parent">
		<div class="form-style">
			<form method="POST" action="ProcessRegister.jsp">
				<fieldset>
					<legend>
						<span class="eyecandy"><i class='fas fa-chess-bishop' style="color:white"></i></span> Create an account
					</legend>
					<input type="text" name="username" placeholder="Username..." onclick="this.value=''" required><br>
					<br> <input type="email" name="email" placeholder="Email..." onclick="this.value=''" required><br>
					<br> 
				</fieldset>
				<input type="submit" value="Create account">
				<a href="Login.jsp">Back</a>
			</form>
		</div>
		<div class="footer footer-login-register">
			<div class="footer-center">
				<a href="http://appserver-01.alunos.di.fc.ul.pt/phpmyadmin/">appserver</a> 
				<span>&#8226;</span> 
				<a href="https://ciencias.ulisboa.pt/pt">FCUL</a> 
				<span>&#8226;</span> 
				<a href="https://moodle.ciencias.ulisboa.pt/">Moodle</a> 
				<span>&#8226;</span> 
				<a href="https://fenix.ciencias.ulisboa.pt/">Fenix</a> 
				<span>&#8226;</span> 
				<%
			    LocalDateTime myDateObj = LocalDateTime.now();
			    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("E MMM dd HH:mm:ss yyyy");
			    String formattedDate = myDateObj.format(myFormatObj);
			    out.println(formattedDate);
			    %>
			</div>
		</div>
	</div>
</body>
</html>
