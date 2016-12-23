package ncu.csie.game.gfx;

import java.awt.image.BufferedImage;
import java.util.Timer;
import java.util.TimerTask;

import ncu.csie.game.ClientEnd.GameHandler;

public class Animation {
	
	private GameHandler handler;
	private int speed, index,type;
	private long lastTime, timer;
	private BufferedImage[] frames;
	
	public Animation(GameHandler handler, int speed, BufferedImage[] frames, int type){
		this.handler = handler;
		this.speed = speed;
		this.frames = frames;
		this.type = type;
		index = 0;
		timer = 0;
		lastTime = System.currentTimeMillis();
	}
	
	
	public void tick(){
		timer += System.currentTimeMillis() - lastTime;
		lastTime = System.currentTimeMillis();
		
		if(timer > speed){
			if(type == 1 || (type == 2 && (handler.getKeyManager().up || handler.getKeyManager().down || handler.getKeyManager().left || handler.getKeyManager().right)))
			{
				index++;
				timer = 0;
				if(index >= frames.length){
					index = 0;
				}
			}
		}
	}
	
	public BufferedImage getCurrentFrame(){
		return frames[index];
	}
	
}
