package ncu.csie.game.tiles;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Tile {
	
	public static Tile[] tiles = new Tile[256];
	public static Tile grassTile = new GrassTile(0);
	public static Tile lawnTile = new LawnTile(1);
	public static Tile fireTile = new FireTile(2);
	public static Tile waterTile = new WaterTile(3);
	public static Tile rockTile = new RockTile(4);
	
	public static final int TILEWIDTH = 100, TILEHEIGHT = 100;
	
	protected BufferedImage texture;
	protected final int id;
	
	public Tile(BufferedImage texture, int id){
		this.texture = texture;
		this.id = id;
		tiles[id] = this;
	}
	
	public int getId(){
		return id;
	}
	
	public void tick(){
		
	}
	public void render(Graphics g, int x, int y){
		g.drawImage(texture, x, y, TILEWIDTH, TILEHEIGHT, null);
	}
	
	public boolean isSolid(){
		return false;
	}
}
