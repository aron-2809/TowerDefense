/**
 * 
 */
package utility;

import java.util.ArrayList;
import java.util.Iterator;

import critter.Critter;
import critter.CritterFactory;
import main.GameStateManager;

/**
 * THis class generates the wave of critter and shoots them. 
 * @author eshinig
 *
 */
public class Wave {
	private float timeLastSpawn, spawnTime;
	private String critterType;
	private ArrayList<Critter> critterList;
	private static int numofCrittersInWave=3;
	private static int critterCounter=0;//No more than 3 counter in the wave
	
	public Wave(float spawnTime, String critterType)
	{
		this.critterType=critterType;
		this.spawnTime=spawnTime;
		this.timeLastSpawn=0;
		this.critterList=new ArrayList<Critter>();
	}

/**
 * This method updates and draws the critter on the map 
 */
	public void update()
	{
		timeLastSpawn+= Clock.delta();
		if(timeLastSpawn>spawnTime)
		{
			this.Spawn();
			timeLastSpawn=0;
		}
		System.out.println("Size of CritterLIst"+critterList.size());
		if(critterList.size()<=numofCrittersInWave)
		{
	
			
			Iterator<Critter> iter = critterList.iterator();
			//Remove the critter if reached endpoint
			while (iter.hasNext()) {
				Critter critter = iter.next();
				if (!critter.isCriterAlive)
					iter.remove();
				else
				{
					critter.update();
					critter.draw();
				}
			}
		}
		
		if(critterList.size()==0)
			GameStateManager.setGameState("IDLE");
			
	}
	
	/**
	 * THis method creates new Critter using factory design pattern
	 */
	private void Spawn()
	{
		if(critterCounter<numofCrittersInWave)
			{
			critterList.add(CritterFactory.getCritter(critterType));
			critterCounter++;
			
			}
	}
	/**
	 * Reset Critter counter to zero to enable or create another wave 
	 */
	public static int resetCritterCounter()
	{
		critterCounter=0;
		return critterCounter;
		
	}

}
