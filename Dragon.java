package gameOfTh;

import java.util.Random;

public class Dragon {
	private String teamColor;
	private int X;
	private int Y;
	private int sizeBoard;
	private String nameDrag;

	public Dragon(String nameDrag, String teamColor, int x, int y, int boardSize) {
		this.teamColor = teamColor;
		this.X = x;
		this.Y = y;
		this.nameDrag = nameDrag;
		this.sizeBoard = boardSize;
	}

	public void move(String[][] gameBoard) {
		Random random = new Random();
		boolean rndMove = random.nextBoolean();
		boolean canKeepMoving;
		if(gameBoard[X][Y] == null) {
			//do nothing 
		}else if (gameBoard[X][Y].equals(" " + nameDrag + " ")) {
			if (teamColor.equals("red")) {
				canKeepMoving = redNextStep(gameBoard, X+1, Y);
				if (rndMove && canKeepMoving) {
					canKeepMoving = redNextStep(gameBoard, X, Y+1);
					
				}else if (canKeepMoving) {
					canKeepMoving = redNextStep(gameBoard, X, Y-1);
					
				}
				if (canKeepMoving) {
					redNextStep(gameBoard, X+1, Y);
					
				}
			// blue Dragon move
			}else {
				canKeepMoving = blueNextStep(gameBoard, X-1, Y);
				if (rndMove && canKeepMoving) {
					canKeepMoving = blueNextStep(gameBoard, X, Y+1);
					
				}else if (canKeepMoving) {
					canKeepMoving = blueNextStep(gameBoard, X, Y-1);
					
				}
				if(canKeepMoving) {
					blueNextStep(gameBoard, X-1, Y);
				}
			}
		}

	}
	public String getNameDrag() {
		return nameDrag;
	}

	public void setNameDrag(String nameDrag) {
		this.nameDrag = nameDrag;
	}

	private boolean redNextStep(String[][] gameBoard, int next_x, int next_y) {
		boolean keepMoving = false;
		// Dragon Moving forward
		if(next_x != -1 && next_y != -1 && gameBoard[next_x % sizeBoard][next_y % sizeBoard] == null ) {
				gameBoard[next_x % sizeBoard][next_y % sizeBoard] = " "+nameDrag+" ";
				gameBoard[X][Y] = null;
				this.X = next_x % sizeBoard;
				this.Y = next_y % sizeBoard;
				keepMoving = true;
		// edge cases for Blue Dragons jumping from row
		}else if(next_x == -1 && gameBoard[next_x + sizeBoard][next_y % sizeBoard] == null){
				gameBoard[next_x  + sizeBoard][next_y % sizeBoard] = " "+nameDrag+" ";
				gameBoard[X][Y] = null;
				this.X = next_x + sizeBoard;
				this.Y = next_y % sizeBoard;
				keepMoving = true;
		// edge case for both Dragons jumping to the first column
		}else if(next_x != -1 && next_y == -1 && gameBoard[next_x % sizeBoard][next_y + sizeBoard] == null){
				gameBoard[next_x % sizeBoard][next_y + sizeBoard] = " "+nameDrag+" ";
				gameBoard[X][Y] = null;
				this.X = next_x;
				this.Y = next_y + sizeBoard;
				keepMoving = true;
		// all Conflicts.
		// Dragon & Dragon interactions........
		// ( 3 else if's)
		}else if(next_x != -1 && next_y != -1 && gameBoard[next_x % sizeBoard][next_y % sizeBoard].substring(0, 7).equals(" BLUE_D")) {
				System.out.println("\n Conflict characters: "+gameBoard[next_x % sizeBoard][next_y % sizeBoard]+" and "+gameBoard[X][Y]+" ");
				System.out.print(""+gameBoard[X][Y]+" removed! "+gameBoard[next_x % sizeBoard][next_y % sizeBoard]+" removed! \n");
				gameBoard[next_x % sizeBoard][next_y % sizeBoard] = null;
				gameBoard[X][Y] = null;
		}else if(next_x == -1 && next_y != -1 &&  gameBoard[next_x + sizeBoard][next_y % sizeBoard].substring(0, 7).equals(" BLUE_D")) {
				System.out.println("\n Conflict characters: "+gameBoard[next_x + sizeBoard][next_y % sizeBoard]+" and "+gameBoard[X][Y]+"");
				System.out.print(""+gameBoard[X][Y]+" removed! "+gameBoard[next_x + sizeBoard][next_y % sizeBoard]+" removed! \n");
				gameBoard[next_x + sizeBoard][next_y % sizeBoard] = null;
				gameBoard[X][Y] = null;
		// edge cases for Dragons jumping to the first column  (2 else if's)
		}else if(next_x != -1 && next_y == -1 && gameBoard[next_x % sizeBoard][next_y + sizeBoard].substring(0, 7).equals(" BLUE_D")) {
				System.out.println("\n Conflict characters: "+gameBoard[next_x % sizeBoard][next_y + sizeBoard]+" and "+gameBoard[X][Y]+" ");
				System.out.print(""+gameBoard[X][Y]+" removed! "+gameBoard[next_x % sizeBoard][next_y + sizeBoard]+" removed! \n");
				gameBoard[next_x % sizeBoard][next_y + sizeBoard] = null;
				gameBoard[X][Y] = null;
		// Dragon interacting with Soldier
		// (3 else if's)
		// handling all the Rows
		}else if(next_x != -1 && next_y != -1 && gameBoard[next_x % sizeBoard][next_y % sizeBoard].substring(0, 8).equals(" BLUE__S")) {
				System.out.println("\n Conflict characters: "+gameBoard[next_x % sizeBoard][next_y % sizeBoard]+" and "+gameBoard[X][Y]+"");
				System.out.print(gameBoard[next_x % sizeBoard][next_y % sizeBoard]+" removed! \n");
				gameBoard[next_x % sizeBoard][next_y % sizeBoard] = " " + nameDrag + " ";
				gameBoard[X][Y] = null;
				this.X = next_x % sizeBoard;
				this.Y = next_y % sizeBoard;
			
		}else if(next_x == -1 && next_y != -1 && gameBoard[next_x + sizeBoard][next_y % sizeBoard].substring(0, 8).equals(" BLUE__S")) {
				System.out.println("\n Conflict characters: "+gameBoard[next_x + sizeBoard][next_y % sizeBoard]+" and "+gameBoard[X][Y]+"");
				System.out.print(gameBoard[next_x + sizeBoard][next_y % sizeBoard]+" removed! \n");
				gameBoard[next_x + sizeBoard][next_y % sizeBoard] = " " + nameDrag + " ";
				gameBoard[X][Y] = null;
				this.X = next_x + sizeBoard;
				this.Y = next_y % sizeBoard;
		}else if(next_x != -1 && next_y == -1 && gameBoard[next_x % sizeBoard][next_y + sizeBoard].substring(0, 8).equals(" BLUE__S")) {
				System.out.println("\n Conflict characters: "+gameBoard[next_x % sizeBoard][next_y + sizeBoard]+" and "+gameBoard[X][Y]+"");
				System.out.print(gameBoard[next_x % sizeBoard][next_y + sizeBoard]+" removed! \n");
				gameBoard[next_x % sizeBoard][next_y + sizeBoard] = " " + nameDrag + " ";
				gameBoard[X][Y] = null;
				this.X = next_x % sizeBoard;
				this.Y = next_y + sizeBoard;
				
		}
		return keepMoving;
	}
	private boolean blueNextStep(String[][] gameBoard ,int next_x, int next_y) {
		boolean keepMoving = false;
		// Dragon Moving forward
		if(next_x != -1 && next_y != -1 && gameBoard[next_x % sizeBoard][next_y % sizeBoard] == null ) {
				gameBoard[next_x % sizeBoard][next_y % sizeBoard] = " "+nameDrag+" ";
				gameBoard[X][Y] = null;
				this.X = next_x % sizeBoard;
				this.Y = next_y % sizeBoard;
				keepMoving = true;
		// edge cases for Blue Dragons jumping from row
		}else if(next_x == -1 && gameBoard[next_x + sizeBoard][next_y % sizeBoard] == null){
				gameBoard[next_x  + sizeBoard][next_y % sizeBoard] = " "+nameDrag+" ";
				gameBoard[X][Y] = null;
				this.X = next_x + sizeBoard;
				this.Y = next_y;
				keepMoving = true;
		
		// edge case for both Dragons jumping to the first column
		}else if(next_x != -1 && next_y == -1 && gameBoard[next_x % sizeBoard][next_y + sizeBoard] == null){
				gameBoard[next_x % sizeBoard][next_y + sizeBoard] = " "+nameDrag+" ";
				gameBoard[X][Y] = null;
				this.X = next_x;
				this.Y = next_y + sizeBoard;
				keepMoving = true;
		// all Conflicts.
		// Dragon & Dragon interactions........
		// ( 3 else if's)
		}else if(next_x != -1 && next_y != -1 && gameBoard[next_x % sizeBoard][next_y % sizeBoard].substring(0, 7).equals(" RED__D")) {
				System.out.println("\n Conflict characters: "+gameBoard[next_x % sizeBoard][next_y % sizeBoard]+" and "+gameBoard[X][Y]+" ");
				System.out.print(""+gameBoard[X][Y]+" removed! "+gameBoard[next_x % sizeBoard][next_y % sizeBoard]+" removed! \n");
				gameBoard[next_x % sizeBoard][next_y % sizeBoard] = null;
				gameBoard[X][Y] = null;
		}else if(next_x == -1 && next_y != -1 &&  gameBoard[next_x + sizeBoard][next_y % sizeBoard].substring(0, 7).equals(" RED__D")) {
				System.out.println("\n Conflict characters: "+gameBoard[next_x + sizeBoard][next_y % sizeBoard]+" and "+gameBoard[X][Y]+"");
				System.out.print(""+gameBoard[X][Y]+" removed! "+gameBoard[next_x + sizeBoard][next_y % sizeBoard]+" removed! \n");
				gameBoard[next_x + sizeBoard][next_y % sizeBoard] = null;
				gameBoard[X][Y] = null;
		// edge cases for Dragons jumping to the first column  (2 else if's)
		}else if(next_x != -1 && next_y == -1 && gameBoard[next_x % sizeBoard][next_y + sizeBoard].substring(0, 7).equals(" RED__D")) {
				System.out.println("\n Conflict characters: "+gameBoard[next_x % sizeBoard][next_y + sizeBoard]+" and "+gameBoard[X][Y]+" ");
				System.out.print(""+gameBoard[X][Y]+" removed! "+gameBoard[next_x % sizeBoard][next_y + sizeBoard]+" removed! \n");
				gameBoard[next_x % sizeBoard][next_y + sizeBoard] = null;
				gameBoard[X][Y] = null;
		// Dragon interacting with Soldier
		// (3 else if's)
		// handling all the Rows
		}else if(next_x != -1 && next_y != -1 && gameBoard[next_x % sizeBoard][next_y % sizeBoard].substring(0, 8).equals(" RED___S")) {
				System.out.println("\n Conflict characters: "+gameBoard[next_x % sizeBoard][next_y % sizeBoard]+" and "+gameBoard[X][Y]+"");
				System.out.print(gameBoard[next_x % sizeBoard][next_y % sizeBoard]+" removed! \n");
				gameBoard[next_x % sizeBoard][next_y % sizeBoard] = " " + nameDrag + " ";
				gameBoard[X][Y] = null;
				this.X = next_x % sizeBoard;
				this.Y = next_y % sizeBoard;
		}else if(next_x == -1 && next_y != -1 && gameBoard[next_x + sizeBoard][next_y % sizeBoard].substring(0, 8).equals(" RED___S")) {
				System.out.println("\n Conflict characters: "+gameBoard[next_x + sizeBoard][next_y % sizeBoard]+" and "+gameBoard[X][Y]+"");
				System.out.print(gameBoard[next_x + sizeBoard][next_y % sizeBoard]+" removed! \n");
				gameBoard[next_x + sizeBoard][next_y % sizeBoard] = " " + nameDrag + " ";
				gameBoard[X][Y] = null;
				this.X = next_x + sizeBoard;
				this.Y = next_y % sizeBoard;
		}else if(next_x != -1 && next_y == -1 && gameBoard[next_x % sizeBoard][next_y + sizeBoard].substring(0, 8).equals(" RED___S")) {
				System.out.println("\n Conflict characters: "+gameBoard[next_x % sizeBoard][next_y + sizeBoard]+" and "+gameBoard[X][Y]+"");
				System.out.print(gameBoard[next_x % sizeBoard][next_y + sizeBoard]+" removed! \n");
				gameBoard[next_x % sizeBoard][next_y + sizeBoard] = " " + nameDrag + " ";
				gameBoard[X][Y] = null;
				this.X = next_x % sizeBoard;
				this.Y = next_y + sizeBoard;
		}
		return keepMoving;
	}
//	
}