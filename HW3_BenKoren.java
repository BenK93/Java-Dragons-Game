package gameOfTh;

import java.util.Scanner;
import gameOfTh.gameOfThrone;

public class HW3_BenKoren {
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int X = 0 ;
		System.out.println("********** GAME OF THRONE BOARD X * X **********");
		System.out.print("Enter X size: ");
		X = input.nextInt();
		gameOfThrone Game = new gameOfThrone(X);
		Game.printBoard(X);
		input.close();
		}
	}
