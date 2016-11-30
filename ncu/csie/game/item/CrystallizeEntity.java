package ncu.csie.game.item;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Timer;
import java.util.TimerTask;

import ncu.csie.game.Handler;
import ncu.csie.game.entities.statics.StaticEntity;
import ncu.csie.game.gfx.Animation;
import ncu.csie.game.gfx.Assets;

public class CrystallizeEntity extends StaticEntity{
	private int timer = 0;
	private Animation wall;
	private BufferedImage[] wallDirection;
	private Timer test = new Timer();
	private int delay = 0;
	
	public CrystallizeEntity(Handler handler, float x, float y, int width, int height , int direction) {
		super(handler, x, y, width, height);
		if(direction%2 == 1){
			wall = new Animation(handler , 0 , Assets.crystallizeVertical);
			wallDirection = Assets.crystallizeVertical;
		}
		else{
			wall = new Animation(handler , 0 , Assets.crystallizeHori);
			wallDirection = Assets.crystallizeHori;
		}
	}
	
	public void render(Graphics g){
		if(timer < 18){
			g.drawImage(wallDirection[timer], (int) (x-handler.getGameCamera().getxOffset()), 
					(int) (y-handler.getGameCamera().getyOffset()), width, height, null);
			
		}
	}
	
	public void tick(){
		test.schedule(new TimerTask() {  
            @Override  
            public void run(){  
            	wall.tick();
        		timer++;
            }
        },delay);
		if(timer==6)
			delay = 4500;
		//delay 跟消失時間， 差距太小會造成 NullPointerException
	}

}
