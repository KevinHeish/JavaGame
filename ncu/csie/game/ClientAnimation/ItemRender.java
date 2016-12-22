package ncu.csie.game.ClientAnimation;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import ncu.csie.game.ClientEnd.GameHandler;
import ncu.csie.game.gfx.Assets;

public class ItemRender extends EntityRender{
	private BufferedImage itemImage;
	
	public ItemRender(GameHandler handler, float x, float y, int width, int height ,int itemIndex) {
		super(handler, x, y, width, height);
		setItemImage(itemIndex);
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(itemImage, (int) (x-handler.getGameCamera().getxOffset()), 
				(int) (y-handler.getGameCamera().getyOffset()), width, height, null);
	}
	
	public BufferedImage getItemImage()
	{
		return itemImage;
	}
	
	private void setItemImage(int index)
	{
		switch(index)
		{
			case 0:{
				itemImage = Assets.crystallize;
				break;
			}
			case 1:{
				itemImage = Assets.flash;
				break;
			}
			case 2:{
				itemImage = Assets.snowball;
				break;
			}
			case 3:{
				itemImage = Assets.ghost_walk;
				break;
			}
			case 4:{
				itemImage = Assets.ultralight;
				break;
			}
		}
	}
}
