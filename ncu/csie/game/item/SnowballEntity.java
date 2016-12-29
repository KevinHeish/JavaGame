package ncu.csie.game.item;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import ncu.csie.game.worlds.Handler;
import ncu.csie.game.entities.Entity;
import ncu.csie.game.entities.creatures.Monster;
import ncu.csie.game.gfx.Animation;


public class SnowballEntity extends ItemEntity{
	private int moveSpeed;
	public SnowballEntity(Handler handler, float x, float y, int width, int height,int speed) {
		super(handler, x, y, width, height);
		moveSpeed = speed;
	}

	
	public void move(String direction)
	{
		int i;

		for (i = 0; i < 4; i++) {
			if (direction.equals(key[i])) {
				break;
			}
		}
		setX(getX() + diffX[i] * moveSpeed);
		setY(getY() + diffY[i] * moveSpeed);
			
		//ToDo : Collision detection .
	}


	@Override
	public void effectResult(ArrayList<Entity> list , Entity object) {
		Timer timer = new Timer();
		
		for(int i = 0 ; i < list.size(); i++)
		{
			if(list.get(i).equals(object)){
				((Monster)object).setSpeedup(-5);

				timer.schedule(new TimerTask(){  
		            @Override  
		            public void run(){  
		            	((Monster)object).setSpeedup(0);
		            }
		        },2000);
				break;
			}
		}
	}
	
}
