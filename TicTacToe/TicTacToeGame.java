import java.awt.Point;

/**
 * The TicTacToe interface defines methods for a class that manages
 * game state information and logic for a game of tic-tac-toe
 * 
 * @author Rich B
 */
public class TicTacToeGame implements TicTacToe
{
	//FIELDS
	private BoardChoice[][] grid;
	private Point[] moves;
	private int numMoves;
	private int size = 3; //this is the amount of rows and col.
	private int pointSize = 9;
	private GameState state;
	private BoardChoice lastPlayer;
	
	//CONSTRUCTOR
	/**
	 * this constructor initiate the size of the grid, moves, and number of moves.
	 */
	public TicTacToeGame() 
	{
		this.grid = new BoardChoice[size][size];
		this.moves  = new Point[pointSize];
		this.numMoves = 0;
		newGame();
	}
	
	//METHODS
	/**
	 * This method makes sure that all the boxes are open when the game starts
	 */
	@Override
	public void newGame() 
	{
		this.numMoves = 0;
		this.state = GameState.IN_PROGRESS;
		for(int row = 0; row < grid.length; row++)
			for(int col = 0; col<grid[row].length; col++)
				grid[row][col] = BoardChoice.OPEN;
	}

	/**
	 * this method allows the player to select a box and checks if the box selected is 
	 * open or not. this method also check if the player selecting is the last player
	 *  to prevent one person to select a box multiple times in a row.
	 */
	@Override
	public boolean choose(BoardChoice player, int row, int col) 
	{
		if(gameOver() || grid[row][col] != TicTacToe.BoardChoice.OPEN || player == lastPlayer )
			return false;
		
		//Then let them choose that spot
		grid[row][col] = player;
		lastPlayer = player;
		
		moves[numMoves] = new Point(row, col);
		numMoves++;
		
		gameOver(); 
		return true;
	
	}
	
	/**
	 * This method define when the game is over, whether vertically, 
	 * horizontally, or diagonally, or if all positions have been claimed without a winner.
	 */
	@Override
	public boolean gameOver() 
	{
		//if someone has vertical or horizontal - state to X or O
		for(int row = 0; row < grid.length; row++)
		{
			if(grid[row][0]==TicTacToe.BoardChoice.X && grid[row][1]==TicTacToe.BoardChoice.X  
					&& grid[row][2]==TicTacToe.BoardChoice.X)
			{
				state = GameState.X_WON;
				return true;
			}
			
			if(grid[row][0]==TicTacToe.BoardChoice.O && grid[row][1]==TicTacToe.BoardChoice.O 
					&& grid[row][2]==TicTacToe.BoardChoice.O)
			{
			state = GameState.O_WON;
			return true;
			}
		}
			
		for(int col = 0; col<grid[2].length; col++)
		{
			if(grid[0][col]==TicTacToe.BoardChoice.X && grid[1][col]==TicTacToe.BoardChoice.X  
					&& grid[2][col]==TicTacToe.BoardChoice.X)
			{
				state = GameState.X_WON;
				return true;
			}
				
			if(grid[0][col]==TicTacToe.BoardChoice.O && grid[1][col]==TicTacToe.BoardChoice.O 
					&& grid[2][col]==TicTacToe.BoardChoice.O)
			{
					state = GameState.O_WON;
				return true;
			}
		}
		//if someone has diagonal - state to X or O
	
		if(grid[0][0]==TicTacToe.BoardChoice.X && grid[1][1]==TicTacToe.BoardChoice.X  
				&& grid[2][2]==TicTacToe.BoardChoice.X)
		{
			state = GameState.X_WON;
			return true;
		}
		
		else if(grid[0][2]==TicTacToe.BoardChoice.X && grid[1][1]==TicTacToe.BoardChoice.X  
				&& grid[2][0]==TicTacToe.BoardChoice.X)
		{
			state = GameState.X_WON;
			return true;
		}
		
	
		else if(grid[0][0]==TicTacToe.BoardChoice.O && grid[1][1]==TicTacToe.BoardChoice.O 
				&& grid[2][2]==TicTacToe.BoardChoice.O)
		{
			state = GameState.O_WON;
			return true;
		}
			
		else if(grid[0][2]==TicTacToe.BoardChoice.O && grid[1][1]==TicTacToe.BoardChoice.O 
				&& grid[2][0]==TicTacToe.BoardChoice.O)
		{
			state = GameState.O_WON;
			return true;
		}
		
		//if tie - state to Tie
		if(numMoves >= pointSize)
		{
			state = GameState.TIE;
			return true;
		}
		else 
		{
			return false;
		}	
	}

	/**
	 * This returns the state of the game 
	 */
	@Override
	public GameState getGameState() 
	{
		return state;
	}

	/**
	 * This method creates a copy of the grid and returns said copy.
	 */
	@Override
	public BoardChoice[][] getGameGrid() 
	{
		BoardChoice[][] copyGrid = new BoardChoice[size][size];
		for(int row = 0; row < grid.length; row++)
			for(int col = 0; col < grid[row].length; col++)
				copyGrid[row][col] = grid[row][col];
		
		return copyGrid;
	}

	/**
	 * This method creates a copy of the moves made and returns said copy.
	 */
	@Override
	public Point[] getMoves() 
	{
		Point[] copyMoves = new Point[numMoves];
		for(int i = 0; i < copyMoves.length; i++)
			copyMoves[i] = new Point(moves[i]);
		return copyMoves;
	}
}
