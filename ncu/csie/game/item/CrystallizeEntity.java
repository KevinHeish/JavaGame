package ncu.csie.game.item;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Timer;
import java.util.TimerTask;

import ncu.csie.game.worlds.Handler;
import ncu.csie.game.entities.statics.StaticEntity;
import ncu.csie.game.gfx.Animation;
import ncu.csie.game.gfx.Assets;

public class CrystallizeEntity extends StaticEntity{
	private int timer = 0;
	private Timer test = new Timer();
	private int delay = 0;
	
	public CrystallizeEntity(Handler handler, float x, float y, int width, int height , int direction) {
		super(handler, x, y, width, height);
	}

	@Override
	public void tick() {
		
	}
	

}
