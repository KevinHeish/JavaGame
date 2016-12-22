package ncu.csie.game.ClientAnimation;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import ncu.csie.game.ClientEnd.GameHandler;
import ncu.csie.game.gfx.Animation;
import ncu.csie.game.gfx.Assets;

public class MonsterRender extends CreatureRender{
	private Animation animLeft, animRight;//animDown, animUp, 
	private int direction;
	
	public MonsterRender(GameHandler handler, int x , int y, int id)
	{
		super(handler , x ,y ,CreatureRender.DEFUAL_CREATURE_WIDTH , CreatureRender.DEFAULT_CREATURE_HEIGHT);
		
		if(id<15)//WalkingGrass
		{
			animLeft = new Animation(handler, 300, Assets.WalkingGrass_left);
			animRight = new Animation(handler, 300, Assets.WalkingGrass_right);
		}
		else if(id>=15 && id<20)//FireDragon
		{
			animLeft = new Animation(handler, 300, Assets.FireDragon_left);
			animRight = new Animation(handler, 300, Assets.FireDragon_right);
		}
		else if(id>=20 && id<25)//Piplup
		{
			animLeft = new Animation(handler, 300, Assets.Piplup_left);
			animRight = new Animation(handler, 300, Assets.Piplup_right);
		}
		else//LightingBird
		{
			animLeft = new Animation(handler, 300, Assets.LightningBird_left);
			animRight = new Animation(handler, 300, Assets.LightningBird_right);
		}
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(getCurrentAnimationFrame(), (int) (x-handler.getGameCamera().getxOffset()), (int) (y-handler.getGameCamera().getyOffset()), width, height, null);
	}

	@Override
	public void tick() {
		animLeft.tick();
		animRight.tick();
	}
	
	
	private BufferedImage getCurrentAnimationFrame(){
		
		if(direction == 3){
			return animLeft.getCurrentFrame();
		}
		else if(direction == 1){
			return animRight.getCurrentFrame();
		}
		else if(direction == 0){
			return animRight.getCurrentFrame();
		}
		else if(direction == 2){
			return animRight.getCurrentFrame();
		}
		else{
			return animRight.getCurrentFrame();
		}
	}
}
