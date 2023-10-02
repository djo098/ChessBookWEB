package domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

/**
 * Class responsible for the creation of a move of chess
 * 
 * @author Joaquim Simoes, nº54599
 * @author Diogo Mateus, nº53374
 *
 */
@Entity
public class ChessMove implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idMove;

	@Column(name = "origin", nullable = false)
	private ChessPosition origin;
	@Column(name = "destination", nullable = false)
	private ChessPosition destination;
	@Column(name = "duration", nullable = false)
	private int duration;
	@Column(name = "ChessPiece", nullable = false)
	private ChessPiece cp;
	@JoinColumn(name = "player", nullable = true)
	private ChessPlayer p;

	/**
	 * Empty constructor for JPA purposes
	 */
	public ChessMove() {
	}

	/**
	 * Class constructor
	 * 
	 * @param origin      Original ChessPosition of a ChessPiece
	 * @param destination ChessPosition of a ChessPiece after a ChessMove
	 * @param duration    An int with the time duration of a play
	 * @param cp          ChessPiece that will be moved
	 */
	public ChessMove(ChessPiece cp, ChessPosition origin, ChessPosition destination) {
		this.origin = origin;
		this.destination = destination;
		this.cp = cp;
	}

	/**
	 * Getter for the original position of a ChessPiece
	 * 
	 * @return Returns the original position of a ChessPiece
	 */
	public ChessPosition getOrigin() {
		return this.origin;
	}

	/**
	 * Getter for the ChessPosition of a ChessPiece after a ChessMove
	 * 
	 * @return Returns the ChessPosition of a ChessPiece after a ChessMove
	 */
	public ChessPosition getDestination() {
		return this.destination;
	}

	/**
	 * Getter for the duration of time spent making a ChessMove
	 * 
	 * @return Returns the time spent making a ChessMove
	 */
	public int getDuration() {
		return this.duration;
	}

	/**
	 * Getter for the ChessPiece to be moved
	 * 
	 * @return Returns the ChessPiece to be moved
	 */
	public ChessPiece getCp() {
		return this.cp;
	}
}
