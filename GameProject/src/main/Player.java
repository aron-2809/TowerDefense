package main;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import tower.TowerCannon;
import static graphics.Designer.*;

import java.awt.List;
import java.util.ArrayList;

import map.GameScreenManager;
import map.TileGrid;
import map.TileType;
/**
 * This class accepts Mouse or Keyboard events and sends the response Model class i.e. Map package   
 * @author s_niga
 *
 */
public class Player {

	private TileGrid grid;
	int  blockSize =32;
	public TileType currentTile= TileType.Grass;
	private ArrayList<Integer> intList = new ArrayList<Integer>();
	public static TowerCannon towerCannon;
	public static int towerX=0,towerY=0;
	public static int money = 100;
	Player(TileGrid grid){
		this.grid=grid;


	}

	//TODO remove printing, set proper if and check it should not crash
	/**
	 * This method sets the Green tile to Dirt tile when mouse points to a valid tile
	 * 
	 * <p>
	 * @param  towerX 
	 * @param  towerY
	 * @return void
	 */
	public void setTile(){

		//TODO do not set tile multiple times i.e. set the tile only once and add toggle effect
		while(Mouse.next()){
			if(Mouse.getEventButtonState())
			{

				if(((Mouse.getX() / blockSize) < Boot.getNoColumns()) && (((HEIGHT - Mouse.getY()) / blockSize) < Boot.getNoRows()))

				{
					if(Mouse.isButtonDown(0)) // if left mouse key is pressed
					{
						grid.setTile((int)Math.floor(Mouse.getX() / blockSize),(int)Math.floor((HEIGHT-Mouse.getY()-1) / blockSize),currentTile);
					}
				}

				else if((HEIGHT - Mouse.getY()) / blockSize==0)
				{	
					if((int)Math.floor(Mouse.getX() / blockSize)==Boot.getNoColumns())
						currentTile=TileType.Water;

					if((int)Math.floor(Mouse.getX() / blockSize)==Boot.getNoColumns()+1)
						currentTile=TileType.Dirt;

					if((int)Math.floor(Mouse.getX() / blockSize)==Boot.getNoColumns()+2)
						currentTile=TileType.Grass;
				}
				else if((HEIGHT - Mouse.getY()) / blockSize==1)
				{
					if((int)Math.floor(Mouse.getX() / blockSize)==Boot.getNoColumns())
						currentTile=TileType.TowerCannon;

					if((int)Math.floor(Mouse.getX() / blockSize)==Boot.getNoColumns()+1)
						currentTile=TileType.TowerBomb;

				}

				else{
					currentTile=TileType.Grass;
				}
			}
		}

		//Map save by pressing 's' key and load map by pressing l
		while (Keyboard.next()) {
			if (Keyboard.getEventKeyState()) {
				if (Keyboard.getEventKey() == Keyboard.KEY_S) {
					Boot.gameScreen.saveMap();
				}
				if (Keyboard.getEventKey() == Keyboard.KEY_L) {
					System.out.println("Loading the map");
				}
			}
		}

	}
}
