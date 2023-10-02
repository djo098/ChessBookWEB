package jUnitTest;

import persist.ChessGameDM;
import persist.ChessPlayerDM;
import domain.ChessBoard;
import domain.ChessGame;
import domain.ChessMove;
import domain.ChessPiece;
import domain.ChessPlayer;
import domain.ChessPosition;
import domain.IllegalMoveException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer;

@TestMethodOrder(MethodOrderer.MethodName.class)
public class JUnitTest {
	private static ChessPlayerDM cpdm;
	private static ChessGameDM cgdm;
	public static EntityManagerFactory emf = null;
	public static int i = 0;
	public static ChessGame cg;

//	@Test
//	public void testA() {
//		++i;
//		System.out.println("\n------ BEFORE ALL ------");
//		System.out.println(i + ") Creating instances");
//
//		emf = Persistence.createEntityManagerFactory("JPATestMySQL");
//		cpdm = ChessPlayerDM.getInstance();
//		cgdm = ChessGameDM.getInstance();
//	}
//
//	@Test
//	public void testZ() {
//		++i;
//		System.out.println("\n------ AFTER ALL ------");
//		System.out.println(i + ") Erasing database");
//		List<ChessGame> listToDeleteCG = cgdm.chessGamesList();
//		List<ChessPlayer> listToDeleteCP = cpdm.chessPlayersList();
//		cgdm.deleteAllGames(listToDeleteCG);
//		cpdm.deleteAllPlayers(listToDeleteCP);
//	}
//
//	@Test
//	public void testB() {
//		++i;
//		System.out.println("\n------ GET INSTANCE ------");
//		System.out.println(i + ") Testing the Singleton pattern");
//		ChessPlayerDM cpdm2 = ChessPlayerDM.getInstance();
//		assertTrue(cpdm == cpdm2);
//	}
//
//	@Test
//	public void testC() {
//		++i;
//		System.out.println("\n------ CREATE PLAYERS ------");
//		System.out.println(i + ") Asserting the player was created");
//
//		ChessPlayer p1 = cpdm.createPlayer(913618008, "Quim", "quim@chess.org");
//		cpdm.createPlayer(913295764, "Djo", "djo@chess.org");
//		ChessPlayer foundPlayer = cpdm.findByTel(913295764);
//		assertTrue(foundPlayer != null);
//		System.out.println("Players created 👍");
//	}
//
//	@Test
//	public void testD() {
//		++i;
//		System.out.println("\n------ LIST PLAYERS ------");
//		System.out.println(i + ") Asserting the player list isn't empty upon creating a player");
//		List<ChessPlayer> rp = cpdm.chessPlayersList();
//		System.out.println("ID\tName\tEmail");
//		for (ChessPlayer cp : rp) {
//			System.out.println(cp.getId() + "\t" + cp.getName() + "\t" + cp.getEmail());
//		}
//		assertTrue(rp.size() > 0);
//	}
//
//	@Test
//	public void testE() {
//		++i;
//		System.out.println("\n------ CREATE GAME ------");
//		System.out.println(i + ") Asserting that a game has been created");
//		ChessPlayer you = cpdm.findByTel(913295764);
//		ChessPlayer opponent = cpdm.findByTel(913618008);
//		cg = cgdm.createGame(you, opponent);
//		assertEquals(cg.getWhite(), you);
//		System.out.println("Game created with ID " + cg.getId() + " 👍");
//	}
//
//	@Test
//	public void testF() {
//		System.out.println("\n------ MAKE A MOVE ------");
//		System.out.println(i + ") Making a move");
//		ChessBoard board = cgdm.chessGamesList().get(0).getBoard();
//		ChessPlayer player = cpdm.findByTel(913295764);
//		Scanner in = new Scanner(System.in);
//		System.out.println(player.getTel() + " " + board.getNextTurn());
//		try {
//			main.Menu.loadTable(board, player, in, cg);
//		} catch (IllegalMoveException e) {
//			e.printStackTrace();
//		}
//		
//		assertEquals("The piece " + piece + " should be in position " + from, cg.getBoard().getPieceObj(0, 0), piece);
//	}
}
