 package ncu.csie.game.ClientAnimation;

import java.awt.Graphics;
import java.awt.Rectangle;

import ncu.csie.game.ClientEnd.GameHandler;
import ncu.csie.game.entities.Entity;
import ncu.csie.game.worlds.Handler;

public abstract class EntityRender {
	protected GameHandler handler;
	protected double x, y;
	protected int width, height;
	protected Rectangle bounds;
	
	public EntityRender(GameHandler handler, float x, float y, int width, int height){
		this.handler = handler;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		bounds = new Rectangle(0, 0, width, height);
	}
	
	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
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

	public void tick(){}
	public abstract void render(Graphics g);
	
	
}
