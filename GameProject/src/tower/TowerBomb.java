package tower;

import main.Controller;
import map.Tile;
import static graphics.Designer.*;

import java.util.ArrayList;

import org.newdawn.slick.opengl.Texture;
/**
 * This class is a sub class of Tower with more specific characteristic
 * @author Rashpal
 *
 */
public class TowerBomb extends Tower {

	/**
	 * This constructor initializes the TowerBomb object.
	 * @param texture Shape or kind of Tower
	 * @param startTile Location of the tile in the grid
	 */
	public TowerBomb(Texture texture, Tile startTile) {
		super();
		this.x=startTile.getX();
		this.y=startTile.getY();
		this.width=startTile.getWidth();
		this.height=startTile.getHeight();
		this.damage=20;
		this.range=20;
		this.texture=texture;
		this.price=50;
		this.speedOfFire = 30; //speed of firing the bullets
		this.lastShootTime = 0; //the time for last shooted bullet
		this.shootTiles = new ArrayList<ShootTile>(); //list of bullets
	}
	
	/**
	 * Draws the Tower on the map
	 */
	@Override
	public void draw() {
		drawQuadTex(texture, x, y, width, height);

	}

	@Override
	public boolean buy() {
		if(Controller.money >=price)
		{
			Controller.money=Controller.money-price;
			return true;
		}
	else
		return false;	
	}
	
	/**
	 * Buys the tower 
	 */

	public void description() {

		System.out.println("-----Discription of Bomb Tower-----");
		System.out.println("Tower Pwoer " + damage);
		System.out.println("Tower Range " + range);
		System.out.println("Price of tower $" + price);
		System.out.println();
	}
	
	public void sell() {
		System.out.println("You sold the Bomb Tower");
		Controller.money = Controller.money + price;
		System.out.println("your current money $" + Controller.money);
		System.out.println();

	}
	
	public void update() {
		if(Controller.money >=10)
	{
		Controller.money=Controller.money-10;
		this.range=this.range+10;
		System.out.println("Bomb Tower's updated range:"+this.range);
		System.out.println("Your Current Money: &"+Controller.money);
	}
	else{
			System.out.println("Sorry your money is less the price to update the tower");
			System.out.println("Your Current Money: &"+Controller.money);	
		}	
	}

	

}