package ncu.csie.game.item;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;
import ncu.csie.game.Handler;
import ncu.csie.game.entities.Entity;
import ncu.csie.game.entities.creatures.Monster;
import ncu.csie.game.gfx.Animation;
import ncu.csie.game.gfx.Assets;

public class UltralightEntity extends ItemEntity{
		private Animation light;
		private BufferedImage[] aniDirection;
		
		public UltralightEntity(Handler handler, float x, float y, int width, int height, int i) {
			super(handler, x, y, width, height);
			timer = 0;
			aniDirection = new BufferedImage[17];

			if(i == 0){
				light = new Animation(handler , 0 , Assets.ultra_ani_up);
				aniDirection = Assets.ultra_ani_up;
			}
			else if(i == 1){
				light = new Animation(handler , 0 , Assets.ultra_ani_right);
				aniDirection = Assets.ultra_ani_right;
			}
			else if(i == 2){
				light = new Animation(handler , 0 , Assets.ultra_ani_down);
				aniDirection = Assets.ultra_ani_down;
				
			}
			else if(i == 3){
				light = new Animation(handler , 0 , Assets.ultra_ani_left);
				aniDirection = Assets.ultra_ani_left;
			}
		}

		@Override
		public void render(Graphics g) {
			if(timer<17){
				g.drawImage(aniDirection[timer], (int) (x-handler.getGameCamera().getxOffset()), 
						(int) (y-handler.getGameCamera().getyOffset()), width, height, null);
			}
		}
		
		@Override
		public void tick() {
			light.tick();
			timer++;
		}

		@Override
		public void effectResult(ArrayList<Entity> list , Entity object) {
			Iterator<Entity> it= list.iterator();
			
			while(it.hasNext())
			{
				if(object==it.next()){
					it.remove();
					handler.getWorld().monsterReborn((Monster)object);
				}
			}
		}

}
