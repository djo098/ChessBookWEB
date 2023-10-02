<%@ page
	import="persist.ChessPlayerDM, domain.ChessPlayer, java.util.ArrayList, java.util.List"
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
	String name = request.getParameter("username");
	String email = request.getParameter("email");

	ChessPlayer result = cpdm.findByEmail(email);
	if (result == null) {
		response.sendRedirect("FailedLogin.jsp");
	} else {
		session.setAttribute("email", email);
		session.setAttribute("username", name);
		response.sendRedirect("Home.jsp");
		
	}
	%>
</body>
</html>
