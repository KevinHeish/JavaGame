package ncu.csie.game.entities.statics;

import java.awt.Color;
import java.awt.Graphics;

import ncu.csie.game.Handler;
import ncu.csie.game.gfx.Assets;
import ncu.csie.game.tiles.Tile;

public class Tree extends StaticEntity{

	public Tree(Handler handler, float x, float y) {
		super(handler, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT*2);		
		bounds.setBounds(0, height/2, width, height/2);
	}

	@Override
	public void tick() {
		
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.tree, (int) (x-handler.getGameCamera().getxOffset()), 
				(int) (y-handler.getGameCamera().getyOffset()), width, height, null);
		
		
		//g.setColor(Color.red);
		//g.fillRect((int)(x+bounds.x-handler.getGameCamera().getxOffset())
		//		, (int)(y+bounds.y-handler.getGameCamera().getyOffset()), bounds.width, bounds.height);
	}
	
	
	
}
