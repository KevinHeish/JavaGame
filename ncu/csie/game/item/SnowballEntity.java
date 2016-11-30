package ncu.csie.game.item;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import ncu.csie.game.Handler;
import ncu.csie.game.entities.Entity;
import ncu.csie.game.entities.creatures.Monster;
import ncu.csie.game.gfx.Animation;
import ncu.csie.game.gfx.Assets;

public class SnowballEntity extends ItemEntity{
	private int moveSpeed;
	private Animation itemAnimation;
	public SnowballEntity(Handler handler, float x, float y, int width, int height,int speed) {
		super(handler, x, y, width, height);
		timer = 0;
		itemAnimation = new Animation(handler , speed , Assets.rollingBall);
		moveSpeed = speed;
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.snowballEntity, (int) (x-handler.getGameCamera().getxOffset()), 
				(int) (y-handler.getGameCamera().getyOffset()), width, height, null);
	}
	
	@Override
	public void tick() {
		itemAnimation.tick();
		timer++;
	}
	
	public void move(String direction)
	{
		int i;

		for (i = 0; i < 4; i++) {
			if (key[i] == direction) {
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
