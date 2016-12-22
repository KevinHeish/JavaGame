package ncu.csie.game.item;

import java.awt.Graphics;
import java.util.ArrayList;

import ncu.csie.game.worlds.Handler;
import ncu.csie.game.entities.Entity;


public abstract class ItemEntity extends Item{
	protected int timer = 0;
	
	public ItemEntity(Handler handler, float x, float y, int width, int height) {
		super(handler, x, y, width, height);
	}

	
	public abstract void effectResult(ArrayList<Entity> list , Entity object);

}
