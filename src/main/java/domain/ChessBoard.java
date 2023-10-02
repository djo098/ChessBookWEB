package domain;

import java.sql.Timestamp;
import java.util.List;

/**
 * Class responsible for building the board, turns and timers
 * 
 * @author Joaquim Simoes, nº54599
 * @author Diogo Mateus, nº53374
 *
 */
public class ChessBoard {
	private ChessPiece[][] initialGameState = { { new ChessPiece(Color.WHITE, ChessPieceKind.ROOK),
			new ChessPiece(Color.WHITE, ChessPieceKind.KNIGHT), new ChessPiece(Color.WHITE, ChessPieceKind.BISHOP),
			new ChessPiece(Color.WHITE, ChessPieceKind.QUEEN), new ChessPiece(Color.WHITE, ChessPieceKind.KING),
			new ChessPiece(Color.WHITE, ChessPieceKind.BISHOP), new ChessPiece(Color.WHITE, ChessPieceKind.KNIGHT),
			new ChessPiece(Color.WHITE, ChessPieceKind.ROOK) },
			{ new ChessPiece(Color.WHITE, ChessPieceKind.PAWN), new ChessPiece(Color.WHITE, ChessPieceKind.PAWN),
					new ChessPiece(Color.WHITE, ChessPieceKind.PAWN), new ChessPiece(Color.WHITE, ChessPieceKind.PAWN),
					new ChessPiece(Color.WHITE, ChessPieceKind.PAWN), new ChessPiece(Color.WHITE, ChessPieceKind.PAWN),
					new ChessPiece(Color.WHITE, ChessPieceKind.PAWN),
					new ChessPiece(Color.WHITE, ChessPieceKind.PAWN) },
			{ null, null, null, null, null, null, null, null }, { null, null, null, null, null, null, null, null },
			{ null, null, null, null, null, null, null, null }, { null, null, null, null, null, null, null, null },
			{ new ChessPiece(Color.BLACK, ChessPieceKind.PAWN), new ChessPiece(Color.BLACK, ChessPieceKind.PAWN),
					new ChessPiece(Color.BLACK, ChessPieceKind.PAWN), new ChessPiece(Color.BLACK, ChessPieceKind.PAWN),
					new ChessPiece(Color.BLACK, ChessPieceKind.PAWN), new ChessPiece(Color.BLACK, ChessPieceKind.PAWN),
					new ChessPiece(Color.BLACK, ChessPieceKind.PAWN),
					new ChessPiece(Color.BLACK, ChessPieceKind.PAWN) },
			{ new ChessPiece(Color.BLACK, ChessPieceKind.ROOK), new ChessPiece(Color.BLACK, ChessPieceKind.KNIGHT),
					new ChessPiece(Color.BLACK, ChessPieceKind.BISHOP),
					new ChessPiece(Color.BLACK, ChessPieceKind.QUEEN), new ChessPiece(Color.BLACK, ChessPieceKind.KING),
					new ChessPiece(Color.BLACK, ChessPieceKind.BISHOP),
					new ChessPiece(Color.BLACK, ChessPieceKind.KNIGHT),
					new ChessPiece(Color.BLACK, ChessPieceKind.ROOK) } };

	private ChessPiece[][] GameState;
	private ChessPlayer white = null;
	private ChessPlayer black = null;
	private ChessMove lastTurn = null;
	private Timestamp startTurn = null;
	public boolean gameisover = false;

	/**
	 * Class constructor<br>
	 * Use this when initializing the board for the first time
	 * 
	 * @param player1 Player of the type ChessPlayer associated with white pieces
	 * @param player2 Player of the type ChessPlayer associated with black pieces
	 */
	public ChessBoard(ChessPlayer player1, ChessPlayer player2) {
		this.GameState = initialGameState;
		this.white = player1;
		this.black = player2;
	}

	/**
	 * Class constructor<br>
	 * Initializes the board for ongoing games with a list of moves
	 * 
	 * @param movelist List with the moves made by the players
	 * @param player1  Player of the type ChessPlayer associated with white pieces
	 * @param player2  Player of the type ChessPlayer associated with black pieces
	 */
	public ChessBoard(List<ChessMove> movelist, ChessPlayer player1, ChessPlayer player2) {
		this.GameState = initialGameState;
		this.white = player1;
		this.black = player2;
		for (ChessMove move : movelist) {
			try {
				update(move);
				this.lastTurn = move; // Saves last move made
			} catch (Exception e) {
				if ((move.getOrigin().getCol() == 'z') && (move.getDestination().getCol() == 'z')
						&& (move.getOrigin().getRow() == 0) && (move.getDestination().getRow() == 0)
						&& (move.getCp().getChessPieceKind() == ChessPieceKind.KING)) {
					this.gameisover = true;
					break;
					// Ends the game and leaves the board
				}
			}
		}
	}

	/**
	 * Updates the board with the ChessMove made
	 * 
	 * @param cm Move of the type ChessMove with the new position for a ChessPiece
	 * @throws IllegalMoveException Thrown if the ChessMove given is illegal
	 */
	public void update(ChessMove cm) throws IllegalMoveException {
		if (validMoveCheck(cm)) {
			ChessPosition from = cm.getOrigin();
			ChessPosition to = cm.getDestination();
			try {
				this.GameState[from.getRow()][charToInt(from.getCol())] = null;
				this.GameState[to.getRow()][charToInt(to.getCol())] = cm.getCp();
				this.lastTurn = cm; // Saves the last move made
			} catch (Exception e) {
				throw e;
			}
		} else {
			System.out.println("Invalid move...");
		}
	}

	/**
	 * This function is responsible for converting a char to an int value usable on
	 * the 2D ChessBoard array
	 * 
	 * @param column Char from 'a' to 'h' that represents the column of the
	 *               ChessPiece's position on the board
	 * @return An int value from 0 to 7 that represents the column in the 2D
	 *         ChessBoard array
	 */
	private static int charToInt(char column) {
		int val = -1;
		switch (column) {
		case 'a':
			val = 0;
			break;
		case 'b':
			val = 1;
			break;
		case 'c':
			val = 2;
			break;
		case 'd':
			val = 3;
			break;
		case 'e':
			val = 4;
			break;
		case 'f':
			val = 5;
			break;
		case 'g':
			val = 6;
			break;
		case 'h':
			val = 7;
			break;

		default:
			break;
		}
		return val;
	}

	/**
	 * This function checks the validity of a ChessMove according to the
	 * ChessPieceKind's chess rules
	 * 
	 * @param cm Move of the type ChessMove given to the ChessPiece
	 * @return Will return true if the ChessMove was validated and false if not
	 */
	public boolean validMoveCheck(ChessMove cm) {
		switch (cm.getCp().getChessPieceKind()) {
		case KING:
			return (checkInBounds(cm) && checkFriendlyOverlap(cm)
					&& checkMoveBlocking(cm, new ChessPosition(-1, 'o'), true, 1));
		case QUEEN:
			return (checkInBounds(cm) && checkFriendlyOverlap(cm)
					&& (cm.getOrigin().IsAllignedhor(cm.getDestination())
							|| cm.getOrigin().IsAllignedvert(cm.getDestination())
							|| cm.getOrigin().IsAlligneddiag(cm.getDestination()))
					&& checkMoveBlocking(cm, new ChessPosition(-1, 'o'), true, 9));
		case BISHOP:
			// TODO Make the Bishop validity check
			return true;
		case KNIGHT:
			// TODO Make the Knight validity check
			return true;
		case ROOK:
			return (checkInBounds(cm) && checkFriendlyOverlap(cm)
					&& (cm.getOrigin().IsAllignedhor(cm.getDestination())
							|| cm.getOrigin().IsAllignedvert(cm.getDestination()))
					&& checkMoveBlocking(cm, new ChessPosition(-1, 'o'), true, 9));
		case PAWN:
			// TODO Make the Pawn validity check
			return true;
		}
		return false;
	}

	/**
	 * This function checks if the ChessMove given to a Piece don't cross the limits
	 * of the board
	 * 
	 * @param cm Move of the type ChessMove given to the ChessPiece
	 * @return Will return true if the move given is inside the board's limits or
	 *         false if it isn't
	 */
	public boolean checkInBounds(ChessMove cm) {
		return (cm.getDestination().getRow() < 0 || cm.getDestination().getCol() < 0
				|| cm.getDestination().getCol() >= GameState.length
				|| cm.getDestination().getRow() >= GameState[0].length);
	}

	/**
	 * Check if the move hit a place where was a Piece of other color
	 * 
	 * @param cm Move of the type ChessMove given to the ChessPiece
	 * @return Will return true if there is NO overlap of same color pieces or false
	 *         if there is
	 */
	private boolean checkFriendlyOverlap(ChessMove cm) {
		return this.GameState[cm.getDestination().getRow()][cm.getDestination().vInt()] == null
				|| this.GameState[cm.getDestination().getRow()][cm.getDestination().vInt()].getColor() != cm.getCp()
						.getColor();
	}

	/**
	 * This recursive function will check if the ChessMove made is being blocked by
	 * another ChessPiece
	 * 
	 * @param cm           Move of the type ChessMove given to the ChessPiece
	 * @param nextPosCheck Position of the type ChessPosition that will be evaluated
	 *                     next
	 * @param init         A boolean value that, if false, will check whether the
	 *                     nextPosCheck is not null
	 * @param limit        An int value that represents the limit of a
	 *                     ChessPieceKind's moves (Example: KING has a limit of a 1
	 *                     diagonal/vert/hor position move)
	 * @return Returns true if the move is NOT blocked, false if it is
	 */
	private boolean checkMoveBlocking(ChessMove cm, ChessPosition nextPosCheck, boolean init, int limit) {
		// It will reach the possibilities' limits and return false
		if (limit < 0)
			return false;
		// If the destination of the ChessMove isn't blocked, will return true
		if (cm.getDestination().getRow() == nextPosCheck.getRow()
				&& cm.getDestination().vInt() == nextPosCheck.vInt()) {
			return true;
		}
		// If init is false, it will check if the next possible position currently being
		// evaluated is blocked A.K.A not null
		if (!init)
			if (this.GameState[nextPosCheck.getRow()][nextPosCheck.vInt()] != null)
				return false;
		// If neither of the previous conditions returned true/false,
		// it will assign the new positions to be checked on the next iteration
		int toRow = cm.getDestination().getRow();
		int toCol = cm.getDestination().vInt();
		int fromRow = 0;
		int fromCol = 0;
		if (init) {
			fromRow = cm.getOrigin().getRow();
			fromCol = cm.getOrigin().vInt();
		} else {
			fromRow = nextPosCheck.getRow();
			fromCol = nextPosCheck.vInt();
		}
		int nextPosRow = -1;
		if (toRow > fromRow) {
			nextPosRow = fromRow + 1;

		} else if (toRow < fromRow) {
			nextPosRow = fromRow - 1;
		} else {
			nextPosRow = fromRow;
		}
		int nextPosCol = -1;
		if (toCol > fromCol) {
			nextPosCol = fromCol + 1;
		} else if (toCol < fromCol) {
			nextPosCol = fromCol - 1;
		} else {
			nextPosCol = fromCol;
		}
		return checkMoveBlocking(cm, new ChessPosition(nextPosRow, intToChar(nextPosCol)), false, limit - 1);
	}

	/**
	 * This function is responsible for converting an int to a char value
	 * 
	 * @param n An int from '0' to '7' that represents the numeric value of the
	 *          column of the ChessPiece's position on the board
	 * @return A char value from 'a' to 'h' that represents the column in the 2D
	 *         ChessBoard array
	 */
	public char intToChar(int n) {
		switch (n) {
		case 0:
			return 'a';
		case 1:
			return 'b';
		case 2:
			return 'c';
		case 3:
			return 'd';
		case 4:
			return 'e';
		case 5:
			return 'f';
		case 6:
			return 'g';
		case 7:
			return 'h';
		default:
			return ' ';
		}
	}

	/**
	 * Get the phone number of the player that will play next
	 * 
	 * @return Returns the phone number of the next player to play based on the last
	 *         one who played
	 */
	public int getNextTurn() {
		Color color = getLastTurnColor();
		if (color == Color.WHITE) {
			return black.getId();
		} else {
			return white.getId();
		}
	}

	/**
	 * Get the color of the last turn's player
	 * 
	 * @return return the last color who played VER!!!!!
	 */
	public Color getLastTurnColor() {
		Color color = Color.BLACK;
		if (this.lastTurn != null) {
			color = lastTurn.getCp().getColor();
		}
		return color;
	}

	/**
	 * Gets a string with the name of the ChessPiece given a position on the board
	 * 
	 * @param i An int representing an horizontal position on the board
	 * @param j An int representing a vertical position on the board
	 * @return Returns a string with the name of the piece in that position, null if
	 *         position is empty
	 */
	public String getPiece(int i, int j) {
		return (this.GameState[i][j] == null ? null : this.GameState[i][j].toString());
	}

	/**
	 * Gets the ChessPiece object given a position on the board
	 * 
	 * @param i An int representing an horizontal position on the board
	 * @param j An int representing a vertical position on the board
	 * @return Returns the ChessPiece object in that position, null if position is
	 *         empty
	 */
	public ChessPiece getPieceObj(int i, int j) {
		return (this.GameState[i][j] == null ? null : this.GameState[i][j]);
	}

	/**
	 * This function initializes the startTurn parameter with the system's current
	 * time in milliseconds
	 */
	public void startTimer() {
		this.startTurn = new Timestamp(System.currentTimeMillis());
	}

	/**
	 * Getter for the time in seconds it took to make a move
	 * 
	 * @return returns the time in seconds of a move
	 */
	public int getTimer() {
		Timestamp now = new Timestamp(System.currentTimeMillis());
		return (int) (now.getTime() - this.startTurn.getTime()) / 1000; // Seconds
	}

	/**
	 * Checks the color of the logged player
	 * 
	 * @param cp Player of the type ChessPlayer that is currently playing
	 * @return Returns the enumerate type Color of the player that is currently
	 *         playing
	 */
	public Color myColorCheck(ChessPlayer cp) {
		Color color = null;
		if (this.white.getEmail().equals(cp.getEmail())) {
			color = Color.WHITE;
		} else {
			color = Color.BLACK;
		}
		return color;
	}

	/**
	 * This method is responsible for updating the player's turn if the move was
	 * made correctly
	 * 
	 * @param cm Move of the type ChessMove given to the ChessPiece
	 * @param cp Player of the type ChessPlayer that is currently playing
	 * @return Returns true if the player status was updated, false if it wasn't
	 * @throws IllegalMoveException Thrown if the move a player is trying to make is
	 *                              illegal
	 */
	public boolean updatePlayer(ChessMove cm, ChessPlayer cp) throws IllegalMoveException {
		ChessPosition origin = cm.getOrigin();
		if (this.GameState[origin.getRow()][charToInt(origin.getCol())] == null) {
			throw new IllegalMoveException("You tried to move a piece on "+cm.getOrigin()+" but there's nothing there...");
		}
		if ((myColorCheck(cp) == cm.getCp().getColor()) && ((this.lastTurn == null) || (getNextTurn() == cp.getId()))) {
			if (!validMoveCheck(cm)) {
				throw new IllegalMoveException(
						"Make sure you aren't overlapping your own pieces or that you aren't going outside of the chessboard's borders...");
			}
			update(cm);
			return true;
		} else {
			throw new IllegalMoveException("The piece you tried to move is not yours...");
		}
	}
	
	
	public Color whoKillWho() {
		boolean whiteKiller = false;
		boolean blackKiller = false;
		for (ChessPiece[] row: this.GameState) {
			for (ChessPiece piece : row) {
				if( piece != null && piece.getChessPieceKind()==ChessPieceKind.KING) {
					if (piece.getColor()==Color.WHITE) {
						blackKiller = true;
						
					}else {
						whiteKiller = true;
					}
				}
				
			}
		}
		
		if (!blackKiller) {
			return Color.WHITE;
		}
		if (!whiteKiller) {
			return Color.BLACK;
		}
		
		return null;
	}
	
	

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("  ");
		for (int i = 0; i < 8; i++) {
			sb.append("  ").append((char) ('a' + i)).append("  ");
		}
		sb.append("\n  +");
		for (int i = 0; i < 8; i++) {
			sb.append("----+");
		}
		sb.append("\n");
		for (int i = 7; i >= 0; i--) {
			sb.append("").append(i + 1).append(" ");
			for (int j = 0; j < 8; j++) {
				if (getPiece(i, j) != null) {
					sb.append("| ").append(getPiece(i, j)).append(" ");
				} else {
					sb.append("|    ");
				}
			}
			sb.append("|\n  +");
			for (int k = 0; k < 8; k++) {
				sb.append("----+");
			}
			sb.append("\n");
		}
		return sb.toString();
	}
	
	public String toIconsHTML() {
		StringBuilder sb = new StringBuilder();
		sb.append("<table class='game-table'><thead><tr>");
		sb.append("<th></th>");
		for (int i = 0; i < 8; i++) {
			sb.append("<th>").append((char) ('a' + i)).append("</th>");
		}
		sb.append("</tr></thead>");
		sb.append("<tbody>");
		for (int i = 7; i >= 0; i--) {
			sb.append("<tr><td class='first-col'>").append(i + 1).append("</td>");
		    for (int j = 0; j < 8; j++) {
	            if (getPiece(i, j) != null) {
	        	    sb.append("<td>").append(getPieceObj(i, j).toIconsHTML2()).append("</td>");
	            } else {
        	        sb.append("<td></td>");
	            }
	        }
	        sb.append("</tr>");
	    }
		sb.append("</tbody></table>");
	    return sb.toString();
	}
}