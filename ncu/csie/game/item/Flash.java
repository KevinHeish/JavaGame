package ncu.csie.game.item;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import ncu.csie.game.Handler;
import ncu.csie.game.entities.Entity;
import ncu.csie.game.entities.creatures.Player;
import ncu.csie.game.gfx.Assets;

public class Flash extends Item{

	public Flash(Handler handler, float x, float y, int width, int height) {
		super(handler, x, y, width, height);
		itemImage = Assets.flash;
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(itemImage, (int) (x-handler.getGameCamera().getxOffset()), 
				(int) (y-handler.getGameCamera().getyOffset()), width, height, null);
	}
	
	
	@Override
	public boolean effect() {
		// 0 up , 1 right ,2 down , 3 left
		int flashDistance = 550;
		String direction = handler.getWorld().getEntityManager().getPlayer().getKey();
		Player character= handler.getWorld().getEntityManager().getPlayer();
		FlashAnimation flash = new FlashAnimation(handler , character.getX() ,
				character.getY() ,character.getWidth() , character.getHeight());
		
		handler.getWorld().getEntityManager().addEntity(flash);
		ArrayList<Entity> list =  handler.getWorld().getEntityManager().getEntities();
		
		Timer timer = new Timer();
		timer.schedule(new TimerTask(){  
            @Override  
            public void run(){  
            	for(int k = 0 ; k < list.size(); k++)
        		{
        			if(list.get(k)==flash){
        				list.remove(k);
        				break;
        			}
        		}
            }
        },50);

		
		for(int i = 0 ; i < 4 ; i++)
		{
			if(direction == key[i])
			{
				character.setxMove(diffX[i] * flashDistance);
				character.setyMove(diffY[i] * flashDistance);
				return true;
			}
		}
		return true;
	}
	
}
