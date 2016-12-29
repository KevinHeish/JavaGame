package ncu.csie.game.ClientAnimation;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import ncu.csie.game.ClientEnd.GameHandler;
import ncu.csie.game.entities.creatures.Creature;
import ncu.csie.game.gfx.Animation;
import ncu.csie.game.gfx.Assets;

public class VirtualCharRender extends CreatureRender{
		private Animation animDown, animUp, animLeft, animRight;
		private int charIndex;
		private String direction;
		
		public VirtualCharRender(GameHandler handler, float x, float y) {
			super(handler, x , y ,Creature.DEFUAL_CREATURE_WIDTH , Creature.DEFAULT_CREATURE_HEIGHT);
			charIndex = -121;		
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
					break;
				case 1:   //Hao
					animDown = new Animation(handler, 250, Assets.actor2_down, 2);
					animUp = new Animation(handler, 250, Assets.actor2_up, 2);
					animLeft = new Animation(handler, 250, Assets.actor2_left, 2);
					animRight = new Animation(handler, 250, Assets.actor2_right, 2);
					break;
				case 2://Hasiaki
					animDown = new Animation(handler, 250, Assets.actor3_down, 2);
					animUp = new Animation(handler, 250, Assets.actor3_up, 2);
					animLeft = new Animation(handler, 250, Assets.actor3_left, 2);
					animRight = new Animation(handler, 250, Assets.actor3_right, 2);
					break;
				case 3://Jade
					animDown = new Animation(handler, 250, Assets.actor4_down, 2);
					animUp = new Animation(handler, 250, Assets.actor4_up, 2);
					animLeft = new Animation(handler, 250, Assets.actor4_left, 2);
					animRight = new Animation(handler, 250, Assets.actor4_right, 2);
					break;
				case 4://Sai
					animDown = new Animation(handler, 250, Assets.actor5_down, 2);
					animUp = new Animation(handler, 250, Assets.actor5_up, 2);
					animLeft = new Animation(handler, 250, Assets.actor5_left, 2);
					animRight = new Animation(handler, 250, Assets.actor5_right, 2);
					break;
				case 5://Yuki
					animDown = new Animation(handler, 250, Assets.actor6_down, 2);
					animUp = new Animation(handler, 250, Assets.actor6_up, 2);
					animLeft = new Animation(handler, 250, Assets.actor6_left, 2);
					animRight = new Animation(handler, 250, Assets.actor6_right, 2);
					break;
				case -1://ghost
					animDown = new Animation(handler, 250, Assets.ghost_right, 2);
					animUp = new Animation(handler, 250, Assets.ghost_right, 2);
					animLeft = new Animation(handler, 250, Assets.ghost_left, 2);
					animRight = new Animation(handler, 250, Assets.ghost_right, 2);
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

		@Override
		public void tick() {
			animDown.tick();
			animUp.tick();
			animLeft.tick();
			animRight.tick();
		}
		
		public void update(int x , int y , String dir)
		{
			updatePosition(x, y);
			direction = dir;
		}
		
		public int getIndex()
		{
			return charIndex;
		}
		
}
