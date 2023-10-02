package domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Class responsible for building an entity of a game of chess with two players
 * and a list of moves
 * 
 * @author Joaquim Simoes, nº54599
 * @author Diogo Mateus, nº53374
 */
@Entity
public class ChessGame implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idGame;

	@JoinColumn(nullable = false)
	@ManyToOne
	private ChessPlayer white;

	@JoinColumn(nullable = false)
	@ManyToOne
	private ChessPlayer black;

	@JoinColumn(name = "moves")
	@OneToMany
	private List<ChessMove> moves;

	// private List<ChessGame> games;

	@Column(name = "initDate", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date startDate;

	/**
	 * Empty constructor for JPA purposes
	 */
	public ChessGame() {
	}

	/**
	 * Class constructor
	 * 
	 * @param p1 Player one, associated with white pieces
	 * @param p2 Player two, associated with black pieces
	 */
	public ChessGame(ChessPlayer p1, ChessPlayer p2, Date startDate) {
		this.white = p1;
		this.black = p2;
		this.moves = new ArrayList<ChessMove>();
		this.startDate = startDate;
	}

	/**
	 * Getter for the ID of the ChessGame in the database
	 * 
	 * @return Returns the database column ID of a ChessGame
	 */
	public int getId() {
		return idGame;
	}

	/**
	 * Getter for the ChessPlayer with white pieces
	 * 
	 * @return Returns the player with white pieces
	 */
	public ChessPlayer getWhite() {
		return white;
	}

	/**
	 * Getter for the ChessPlayer with black pieces
	 * 
	 * @return Returns the player with black pieces
	 */
	public ChessPlayer getBlack() {
		return black;
	}

	/**
	 * Getter for the date that the ChessGame was created
	 * 
	 * @return Returns the date of creation of a ChessGame
	 */
	public Date getInitDate() {
		return startDate;
	}

	/**
	 * Getter for the list of moves that exist in a ChessGame
	 * 
	 * @return A list of ChessMoves in a ChessGame
	 */
	public List<ChessMove> getMoves() {
		return moves;
	}

	/**
	 * Getter for the ChessBoard's current state
	 * 
	 * @return Returns the ChessBoard's current state
	 */
	public ChessBoard getBoard() {
		ChessBoard board = null;
		if (getMoves().isEmpty()) {
			board = new ChessBoard(getWhite(), getBlack());
		} else {
			board = new ChessBoard(getMoves(), getWhite(), getBlack());
		}
		return board;
	}

	/**
	 * Adds a move to the list of moves
	 * 
	 * @param mv Move of the type ChessMove to be added to the list
	 */
	public void addMove(ChessMove mv) throws IllegalMoveException {
		moves.add(mv);
	}
	
	public ChessPlayer findOpponent(ChessPlayer me) {
		if(this.white == me) {
			return this.black;
		}else {
			return this.white;
		}
	}

	@Override
	public String toString() {
		return white.getName() + " (White) VS (Black) " + black.getName();
	}
}
