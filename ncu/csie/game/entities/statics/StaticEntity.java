package ncu.csie.game.entities.statics;

import ncu.csie.game.entities.Entity;
import ncu.csie.game.worlds.Handler;

public abstract class StaticEntity extends Entity{
	
	public StaticEntity(Handler handler, float x, float y, int width, int height){
		super(handler, x, y, width, height);
	}
}
