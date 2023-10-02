package main;

import java.io.IOException;
import java.util.Scanner;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import domain.IllegalMoveException;

/**
 * This is the main class, it will create the EntityManagerFactory
 * 
 * @author Joaquim Simoes, nº54599
 * @author Diogo Mateus, nº53374
 */
public class Main {
	public static EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPATestMySQL");

	/**
	 * 
	 * @param args
	 * @throws IOException
	 * @throws IllegalMoveException
	 */
	public static void main(String[] args) throws IOException, IllegalMoveException {
		Scanner in = new Scanner(System.in);
		Menu.mainMenu(in);
		System.out.println("Exited.");
		System.exit(0);
	}
}
