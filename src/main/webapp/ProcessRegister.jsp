<%@page import="persist.ChessPlayerDM, domain.ChessPlayer"
	language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	<%
	ChessPlayerDM cpdm = ChessPlayerDM.getInstance();
	ChessPlayer p = cpdm.createPlayer(request.getParameter("username"), request.getParameter("email"));
	response.sendRedirect("Login.jsp");
	%>
</body>
</html>
