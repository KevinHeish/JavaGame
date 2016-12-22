package ncu.csie.game.item;

import java.awt.Graphics;
import java.util.Timer;
import java.util.TimerTask;

import ncu.csie.game.worlds.Handler;
import ncu.csie.game.entities.creatures.Player;
import ncu.csie.game.gfx.Assets;

public class Ghostwalk extends Item{

	public Ghostwalk(Handler handler, float x, float y, int width, int height,int id) {
		super(handler, x, y, width, height);
		this.id = id;
	}

	
	@Override
	public boolean effect(int playerId) {
		Player character= handler.getWorld().getPlayers().get(playerId);
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
