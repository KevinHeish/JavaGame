package ncu.csie.game.worlds;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Timer;
import java.util.TimerTask;

import ncu.csie.game.entities.EntityManager;
import ncu.csie.game.entities.creatures.Monster;
import ncu.csie.game.entities.creatures.Player;
import ncu.csie.game.item.*;
import ncu.csie.game.tiles.Tile;
import ncu.csie.game.utils.Utils;

public class World extends Thread{
	
	private Handler handler;
	private int width, height;
	private int spawnX, spawnY;
	private int[][] tiles;
	private Queue<Monster> monsterGenerate = new LinkedList<Monster>();
	//Entities
	private EntityManager entityManager;
	private ArrayList<Player> players;
	private ArrayList<Monster> monsters;
	private ArrayList<Item> items;
	private int playerNumbers = 1;
	
	
	public World(Handler handler, String path){
		this.handler = handler;
		Timer timer = new Timer();
		loadWorld(path);
		players = new ArrayList<Player>();
		monsters = new ArrayList<Monster>();
		items = new ArrayList<Item>();
		
		Player createPlayer = new Player(handler ,100 , 100,0);
		players.add(createPlayer);
		
		Player createPlayer2 = new Player(handler ,300 , 200,1);
		players.add(createPlayer2);
		createPlayer2.setPlayerid(3);
		
		Player createPlayer3 = new Player(handler ,500 , 300,2);
		players.add(createPlayer3);
		createPlayer3.setPlayerid(5);
		
		Player createPlayer4 = new Player(handler ,600 , 100,3);
		players.add(createPlayer4);
		createPlayer4.setPlayerid(2);
		
		entityManager = new EntityManager(handler, players);
		
		timer.schedule(new TimerTask() {  
            @Override  
            public void run(){
            	
        		if(monsterGenerate.peek()!=null)
        		{
        			Monster monsternew = monsterGenerate.poll();
        			
        			monsternew.setX(4500);
        			monsternew.setY(1500);
        		}
            }
        },0 , 5000);
			
		for(int i = 0 ; i < 15 ; i++){
			int itemIndex = (int)(Math.random() * 5);
			
			itemGenerator(itemIndex);
		}
		
		for (int i = 0; i < 25; ++i) {
			int posX = (int) ((Math.random() * 8000 + 100) - 100) / Tile.TILEWIDTH;
			int posY = (int) ((Math.random() * 2000 + 100) - 100) / Tile.TILEHEIGHT;
			// System.out.println(posX +" "+posY);

			while (tiles[posX][posY] == 4) {
				posX = (int) ((Math.random() * 8000 + 100) - 100) / Tile.TILEWIDTH;
				posY = (int) ((Math.random() * 2000 + 100) - 100) / Tile.TILEHEIGHT;
			}

			Monster monster;
			if (i < 15)// WalkingGrass
			{
				monster = new Monster(handler, posX * Tile.TILEWIDTH, posY * Tile.TILEHEIGHT, i, 5, 8, 1);
			} 
			else if (i >= 15 && i < 20)// FireDragon
			{
				monster = new Monster(handler, posX * Tile.TILEWIDTH, posY * Tile.TILEHEIGHT, i, 3, 5, 7);
			} 
			else// Piplup
			{
				monster = new Monster(handler, posX * Tile.TILEWIDTH, posY * Tile.TILEHEIGHT, i, 1, 2, 10);
			}
			entityManager.addEntity(monster);
			monsters.add(monster);
		}
		assert monsters.size() ==25;
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
		Item tempItem = null;
		
		if(index == 1){
			tempItem = new Flash(handler, rx*Tile.TILEWIDTH, ry*Tile.TILEHEIGHT, 50,50,index);
		}
		else if(index == 2){
			tempItem = new Crystallize(handler,rx*Tile.TILEWIDTH, ry*Tile.TILEHEIGHT, 50, 50,index);
		}
		else if(index == 3){
			tempItem = new Snowball(handler, rx*Tile.TILEWIDTH, ry*Tile.TILEHEIGHT , 50, 50,index);
		}
		else if(index == 4){
			tempItem = new Ghostwalk(handler, rx*Tile.TILEWIDTH, ry*Tile.TILEHEIGHT , 50, 50,index);
		}
		else if(index==0){
			tempItem = new Ultralight(handler, rx*Tile.TILEWIDTH, ry*Tile.TILEHEIGHT , 50, 50,index);
		}
		entityManager.addEntity(tempItem);
		items.add(tempItem);
	}
	
	
	public int getMapTile(int x , int y)
	{
		if(x<0 || y<0 || x >width || y >height)
			return -1;
		else
			return tiles[x][y];
	}
	
	public ArrayList<Player> getPlayers()
	{
		return players;
	}
	
	public ArrayList<Monster> getMonsters()
	{
		return monsters;
	}
	
	public ArrayList<Item> getItems()
	{
		return items;
	}

	@Override
	public void run()
	{
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {  
	        @Override  
	        public void run(){
	        	for(int i = 0; i < monsters.size(); i++){
	    			monsters.get(i).tick();
	    		}
	        }
		},0 ,25);
		
	}
	
	public void itemRemoved(Item item)
	{	
		item.setX(99999);
		item.setY(99999);
	}
	
}
