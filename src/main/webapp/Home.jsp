<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="persist.ChessPlayerDM, domain.ChessMove, persist.ChessGameDM, domain.ChessGame, domain.ChessPlayer, domain.ChessBoard, java.util.List, java.util.Date, java.time.LocalDateTime, java.time.format.DateTimeFormatter"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ChessBook | Home</title>
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script src='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css'
	crossorigin='anonymous'></script>
<style>
<%@include file="css/main.css"%>
<%@include file="css/Home.css"%>
</style>
</head>
<body>
	<%
	ChessPlayerDM cpdm = ChessPlayerDM.getInstance();
	ChessGameDM cgdm = ChessGameDM.getInstance();
	ChessPlayer player = cpdm.findByEmail((String) session.getAttribute("email"));
	int yourId = player.getId();
	%>
	<div class="parent">
		<div class="nav-bar">
			<div class="nav-bar-left">
				<a href="Home.jsp" class="a-left"><i class="fa fa-home"></i></a>
			</div>

			<div class="nav-bar-centered">
				<a href="#" class="a-centered"> <%out.print(session.getAttribute("username"));%>
				</a>
			</div>
			<div class="nav-bar-right">
				<a href="ManageDB.jsp" class="a-right"><i class="fa fa-database"></i></a>
				<a href="ProcessLogout.jsp" class="a-right"><i
					class="fa fa-power-off"></i></a>
			</div>
		</div>

		

		<div class="active-games">
			<table class="styled-table" id="table1">
				<thead>
					<tr>
						<th colspan="2" style="text-align:right;">Active games</th>
						<th colspan="2" style="text-align:left;">Click to view board</th>
					</tr>
				</thead>
				<tbody>
					<%
					List<ChessGame> games = cgdm.findByPlayer(player);
					if (games.isEmpty()) {
						%>
						<tr id="empty-row">
						<td id="empty-data">No active games<br>Challenge someone by clicking their name or typing their email</td>
						</tr>
						<%
					} else {
				
					for (ChessGame game : games) {
						List<ChessMove> moves = game.getMoves();
						ChessBoard board = new ChessBoard(moves, game.getWhite(), game.getBlack());
						int nextId = board.getNextTurn();
					%>
					<tr class="tr-link">
						<td class="td-link">
							<%
							out.println(game.getId());
							%>
						</td>
						<td class="td-link" style="text-align:right;">
							<%
							if (game.getWhite().getName().equals(session.getAttribute("username"))) {
							%><strong>
								<%
								out.println(game.getWhite().getName());
								%>
						</strong> &#9812;
							<%
							} else {
							out.println(game.getWhite().getName() + " &#9812;");
							}
							%>
						</td>
						<td class="td-link" style="text-align:left;">
							<%
							if (game.getBlack().getName().equals(session.getAttribute("username"))) {
							%>&#9818; <strong><i>
								<%
								out.println(game.getBlack().getName());
								%>
						</i></strong>
							<%
							} else {
							out.println("&#9818; " + game.getBlack().getName());
							}
							%>
						</td>
						<%
						if (nextId == yourId) {
						%>
						<td class="td-link" id="your-turn" style="text-align:left;"><a
							href=<%out.println("Play.jsp?gameId=" + game.getId());%>>
								Your Turn</a></td>
						<% 
						} else {
						%>
						<td class="td-link" id="opp-turn" style="text-align:left;"><a
							href=<%out.println("Play.jsp?gameId=" + game.getId());%>>Not
								Your Turn...</a></td>
						<%
						}
						%>
					</tr>
					<%
					}
					}
					%>
				</tbody>
			</table>
		</div>
		
		<div class="list-players">
			<table class="players-table" id="table2">
				<thead>
					<tr>
						<th colspan="2" style="text-align:right;">Active players</th>
						<th colspan="2" style="text-align:left;">Click to challenge someone</th>
					</tr>
				</thead>
				<tbody>
					<%
					List<ChessPlayer> players = cpdm.chessPlayersList();
					for (ChessPlayer p : players) {
// 						List<ChessMove> moves = game.getMoves();
// 						ChessBoard board = new ChessBoard(moves, game.getWhite(), game.getBlack());
// 						int nextId = board.getNextTurn();
					%>
					<tr class="tr-link">
						<td class="td-link" style="text-align:right;">
							 <strong><span class="rank-id"></span></strong> <i class="fas fa-chess rank"></i>
						</td>
						<td class="td-link" style="text-align:right;">
							<%out.println(p.getId());%>
						</td>
						<td class="td-link" style="text-align:left;">
							<%out.println(p.getName());%>
						</td>
						<td class="td-link" style="text-align:left;">
							<%out.println(p.getEmail());%>
						</td>
					</tr>
					<%
					}
					%>
				</tbody>
			</table>
		</div>
		
		<div class="challenge-form challenger">
			<div class="centered-form">
				<form method="POST" action="FindOpponent.jsp">
					<fieldset>
						<legend>
							<span class="eyecandy"><i class='fas fa-chess-knight'
								style="color: white"></i></span> <strong>Challenge someone</strong>
						</legend>
						<input type="email" name="opponent"
							placeholder="Email of the opponent..." required> <input
							type="submit" value="Challenge">
					</fieldset>
				</form>
			</div>
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
<script>
function  table1() {
	var table = document.getElementById("table1");
    var rows = table.getElementsByTagName("tr");
    for (i = 0; i < rows.length; i++) {
        var currentRow = table.rows[i];
        var createClickHandler = 
            function(row) 
            {
        		return function() { 
					var cell = row.getElementsByTagName("td")[0];
                    var id = cell.innerHTML;
                    if (!isNaN(id)) {
                    	window.location.href="Play.jsp?gameId=" + id;
                    };
            	};
            };
        currentRow.onclick = createClickHandler(currentRow);
    }
   
}
	
function table2() {
	var table = document.getElementById("table2");
    var rows = table.getElementsByTagName("tr");
    for (i = 0; i < rows.length; i++) {
        var currentRow = table.rows[i];
        var createClickHandler = 
            function(row) 
            {
        		return function() { 
					var cell = row.getElementsByTagName("td")[3];
                    var id = cell.innerHTML;
                    window.location.href="FindOpponent.jsp?opponent="+id;
        		};
            };
        currentRow.onclick = createClickHandler(currentRow);
    }
}

function randomColors() {
	var colors = ['#f0eff4', '#0dab76', '#8093f1', '#a54657', '#2f2f2f'];
	var rank = document.getElementsByClassName('rank');
	var rankId = document.getElementsByClassName('rank-id');
	for (i = 0; i < rank.length; i++) {
		var random_color = colors[Math.floor(Math.random() * colors.length)];
		rank[i].style.color = random_color;
		rankId[i].style.color = random_color;
		if (random_color == '#f0eff4') {
			rankId[i].innerHTML = "Novice";
		} else if (random_color == '#0dab76') {
			rankId[i].innerHTML = "Beginner";
		} else if (random_color == '#8093f1') {
			rankId[i].innerHTML = "Intermediate";
		} else if (random_color == '#a54657') {
			rankId[i].innerHTML = "Advanced";
		} else if (random_color == '#2f2f2f') {
			rankId[i].innerHTML = "Expert";
		}
	}
}

window.onload = table1();
window.onload = table2();
window.onload = randomColors();
</script>
</html>