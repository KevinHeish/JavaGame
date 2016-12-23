package ncu.csie.game.entities.creatures;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import ncu.csie.game.ClientEnd.State;
import ncu.csie.game.TCP.TCPClient;
import ncu.csie.game.entities.Entity;
import ncu.csie.game.gfx.Animation;
import ncu.csie.game.gfx.Assets;
//import ncu.csie.game.item.Item;
//import ncu.csie.game.item.ItemEntity;
import ncu.csie.game.tiles.Tile;
import ncu.csie.game.worlds.Handler;

public class Player extends Creature{	  
	private int blood, orispeed, speed, skillCD;
	private char type;
	private boolean SkillUse = true; //Can use skill or not
	//private Item[] bag = {null, null};
	private int BagCapacity; //How many things in the bag
	private int status[] = {0,0,0};//mapbuff,itembuff,skillbuff
	private Timer timer;
	private boolean onFire = false;
	private int player_id;
	private String direction;
	private int connectionId;
	private boolean alive = true;
	
	public Player(Handler handler, float x, float y, int index) {
		super(handler, x, y, Creature.DEFUAL_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);
		
		bounds.x = 15;
		bounds.y = 50;
		bounds.width = 70;
		bounds.height = 50;
		timer = new Timer();
		//Animations
		BagCapacity = 2;
		speed = 10;
		connectionId = index;
		direction = "down";
	}
	
	@Override
	public void tick(){
		buffcheck();
		move();
		if(GetBlood()<1){
			alive = false;
			setPlayerid(-1);
			}
		if(alive){
			if(!onFire){
				onFire = true;
				burnToDeath();
			}
			buffcheck();
		}
	}
	
	public void getInput(String instruction){
		xMove = 0;
		yMove = 0;
		getBuff();
		
		
		if(instruction.equals("up")){
			direction = instruction;
			yMove = -1*GetSpeed();
			
		}
		else if(instruction.equals("right")){
			direction = instruction;
			xMove = GetSpeed();
		
		}
		else if(instruction.equals("down")){
			direction = instruction;
			yMove = GetSpeed();

		}
		else if(instruction.equals("left")){
			direction = instruction;
			xMove = -1*GetSpeed();
		}
		
		//System.out.println(GetSpeed());
		//Item can be used simultaneously when moving.
		/*
		if(instruction=="useItem1"){
			Item useCase = GetBag(0);
			if(useCase!=null){
				System.out.println("D Pressed");
				if(useCase.effect()){
					DeleteBag(0);
				}
			}
		}
		
		if(instruction=="useItem2"){
			Item useCase = GetBag(1);
			if(useCase!=null){
				System.out.println("F Pressed");
				if(useCase.effect()){
					DeleteBag(1);
				}
			}
		}
		*/
		if(instruction.equals("useSkill"))
		{
			if(GetSkillUse()==true){
				useSkill();
			}
			skillcd();
			speed = GetSpeed();			
		}
	}
	
	
	private void buffcheck(){
		int buffadd = 0;
		
		for(int i=0;i<status.length;i++)
		{
			buffadd += status[i];
		}
		
		speed = orispeed + buffadd * 4;
	}
	
	public void setPlayerid(int player)
	{
		player_id = player;
		switch(player_id)
		{
		case 0:   //Asuna
			setInfo(80,6,'w',15);
			break;
		case 1:   //Hao
			setInfo(41,9,'f',15);
			break;
		case 2://Hasiaki
			setInfo(41,8,'g',15);
			break;
		case 3://Jade
			setInfo(50,8,'f',15);
			break;
		case 4://Sai
			setInfo(41,9,'g',15);
			break;
		case 5://Yuki
			setInfo(50,8,'w',15);
			break;
		default:
			break;
		}
	}
	
	
	@Override
	public boolean checkEntityCollisions(double xOffset, double yOffset){
		if(!alive){return false;}
		for(Entity e: handler.getWorld().getEntityManager().getEntities()){
			if(e.equals(this)){
				continue;
			}
			if(e.getCollisionBounds(0f, 0f).intersects(getCollisionBounds(xOffset, yOffset))){
				/*if(e instanceof ItemEntity)
				{
					//System.out.println("Do something");
					return false;
				}
				
				
				else if(e instanceof Item)
				{
					int index = this.SetBag((Item)e);
					if(index!=-1)
						handler.getWorld().getEntityManager().itemRemoved(index , player_id);
					return false;
				}
				*/
				if(e instanceof Monster)
				{
					ArrayList<Entity> sEntity = handler.getWorld().getEntityManager().getEntities();
					for(int j = 0; j < sEntity.size();j++)
	        		{
						if(e == sEntity.get(j))
						{
							SetBlood(GetBlood()-((Monster)(sEntity.get(j))).GetLoseHp());
							e.setX(-100);
		        			e.setY(-100);
							handler.getWorld().monsterReborn((Monster)sEntity.get(j));
							sEntity.remove(j);
							break;
						}
	        		}
					return false;
				}
				
				return true;
			}			
		}
		return false;
	}


	
	public void getBuff()
	{
		int typeIndex = 0;
		char[] type = {'g' ,'n' , 'f' , 'w', 'r'};
		
		typeIndex = getCurrentTile();

		if(type[typeIndex] == getType())
		{
			status[0] = 1;
		}
		else
		{
			status[0] = 0;
		}
	}
	
	public void burnToDeath()
	{
		timer.schedule(new TimerTask() {  
            @Override  
            public void run(){
            	if(getCurrentTile() == 2 && getType()!= 'f'){
        			lostHP(1);
        		}
            }
        },0,1000);
	}
	
	public int getCurrentTile(){
		int posX = (int)(x + this.getWidth()) / Tile.TILEWIDTH;
		int posY = (int)(y + this.getHeight()) / Tile.TILEHEIGHT;
		int typeIndex = handler.getWorld().getMapTile(posX,posY);
				
		return typeIndex;
	}
	
	public void useSkill(){

		ArrayList<Entity> e = handler.getWorld().getEntityManager().getEntities();
		Timer timer = new Timer();
		switch(GetID()) { 
		case 1:
	  		for(int i=0;i<e.size();i++){
        		if(e.get(i) instanceof Monster){
        			if(((Monster)e.get(i)).GetTarget()==1){
        				((Monster)e.get(i)).setSpeedup(-5);
        			}
			
        		}
        	}
	  		timer.schedule(new TimerTask() {  
	            @Override  
	            public void run() { 
	            	for(int i=0;i<e.size();i++){
	            		if(e.get(i) instanceof Monster){
	            			//if(((Monster)e.get(i)).getMonsterInfo().GetTarget()!=-1){
	            				((Monster)e.get(i)).setSpeedup(0);
	            			//}
	  			
	            		}
	            	}
	            }  
	  		},5000);  
	  			break;
		  	case 2:
		  		if(GetBlood()<80&&GetBlood()>=70){
		  			SetBlood(80);
		  		}
		  		else{
		  			SetBlood(GetBlood()+10);
		  		}
		  		break;
		  		
		  	case 3:	  		
		  		for(int i=0;i<e.size();i++){
	        		if(e.get(i) instanceof Monster){
	        			if(((Monster)e.get(i)).GetTarget()==1){
	        				((Monster)e.get(i)).setSpeedup(-1*((Monster)e.get(i)).GetAttackspeed());
	        			}
				
	        		}
	        	}
		  		timer.schedule(new TimerTask() {  
		            @Override  
		            public void run() { 
		            	for(int i=0;i<e.size();i++){
		            		if(e.get(i) instanceof Monster){
		            			if(((Monster)e.get(i)).GetTarget()==1){
		            				((Monster)e.get(i)).setSpeedup(0);
		            			}
		  			
		            		}
		            	}
		            }  
		  		},2000);  
		  		
		  			break;
		  		
			case 4: 
				setStatusOn(2); 
		  		timer.schedule(new TimerTask() {  
		            @Override  
		            public void run() { 
		            	setStatusOff(2);
		            }  
		  		},7000);  
		  		break;
		  		
			case 6:
		  		
		  		for(int i=0;i<e.size();i++){
	        		if(e.get(i) instanceof Monster){
	        			if(((Monster)e.get(i)).GetTarget()==1){
	        				((Monster)e.get(i)).setSpeedup(-1*((Monster)e.get(i)).GetAttackspeed());
	        			}
				
	        		}
	        	}
		  		timer.schedule(new TimerTask() {  
		            @Override  
		            public void run() { 
		            	for(int i=0;i<e.size();i++){
		            		if(e.get(i) instanceof Monster){
		            			if(((Monster)e.get(i)).GetTarget()==1){
		            				((Monster)e.get(i)).setSpeedup(0);
		            			}
		  			
		            		}
		            	}
		            }  
		  		},5000);  
		  		
		  		break;
		}
		
	}
	
	public void skillcd() {
		setskillcd(skillCD*1000);
	}
	
	public void setskillcd(int time){

		Timer timer = new Timer();
		SetSkillUse(false);
		timer.schedule(new TimerTask(){
			
			public void run(){
				SetSkillUse(true);
			}
		},time);
		
	}
	
	public char getType()
	{
		return type;
	}
	
	public int GetID(){
		return player_id;
	}
	
	public void SetBlood(int BloodNew){
		blood = BloodNew;
	}
	
	public int GetBlood(){
		return blood;
	}
	
	public int GetSpeed(){
		return speed;
	}

	public void SetSkillUse(boolean SkillUseNew){
		SkillUse = SkillUseNew;
	}
	
	public boolean GetSkillUse(){ 
		return SkillUse;
	}
	
	public boolean isAlive()
	{
		return alive;
	}
	/*
	public int SetBag(Item getItem){
		for(int i = 0 ; i < BagCapacity ;i++)
		{
			if(bag[i]==null)
			{
				bag[i] = getItem;
				return i;
			}
		}
		return -1;
	}

	//Index range 0~1
	public void DeleteBag(int index){
		bag[index] = null;
	}
	
	public Item GetBag(int index){
		return bag[index];
	}
	*/
	
	
	
	public void setSpeed(int value)
	{
		speed = value;
	}
	
	public void setStatusOn(int index)
	{
		status[index] = 1;
	}
	
	public void setStatusOff(int index)
	{
		status[index] = 0;
	}
	
	public void lostHP(int damage)
	{
		blood -= damage;
	}
	
	public void setInfo(int PlayerBlood ,int PlayerSpeed ,char PlayerType ,int PlayerSkillCD)
	{
		blood = PlayerBlood;			
		orispeed = PlayerSpeed;
		type = PlayerType;
		skillCD = PlayerSkillCD;

	}

	public String getDirection()
	{
		return direction;
	}
	
	public int getConnectionId()
	{
		return connectionId;
	}
}
