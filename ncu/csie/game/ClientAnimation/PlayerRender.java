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
	private int playerId , hp;
	private String direction;
	private boolean skill;
	
	public PlayerRender(GameHandler handler, float x, float y) {
		super(handler, x , y ,Creature.DEFUAL_CREATURE_WIDTH , Creature.DEFAULT_CREATURE_HEIGHT);
		animDown = new Animation(handler, 300, Assets.actor1_down);
		animUp = new Animation(handler, 300, Assets.actor1_up);
		animLeft = new Animation(handler, 300, Assets.actor1_left);
		animRight = new Animation(handler, 300, Assets.actor1_right);
		this.playerId = 0;
		direction = "down";
	}
	
	
	@Override
	public void render(Graphics g) {
		g.drawImage(getCurrentAnimationFrame(), (int) (x-handler.getGameCamera().getxOffset()),
				(int) (y-handler.getGameCamera().getyOffset()), width, height, null);
	}
	
	
	
	public void setPlayerid(int player)
	{
		playerId = player;
		switch(playerId)
		{
		case 0:   //Asuna
			animDown = new Animation(handler, 250, Assets.actor1_down);
			animUp = new Animation(handler, 250, Assets.actor1_up);
			animLeft = new Animation(handler, 250, Assets.actor1_left);
			animRight = new Animation(handler, 250, Assets.actor1_right);
			break;
		case 1:   //Hao
			animDown = new Animation(handler, 250, Assets.actor2_down);
			animUp = new Animation(handler, 250, Assets.actor2_up);
			animLeft = new Animation(handler, 250, Assets.actor2_left);
			animRight = new Animation(handler, 250, Assets.actor2_right);
			break;
		case 2://Hasiaki
			animDown = new Animation(handler, 250, Assets.actor3_down);
			animUp = new Animation(handler, 250, Assets.actor3_up);
			animLeft = new Animation(handler, 250, Assets.actor3_left);
			animRight = new Animation(handler, 250, Assets.actor3_right);
			break;
		case 3://Jade
			animDown = new Animation(handler, 250, Assets.actor4_down);
			animUp = new Animation(handler, 250, Assets.actor4_up);
			animLeft = new Animation(handler, 250, Assets.actor4_left);
			animRight = new Animation(handler, 250, Assets.actor4_right);
			break;
		case 4://Sai
			animDown = new Animation(handler, 250, Assets.actor5_down);
			animUp = new Animation(handler, 250, Assets.actor5_up);
			animLeft = new Animation(handler, 250, Assets.actor5_left);
			animRight = new Animation(handler, 250, Assets.actor5_right);
			break;
		case 5://Yuki
			animDown = new Animation(handler, 250, Assets.actor6_down);
			animUp = new Animation(handler, 250, Assets.actor6_up);
			animLeft = new Animation(handler, 250, Assets.actor6_left);
			animRight = new Animation(handler, 250, Assets.actor6_right);
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
			TCPClient.inputMoves("up");
		}
		else if(handler.getKeyManager().down){
			TCPClient.inputMoves("down");
		}
		else if(handler.getKeyManager().left){
			TCPClient.inputMoves("left");
		}
		else if(handler.getKeyManager().right){
			TCPClient.inputMoves("right");
		}
		
		//System.out.println(GetSpeed());
		//Item can be used simultaneously when moving.
		
		if(handler.getKeyManager().item1){
			TCPClient.inputMoves("useItem1");
		}
		if(handler.getKeyManager().item2){
			TCPClient.inputMoves("useItem2");
		}
		if(handler.getKeyManager().skill){
			TCPClient.inputMoves("useSkill");	
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
	
	public void update(int x , int y, int hp , boolean skillUseable ,String direction){
				
		updatePosition(x , y);
		skill = skillUseable;
		this.direction = direction;
	}
	
}
