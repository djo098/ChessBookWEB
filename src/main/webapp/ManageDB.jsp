<%@ page language="java" contentType="text/html; charset=UTF-8"
import="java.util.List, persist.ChessPlayerDM, domain.ChessPlayer, persist.ChessGameDM, domain.ChessGame"
	pageEncoding="UTF-8"%>
<%
ChessPlayerDM cpdm = ChessPlayerDM.getInstance();
ChessGameDM cgdm = ChessGameDM.getInstance();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ManageDB</title>
<style><%@include file="css/main.css"%></style>
<style><%@include file="css/ManageDB.css"%></style>
</head>
<body>
	<a href="" onclick="history.back()">Back</a>
	<div class="row">
		<div class="column">
			<table>
				<caption>ChessPlayer's DB</caption>
				<thead>
					<tr>
						<th>PlayerID</th>
						<th>Name</th>
						<th>Email</th>
					</tr>
				</thead>
				<tbody>
					<%
					List<ChessPlayer> rp = cpdm.chessPlayersList();
					for (ChessPlayer cp : rp) {
						out.println("      <tr>");
						out.println("<td>" + cp.getId() + "</td><td>" + cp.getName() + "</td><td>" + cp.getEmail() + "</td>");
						out.println("      </tr>");
					}
					%>
				</tbody>
			</table>
		</div>
		<div class="column">
			<table>
				<caption>ChessGame's DB</caption>
				<thead>
					<tr>
						<th scope='col'>GameID</th>
						<th scope='col'>White</th>
						<th scope='col'>Black</th>
					</tr>
				</thead>
				<tbody>
					<%
					List<ChessGame> allcg = cgdm.chessGamesList();
					for (ChessGame cg : allcg) {
						out.println("      <tr>");
						out.println("<td>" + cg.getId() + "</td><td>" + cg.getWhite() + "</td><td>" + cg.getBlack() + "</td>");
						out.println("      </tr>");
					}
					%>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>