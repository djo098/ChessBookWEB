package persist;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import domain.ChessPlayer;
import main.Main;

/**
 * Class responsible for the persistence of a ChessPlayer instance
 * 
 * @author Joaquim Simoes, nº54599
 * @author Diogo Mateus, nº53374
 */
public class ChessPlayerDM extends ADataMapper<ChessPlayer> {
	// Singleton pattern
	private static ChessPlayerDM instance = new ChessPlayerDM(ChessPlayer.class);
	private static ADataMapper<ChessPlayer> dm = new ChessPlayerDM(null);

	/**
	 * Class Constructor
	 * 
	 * @param e An instance of the class ChessPlayer
	 */
	public ChessPlayerDM(Class<ChessPlayer> e) {
		super(ChessPlayer.class);
	}

	/**
	 * Method used for finding a ChessPlayer given an email address
	 * 
	 * @param e A string representing the email address of the player to find
	 * @return Returns null if the ChessPlayer doesn't exist in the database or the
	 *         ChessPlayer if it does
	 */
	public static ChessPlayer findByEmail(String e) {
		ChessPlayer result = null;
		EntityManager em = null;
		try {
			em = main.Main.emf.createEntityManager();
			TypedQuery<ChessPlayer> query = em.createQuery("SELECT cp FROM ChessPlayer cp WHERE cp.email = ?1",
					ChessPlayer.class);
			query.setParameter(1, e);
			result = query.getSingleResult();
		} catch (NoResultException ex) {
			System.out.println("ERROR: Player not Found");
			System.out.println("Please type the write phone number...");
		} finally {
			em.close();
		}
		return result;
	}

	/**
	 * Method used for finding all the ChessPlayers with the given name
	 * 
	 * @param name A string with the name number of the players to find
	 * @return Returns null if the name doesn't exist in the database or a list of
	 *         all the ChessPlayer if it does
	 */
	public static List<ChessPlayer> findByName(String name) {
		List<ChessPlayer> result = null;
		EntityManager em = null;
		try {
			em = main.Main.emf.createEntityManager();

			TypedQuery<ChessPlayer> query = em.createQuery("SELECT cp FROM ChessPlayer cp WHERE cp.name = ?1",
					ChessPlayer.class);
			query.setParameter(1, name);
			result = query.getResultList();

		} catch (NoResultException e) {
			System.out.println("ERROR: Player not Found");
			System.out.println("Please type the write phone number...");
		} finally {
			em.close();
		}
		return result;
	}

	/**
	 * Method responsible for creating and inserting a ChessPlayer in the database
	 * 
	 * @param name  A string with the name of the new player
	 * @param email A string with the email of the new player
	 * @return Returns the new ChessPlayer object
	 */
	public static ChessPlayer createPlayer(String name, String email) {
		ChessPlayer player = new ChessPlayer(name, email);
		dm.insert(player);
		return player;
	}

	/**
	 * This method lists out all the players in the database
	 * 
	 * @return A list with all the players that exist in the database
	 */
	public static List<ChessPlayer> chessPlayersList() {
		List<ChessPlayer> registeredPlayers = new ArrayList<ChessPlayer>();
		EntityManager em = Main.emf.createEntityManager();
		try {
			em.getTransaction().begin();
			TypedQuery<ChessPlayer> q = em.createQuery("SELECT cp FROM ChessPlayer cp", ChessPlayer.class);
			registeredPlayers = q.getResultList();
		} finally {
			em.close();
		}
		return registeredPlayers;
	}

	/**
	 * This method will delete all players or a smaller list of players from the
	 * database
	 * 
	 * @param listToDelete List of ChessPlayers to be deleted
	 */
	public static void deleteAllPlayers(List<ChessPlayer> listToDelete) {
		for (ChessPlayer chessPlayer : listToDelete) {
			dm.remove(chessPlayer);
		}
	}

	/**
	 * This method is used to obtain the Singleton pattern. It ensures the instance
	 * being used throughout an operation is the same
	 * 
	 * @return The instance of ChessPlayerDM
	 */
	public static ChessPlayerDM getInstance() {
		return instance;
	}
}
