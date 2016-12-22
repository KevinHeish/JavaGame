package ncu.csie.game.ClientAnimation;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Timer;
import java.util.TimerTask;

import ncu.csie.game.ClientEnd.GameHandler;
import ncu.csie.game.gfx.Animation;
import ncu.csie.game.gfx.Assets;

public class ItemEffectRender extends EntityRender{
	protected int timer;
	public ItemEffectRender(GameHandler handler, float x, float y, int width, int height) {
		super(handler, x, y, width, height);
		
	}

	@Override
	public void render(Graphics g) {
		
	}
	
}
