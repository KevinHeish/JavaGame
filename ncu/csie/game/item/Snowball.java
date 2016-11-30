package ncu.csie.game.item;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import ncu.csie.game.Handler;
import ncu.csie.game.entities.Entity;
import ncu.csie.game.entities.creatures.Player;
import ncu.csie.game.gfx.Assets;

public class Snowball extends Item{
		
	public Snowball(Handler handler, float x, float y, int width, int height) {
		super(handler, x, y, width, height);
		itemImage = Assets.snowball;
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(itemImage, (int) (x-handler.getGameCamera().getxOffset()), 
				(int) (y-handler.getGameCamera().getyOffset()), width, height, null);
	}
	
	@Override
	public boolean effect() {
		int moveSpeed = 12 ,i;
		String direction = handler.getWorld().getEntityManager().getPlayer().getKey();
		Player character= handler.getWorld().getEntityManager().getPlayer();
		
		
		for (i = 0; i < 4; i++) {
			if (key[i] == direction) {
				break;
			}
		}
		
		SnowballEntity thrownBall = new SnowballEntity(handler , character.getX()+diffX[i]*150,
				character.getY()+diffY[i]*150 , 40 , 40 ,moveSpeed);
		
		handler.getWorld().getEntityManager().addEntity(thrownBall);
		
		
		ArrayList<Entity> sEntity = handler.getWorld().getEntityManager().getEntities();
		
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {  
            @Override  
            public void run(){  
            	for(int i = 0; i < sEntity.size();i++)
        		{
        			if(sEntity.get(i)==thrownBall){
        				sEntity.remove(i);
        				timer.cancel();
        				break;
        			}
        		}
            }
        },2000);
		
		timer.schedule(new TimerTask() {  
            @Override  
            public void run(){
            	thrownBall.move(direction);
            }
        },0,16);
		
		
		return true;
	}
	
}
