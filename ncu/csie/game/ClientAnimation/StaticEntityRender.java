 package ncu.csie.game.ClientAnimation;

import java.awt.Graphics;

import ncu.csie.game.ClientEnd.GameHandler;
import ncu.csie.game.worlds.Handler;

public class StaticEntityRender extends EntityRender{
	public StaticEntityRender(GameHandler handler, float x, float y, int width, int height){
		super(handler, x, y, width, height);
	}

	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics g) {
	
	}
}
