package main;
import static graphics.Designer.*;

import java.awt.Font;
import java.io.File;
import java.io.FilenameFilter;
import java.util.Scanner;

import map.GameScreenManager;
import map.Tile;
import map.TileGrid;
import map.TileType;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;

import tower.TowerCannon;
import utility.FileExplorer;

/**
 * This is main the class which launches the game and displays the screen to the user.
 * This class is a view in MVC architecture. This class pulls the map from the model after each iteration
 * @author eshinig
 *
 */
public class Boot {
	private static int noRows;
	private static int noColumns;
	public static Player player=null;
	public static GameScreenManager gameScreen = null;
	public Scanner keyboard = new Scanner(System.in);
	public static int[][] map  = null;
	/**
	 * This constructor initializes openGL library , accepts user input to either start 
	 * a new game or load the saved game
	 * 
	 */
	public Boot()
	{	
		int choice;

		System.out.println("Enter '1' for New Game or '2' to load Saved game");
		choice=keyboard.nextInt();

		switch (choice) {
		case 1:
		{
			this.newGame();
			break;
		}
		case 2:
		{
			this.loadGame();
			break;
		}
		}

		beginSession(noRows,noColumns);

		TrueTypeFont font;
		boolean antiAlias = false;
		Font awtFont = new Font("Times New Roman", Font.BOLD, 16);

		font = new TrueTypeFont(awtFont, antiAlias);

		TileGrid grid=new TileGrid(map,noRows, noColumns);//draws the green tiles
		gameScreen = new GameScreenManager(grid);
		player=new Player(grid);

		//String currentCredits = "CreditLeft:$" + Integer.toString(player.money);
		System.out.println("You have $" + player.money);
		while(!Display.isCloseRequested()){
			//Draws the grid with current assignment of Grid
			grid.draw();
			//Captures the user input and sets the tile
			player.setTile();
			//Displays the text in the Screen Area
			//font.drawString(32*10, 64, currentCredits, Color.white);
			Display.update();
			Display.sync(60);
		}

		Display.destroy();
	}

	/**
	 * This method accepts no of Rows and Columns from the user.
	 * At the moment Rows and Columns must be equal. 
	 * @return 0 if rows and columns are same otherwise 1.
	 */
	public int newGame()
	{	
		System.out.println("Enter Number of Rows & Columns for the MAP(max. 20x20)=");
		noRows=keyboard.nextInt();
		noColumns=keyboard.nextInt();
		map  = new int[noRows][noColumns];

		if(noRows==noColumns)
			return 0;

		else 
			return 1;
	}
	/**
	 * This method loads the map from the saved XML file
	 */
	public void loadGame()
	{	
		int mapToLoad;
		System.out.println("Following Maps saved");
		FileExplorer fileExplorer = new FileExplorer();
		fileExplorer.displayXMLFiles();
		GameScreenManager temp = new GameScreenManager();
		System.out.println("Enter the File number of the Map to Load:");
		mapToLoad=keyboard.nextInt();
		map=temp.loadMap(fileExplorer.getFileName(mapToLoad));
		noRows= temp.getNoRows();
		noColumns=temp.getNoColumns();
	}

	/**
	 * This method returns number of Rows in the screen
	 * @return int number of Rows
	 */
	public static int getNoRows() {
		return noRows;
	}

	/**
 * This method returns number of Columns in the screen
	 * @return int number of Cplumns
	 */
	public static int getNoColumns() {
		return noColumns;
	}

	/**
	 * Main program and starts by call the constructor
	 * @param args
	 */
	public static void main(String args[])
	{
		new Boot();
		
	}

	/**
	 * This method returns the static object of Class GameScreenManager
	 * @return Returns the game object
	 */
	public  GameScreenManager getGameScreen() {
		return gameScreen;
	}

}
