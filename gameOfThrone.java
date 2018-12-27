package gameOfTh;

import java.util.Scanner;
public class gameOfThrone {
	private String[][] board;
	private Dragon[] redDragons;
	private Dragon[] blueDragons;
	private Soldier[] redSoldiers;
	private Soldier[] blueSoldiers;
	private static int numOfSteps = 1;
	
	public gameOfThrone(int size) {
		this.board = new String[size][size];
		gameCreation(size);
	}
	private void gameCreation(int sizeBoard) {
		Scanner input = new Scanner(System.in);
		int numOfBlueDragons,numOfBlueSoldiers,numOfRedDragons,numOfRedSoldiers;
/*
		   Red players turn.
*/
		// number of Soldiers
		System.out.println("******** RED PLAYER CHARACTER CREATION ********");
		System.out.print("Enter Number Of Soldiers: ");
		numOfRedSoldiers = input.nextInt();
		redSoldiers = new Soldier[numOfRedSoldiers]; 
		setSoldiers(numOfRedSoldiers, "RED", input, sizeBoard);
		// number ofDragons.
		System.out.print("Enter Number Of Dragons: ");
		numOfRedDragons = input.nextInt();
		redDragons = new Dragon[numOfRedDragons];
		setDragons(numOfRedDragons, "RED", input, sizeBoard);

		System.out.println("************************************************");
		
/*
			   Blue players turn.				
*/
		// number of Soldiers
		System.out.println("******** Blue PLAYER CHARACTER CREATION ********");
		System.out.print("Enter Number Of Soldiers: ");
		numOfBlueSoldiers = input.nextInt();
		blueSoldiers = new Soldier[numOfBlueSoldiers];
		setSoldiers(numOfBlueSoldiers, "BLUE", input, sizeBoard);
			
		// number of Dragons.
		System.out.print("Enter Number Of Dragon: ");
		numOfBlueDragons = input.nextInt();
		blueDragons = new Dragon[numOfBlueDragons];
		setDragons(numOfBlueDragons, "BLUE", input, sizeBoard);
		System.out.println("************************************************");
	}

	public void setSoldiers(int numOfSold, String team,Scanner input, int sizeBoard) {
		int x_Val,y_Val =0;
		for (int i = 0; i < numOfSold; i++) {
			System.out.println("--- ENTER SOLDIER "+(i+1)+" INFO ---");
			System.out.print("Enter x value: ");
			x_Val = input.nextInt();
			System.out.print("Enter y value: ");
			y_Val = input.nextInt();
			System.out.println("Soldier Created Successfully!");
			if (team == "RED") {
				if(i < 10) {
					addSoldier(x_Val,y_Val,"red", "RED___Sol"+i );
					redSoldiers[i] = new Soldier("RED___Sol"+i,"red",x_Val,y_Val, sizeBoard);
				} else {
					addSoldier(x_Val,y_Val,"red", "RED__Sol"+i );
					redSoldiers[i] = new Soldier("RED__Sol"+i,"red",x_Val,y_Val, sizeBoard);
				}
			}else {
				if(i < 10) {
					addSoldier(x_Val,y_Val,"blue", "BLUE__Sol"+i );
					blueSoldiers[i] = new Soldier("BLUE__Sol"+i,"blue",x_Val,y_Val, sizeBoard);
				}else {
					addSoldier(x_Val,y_Val,"blue", "BLUE_Sol"+i );
					blueSoldiers[i] = new Soldier("BLUE_Sol"+i,"blue",x_Val,y_Val, sizeBoard);
				}
			}
			
		}
	}
	public void setDragons(int numOfDrag, String team,Scanner input, int sizeBoard) {
		int x_Val,y_Val =0;
		for (int i = 0; i < numOfDrag; i++) {
			System.out.println("--- ENTER DRAGON "+(i+1)+" INFO ---");
			System.out.print("Enter x value: ");
			x_Val = input.nextInt();
			System.out.print("Enter y value: ");
			y_Val = input.nextInt();
			System.out.println("Dragon Created Successfully!");
			if (team == "RED" ) {
				if(i < 10) {
					addDragon(x_Val,y_Val,"red", "RED__Drag"+i );
					redDragons[i] = new Dragon("RED__Drag"+i,"red",x_Val,y_Val, sizeBoard);
				}else {
					addDragon(x_Val,y_Val,"red", "RED__Dra"+i );
					redDragons[i] = new Dragon("RED__Dra"+i,"red",x_Val,y_Val, sizeBoard);
				}
			}else {
				if(i < 10) {
					addDragon(x_Val,y_Val,"blue", "BLUE_Drag"+i );
					blueDragons[i] = new Dragon("BLUE_Drag"+i,"blue",x_Val,y_Val, sizeBoard);
				}else {
					addDragon(x_Val,y_Val,"blue", "BLUE_Dra"+i );
					blueDragons[i] = new Dragon("BLUE_Dra"+i,"blue",x_Val,y_Val, sizeBoard);
				
				}
			}
			
		}
	}
	
	private boolean checkForATie(){
		boolean itIsAtie = false;
		int numOfSoldiers = 0;
		for(int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
					if(board[i][j] == null) {
						// do nothing 
					}else if (board[i][j].substring(0, 10).equals(" RED___Sol") || board[i][j].substring(0, 10).equals(" BLUE__Sol") ||
							  board[i][j].substring(0, 9).equals(" RED__Sol")   || board[i][j].substring(0, 9).equals(" BLUE_Sol") ) {
								numOfSoldiers++;
				}
			}
		}
		if(numOfSoldiers == 0) {
			System.out.println("\n GAME OVER! All Soldiers are DEAD ");
			itIsAtie = true;
			return itIsAtie;
		}
		return itIsAtie;
	}
	
	private boolean checkForWinner() {
		boolean SomeOneWon = false;
			for (int i = 0; i < redSoldiers.length; i++) {
				int redLocationOnBoard = redSoldiers[i].getX();
				if(redLocationOnBoard == board.length -1) {
					SomeOneWon = true;
					return SomeOneWon;
				}
			}
			for (int i = 0; i < blueSoldiers.length; i++) {
				int blueLocationOnBoard = blueSoldiers[i].getX();
				if(blueLocationOnBoard == 0) {
					SomeOneWon = true;
					return SomeOneWon;
				}
			}return SomeOneWon;
	}
	
	public void addSoldier(int x, int y, String team, String name ) {
		board[x][y] = " "+name+" ";
	}

	public void addDragon(int x, int y, String team, String name ) {
		board[x][y] = " "+name+" ";
	}
	
	public void printLastBoard() {
		System.out.println();
		for(int i = 0; i < board.length*12; i++) {
			System.out.print(i == (board.length*12)/2 ? " STEP "+numOfSteps+" " : "-");
		}
		numOfSteps++;
		for (int i = -1; i < board.length+1; i++) {
			System.out.println();
			if(i < 10) {
				System.out.print(i == -1 || i == board.length ? "" : " "+i);
			}else {
				System.out.print(i == -1 || i == board.length ? "" : ""+i);
			}
			for (int j = 0; j < board.length; j++) {
				if(i == -1 && j == 0|| i == board.length && j == 0) {
						for (int d = 0; d < board.length +1; d++) {
							if(d == 0 || d == board.length) {
								System.out.print(" ");
							}else {
								if (d <10) {
									if(d == 1)
										System.out.print("      "+(d-1)+"      ");
									System.out.print("     "+(d)+"      ");
								}else {
									System.out.print("     "+(d)+"     ");
								}
							}
						} break;
				}else if(board[i][j] == null && i != -1) {
					System.out.print("     *      ");	
				}else if (board[i][j] != null && i != -1) {
					System.out.print(board[i][j]);
				}else {
					
				}
			}
			System.out.print(i == -1 || i == board.length ? "" : ""+i);
		}
	}
	private boolean advanceStep() {
		// red Drag turn
		boolean GameOver = false;
		for (int i = 0; i < redDragons.length; i++) {
			redDragons[i].move(board);
		}
		// Blue Drag turn
		for (int i = 0; i < blueDragons.length; i++) {
			blueDragons[i].move(board);
		}
		// red Sold turn
		for (int i = 0; i < redSoldiers.length; i++) {
			redSoldiers[i].move(board);
			// checking if red won
			GameOver = checkForWinner();
			if(GameOver) {
				printLastBoard();
				System.out.println("\n GAME OVER!  RED PLAYER WON THE GAME! ");
				return GameOver;
			}
		}
		
		// blue Sold turn
		for (int i = 0; i < blueSoldiers.length; i++) {
			blueSoldiers[i].move(board);
			// checking if blue won
			GameOver = checkForWinner();
			if(GameOver) {
				printLastBoard();
				System.out.println("\n GAME OVER!  BLUE PLAYER WON THE GAME! ");
				return GameOver;
			}
		}
		return GameOver;
	}
	public void printBoard(int count) {
		// printing for loop.
		boolean WinnerOfTheGame = false;
		boolean WeHaveATie = false;
		
			while(WinnerOfTheGame == false && WeHaveATie == false) {
				System.out.println();
				for(int i = 0; i < board.length*12; i++) {
					System.out.print(i == (board.length*12)/2 ? " STEP "+numOfSteps+" " : "-");
				}
				numOfSteps++;
				for (int i = -1; i < board.length+1; i++) {
					System.out.println();
					if(i < 10) {
						System.out.print(i == -1 || i == board.length ? "" : " "+i);
					}else {
						System.out.print(i == -1 || i == board.length ? "" : ""+i);
					}
					for (int j = 0; j < board.length; j++) {
						if(i == -1 && j == 0|| i == board.length && j == 0) {
								for (int d = 0; d < count +1; d++) {
									if(d == 0 || d == count) {
										System.out.print(" ");
									}else {
										if (d <10) {
											if(d == 1)
												System.out.print("      "+(d-1)+"      ");
											System.out.print("     "+(d)+"      ");
										}else {
											System.out.print("     "+(d)+"     ");
										}
									}
								} break;
						}else if(board[i][j] == null && i != -1) {
							System.out.print("     *      ");	
						}else if (board[i][j] != null && i != -1) {
							System.out.print(board[i][j]);
						}else {
							
						}
					}
					System.out.print(i == -1 || i == board.length ? "" : ""+i);
				}
				WeHaveATie = checkForATie();
				WinnerOfTheGame = advanceStep();
			}
	}
}
