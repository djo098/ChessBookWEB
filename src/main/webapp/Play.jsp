<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ page import="java.util.List"%>
<%@ page import="java.time.LocalDateTime"%>
<%@ page import="java.sql.Timestamp"%>
<%@ page import="java.time.format.DateTimeFormatter"%>

<%@ page import="main.Menu"%>

<%@ page import="persist.ChessGameDM"%>
<%@ page import="persist.ChessPlayerDM"%>

<%@ page import="domain.ChessPlayer"%>
<%@ page import="domain.ChessBoard"%>
<%@ page import="domain.ChessPosition"%>
<%@ page import="domain.ChessPiece"%>
<%@ page import="domain.ChessMove"%>
<%@ page import="domain.ChessGame"%>
<%@ page import="domain.ChessPieceKind"%>
<%@ page import="domain.Color"%>


<%
ChessPlayerDM cpdm = ChessPlayerDM.getInstance();
ChessGameDM cgdm = ChessGameDM.getInstance();
ChessPlayer player = cpdm.findByEmail((String) session.getAttribute("email"));

int gid = Integer.parseInt(request.getParameter("gameId"));

ChessGame game = cgdm.find(ChessGame.class, gid);
%>
<meta charset="UTF-8">
<title>ChessBook | <%=game.getWhite().getName()%> VS <%=game.getBlack().getName()%></title>
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script src='https://kit.fontawesome.com/a076d05399.js' crossorigin='anonymous'></script>
<style>
	<%@include file="css/main.css"%>
	<%@include file="css/Play.css"%>
</style>
</head>
<body>
	<%
	String move = request.getParameter("move");
	ChessBoard table = new ChessBoard(game.getMoves(), game.getWhite(), game.getBlack());
	boolean myTurn = player.getId() == table.getNextTurn();
	%>
	<div class="parent">
		<div class="nav-bar">
			<div class="nav-bar-left">
				<a href="Home.jsp" class="a-left"><i class="fa fa-home"></i></a>
			</div>

			<div class="nav-bar-centered">
				<a href="#" class="a-centered"><%
					if (game.getWhite().getName().equals(session.getAttribute("username").toString())) {%>
						<strong>you <i class='fas fa-chess-king white-piece'></i></strong> vs
						<%out.println(game.getBlack().getName());%>
						<i class='fas fa-chess-king black-piece'></i><%
					} else {
						out.println(game.getWhite().getName());%>
						<i class='fas fa-chess-king white-piece'></i> vs <strong>You <i class='fas fa-chess-king black-piece'></i></strong><%
					}%>
				</a>
			</div>
			<div class="nav-bar-right">
				<a href="ManageDB.jsp" class="a-right"><i class="fa fa-database"></i></a>
				<a href="ProcessLogout.jsp" class="a-right"><i
					class="fa fa-power-off"></i></a>
			</div>
		</div>
		
		<div class="board-area">
			<%
			if (move != null) {
				if (move.equals("resign")) {
					Color winner;
					if (player.getName() == game.getWhite().getName()) {
						winner = Color.BLACK;
					} else {
						winner = Color.WHITE;
					}
					ChessMove end = new ChessMove(new ChessPiece(winner, ChessPieceKind.KING), new ChessPosition(0, 'z'),
					new ChessPosition(0, 'z'));
					game.addMove(end);
					ChessGameDM obj = new ChessGameDM(ChessGame.class);
					try {
						obj.update(game);
					} catch (Exception e) {
						out.println("Changes NOT applied, Contact administration for further information");
					}
					session.setAttribute("isWinner", false);
					response.sendRedirect("Play.jsp?gameId="+gid);
				} else {
					ChessMove newMove = null;
					boolean incoherentMove = false;
					try {
				char[] posArray = move.toCharArray();
				char fromCol = posArray[0];
				int fromRow = Character.getNumericValue(posArray[1])-1;
				char toCol = posArray[3];
				int toRow = Character.getNumericValue(posArray[4])-1;

				ChessPosition origin = new ChessPosition(fromRow, fromCol);
				ChessPosition destination = new ChessPosition(toRow, toCol);

				ChessPiece piece = table.getPieceObj(origin.getRow(), origin.vInt());

				newMove = new ChessMove(piece, origin, destination);

				myTurn = !table.updatePlayer(newMove, player);
					} catch (Exception e) {
				incoherentMove = true;
					}
					//Se a jogada for bem sucedida, entao temos de guardar
					if ((!myTurn) && (!incoherentMove)) {
				game.addMove(newMove);
				ChessGameDM obj = new ChessGameDM(ChessGame.class);
				try {
					obj.update(game);
				} catch (Exception e) {
					out.println("Changes NOT applied, Contact administration for further information");
				}
				session.setAttribute("game", game);
					}
				}

			} else {
				myTurn = (player.getId() == table.getNextTurn());
			}
			Color kingDeath = null;
			kingDeath = table.whoKillWho();
			%>

			<div id="table">
				<div>
					<%
					out.print(table.toIconsHTML());
					%>
				</div>
			</div>
		</div>

		<div class="list-moves">
			<table class="moves-table" id="table2">
				<thead>
					<tr>
						<th colspan="4" style="text-align:center;">List of moves</th>
					</tr>
				</thead>
				<tbody>
					<%
					List<ChessMove> cm = game.getMoves();
					int i = 1;
					for (ChessMove m : cm) {
					%>
					<tr>
						<td>
							<span style="float:left"><%out.println(i);%></span>
							<strong><%
								if (m.getCp().getColor() == Color.WHITE) {
									out.println(game.getWhite().getName());
								} else {
									out.println(game.getBlack().getName());
								}
							%></strong>
						</td>
						<td>From: <%out.println(m.getOrigin());%></td>
						<td>To: <%out.println(m.getDestination());%></td>
						<td><%out.println(m.getCp().toIconsHTML());%></td>
					</tr>
					<%
						i++;
					}
					%>
				</tbody>
			</table>
		</div>

		<div class="move-form move">
			<%
			if ((kingDeath != null) || (table.gameisover)) {
				ChessPlayer winner;

				if (kingDeath == Color.WHITE) {
					winner = game.getBlack();
				} else if (kingDeath == Color.BLACK) {
					winner = game.getWhite();
				} else {
					if (table.getLastTurnColor() == Color.WHITE) {
				winner = game.getWhite();
					} else {
				winner = game.getBlack();
					}
				}
				if (move != null) {
					session.setAttribute("isWinner", winner.getId() == player.getId());
					ServletContext sc = getServletContext();
					RequestDispatcher r = sc.getRequestDispatcher("EndScreen.jsp");
					r.forward(request, response);
				}
			%>
				<div class="centered-p">
					<p>
						The game has ended and
						<%=winner.getName()%>
						won.
					</p>
				</div>
			<%
			} else if (!myTurn) {
			%>
				<div class="centered-p">
					<p>
						Waiting for <%out.println(cpdm.find(ChessPlayer.class, table.getNextTurn()).getName());%> to make his move...
					</p>
				</div>
			<%
			} else {
			%>
				<div class="centered-form">
					<form method="POST" action=<%="Play.jsp?gameId=" + gid%>>
						<fieldset>
							<legend>
								<span class="eyecandy"><i class='fa fa-hourglass-3'
									style="color: white"></i></span> <strong>Plan your next move</strong>
							</legend>
							<input type="text" name="move"
								placeholder="For example: 'a7 a6' &#8226; Type 'resign' to give up" required>
								<input type="submit" value="Move">
						</fieldset>
					</form>
				</div>
				<%
			}
			%>
		</div>

		<div class="footer">
			<div class="footer-left">
				ChessBookWEB <span>&#8226;</span>  Diogo Mateus 53374 <span>&#8226;</span>  Joaquim Simoes 54599
			</div>
			<div class="footer-right">
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