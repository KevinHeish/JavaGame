package ncu.csie.game.entities.statics;

import java.awt.Color;
import java.awt.Graphics;

import ncu.csie.game.gfx.Assets;
import ncu.csie.game.tiles.Tile;
import ncu.csie.game.worlds.Handler;

public class Tree extends StaticEntity{

	public Tree(Handler handler, float x, float y) {
		super(handler, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT*2);		
		bounds.setBounds(0, height/2, width, height/2);
	}

	@Override
	public void tick() {
		
		
	}

	
}
