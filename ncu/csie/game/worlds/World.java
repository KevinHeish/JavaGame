package ncu.csie.game.worlds;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Timer;
import java.util.TimerTask;

import ncu.csie.game.Handler;
import ncu.csie.game.entities.EntityManager;
import ncu.csie.game.entities.creatures.Monster;
import ncu.csie.game.entities.creatures.Player;
import ncu.csie.game.entities.statics.Tree;
import ncu.csie.game.item.*;
import ncu.csie.game.tiles.Tile;
import ncu.csie.game.utils.Utils;

public class World {
	
	private Handler handler;
	private int width, height;
	private int spawnX, spawnY;
	private int[][] tiles;
	private Queue<Monster> monsterGenerate = new LinkedList<Monster>();
	private int player_id;
	//Entities
	private EntityManager entityManager;
	
	public World(Handler handler, String path){
		this.handler = handler;
		Timer timer = new Timer();
		
		loadWorld(path);
		int id = 0;//test
		
		entityManager = new EntityManager(handler, new Player(handler, 100, 100, id));
		//entityManager.addEntity(new Tree(handler, 500, 500));
		//entityManager.addEntity(new Tree(handler, 700, 700));
		//entityManager.addEntity(new Tree(handler, 900, 900));
		
		timer.schedule(new TimerTask() {  
            @Override  
            public void run(){
            	int itemIndex = (int)(Math.random() * 5);
        		itemGenerator(itemIndex);
        		if(monsterGenerate.peek()!=null)
        		{
        			Monster monsternew = monsterGenerate.poll();
        			monsternew.setX(4500);//((int)((Math.random() * 8000+100) - 100));
        			monsternew.setY(1500);//((int)((Math.random() * 2000+100) - 100));
        			entityManager.addEntity(monsternew);
        		}
        		//System.out.println(itemIndex + " : Item Generated.");
        		
            }
        },0 , 5000);
		
		entityManager.addEntity(new Flash(handler, 200, 200, 50,50));
		entityManager.addEntity(new Crystallize(handler, 300, 300 , 50, 50));
		entityManager.addEntity(new Snowball(handler, 400, 400 , 50, 50));
		entityManager.addEntity(new Ghostwalk(handler, 280, 450, 50, 50));
		entityManager.addEntity(new Ultralight(handler, 600, 600 , 50, 50));
				
			for(int i=0;i<25;++i)
			{
				int posX = (int)((Math.random() * 8000+100) - 100) /Tile.TILEWIDTH;
				int posY = (int)((Math.random() * 2000+100) - 100) /Tile.TILEHEIGHT;
				//System.out.println(posX +" "+posY);
				
				while(tiles[posX][posY]==4){
					posX = (int)((Math.random() * 8000+100) - 100) /Tile.TILEWIDTH;
					posY = (int)((Math.random() * 2000+100) - 100) /Tile.TILEHEIGHT;
				}
				
				Monster monster;
				if(i<15)//WalkingGrass
				{
					monster = new Monster(handler , posX*Tile.TILEWIDTH, posY*Tile.TILEHEIGHT,i,5,8,1);
				}
				else if(i>=15 && i<20)//FireDragon
				{
					monster = new Monster(handler , posX*Tile.TILEWIDTH, posY*Tile.TILEHEIGHT,i,3,5,7);
				}
				else//Piplup
				{
					monster = new Monster(handler , posX*Tile.TILEWIDTH, posY*Tile.TILEHEIGHT,i,1,2,10);
				}
				entityManager.addEntity(monster);
			}
		

		entityManager.getPlayer().setX(spawnX);
		entityManager.getPlayer().setY(spawnY);
	}
	
	public void tick(){
		entityManager.tick();
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
		//Entities
		entityManager.render(g);
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
	
	private void loadWorld(String path){
		String file = Utils.loadFileAsString(path);
		String[] tokens = file.split("\\s+");
		width = Utils.parseInt(tokens[0]);
		height = Utils.parseInt(tokens[1]);
		spawnX = Utils.parseInt(tokens[2]);
		spawnY = Utils.parseInt(tokens[3]);		
		tiles = new int[width][height];
		for(int y=0; y<height; ++y){
			for(int x=0; x<width; ++x){
				tiles[x][y] = Utils.parseInt(tokens[(x+y*width)+4]);
			}
		}
		
	}
	
	public int getWidth(){
		return width;
	}
	
	public int getHeight(){
		return height;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}
	
	public void monsterReborn(Monster deadmonster)
	{
		monsterGenerate.offer(deadmonster);
	}
	
	public void itemGenerator(int index)
	{
		int rx = (int)((Math.random() * 8000+100) - 50) /Tile.TILEWIDTH;
		int ry = (int)((Math.random() * 2000+100) - 50) /Tile.TILEHEIGHT;
		
		
		while(tiles[rx][ry]==4){
			rx = (int)((Math.random() * 8000+100) - 50) /Tile.TILEWIDTH;
					ry = (int)((Math.random() * 2000+100) - 50) /Tile.TILEHEIGHT;
		}
		
		if(index == 1)
			entityManager.addEntity(new Flash(handler, rx*Tile.TILEWIDTH, ry*Tile.TILEHEIGHT, 50,50));
		
		else if(index == 2)
			entityManager.addEntity(new Crystallize(handler,rx*Tile.TILEWIDTH, ry*Tile.TILEHEIGHT, 50, 50));
		
		else if(index == 3)
			entityManager.addEntity(new Snowball(handler, rx*Tile.TILEWIDTH, ry*Tile.TILEHEIGHT , 50, 50));
		
		else if(index == 4)
			entityManager.addEntity(new Ghostwalk(handler, rx*Tile.TILEWIDTH, ry*Tile.TILEHEIGHT , 50, 50));
		
		else if(index==0)
			entityManager.addEntity(new Ultralight(handler, rx*Tile.TILEWIDTH, ry*Tile.TILEHEIGHT , 50, 50));
	}
	
	public int getMapTile(int x , int y)
	{
		if(x<0 || y<0 || x >width || y >height)
			return -1;
		else
			return tiles[x][y];
	}
	
}
