package persist;

import domain.ChessGame;
import domain.ChessPlayer;
import main.Main;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 * Class responsible for the persistence of a ChessGame instance
 * 
 * @author Joaquim Simoes, nº54599
 * @author Diogo Mateus, nº53374
 */
public class ChessGameDM extends ADataMapper<ChessGame> {
	// Singleton pattern
	private static ChessGameDM instance = new ChessGameDM(ChessGame.class);
	private static ADataMapper<ChessGame> dm = new ChessGameDM(null);

	/**
	 * Class constructor
	 * 
	 * @param e An instance of the class ChessGame
	 */
	public ChessGameDM(Class<ChessGame> e) {
		super(ChessGame.class);
	}

	/**
	 * This function is used to find the games that a player is enrolled in, given
	 * an instance of ChessPlayer
	 * 
	 * @param p1 Instance of ChessPlayer
	 * @return Returns a list of ChessGame that the ChessPlayer is participating
	 */
	public static List<ChessGame> findByPlayer(ChessPlayer p1) {// ver com o moodle
		EntityManager em = null;
		try {
			em = main.Main.emf.createEntityManager();
			TypedQuery<ChessGame> query = em
					.createQuery("SELECT cg FROM ChessGame cg WHERE cg.white = :p OR cg.black= :q", ChessGame.class);
			query.setParameter("p", p1);
			query.setParameter("q", p1);
			return query.getResultList();
		} finally {
			if (em != null)
				em.close();
		}
	}

	/**
	 * This function is used to find the games that a player is enrolled in, given
	 * an email
	 * 
	 * @param email String with the given email
	 * @return Returns a list of ChessGame that the ChessPlayer is participating
	 */
	public static List<ChessGame> findPlayerEmail(String email) {// Diogo ver
		EntityManager em = null;
		try {
			em = main.Main.emf.createEntityManager();
			TypedQuery<ChessGame> query = em
					.createQuery("SELECT cg FROM ChessGame cg WHERE cg.white = :p OR cg.black= :q", ChessGame.class);
			query.setParameter("p", email);
			query.setParameter("q", email);
			return query.getResultList();
		} finally {
			if (em != null)
				em.close();
		}
	}

	/**
	 * This function will persist and create a ChessGame given two players
	 * 
	 * @param p1 ChessPlayer one, will be the white pieces
	 * @param p2 ChessPlayer two, will be the black pieces
	 * @return Returns the ChessGame after being persisted
	 */
	public static ChessGame createGame(ChessPlayer p1, ChessPlayer p2, Date startDate) {
		ChessGame game = null;
		EntityManager em = null;
		try {
			game = new ChessGame(p1, p2, new Date());
			em = main.Main.emf.createEntityManager();
			em.getTransaction().begin();
			em.persist(game);
			em.getTransaction().commit();
			return game;
		} finally {
			em.close();
		}
	}

	/**
	 * This method will get from the database all the existing ChessGames in it
	 * 
	 * @return Returns a list of existing ChessGames in the database
	 */
	public static List<ChessGame> chessGamesList() {
		List<ChessGame> existingGames = new ArrayList<ChessGame>();
		EntityManager em = Main.emf.createEntityManager();
		try {
			em.getTransaction().begin();
			TypedQuery<ChessGame> q = em.createQuery("SELECT cg FROM ChessGame cg", ChessGame.class);
			existingGames = q.getResultList();
		} finally {
			em.close();
		}
		return existingGames;
	}

	/**
	 * This method will delete all games or a smaller list of existing games from
	 * the database. Use before using ChessPlayerDM.deleteAllPlayers().
	 * 
	 * @param listToDelete List of ChessGames to be deleted
	 */
	public static void deleteAllGames(List<ChessGame> listToDelete) {
		for (ChessGame chessGame : listToDelete) {
			dm.remove(chessGame);
		}
	}

	/**
	 * This method is used to obtain the Singleton pattern. It ensures the instance
	 * being used throughout an operation is the same
	 * 
	 * @return The instance of ChessPlayerDM
	 */
	public static ChessGameDM getInstance() {
		return instance;
	}
}
