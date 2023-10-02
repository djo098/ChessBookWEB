package main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import domain.ChessBoard;
import domain.ChessGame;
import domain.ChessMove;
import domain.ChessPiece;
import domain.ChessPlayer;
import domain.ChessPosition;
import domain.IllegalMoveException;
import persist.ChessGameDM;
import persist.ChessPlayerDM;

/**
 * Class responsible for the main menu of the game
 * 
 * @author Joaquim Simoes, nº54599
 * @author Diogo Mateus, nº53374
 */

public class Menu {
	/**
	 * Main menu of the project
	 * 
	 * @param in User input
	 * @throws IOException
	 * @throws IllegalMoveException
	 */
	static void mainMenu(Scanner in) throws IOException, IllegalMoveException {
		// alias exec="mvn exec:java -Dexec.mainClass=main.Main -Dexec.cleanupDaemonThreads=false"
		boolean end = false;
		do {
			System.out.println("\033[H\033[2J");
			System.out.flush();
			System.out.println("+--------------------+");
			System.out.println("| Login .......... 1 |");
			System.out.println("| New account .... 2 |");
			System.out.println("| Exit ........... 0 |");
			System.out.println("+--------------------+");
			System.out.println("Choose an option...");
			ChessPlayer player = null;
			switch (nextInt(in)) {
			case 1:
				System.out.println("\033[H\033[2J");
				System.out.flush();
				System.out.println("+-------------------+");
				System.out.println("|       Login       |");
				System.out.println("+-------------------+");
				System.out.println("Authenticate with your email");
				String e = nextLine(in);
				player = login(in, e);
				if (player == null) {
					System.out.println("\nThis player does not exist...");
					System.out.println("Please make sure you typed the right number");
				} else {
					System.out.println("\nLogin successfull");
					loggedInMenu(in, player);
				}
				break;
			case 2:
				System.out.println("\033[H\033[2J");
				System.out.flush();
				System.out.println("+--------------------+");
				System.out.println("|   Create account   |");
				System.out.println("+--------------------+");
				System.out.println("Name: ");
				String name = nextLine(in);
				System.out.println("Email: ");
				String email = nextLine(in);
				player = ChessPlayerDM.createPlayer(name, email);
				System.out.println("Player created");
				break;
			case 0:
				end = true;
				System.out.println("Exiting...");
				break;
			}
		} while (!end);
	}

	/**
	 * Main menu of a logged in player
	 * 
	 * @param in           User input
	 * @param loggedPlayer ChessPlayer that is already registered
	 * @throws IOException
	 * @throws IllegalMoveException
	 */
	static void loggedInMenu(Scanner in, ChessPlayer loggedPlayer) throws IOException, IllegalMoveException {
		boolean end = false;
		do {
			System.out.println("\033[H\033[2J");
			System.out.flush();
			System.out.println("+--------------------+");
			System.out.println("| Active games ... 1 |");
			System.out.println("| New game ....... 2 |");
			System.out.println("| Player list .... 3 |");
			System.out.println("| Logout ......... 0 |");
			System.out.println("+--------------------+");
			System.out.println("Choose an option...");
			switch (nextInt(in)) {
			case 1:
				System.out.println("+--------------------+");
				System.out.println("| Your ongoing games |");
				System.out.println("+--------------------+");
				System.out.println("|  Join one of them  |");
				System.out.println("| by typing their id |");
				System.out.println("+--------------------+");
				showActiveGames(in, loggedPlayer);
				break;
			case 2:
				System.out.println("\033[H\033[2J");
				System.out.flush();
				System.out.println("+--------------------+");
				System.out.println("|   Create new game  |");
				System.out.println("+--------------------+");
				createNewGame(in, loggedPlayer);
				break;
			case 3:
				System.out.println("+--------------------+");
				System.out.println("| Registered players |");
				System.out.println("+--------------------+");
				System.out.println("ID\tName\tEmail");
				List<ChessPlayer> rp = ChessPlayerDM.chessPlayersList();
				for (ChessPlayer cp : rp) {
					System.out.println(cp.getId() + "\t" + cp.getName() + "\t" + cp.getEmail());
				}
				System.out.println("Press \"ENTER\" to continue...");
				Scanner scanner = new Scanner(System.in);
				scanner.nextLine();
				break;
			case 0:
				end = true;
				System.out.println("Logging out...");
				break;
			}
		} while (!end);
	}

	/**
	 * Show the list of active games of the logged in player
	 * 
	 * @param in User input
	 * @param cp Logged in ChessPlayer
	 * @throws IllegalMoveException
	 */
	private static void showActiveGames(Scanner in, ChessPlayer cp) throws IllegalMoveException {
		boolean end = false;
		do {
			List<ChessGame> result = ChessGameDM.findByPlayer(cp);
			printGameList(result, cp);
			System.out.println("Enter 0 if you wish to return");
			int option = nextInt(in);
			if (option == 0) {
				end = true;
			} else {
				ChessGameDM obj = new ChessGameDM(ChessGame.class);
				ChessGame game = obj.find(ChessGame.class, option);
				ChessBoard table = new ChessBoard(game.getMoves(), game.getWhite(), game.getBlack());
				loadTable(table, cp, in, game);
				end = true;
			}
		} while (!end);
	}

	/**
	 * Create a game between two ChessPlayer
	 * 
	 * @param in  User input
	 * @param you Logged in ChessPlayer
	 * @throws IOException
	 * @throws IllegalMoveException
	 */
	private static void createNewGame(Scanner in, ChessPlayer you) throws IOException, IllegalMoveException {
		ChessPlayer opponent = null;
		do {
			System.out.println("Insert your opponent's email address");
			String emailOpponent = nextLine(in);

			opponent = login(in, emailOpponent);
			if (opponent == null) {
				System.out.println("\nThis player does not exist...");
				System.out.println("Please make sure you typed the right email address");
			}
		} while (opponent == null);
		Date startDate = new Date();
		ChessGame cg = ChessGameDM.createGame(you, opponent, startDate);
		ChessBoard table = new ChessBoard(you, opponent);
	}

	/**
	 * Generate the ChessBoard and prompt the user for the ChessMove if it's his
	 * turn
	 * 
	 * @param table ChessBoard to be presented
	 * @param cp    Logged in ChessPlayer
	 * @param in    User input
	 * @param cg    ChessGame to be updated if any move is made
	 * @throws IllegalMoveException
	 */
	public static void loadTable(ChessBoard table, ChessPlayer cp, Scanner in, ChessGame cg)
			throws IllegalMoveException {
		System.out.println("\033[H\033[2J");
		System.out.flush();
		if (cp.getId() == cg.getWhite().getId()) {
			System.out.println(cg.toString());
		}
		System.out.println(table);
		boolean turn = cp.getId() == table.getNextTurn();
		System.out.println(cp.getId() + " " + table.getNextTurn());
		while (turn) {
			table.startTimer();
			System.out.println("\nMake your move...\n");

			System.out.println("From (example: a2)");
			String from = nextLine(in);
			char[] fromArray = from.toCharArray();
			char fromCol = fromArray[0];
			int fromRow = Character.getNumericValue(fromArray[1]) - 1;

			System.out.println("\nTo (example a3)");
			String to = nextLine(in);
			char[] toArray = to.toCharArray();
			char toCol = toArray[0];
			int toRow = Character.getNumericValue(toArray[1]) - 1;

			ChessPosition origin = new ChessPosition(fromRow, fromCol);
			ChessPosition destination = new ChessPosition(toRow, toCol);
			ChessPiece piece = table.getPieceObj(fromRow, charToInt(fromCol));
			ChessMove move = new ChessMove(piece, origin, destination);

			turn = !table.updatePlayer(move, cp);

			if (!turn) {
				cg.addMove(move);
				ChessGameDM obj = new ChessGameDM(ChessGame.class);
				try {
					obj.update(cg);
					System.out.println("You've made your move in " + table.getTimer() + " seconds!");
				} catch (Exception e) {
					System.out.println("Error executing your move...");
				}
			}
			System.out.println(table);
		}
		System.out.println("Wait for your turn...");
		System.out.println("Press \"ENTER\" to continue...");
		Scanner scanner = new Scanner(System.in);
		scanner.nextLine();
	}

	/**
	 * This function is responsible for converting a char to an int value usable on
	 * the 2D ChessBoard array
	 * 
	 * @param fromColumn Char from 'a' to 'h' that represents the column of the
	 *                   ChessPiece's position on the board
	 * @return An int value from 0 to 7 that represents the column in the 2D
	 *         ChessBoard array
	 */
	private static int charToInt(char fromColumn) {
		int val = -1;
		switch (fromColumn) {
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
	 * Prints out a list of all games the logged in ChessPlayer is participating on
	 * with the turn status
	 * 
	 * @param cgList List of ChessGame
	 * @param cp     Logged in ChessPlayer
	 */
	private static void printGameList(List<ChessGame> cgList, ChessPlayer cp) {
		if (cgList.size() > 0) {
			List<List<String>> printableResult = new ArrayList<>();
			for (ChessGame cg : cgList) {
				ChessBoard table = new ChessBoard(cg.getMoves(), cg.getWhite(), cg.getBlack());
				int nextTurn = table.getNextTurn();
				List<String> list = new ArrayList<>();
				list.add(Integer.toString(cg.getId()));
				list.add(cg.toString());
				list.add(nextTurn == cp.getId() ? "Your turn" : "Your opponent's turn");
				printableResult.add(list);
			}
			System.out.println(utils.Table.tableToString(printableResult));
		} else {
			System.out.println("You have no active games yet... Go back and create one!");
		}
	}

	/**
	 * Method responsible for the login of a ChessPlayer
	 * 
	 * @param in User input
	 * @return Returns null if the login failed or the ChessPlayer if successful
	 * @throws IOException
	 */
	public static ChessPlayer login(Scanner in, String email) throws IOException {
		ChessPlayer player = null;
		player = persist.ChessPlayerDM.findByEmail(email);
		return player;
	}

	/**
	 * Auxiliary function to aid on reading user inputs
	 * 
	 * @param in User input
	 * @return Returns an int of the user's input
	 */
	private static int nextInt(Scanner in) {
		String s = in.nextLine();
		while (s.startsWith("#")) {
			s = in.nextLine();
		}
		if (s.contains("#")) {
			try (Scanner sc = new Scanner(s)) {
				s = sc.next();
			} finally {

			}
		}
		return Integer.parseInt(s);
	}

	/**
	 * Auxiliary function to aid on reading user inputs
	 * 
	 * @param in User input
	 * @return Returns a string of the user's input
	 */
	private static String nextLine(Scanner in) {
		String s = in.nextLine();
		while (s.startsWith("#")) {
			s = in.nextLine();
		}
		if (s.contains("#")) {
			int p = s.indexOf("#");
			s = s.substring(0, p).trim();
		}
		return s;
	}
}
