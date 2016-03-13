/**
 * 
 */
package critter;

import map.Tile;

import static critter.Critter.pathStepIndex;

import org.lwjgl.Sys;
import org.newdawn.slick.opengl.Texture;

import ai.Path;
import main.Boot;
import utility.Clock;
import utility.CoordinateConverter;
/**
 * This class is the base for critter
 * 
 *
 */
public abstract class Critter {
	protected int width, height,health;
	protected float speed,x, y;
	protected Texture tex;
	protected Tile startTile;
	protected Tile nextTile;
	public static int pathStepIndex=0;
	protected boolean first = true;

	public Critter() {
		this.nextTile = Boot.grid.getTile(CoordinateConverter.getYCordinate(Path.continousPath.get(pathStepIndex+1)), 
				CoordinateConverter.getXCordinate(Path.continousPath.get(pathStepIndex+1)));
	}

	public abstract void draw();

	public final void update() {


		boolean critterMovedOneTile=false;
		//this first is set to false to avoid reading large Clock.delta
		if(first)
			first =false;
		else
		{
			//Critter move right
			if(x<(nextTile.getX()) && y==nextTile.getY() && critterMovedOneTile==false)
			{	
				x = x+Clock.delta() * speed;
				if(x>=nextTile.getX())
					critterMovedOneTile=true;

			}
			//Move critter down
			if(y<(nextTile.getY()) && x==nextTile.getX() && critterMovedOneTile==false)
			{
				y += Clock.delta() * speed;
				if(y>=nextTile.getY())
					critterMovedOneTile=true;
			}

			//Move Critter left
			if(x>(nextTile.getX()) && y==nextTile.getY() && critterMovedOneTile==false)
			{	
				x -= Clock.delta() * speed;

				if(x<=nextTile.getX())
					critterMovedOneTile=true;
			}
			
			if(y>(nextTile.getY()) && x==nextTile.getX() && critterMovedOneTile==false)
			{	
				y -= Clock.delta() * speed;

				if(y<=nextTile.getY())
					critterMovedOneTile=true;
			}


			//Critter moved to the next tile so change the start tile and next tile for it
			if(critterMovedOneTile)
			{	
				if(pathStepIndex<Path.continousPath.size()-2)
				{	
					startTile=nextTile;
					x=startTile.getX();
					y=startTile.getY();
					pathStepIndex++;
					this.nextTile = Boot.grid.getTile(CoordinateConverter.getYCordinate(Path.continousPath.get(pathStepIndex+1)), 
							CoordinateConverter.getXCordinate(Path.continousPath.get(pathStepIndex+1)));
					critterMovedOneTile=false;
				}
			}
		}

	}
}