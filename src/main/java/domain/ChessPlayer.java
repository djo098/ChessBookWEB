package domain;

import java.io.Serializable;
//import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * Class responsible for building an entity of a player
 * 
 * @author Joaquim Simoes, nº54599
 * @author Diogo Mateus, nº53374
 */
@Entity
public class ChessPlayer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "name", length = 50, nullable = false)
	private String name;

	@Column(name = "email", length = 255, nullable = false)
	private String email;

	@OneToMany
	private Set<ChessGame> games;

	/**
	 * Empty constructor for JPA purposes
	 */
	public ChessPlayer() {
	}

	/**
	 * Class constructor
	 * 
	 * @param name  A string with the name of a player
	 * @param email A string with the email of a player
	 */
	public ChessPlayer(String name, String email) {
		this.name = name;
		this.email = email;
	}

	/**
	 * Getter for the name of a ChessPlayer
	 * 
	 * @return Returns the ChessPlayer's name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Setter for the name of a ChessPlayer
	 * 
	 * @param name String with the name to be set
	 */
	public void setName(String name) {
		this.name = name;

	}

	/**
	 * Getter for the list of ChessGames the ChessPlayer is participating
	 * 
	 * @return Returns a list of ChessGames
	 */
	public Set<ChessGame> getGames() {
		return games;
	}

	/**
	 * Setter for the list of ChessGames, adds a ChessGame to the list of games
	 * 
	 * @param games ChessGame to be added
	 */
	public void setGames(ChessGame games) {
		this.games.add(games);
	}

	/**
	 * Getter for the email of a ChessPlayer
	 * 
	 * @return Returns the ChessPlayer's email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Setter for the email of a ChessPlayer
	 * 
	 * @param email String with the email to be set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Getter for database ID of a ChessPlayer
	 * 
	 * @return Returns the ID of the ChessPlayer
	 */
	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return getName() + "#" + getId();
	}
}
