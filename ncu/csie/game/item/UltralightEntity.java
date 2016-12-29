package ncu.csie.game.item;

import java.util.ArrayList;
import java.util.Iterator;
import ncu.csie.game.worlds.Handler;
import ncu.csie.game.entities.Entity;
import ncu.csie.game.entities.creatures.Monster;


public class UltralightEntity extends ItemEntity{
		
		public UltralightEntity(Handler handler, float x, float y, int width, int height, int i) {
			super(handler, x, y, width, height);
			
		}

		@Override
		public void effectResult(ArrayList<Entity> list , Entity object) {
			object.setX(-100);
			object.setY(-100);
			handler.getWorld().monsterReborn((Monster)object);
		}

}
