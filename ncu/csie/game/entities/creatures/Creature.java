package ncu.csie.game.entities.creatures;

import ncu.csie.game.entities.Entity;
import ncu.csie.game.tiles.Tile;
import ncu.csie.game.worlds.Handler;

public abstract class Creature extends Entity{

	public static final int DEFAULT_HEALTH = 10;
	public static final float DEFAULT_SPEED = 10f;
	public static final int DEFUAL_CREATURE_WIDTH = 100,
							DEFAULT_CREATURE_HEIGHT = 100;
	protected int health; 	
	protected float speed;
	protected double xMove, yMove;
	
	public Creature(Handler handler, float x, float y, int width, int height) {
		super(handler, x, y, width, height);
		health = DEFAULT_HEALTH;
		speed = DEFAULT_SPEED;
		xMove = 0;
		yMove = 0;
	}
	
	public void move(){
		if(!checkEntityCollisions(xMove, 0f)){
			moveX();
		}
		if(!checkEntityCollisions(0f, yMove)){
			moveY();
		}
				
	}
	
	public void moveX(){
		if(xMove > 0){ //Moving right
			int tx = (int) (x + xMove+bounds.x+bounds.width)/Tile.TILEWIDTH;
			
			if(tx > handler.getWorld().getWidth())
				x = (int)(tx * Tile.TILEWIDTH - bounds.x - bounds.width - xMove);
			
			else if(!collissionWithTile(tx, (int)(y+bounds.y)/Tile.TILEHEIGHT) &&
					!collissionWithTile(tx, (int)(y+bounds.y+bounds.height)/Tile.TILEHEIGHT)){
				x += xMove;
			}
			else{
				x = (int)(tx * Tile.TILEWIDTH - bounds.x - bounds.width - xMove);
			}
		}
		
		else if(xMove < 0){ //Moving left
			int tx = (int) (x + xMove+bounds.x)/Tile.TILEWIDTH;
			
			if(tx < 0)
				x = (int)(tx * Tile.TILEWIDTH - bounds.x - bounds.width - xMove);
			
			
			else if(!collissionWithTile(tx, (int)(y+bounds.y)/Tile.TILEHEIGHT) &&
					!collissionWithTile(tx, (int)(y+bounds.y+bounds.height)/Tile.TILEHEIGHT)){
				x += xMove;
			}	
			else{
				x = (int)(tx * Tile.TILEWIDTH  + Tile.TILEWIDTH - xMove - bounds.x);
			}
		}
	}
	
	public void moveY(){
		if(yMove < 0){ //Moving up
			int ty = (int) (y + yMove + bounds.y) / Tile.TILEHEIGHT;
			
			if(ty < 0)
				y = (int)(ty * Tile.TILEHEIGHT  - yMove - bounds.y);
			
			else if(!collissionWithTile((int)(x + bounds.x)/Tile.TILEWIDTH, ty) && 
					!collissionWithTile((int)(x+bounds.width)/Tile.TILEWIDTH, ty)){
				y += yMove;
			}
			else{		
				y = (int)(ty * Tile.TILEHEIGHT +Tile.TILEHEIGHT - yMove - bounds.y);
			}
		}
		else if(yMove > 0){ //Moving down
			int ty = (int) (y + yMove + bounds.y+bounds.height) / Tile.TILEHEIGHT;
			
			if(ty > handler.getWorld().getHeight())
				y = (int)(ty * Tile.TILEHEIGHT +Tile.TILEHEIGHT - yMove - bounds.y);
			
			else if(!collissionWithTile((int)(x + bounds.x)/Tile.TILEWIDTH, ty) && 
					!collissionWithTile((int)(x+bounds.width)/Tile.TILEWIDTH, ty)){
				y += yMove;
			}
			else{
				y = (int)(ty * Tile.TILEHEIGHT  - bounds.y - bounds.height - yMove);
			}
		}
	}
	
	public boolean collissionWithTile(int x, int y){
		return handler.getWorld().getTile(x , y).isSolid();
	}
	
	public double getxMove() {
		return xMove;
	}
	public void setxMove(double xMove) {
		this.xMove = xMove;
	}
	public double getyMove() {
		return yMove;
	}
	public void setyMove(double yMove) {
		this.yMove = yMove;
	}
	
}
