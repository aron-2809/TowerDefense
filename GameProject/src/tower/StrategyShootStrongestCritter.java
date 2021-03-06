/**
 * 
 */
package tower;

import static graphics.Designer.quickTexture;
import critter.Critter;
import ai.Strategy;

/**
 * @author s_niga
 *This class add new bullets in the list targeting strongest critter
 */
public class StrategyShootStrongestCritter extends Tower implements Strategy {

	@Override
	public int execute(float x, float y, float speedOfBullet, int damage,
			EffectType effectType, ShootStrategyEnum strategyTile,
			Critter strongestCritter) {
		System.out.println("shooting tower with strategy Strongest critter");

		
//		System.out.println(x+" "+y+" "+speedofBullet+" "+damage+" "+effectType.toString()+" "+strategyTile.name()+" "+targetTile.getX()+" "+targetTile.getY());
		
		shootTiles.add(new ShootTile(quickTexture("bullet"), x, y, speedOfBullet, damage, effectType, strategyTile, strongestCritter));
		return 0;
	}

}
