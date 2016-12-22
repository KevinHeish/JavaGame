package ncu.csie.game.gfx;

import java.awt.image.BufferedImage;
import java.util.Timer;
import java.util.TimerTask;

import ncu.csie.game.ClientEnd.GameHandler;

public class Animation {
	
	private GameHandler handler;
	private int speed, index;
	private long lastTime, timer;
	private BufferedImage[] frames;
	
	public Animation(GameHandler handler, int speed, BufferedImage[] frames){
		this.handler = handler;
		this.speed = speed;
		this.frames = frames;
		index = 0;
		timer = 0;
		lastTime = System.currentTimeMillis();
	}
	
	
	public void tick(){
		timer += System.currentTimeMillis() - lastTime;
		lastTime = System.currentTimeMillis();
		
		if(timer > speed && (handler.getKeyManager().up || handler.getKeyManager().down || handler.getKeyManager().left || handler.getKeyManager().right)){
			index++;
			timer = 0;
			if(index >= frames.length){
				index = 0;
			}
		}
	}
	
	public BufferedImage getCurrentFrame(){
		return frames[index];
	}
	
}
