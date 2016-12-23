package ncu.csie.game.entities.creatures;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import ncu.csie.game.entities.Entity;
import ncu.csie.game.entities.statics.StaticEntity;
import ncu.csie.game.gfx.Animation;
import ncu.csie.game.gfx.Assets;
/*
import ncu.csie.game.item.Item;
import ncu.csie.game.item.ItemEntity;
import ncu.csie.game.item.Snowball;
import ncu.csie.game.item.SnowballEntity;
*/
import ncu.csie.game.tiles.Tile;
import ncu.csie.game.worlds.Handler;

public class Monster extends Creature{
	
	//Record last key released
	private String key;
	//Animations
			
	//private Monster monster;
	private int speedup = 0;
	private int walkSpeed = -1;//run speed
	private int ID = -1;
	private int attackSpeed = -1;//attack power
	private int target = -1;
	private int losehp = 0;
	private int direction = -1;
	
	public Monster(Handler handler, float x, float y,int id,int Speed ,int Attack,int Hp)
	{
		super(handler, x, y, Creature.DEFUAL_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);
		
		bounds.x = 15;
		bounds.y = 50;
		bounds.width = 70;
		bounds.height = 50;
		this.speed = 0;
		//
		//monster = new Monster(id,Speed,Attack,Hp);
		ID = id;
		walkSpeed = Speed;
		attackSpeed = Attack;
		losehp = Hp;
		//
		
		key = "left";
		Timer timer = new Timer();
		
		if(target == -1)
		{
			timer.schedule(new TimerTask() {  
		        @Override  
		        public void run(){ 
		        	changeDerection();
		        }
		    },5000,10000);
		}
	}
	
	@Override
	public void tick() {
		
		attackDetection();
		setMove();
		move();
	}
	
	private void attackDetection() {

		int dr = -1;
		int count = 1;
		
		
		for(int playerIndex = 0 ; playerIndex < count ;playerIndex++ ){
			Player player = handler.getWorld().getPlayers().get(playerIndex);
			if(Math.abs(player.getX() - this.x) < 250.0 && Math.abs(player.getY() - this.y) < 250.0 && player.isAlive())//region = 200*200
			{
				target = player.GetID();
				this.speed = attackSpeed + speedup;
				
				if(Math.abs(player.getX() - this.x) > 50.0)
				{
					if(player.getX() < this.x)
					{
						dr = 3;
						direction = 3;
					}
					else
					{
						dr = 1;
						direction = 1;
					}
				}
				if(Math.abs(player.getY() - this.y) > 50.0)
				{
					if(player.getY() < this.y)
					{
						if(dr == -1)direction = 0;
					}
					else
					{
						if(dr == -1)direction = 2;
					}
				}
			}
			else
			{
				this.speed = walkSpeed + speedup;
				if(this.speed<=0)
					this.speed = 0;
				target = -1;
			}
		}
	}
	
	private int clockwiseFind(int startdr) {

		int dr = -1;
		int count = 1;
		
		
		for(int i=0;i<count;i++)
		{
			if((startdr+i)%4 == 1){ //Moving right
				int tx = (int) (this.x + this.speed+bounds.x+bounds.width)/Tile.TILEWIDTH;
				if(!collissionWithTile(tx, (int)(this.y+bounds.y)/Tile.TILEHEIGHT) &&
						!collissionWithTile(tx, (int)(this.y+bounds.y+bounds.height)/Tile.TILEHEIGHT)){
					dr = 1;
				}
			}
			if((startdr+i)%4 == 3)//Moving left
			{ 
				int tx = (int) (this.x - this.speed+bounds.x)/Tile.TILEWIDTH;
				if(!collissionWithTile(tx, (int)(this.y+bounds.y)/Tile.TILEHEIGHT) &&
						!collissionWithTile(tx, (int)(this.y+bounds.y+bounds.height)/Tile.TILEHEIGHT)){
					dr = 3;
				}	
			}
			if((startdr+i)%4 == 0){ //Moving up
				int ty = (int) (y - this.speed + bounds.y) / Tile.TILEHEIGHT;
				if(!collissionWithTile((int)(x + bounds.x)/Tile.TILEWIDTH, ty) && 
						!collissionWithTile((int)(x+bounds.width)/Tile.TILEWIDTH, ty)){
					dr = 0;
				}
			}
			if((startdr+i)%4 == 2){ //Moving down
				int ty = (int) (y + this.speed + bounds.y+bounds.height) / Tile.TILEHEIGHT;	
				if(!collissionWithTile((int)(x + bounds.x)/Tile.TILEWIDTH, ty) && 
						!collissionWithTile((int)(x+bounds.width)/Tile.TILEWIDTH, ty)){
					dr = 2;
				}
			}
			//System.out.println("dr:"+dr);
			if(dr != -1)break;
		}
		
		return dr;
	}

	private void changeDerection() {
		
		int dr = (int)(Math.random() * 4);	
		direction = clockwiseFind(dr);
		
	}
	
	private void setMove(){
		xMove = 0;
		yMove = 0;
		direction = clockwiseFind(direction);
		if(direction == 0){
			
			yMove = -speed;
		}
		else if(direction == 2){
			yMove = speed;
		}
		else if(direction == 3){
			xMove = -speed;
			key = "left";
		}
		else if(direction == 1){
			xMove = speed;
			key = "right";
		}
		
	}

	@Override
	public boolean checkEntityCollisions(double xOffset, double yOffset){
		ArrayList<Entity> e = handler.getWorld().getEntityManager().getEntities();
		
		for(int i = 0 ;i<e.size();i++){
			if(e.get(i).equals(this)){
				continue;
			}
			if(e.get(i).getCollisionBounds(0f, 0f).intersects(getCollisionBounds(xOffset, yOffset))){
				if(e.get(i) instanceof StaticEntity)//tree
				{
					direction = ((direction+1) % 4);
					return true;
				}
				
				
				/*
				if(e.get(i) instanceof ItemEntity){
					ArrayList<Entity> list = handler.getWorld().getEntityManager().getEntities();
					((ItemEntity) e.get(i)).effectResult(list, this);
					
					
					if(e.get(i).getClass().equals(SnowballEntity.class))
					{
						for(int index = 0 ; index < list.size(); index++)
						{
							if(e.get(i) == list.get(index))
							{
								list.remove(index);
							}
						}
					}
					
				}
				else if(e.get(i) instanceof Item){
					//System.out.println("¼²¨ì©Ô");
					continue;
				}
				*/
			}
		}
		return false;
	}

	//
	public int GetID()
	{
		return ID;
	}
		
	public int GetSpeed()
	{
		return walkSpeed;
	}
	
	public int GetLoseHp()
	{
		return losehp;
	}
	
	public int GetAttackspeed()
	{
		return attackSpeed;
	}
	
	public int GetTarget()
	{
		return target;
	}
	public void SetTarget(int PlayerID)
	{
		target = PlayerID;
	}
	
	public void SetAttack(int at)
	{
		attackSpeed = at;
	}
	public void setSpeedup(int sp)
	{
		speedup = sp;
	}
	
	public int getSpeedup()
	{
		return speedup;
	}
	
	public int getDirection()
	{
		return direction;
	}
	
}