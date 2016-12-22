package ncu.csie.game.gfx;

import ncu.csie.game.ClientAnimation.EntityRender;
import ncu.csie.game.ClientEnd.GameHandler;
import ncu.csie.game.tiles.Tile;

public class GameCamera {
	
	private GameHandler handler;
	private double xOffset, yOffset;
	public GameCamera(GameHandler handler, float xOffset, float yOffset){
		this.handler = handler;
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}
	
	public void checkBlankSpace(){
		if(xOffset < 0){
			xOffset = 0;
		}
		else if(xOffset > handler.getWidth()*Tile.TILEWIDTH - handler.getWidth()){
			xOffset = handler.getWidth()*Tile.TILEWIDTH - handler.getWidth();
		}
		
		if(yOffset < 0){
			yOffset = 0;
		}
		else if(yOffset > handler.getHeight()*Tile.TILEHEIGHT - handler.getHeight()){
			yOffset =  handler.getHeight()*Tile.TILEHEIGHT - handler.getHeight();
		}
		
		
	}
	
	
	public void centerOnEntity(EntityRender e){
		xOffset = e.getX() - handler.getWidth() / 2 + e.getWidth()/2;
		yOffset = e.getY() - handler.getHeight() / 2 + e.getHeight()/2;
		checkBlankSpace();
	}
	
	
	public void move(float xAmt, float yAmt){
		xOffset += xAmt;
		yOffset += yAmt;
		checkBlankSpace();
	}
	
	public double getxOffset() {
		return xOffset;
	}
	public void setxOffset(double xOffset) {
		this.xOffset = xOffset;
	}
	public double getyOffset() {
		return yOffset;
	}
	public void setyOffset(double yOffset) {
		this.yOffset = yOffset;
	}
}
