package ncu.csie.game.ClientEnd;

import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Queue;

import ncu.csie.game.ClientAnimation.EntityRenderManager;
import ncu.csie.game.ClientAnimation.PlayerRender;
import ncu.csie.game.tiles.Tile;
import ncu.csie.game.utils.Utils;

public class Map {
	private GameHandler handler;
	private int width, height , spawnX , spawnY;
	private int[][] tiles;

	
	
	public Map(GameHandler handler, String path){
		this.handler = handler;
		loadWorld(path);

	}
	
	private void loadWorld(String path){
		String file = Utils.loadFileAsString(path);
		String[] tokens = file.split("\\s+");
		width = Utils.parseInt(tokens[0]);
		height = Utils.parseInt(tokens[1]);
		tiles = new int[width][height];
		spawnX = Utils.parseInt(tokens[2]);
		spawnY = Utils.parseInt(tokens[3]);
		for(int y=0; y<height; ++y){
			for(int x=0; x<width; ++x){
				tiles[x][y] = Utils.parseInt(tokens[(x+y*width)+4]);
			}
		}	
	}
	
	
	public void render(Graphics g){
		int xStart = (int)Math.max(0, handler.getGameCamera().getxOffset()/Tile.TILEWIDTH);
		int xEnd = (int)Math.min(width, (handler.getGameCamera().getxOffset()+handler.getWidth())/Tile.TILEWIDTH+1);
		int yStart = (int)Math.max(0, handler.getGameCamera().getyOffset()/Tile.TILEHEIGHT);
		int yEnd = (int)Math.min(height, (handler.getGameCamera().getyOffset()+handler.getHeight())/Tile.TILEHEIGHT + 1);
		
		for(int y=yStart; y<yEnd; ++y){
			for(int x=xStart; x<xEnd; ++x){
				getTile(x, y).render(g, (int)(x*Tile.TILEWIDTH - handler.getGameCamera().getxOffset())
						, (int)(y*Tile.TILEHEIGHT-handler.getGameCamera().getyOffset()));
			}
		}
		
	}
	
	public Tile getTile(int x, int y){
		if(x < 0 || y < 0 || x >= width || y >= height){
			return Tile.grassTile;
		}
		
		Tile t = Tile.tiles[tiles[x][y]];
		if(t == null)
			return Tile.lawnTile;
		return t;
	}
	
	public int getWidth(){
		return width;
	}
	
	public int getHeight(){
		return height;
	}
}
