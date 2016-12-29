
package ncu.csie.game.item;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import ncu.csie.game.worlds.Handler;
import ncu.csie.game.entities.Entity;
import ncu.csie.game.entities.creatures.Monster;
import ncu.csie.game.entities.creatures.Player;
import ncu.csie.game.gfx.Assets;

public class Ultralight extends Item{
		
	public Ultralight(Handler handler, float x, float y, int width, int height, int id) {
		super(handler, x, y, width, height);
		this.id = id;
	}

	
	@Override
	public void tick() {
	}
	
	@Override
	public boolean effect(int playerId) {
		int i , boundX , boundY , offX = 0, offY = 0;
		String direction = handler.getWorld().getPlayers().get(playerId).getDirection();
		Player character= handler.getWorld().getPlayers().get(playerId);
		
		
		
		for (i = 0; i < 4; i++) {
			if (direction.equals(key[i])){
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