package domain;

import java.io.Serializable;

/**
 * Class responsible for creating the chess pieces
 * 
 * @author Joaquim Simoes, nº54599
 * @author Diogo Mateus, nº53374
 *
 */
public class ChessPiece implements Serializable {
	private static final long serialVersionUID = 1L;
	private Color color;
	private ChessPieceKind kind;

	/**
	 * Class constructor
	 * 
	 * @param color Color of the enumerate type Color of each ChessPiece
	 * @param kind  Kind of the enumerate type ChessPieceKind of a ChessPiece
	 */
	public ChessPiece(Color color, ChessPieceKind kind) {
		this.color = color;
		this.kind = kind;
	}

	/**
	 * Getter for a ChessPiece color
	 * 
	 * @return Returns the Color of a ChessPiece
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * Setter for the ChessPiece color
	 * 
	 * @param color Color of the enumerate type Color to be set
	 */
	public void setColor(Color color) {
		this.color = color;
	}

	/**
	 * Getter for the ChessPiece kind
	 * 
	 * @return Returns the ChessPieceKind of a ChessPiece
	 */
	public ChessPieceKind getChessPieceKind() {
		return kind;
	}

	/**
	 * Set a Piece has a determinate kind of piece
	 * 
	 * @param kind Type ChessPieceKind kind of a Piece
	 */
	public void setKind(ChessPieceKind kind) {
		this.kind = kind;
	}
	
	
	public String toStringHTML() {
        String htmlRepresentation = null;
        if (this.color == Color.WHITE) {
            switch (this.kind) {
            case KING:
                htmlRepresentation = "&#9812;";
                break;
            case QUEEN:
                htmlRepresentation = "&#9813;";
                break;
            case ROOK:
                htmlRepresentation = "&#9814;";
                break;
            case BISHOP:
                htmlRepresentation = "&#9815;";
                break;
            case KNIGHT:
                htmlRepresentation = "&#9816;";
                break;
            case PAWN:
                htmlRepresentation = "&#9817;";
                break;
            default:
                break;
            }
        } else {
            switch(this.kind) {
            case KING:
                htmlRepresentation = "&#9818;";
                break;
            case QUEEN:
                htmlRepresentation = "&#9819;";
                break;
            case ROOK:
                htmlRepresentation = "&#9820;";
                break;
            case BISHOP:
                htmlRepresentation = "&#9821;";
                break;
            case KNIGHT:
                htmlRepresentation = "&#9822;";
                break;
            case PAWN:
                htmlRepresentation = "&#9823;";
                break;
            default:
                break;
            }
        }
        return htmlRepresentation;
    }

	public String toIconsHTML() {
        String htmlRepresentation = null;
        if (this.color == Color.WHITE) {
            switch (this.kind) {
                case KING:
                htmlRepresentation = "<i class='fas fa-chess-king white-piece'></i> King";
                    break;
                case QUEEN:
                htmlRepresentation = "<i class='fas fa-chess-queen white-piece'></i> Queen";
                    break;
                case ROOK:
                htmlRepresentation = "<i class='fas fa-chess-rook white-piece'></i> Rook";
                    break;
                case BISHOP:
                htmlRepresentation = "<i class='fas fa-chess-bishop white-piece'></i> Bishop";
                    break;
                case KNIGHT:
                htmlRepresentation = "<i class='fas fa-chess-knight white-piece'></i> Knight";
                    break;
                case PAWN:
                    htmlRepresentation = "<i class='fas fa-chess-pawn white-piece'></i> Pawn";
                    break;
                default:
                    break;
            }
        } else {
            switch(this.kind) {
            case KING:
            htmlRepresentation = "<i class='fas fa-chess-king black-piece'></i> King";
                break;
            case QUEEN:
            htmlRepresentation = "<i class='fas fa-chess-queen black-piece'></i> Queen";
                break;
            case ROOK:
            htmlRepresentation = "<i class='fas fa-chess-rook black-piece'></i> Rook";
                break;
            case BISHOP:
            htmlRepresentation = "<i class='fas fa-chess-bishop black-piece'></i> Bishop";
                break;
            case KNIGHT:
            htmlRepresentation = "<i class='fas fa-chess-knight black-piece'></i> Knight";
                break;
            case PAWN:
				htmlRepresentation = "<i class='fas fa-chess-pawn black-piece'></i> Pawn";
				break;
            default:
                break;
            }
        }
        return htmlRepresentation;
    }

    public String toIconsHTML2() {
        String htmlRepresentation = null;
        if (this.color == Color.WHITE) {
            switch (this.kind) {
                case KING:
                htmlRepresentation = "<i class='fas fa-chess-king white-piece'></i>";
                    break;
                case QUEEN:
                htmlRepresentation = "<i class='fas fa-chess-queen white-piece'></i>";
                    break;
                case ROOK:
                htmlRepresentation = "<i class='fas fa-chess-rook white-piece'></i>";
                    break;
                case BISHOP:
                htmlRepresentation = "<i class='fas fa-chess-bishop white-piece'></i>";
                    break;
                case KNIGHT:
                htmlRepresentation = "<i class='fas fa-chess-knight white-piece'></i>";
                    break;
                case PAWN:
                    htmlRepresentation = "<i class='fas fa-chess-pawn white-piece'></i>";
                    break;
                default:
                    break;
            }
        } else {
            switch(this.kind) {
            case KING:
            htmlRepresentation = "<i class='fas fa-chess-king black-piece'></i>";
                break;
            case QUEEN:
            htmlRepresentation = "<i class='fas fa-chess-queen black-piece'></i>";
                break;
            case ROOK:
            htmlRepresentation = "<i class='fas fa-chess-rook black-piece'></i>";
                break;
            case BISHOP:
            htmlRepresentation = "<i class='fas fa-chess-bishop black-piece'></i>";
                break;
            case KNIGHT:
            htmlRepresentation = "<i class='fas fa-chess-knight black-piece'></i>";
                break;
            case PAWN:
				htmlRepresentation = "<i class='fas fa-chess-pawn black-piece'></i>";
				break;
            default:
                break;
            }
        }
        return htmlRepresentation;
    }

	@Override
	public String toString() {
		String cor = null;
		String tipo = null;
		cor = (this.color == Color.WHITE ? "w" : "b");

		switch (this.kind) {
		case KING:
			tipo = "K";
			break;
		case QUEEN:
			tipo = "Q";
			break;
		case BISHOP:
			tipo = "B";
			break;
		case KNIGHT:
			tipo = "k";
			break;
		case ROOK:
			tipo = "R";
			break;
		case PAWN:
			tipo = "P";
			break;
		}
		return tipo + cor;
	}
}