package ncu.csie.game.ClientAnimation;

import java.awt.Graphics;

import ncu.csie.game.ClientEnd.GameHandler;
import ncu.csie.game.gfx.Animation;
import ncu.csie.game.gfx.Assets;

public class SnowballEffectRender extends ItemEffectRender {
	private int moveSpeed;
	private Animation itemAnimation;
	
	public SnowballEffectRender(GameHandler handler, float x, float y, int width, int height,int speed) {
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
	
	

}
