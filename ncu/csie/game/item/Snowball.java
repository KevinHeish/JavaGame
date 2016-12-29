package ncu.csie.game.item;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import ncu.csie.game.worlds.Handler;
import ncu.csie.game.entities.Entity;
import ncu.csie.game.entities.creatures.Player;
import ncu.csie.game.gfx.Assets;

public class Snowball extends Item{
		
	public Snowball(Handler handler, float x, float y, int width, int height, int id) {
		super(handler, x, y, width, height);
		this.id = id;
	}


	
	@Override
	public boolean effect(int playerId) {
		int moveSpeed = 12 ,i;
		String direction = handler.getWorld().getPlayers().get(playerId).getDirection();
		Player character= handler.getWorld().getPlayers().get(playerId);
		
		
		for (i = 0; i < 4; i++) {
			if (direction.equals(key[i])) {
				break;
			}
		}
		
		SnowballEntity thrownBall = new SnowballEntity(handler , character.getX()+diffX[i]*150,
				character.getY()+diffY[i]*150 , 40 , 40 ,moveSpeed);
		
		handler.getWorld().getEntityManager().addEntity(thrownBall);
		System.out.println("Throw PoroKing.");
		
		
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
        				System.out.println("Disapper");
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
