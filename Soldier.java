package gameOfTh;

public class Soldier {
	private String teamColor;
	private int X;
	private int Y;
	private int sizeBoard;
	private String nameSol;
	
	public Soldier(String nameSol, String teamColor, int x, int y, int boardSize) {
		this.teamColor = teamColor;
		this.X = x; 
		this.Y = y;
		this.nameSol= nameSol;
		this.sizeBoard = boardSize;
	}
	public void move(String[][] gameBoard) {
			if (gameBoard[X][Y] == null) {
				// doing nothing 
			}else if(gameBoard[X][Y].equals(" "+nameSol+" ")) {
				if(teamColor.equals("red")) {
					if(gameBoard[X+1][Y]==null) {
						gameBoard[X+1][Y]=" "+nameSol+" ";
						gameBoard[X][Y] = null;
						this.X++;
					}else if(gameBoard[X+1][Y].substring(0, 8).equals(" BLUE__S")) {
						System.out.print("\n Conflict characters: "+gameBoard[X+1][Y]+" and "+gameBoard[X][Y]+"  \n");
						System.out.printf(""+gameBoard[X][Y]+" removed! "+gameBoard[X+1][Y]+"removed! \n");
						gameBoard[X+1][Y] = null;
						gameBoard[X][Y] =null; 
					}
					
				}else {
					if(gameBoard[X-1][Y]==null) {
						gameBoard[X-1][Y]=" "+nameSol+" ";
						gameBoard[X][Y] = null;
						this.X--;
					}else if(gameBoard[X-1][Y].substring(0, 8).equals(" RED___S")) {
						System.out.print("\n Conflict characters: "+gameBoard[X-1][Y]+" and "+gameBoard[X][Y]+"  \n");
						System.out.printf(""+gameBoard[X][Y]+" removed! "+gameBoard[X-1][Y]+"removed! \n");
						gameBoard[X-1][Y] = null;
						gameBoard[X][Y] = null;
					}
				}
			}
	}
	public void setNameSol(String nameSol) {
		this.nameSol = nameSol;
	}
	public int getX() {
		return this.X;
	}
}
