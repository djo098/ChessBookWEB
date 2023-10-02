<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="persist.ChessPlayerDM, domain.ChessMove, persist.ChessGameDM, domain.ChessGame, domain.ChessPlayer, domain.ChessBoard, java.util.List, java.util.Date"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	<%
	ChessPlayerDM cpdm = ChessPlayerDM.getInstance();
	ChessGameDM cgdm = ChessGameDM.getInstance();
	String you = (String) session.getAttribute("username");
	String opp = request.getParameter("opponent");
	ChessPlayer player1 = cpdm.findByEmail((String) session.getAttribute("email"));
	ChessPlayer player2 = cpdm.findByEmail(opp);
	Date startDate = new Date();
	if(you != null && opp!=null){
		session.setAttribute("opponent", player2.getEmail());
		ChessGame jogo = cgdm.createGame(player1, player2, startDate);
		session.setAttribute("game", jogo);
		response.sendRedirect("Play.jsp?gameId=" + jogo.getId());
	}else{
		response.sendRedirect("FailedSearch.jsp");
	}
	%>
	
</body>
</html>