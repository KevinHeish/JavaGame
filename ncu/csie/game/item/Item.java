package ncu.csie.game.item;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import ncu.csie.game.Handler;
import ncu.csie.game.entities.Entity;
import ncu.csie.game.entities.creatures.Player;

public abstract class Item extends Entity{
	
	protected int[] diffX = {0 , 1 , 0 ,-1};
	protected int[] diffY = {-1, 0 , 1 ,0};
	protected String[] key = {"up", "right" , "down" ,"left"};
	protected BufferedImage itemImage;
	
	public Item(Handler handler, float x, float y, int width, int height) {
		super(handler, x, y, width, height);
	}

	@Override
	public void tick()
	{
		
	}
	
	public BufferedImage getImage()
	{
		return itemImage;
	}
	
	@Override
	public abstract void render(Graphics g);
	
	public boolean effect(){
		return false;
	}
	
}
