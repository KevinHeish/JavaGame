package ncu.csie.game.item;

import java.util.ArrayList;

import ncu.csie.game.Handler;
import ncu.csie.game.entities.Entity;


//For multiple fly items , no using right now .

public class FlyItemEntity extends ItemEntity{

	public FlyItemEntity(Handler handler, float x, float y, int width, int height) {
		super(handler, x, y, width, height);
		
	}

	@Override
	public void effectResult(ArrayList<Entity> list, Entity object) {
		
	}

}

