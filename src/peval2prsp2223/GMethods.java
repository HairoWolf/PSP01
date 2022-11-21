package peval2prsp2223;

import java.util.Scanner;

/**
 * @author Javier Tienda González
 * @version 1.0
 * @info Class that has all general methods used by the program
 */

public class GMethods {

	/**
	 * Variable to collect data typed by keyboard
	 */
	private static Scanner keyb;

	/**
	 * Constant for the color of the error print
	 */
	private static final String ANSI_RED = "\u001B[31m";
	
	/**
	 * Constant for reset the color
	 */
	private static final String ANSI_RESET = "\u001B[0m";
	
	/**
	 * Constant for the color of the success print
	 */
    private static final String ANSI_GREEN = "\u001B[32m";
	
	/**
	 * Empty class constructor
	 */
	GMethods(){
		
	}

	/**
	 * Method prints a text that you pass as a parameter
	 * 
	 * @param txt
	 */
	public static void println(String txt) {
		System.out.println(txt);
	}

	/**
	 * Method prints a split text
	 */
	public static void printDiv() {
		System.out.println("------------------------------------------");
	}
	
	/**
	 * Method prints a text that you pass as a parameter
	 * 
	 * @param txt
	 */
	public static void printSuccess(String txt) {
		System.out.println(ANSI_GREEN + txt + ANSI_RESET);
	}
	
	/**
	 * Method prints a text that you pass as a parameter
	 * 
	 * @param txt
	 */
	public static void printError(String txt) {
		System.out.println("***" + txt + "***");
	}

	/**
	 * Method that receives a String
	 *
	 * @return number
	 */
	public static int keyBInt() {
		keyb = new Scanner(System.in);
		return keyb.nextInt();
	}

	/**
	 * Method that receives a String
	 *
	 * @return string
	 */
	public static String keyBString() {
		keyb = new Scanner(System.in);
		return keyb.nextLine();
	}
}
	