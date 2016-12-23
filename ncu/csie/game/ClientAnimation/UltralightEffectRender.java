package ncu.csie.game.ClientAnimation;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import ncu.csie.game.ClientEnd.GameHandler;
import ncu.csie.game.gfx.Animation;
import ncu.csie.game.gfx.Assets;


public class UltralightEffectRender extends ItemEffectRender{
	private BufferedImage[] aniDirection;
	
	public UltralightEffectRender(GameHandler handler, float x, float y, int width, int height, int i) {
		super(handler, x, y, width, height);
		timer = 0;
		aniDirection = new BufferedImage[17];

		if(i == 0){
			aniDirection = Assets.ultra_ani_up;
		}
		else if(i == 1){
			aniDirection = Assets.ultra_ani_right;
		}
		else if(i == 2){
			aniDirection = Assets.ultra_ani_down;
			
		}
		else if(i == 3){
			aniDirection = Assets.ultra_ani_left;
		}
	}

	@Override
	public void render(Graphics g) {
		if(timer<17){
			g.drawImage(aniDirection[timer], (int) (x-handler.getGameCamera().getxOffset()), 
					(int) (y-handler.getGameCamera().getyOffset()), width, height, null);
		}
	}
	
	@Override
	public void tick() {
		timer++;
	}

}
