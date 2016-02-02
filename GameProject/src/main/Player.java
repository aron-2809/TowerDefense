package main;

import org.lwjgl.input.Mouse;
import static graphics.Designer.*;
import map.TileGrid;
import map.TileType;

public class Player {

	private TileGrid grid;
	
	Player(TileGrid grid){
		this.grid=grid;
	}
	
	//TODO remove printing, set proper if and check it should not crash
	/**
	 * This method sets the Green tile to Dirt tile when mouse points to a valid tile
	 * 
	 * <p>
	 * @param  none 
	 * @param  none
	 * @return void
	 */
	public void setTile(){
		//TODO do not set tile multiple times i.e. set the tile only once and add toggle effect
		//TODO set only valid tile
		if((Mouse.getX() / 64)<Boot.getNoRows())
			//&& (HEIGHT-Mouse.getY()-1) <=Boot.getNoColumns())
			if(Mouse.isButtonDown(0)) // if left mouse key is pressed
				grid.setTile((int)Math.floor(Mouse.getX() / 64),(int)Math.floor((HEIGHT-Mouse.getY()-1) / 64),TileType.Dirt);
			
		
			if(Mouse.isButtonDown(1))// if right mouse key is pressed
			{
				grid.setTile((int)Math.floor(Mouse.getX() / 64),(int)Math.floor((HEIGHT-Mouse.getY()-1) / 64),TileType.Grass);
			}
//		else
			
		//	System.out.println("Mouse="+(HEIGHT-Mouse.getY()-1) + "Columns=" + Boot.getNoColumns());
	
	}
}
