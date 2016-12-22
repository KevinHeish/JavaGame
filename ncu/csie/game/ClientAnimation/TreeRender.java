package ncu.csie.game.ClientAnimation;

import java.awt.Graphics;

import ncu.csie.game.ClientEnd.GameHandler;
import ncu.csie.game.gfx.Assets;
import ncu.csie.game.tiles.Tile;

public class TreeRender extends StaticEntityRender{

		public TreeRender(GameHandler handler, float x, float y) {
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
		}

}
