package domain;

import java.io.Serializable;

/**
 * Class responsible for checking a ChessPiece's position and verifying their
 * alignments
 * 
 * @author Joaquim Simoes, nº54599
 * @author Diogo Mateus, nº53374
 */
public class ChessPosition implements Serializable {
	private static final long serialVersionUID = 1L;
	private int horizontal;
	private char vertical;

	/**
	 * Class Constructor
	 * 
	 * @param row An int with the row position of a ChessPiece
	 * @param col An int with the column position of a ChessPiece
	 */
	public ChessPosition(int row, char col) {
		this.horizontal = row;
		this.vertical = col;
	}

	/**
	 * Getter for the ChessPosition's row of a ChessPiece
	 * 
	 * @return Returns the row of a ChessPiece
	 */
	public int getRow() {
		return horizontal;
	}

	/**
	 * Getter for the ChessPosition's column of a ChessPiece
	 * 
	 * @return Returns the column of a ChessPiece
	 */
	public char getCol() {
		return vertical;
	}

	/**
	 * Check if the original ChessPosition of a ChessMove is aligned vertically with
	 * the final ChessPosition
	 * 
	 * @param p ChessPosition of the ChessPiece
	 * @return true if it is aligned vertically, false if not
	 */
	public boolean IsAllignedvert(ChessPosition p) {
		return (p.getCol() == this.vertical);
	}

	/**
	 * Check if the original ChessPosition of a ChessMove is aligned horizontally
	 * with the final ChessPosition
	 * 
	 * @param p ChessPosition of the ChessPiece
	 * @return true if it is aligned horizontally, false if not
	 */
	public boolean IsAllignedhor(ChessPosition p) {
		return (p.getRow() == this.horizontal);
	}

	/**
	 * Check if the original ChessPosition of a ChessMove is aligned diagonally with
	 * the final ChessPosition
	 * 
	 * @param p ChessPosition of the ChessPiece
	 * @return true if it is aligned diagonally, false if not
	 */
	public boolean IsAlligneddiag(ChessPosition p) {
		return (Math.abs(p.getRow() - this.horizontal) == Math.abs(p.vInt() - this.vInt()));
	}

	/**
	 * This function is responsible for converting the ChessPosition.vertical to an
	 * int value usable on the 2D ChessBoard array
	 * 
	 * @return An int value from 0 to 7 that represents the column in the 2D
	 *         ChessBoard array
	 */
	public int vInt() {
		switch (this.vertical) {
		case 'a':
			return 0;
		case 'b':
			return 1;
		case 'c':
			return 2;
		case 'd':
			return 3;
		case 'e':
			return 4;
		case 'f':
			return 5;
		case 'g':
			return 6;
		case 'h':
			return 7;
		case 'o':
			return -1;
		}
		return -1;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(vertical);
		sb.append(horizontal+1);
		return sb.toString();
	}
}
