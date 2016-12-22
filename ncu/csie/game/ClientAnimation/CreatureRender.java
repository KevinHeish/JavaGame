package ncu.csie.game.ClientAnimation;

import java.awt.Graphics;

import ncu.csie.game.ClientEnd.GameHandler;
import ncu.csie.game.tiles.Tile;
import ncu.csie.game.worlds.Handler;

public abstract class CreatureRender extends EntityRender{

	
	public static final int DEFAULT_HEALTH = 10;
	public static final float DEFAULT_SPEED = 10f;
	public static final int DEFUAL_CREATURE_WIDTH = 100,
							DEFAULT_CREATURE_HEIGHT = 100;
	protected int health; 	  
	
	public CreatureRender(GameHandler handler, float x, float y, int width, int height) {
		super(handler, x, y, width, height);
		health = DEFAULT_HEALTH;

	}

	abstract public void tick();
	abstract public void render(Graphics g);
	
	
	public void updatePosition(double x , double  y){
		this.x = x;
		this.y = y;
	}
	
}
