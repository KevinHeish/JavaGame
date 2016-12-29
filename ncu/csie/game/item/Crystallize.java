package ncu.csie.game.item;


import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import ncu.csie.game.worlds.Handler;
import ncu.csie.game.entities.Entity;
import ncu.csie.game.entities.creatures.Player;

public class Crystallize extends Item{
	
	public Crystallize(Handler handler, float x, float y, int width, int height, int id) {
		super(handler, x, y, width, height);
		this.id = id;
	}

	@Override
	public boolean effect(int playerId) {
		int i , boundX , boundY ;
		int[] offY = {-150,-250,150,-250};
		int[] offX = {-250,0,-250,-150};
		String direction = handler.getWorld().getPlayers().get(playerId).getDirection();
		Player character= handler.getWorld().getPlayers().get(playerId);
		
		for (i = 0; i < 4; i++) {
			if (direction.equals(key[i])){
				break;
			}
		}
		
		if(i%2==0){
			boundX = 500;
			boundY = 250;
		}
		else{
			boundX = 150;
			boundY = 500;
		}

		CrystallizeEntity crystallize = new CrystallizeEntity(handler , character.getX()+diffX[i]*150 +offX[i],
				character.getY() + diffY[i]*150+offY[i] ,  boundX , boundY , i);
		
		handler.getWorld().getEntityManager().addEntity(crystallize);
		ArrayList<Entity> sEntity = handler.getWorld().getEntityManager().getEntities();
		
		//handler.getWorld().getItemEffect().add(crystallize);
		//ArrayList<Entity> itemEffect = handler.getWorld().getItemEffect();
		
		
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {  
            @Override  
            public void run(){  
            	for(int j = 0; j < sEntity.size();j++)
        		{
        			if(sEntity.get(j)==crystallize){
        				sEntity.remove(j);
        				break;
        			}
        		}
            	/*
            	for(int i = 0; i < itemEffect.size() ;i++)
            	{
            		if(itemEffect.get(i) == crystallize){
            			itemEffect.remove(i);
            			break;
            		}
            	}*/
            }
        },5000);
		return true;
	}
	
	
}
