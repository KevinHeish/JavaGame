package ncu.csie.game.entities.creatures;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import ncu.csie.game.Handler;
import ncu.csie.game.entities.Entity;
import ncu.csie.game.gfx.Animation;
import ncu.csie.game.gfx.Assets;
import ncu.csie.game.item.Item;
import ncu.csie.game.item.ItemEntity;
import ncu.csie.game.states.State;
import ncu.csie.game.tiles.Tile;

public class Player extends Creature{	  
	private int blood, orispeed, speed, skillCD;
	private char type;
	private boolean SkillUse = true; //Can use skill or not
	private Item[] bag = {null, null};
	private int BagCapacity; //How many things in the bag
	private int status[] = {0,0,0};//mapbuff,itembuff,skillbuff
	//Record last key released
	private String key;
	//Animations
	private Animation animDown, animUp, animLeft, animRight;		
	private Timer timer;
	private boolean onFire = false;
	private int player_id;
	
	public Player(Handler handler, float x, float y,int PlayerID ) {
		super(handler, x, y, Creature.DEFUAL_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);
		
		bounds.x = 15;
		bounds.y = 50;
		bounds.width = 70;
		bounds.height = 50;
		timer = new Timer();
		//Animations
		player_id = PlayerID;
		BagCapacity = 2;
		speed = 0;
		setPlayerid(player_id);
		
		animDown = new Animation(handler, 300, Assets.actor1_down);
		animUp = new Animation(handler, 300, Assets.actor1_up);
		animLeft = new Animation(handler, 300, Assets.actor1_left);
		animRight = new Animation(handler, 300, Assets.actor1_right);
	
		key = "";		
		
	}
	
	@Override
	public void tick() {
		
		//Animations
		animDown.tick();
		animUp.tick();
		animLeft.tick();
		animRight.tick();
		//Movement
		getInput();
		buffcheck();
		move();
		handler.getGameCamera().centerOnEntity(this);
	}
	
	private void getInput(){
		xMove = 0;
		yMove = 0;
		getBuff();
				
		
		if(handler.getKeyManager().up){
			yMove = -1*GetSpeed();
			key = "up";
		}
		else if(handler.getKeyManager().down){
			yMove = GetSpeed();
			key = "down";
		}
		else if(handler.getKeyManager().left){
			xMove = -1*GetSpeed();
			key = "left";
		}
		else if(handler.getKeyManager().right){
			xMove = GetSpeed();
			key = "right";
		}
		
		//System.out.println(GetSpeed());
		//Item can be used simultaneously when moving.
		
		if(handler.getKeyManager().item1){
			Item useCase = GetBag(0);
			if(useCase!=null){
				System.out.println("D Pressed");
				if(useCase.effect()){
					DeleteBag(0);
				}
			}
		}
		
		if(handler.getKeyManager().item2){
			Item useCase = GetBag(1);
			if(useCase!=null){
				System.out.println("F Pressed");
				if(useCase.effect()){
					DeleteBag(1);
				}
			}
		}
		
		if(handler.getKeyManager().skill)
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
			animDown = new Animation(handler, 250, Assets.actor1_down);
			animUp = new Animation(handler, 250, Assets.actor1_up);
			animLeft = new Animation(handler, 250, Assets.actor1_left);
			animRight = new Animation(handler, 250, Assets.actor1_right);
			break;
		case 1:   //Hao
			setInfo(41,9,'f',15);
			animDown = new Animation(handler, 250, Assets.actor2_down);
			animUp = new Animation(handler, 250, Assets.actor2_up);
			animLeft = new Animation(handler, 250, Assets.actor2_left);
			animRight = new Animation(handler, 250, Assets.actor2_right);
			break;
		case 2://Hasiaki
			setInfo(41,8,'g',15);
			animDown = new Animation(handler, 250, Assets.actor3_down);
			animUp = new Animation(handler, 250, Assets.actor3_up);
			animLeft = new Animation(handler, 250, Assets.actor3_left);
			animRight = new Animation(handler, 250, Assets.actor3_right);
			break;
		case 3://Jade
			setInfo(50,8,'f',15);
			animDown = new Animation(handler, 250, Assets.actor4_down);
			animUp = new Animation(handler, 250, Assets.actor4_up);
			animLeft = new Animation(handler, 250, Assets.actor4_left);
			animRight = new Animation(handler, 250, Assets.actor4_right);
			break;
		case 4://Sai
			setInfo(41,9,'g',15);
			animDown = new Animation(handler, 250, Assets.actor5_down);
			animUp = new Animation(handler, 250, Assets.actor5_up);
			animLeft = new Animation(handler, 250, Assets.actor5_left);
			animRight = new Animation(handler, 250, Assets.actor5_right);
			break;
		case 5://Yuki
			setInfo(50,8,'w',15);
			animDown = new Animation(handler, 250, Assets.actor6_down);
			animUp = new Animation(handler, 250, Assets.actor6_up);
			animLeft = new Animation(handler, 250, Assets.actor6_left);
			animRight = new Animation(handler, 250, Assets.actor6_right);
			break;
		default:
			break;
		}
	}
	
	public String getKey()
	{
		return key;
	}
	
	@Override
	public boolean checkEntityCollisions(double xOffset, double yOffset){
		for(Entity e: handler.getWorld().getEntityManager().getEntities()){
			if(e.equals(this)){
				continue;
			}
			if(e.getCollisionBounds(0f, 0f).intersects(getCollisionBounds(xOffset, yOffset))){
				if(e instanceof ItemEntity)
				{
					//System.out.println("Do something");
					return false;
				}
				
				
				else if(e instanceof Item)
				{
					int index = this.SetBag((Item)e);
					if(index!=-1)
						handler.getWorld().getEntityManager().itemRemoved(index);
					return false;
				}
				
				else if(e instanceof Monster)
				{
					ArrayList<Entity> sEntity = handler.getWorld().getEntityManager().getEntities();
					for(int j = 0; j < sEntity.size();j++)
	        		{
						if(e == sEntity.get(j))
						{
							SetBlood(GetBlood()-((Monster)(sEntity.get(j))).GetLoseHp());
							handler.getWorld().monsterReborn((Monster)sEntity.get(j));
							sEntity.remove(j);
							//System.out.println("Disapper");
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

	@Override
	public void render(Graphics g) {
		g.drawImage(getCurrentAnimationFrame(), (int) (x-handler.getGameCamera().getxOffset()), (int) (y-handler.getGameCamera().getyOffset()), width, height, null);
		
	}
	
	private BufferedImage getCurrentAnimationFrame(){
		
		if(key.equals("left")){
			return animLeft.getCurrentFrame();
		}
		else if(key.equals("right")){
			return animRight.getCurrentFrame();
		}
		else if(key.equals("up")){
			return animUp.getCurrentFrame();
		}
		else if(key.equals("down")){
			return animDown.getCurrentFrame();
		}
		else{
			return animDown.getCurrentFrame();
		}
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

		Timer timer = new Timer();
		switch(GetID()) { 
		  	case 1: 
		  		setskillcd(1000);
		  		break;
		  	case 2:
		  		setskillcd(60000);
		  		break;
		  	case 3:
		  		setskillcd(10000);
		  		break;
		  	case 4:
		  		setskillcd(10000);
		  		break;
		  	case 5:
		  		setskillcd(120000);
		  		break;
		  	case 6:
		  		setskillcd(25000);
		  		break;
		  		
		}
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
	
}
