package ncu.csie.game.ClientAnimation;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import ncu.csie.game.ClientEnd.GameHandler;
import ncu.csie.game.ClientEnd.KeyManager;
import ncu.csie.game.TCP.TCPClient;
import ncu.csie.game.entities.creatures.Creature;
import ncu.csie.game.gfx.Animation;
import ncu.csie.game.gfx.Assets;

public class PlayerRender extends CreatureRender{
	private Animation animDown, animUp, animLeft, animRight;
	private int charIndex , hp;
	private String direction;
	private boolean skill;
	private int[] bag = {-1,-1};
	
	public PlayerRender(GameHandler handler, float x, float y) {
		super(handler, x , y ,Creature.DEFUAL_CREATURE_WIDTH , Creature.DEFAULT_CREATURE_HEIGHT);
		charIndex = -1;		
		direction = "down";
	}
	
	@Override
	public void render(Graphics g) {
		g.drawImage(getCurrentAnimationFrame(), (int) (x-handler.getGameCamera().getxOffset()),
				(int) (y-handler.getGameCamera().getyOffset()), width, height, null);
	}
		
	public void setcharIndex(int player)
	{
		charIndex = player;
		switch(charIndex)
		{
		case 0:   //Asuna
			animDown = new Animation(handler, 250, Assets.actor1_down, 2);
			animUp = new Animation(handler, 250, Assets.actor1_up, 2);
			animLeft = new Animation(handler, 250, Assets.actor1_left, 2);
			animRight = new Animation(handler, 250, Assets.actor1_right, 2);
			hp = 80;
			break;
		case 1:   //Hao
			animDown = new Animation(handler, 250, Assets.actor2_down, 2);
			animUp = new Animation(handler, 250, Assets.actor2_up, 2);
			animLeft = new Animation(handler, 250, Assets.actor2_left, 2);
			animRight = new Animation(handler, 250, Assets.actor2_right, 2);
			hp = 41;
			break;
		case 2://Hasiaki
			animDown = new Animation(handler, 250, Assets.actor3_down, 2);
			animUp = new Animation(handler, 250, Assets.actor3_up, 2);
			animLeft = new Animation(handler, 250, Assets.actor3_left, 2);
			animRight = new Animation(handler, 250, Assets.actor3_right, 2);
			hp = 41;
			break;
		case 3://Jade
			animDown = new Animation(handler, 250, Assets.actor4_down, 2);
			animUp = new Animation(handler, 250, Assets.actor4_up, 2);
			animLeft = new Animation(handler, 250, Assets.actor4_left, 2);
			animRight = new Animation(handler, 250, Assets.actor4_right, 2);
			hp = 50;
			break;
		case 4://Sai
			animDown = new Animation(handler, 250, Assets.actor5_down, 2);
			animUp = new Animation(handler, 250, Assets.actor5_up, 2);
			animLeft = new Animation(handler, 250, Assets.actor5_left, 2);
			animRight = new Animation(handler, 250, Assets.actor5_right, 2);
			hp = 41;
			break;
		case 5://Yuki
			animDown = new Animation(handler, 250, Assets.actor6_down, 2);
			animUp = new Animation(handler, 250, Assets.actor6_up, 2);
			animLeft = new Animation(handler, 250, Assets.actor6_left, 2);
			animRight = new Animation(handler, 250, Assets.actor6_right, 2);
			hp = 50;
			break;
		case -1://ghost
			animDown = new Animation(handler, 250, Assets.ghost_right, 2);
			animUp = new Animation(handler, 250, Assets.ghost_right, 2);
			animLeft = new Animation(handler, 250, Assets.ghost_left, 2);
			animRight = new Animation(handler, 250, Assets.ghost_right, 2);
			hp = 0;
			break;
		default:
			break;
		}
	}
	
	public String getdirection()
	{
		return direction;
	}
	
	private BufferedImage getCurrentAnimationFrame(){
		
		if(direction.equals("left")){
			return animLeft.getCurrentFrame();
		}
		else if(direction.equals("right")){
			return animRight.getCurrentFrame();
		}
		else if(direction.equals("up")){
			return animUp.getCurrentFrame();
		}
		else if(direction.equals("down")){
			return animDown.getCurrentFrame();
		}
		else{
			return animDown.getCurrentFrame();
		}
	}
	
	private void getInput(){
		if(handler.getKeyManager().up){
			TCPClient.send("up");
		}
		else if(handler.getKeyManager().down){
			TCPClient.send("down");
		}
		else if(handler.getKeyManager().left){
			TCPClient.send("left");
		}
		else if(handler.getKeyManager().right){
			TCPClient.send("right");
		}
		
		//System.out.println(GetSpeed());
		//Item can be used simultaneously when moving.
		
		if(handler.getKeyManager().item1){
			TCPClient.send("useItem1");
		}
		if(handler.getKeyManager().item2){
			TCPClient.send("useItem2");
		}
		if(handler.getKeyManager().skill){
			TCPClient.send("useSkill");	
		}
	}

	@Override
	public void tick() {
		animDown.tick();
		animUp.tick();
		animLeft.tick();
		animRight.tick();
		getInput();
		handler.getGameCamera().centerOnEntity(this);
	}
	
	public void update(int x , int y, int hp , boolean skillUseable ,String direction,int item1, int item2){
		this.hp = hp;
		updatePosition(x , y);
		skill = skillUseable;
		this.direction = direction;
		bag[0] = item1;
		bag[1] = item2;
	}
	
	public int getHp()
	{
		return hp;
	}
	
	public int[] getBag()
	{
		return bag;
	}
}
