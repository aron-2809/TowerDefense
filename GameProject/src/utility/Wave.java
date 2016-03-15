/**
 * 
 */
package utility;

import java.util.ArrayList;

import critter.Critter;
import critter.CritterFactory;

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
	private static int counter;

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
			for(Critter c:critterList)
			{
				c.update();
				c.draw();
			}
		}
		
		
	}
	
	/**
	 * THis method creates new Critter using factory pattern
	 */
	private void Spawn()
	{
		if(critterList.size()<numofCrittersInWave)
			critterList.add(CritterFactory.getCritter(critterType));
	}



}
