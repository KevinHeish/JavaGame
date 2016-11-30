package ncu.csie.game.item;

import java.awt.Graphics;

import ncu.csie.game.Handler;
import ncu.csie.game.entities.Entity;
import ncu.csie.game.gfx.Assets;

public class FlashAnimation extends Entity{

	public FlashAnimation(Handler handler, float x, float y, int width, int height) {
		super(handler, x, y, width, height);
	}

	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.flashDisplay, (int) (x-handler.getGameCamera().getxOffset()), 
				(int) (y-handler.getGameCamera().getyOffset()), width, height, null);
	}

}
