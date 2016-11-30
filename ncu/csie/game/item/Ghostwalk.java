package ncu.csie.game.item;

import java.awt.Graphics;
import java.util.Timer;
import java.util.TimerTask;

import ncu.csie.game.Handler;
import ncu.csie.game.entities.creatures.Player;
import ncu.csie.game.gfx.Assets;

public class Ghostwalk extends Item{

	public Ghostwalk(Handler handler, float x, float y, int width, int height) {
		super(handler, x, y, width, height);
		itemImage = Assets.ghost_walk;
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(itemImage, (int) (x-handler.getGameCamera().getxOffset()), 
				(int) (y-handler.getGameCamera().getyOffset()), width, height, null);
	}
	
	@Override
	public boolean effect() {
		Player character= handler.getWorld().getEntityManager().getPlayer();
		int value = 5;

		character.setStatusOn(1);
		System.out.println("Speed UP GOGO.");
		
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {  
            @Override  
            public void run(){  
            	character.setStatusOff(1);
            	System.out.println("Speed UP Over");
            }
        },2000);
		
		return true;
	}
	
}
