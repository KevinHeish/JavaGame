
package ncu.csie.game.item;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import ncu.csie.game.Handler;
import ncu.csie.game.entities.Entity;
import ncu.csie.game.entities.creatures.Monster;
import ncu.csie.game.entities.creatures.Player;
import ncu.csie.game.gfx.Assets;

public class Ultralight extends Item{
	private int timer = 0;
	
	public Ultralight(Handler handler, float x, float y, int width, int height) {
		super(handler, x, y, width, height);
		timer = 0;
		itemImage = Assets.ultralight;
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(itemImage, (int) (x-handler.getGameCamera().getxOffset()), 
				(int) (y-handler.getGameCamera().getyOffset()), width, height, null);
	}
	
	@Override
	public void tick() {
	}
	
	@Override
	public boolean effect() {
		int i , boundX , boundY , offX = 0, offY = 0;
		String direction = handler.getWorld().getEntityManager().getPlayer().getKey();
		Player character= handler.getWorld().getEntityManager().getPlayer();
		
		for (i = 0; i < 4; i++) {
			if (key[i] == direction){
				if(i==0)
					offY = 700;
				else if(i==3)
					offX = 700;
				break;
			}
		}
		
		if(i%2==0){
			boundX = 100;
			boundY = 800;
		}
		else{
			boundX = 800;
			boundY = 100;
		}

		UltralightEntity test = new UltralightEntity(handler , character.getX()+diffX[i]*150 - offX,
				character.getY() + diffY[i]*150 - offY,  boundX, boundY , i);

		
		handler.getWorld().getEntityManager().addEntity(test);
		ArrayList<Entity> sEntity = handler.getWorld().getEntityManager().getEntities();
		
		
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {  
            @Override  
            public void run(){  
            	for(int j = 0; j < sEntity.size();j++)
        		{
        			if(sEntity.get(j)==test){
        				sEntity.remove(j);
        				break;
        			}
        		}
            }
        },500);
		
		return true;
	}
	
}