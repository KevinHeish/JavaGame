package ncu.csie.game.item;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import ncu.csie.game.Handler;
import ncu.csie.game.entities.Entity;
import ncu.csie.game.entities.creatures.Player;
import ncu.csie.game.gfx.Assets;

public class Crystallize extends Item{
	private int timer;
	
	public Crystallize(Handler handler, float x, float y, int width, int height) {
		super(handler, x, y, width, height);
		timer = 0;
		itemImage = Assets.crystallize;
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(itemImage, (int) (x-handler.getGameCamera().getxOffset()), 
				(int) (y-handler.getGameCamera().getyOffset()), width, height, null);
	}

	@Override
	public boolean effect() {
		int i , boundX , boundY ;
		int[] offY = {-150,-250,150,-250};
		int[] offX = {-250,0,-250,-150};
		String direction = handler.getWorld().getEntityManager().getPlayer().getKey();
		Player character= handler.getWorld().getEntityManager().getPlayer();
		
		for (i = 0; i < 4; i++) {
			if (key[i] == direction){

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

		CrystallizeEntity test = new CrystallizeEntity(handler , character.getX()+diffX[i]*150 +offX[i],
				character.getY() + diffY[i]*150+offY[i] ,  boundX , boundY , i);
		
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
        				System.out.println("wall Destroyed");
        				break;
        			}
        		}
            }
        },5000);
		return true;
	}
	
	
}
