package ncu.csie.game.entities;

import java.awt.Graphics;
import java.awt.Rectangle;

import ncu.csie.game.Handler;
import ncu.csie.game.item.Item;

public abstract class Entity {
	
	protected Handler handler;
	protected float x, y;
	protected int width, height;
	protected Rectangle bounds;
	
	
	public Entity(Handler handler, float x, float y, int width, int height){
		this.handler = handler;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		
		bounds = new Rectangle(0, 0, width, height);
	}
	
	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public abstract void tick();
	public abstract void render(Graphics g);
	
	public boolean checkEntityCollisions(double xOffset, double yOffset){
		for(Entity e: handler.getWorld().getEntityManager().getEntities()){
			if(e.equals(this)){
				continue;
			}
			if(e.getCollisionBounds(0f, 0f).intersects(getCollisionBounds(xOffset, yOffset))){
				return true;
			}			
		}
		return false;
	}
	
	public Rectangle getCollisionBounds(double xOffset, double yOffset){
		return new Rectangle((int)(x+bounds.x+xOffset), (int)(y+bounds.y+yOffset), bounds.width, bounds.height);
	}
	
}
