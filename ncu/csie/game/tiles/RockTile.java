package ncu.csie.game.tiles;

import ncu.csie.game.gfx.Assets;

public class RockTile extends Tile{

	public RockTile(int id) {
		super(Assets.stone, id);		
	}
	
	@Override
	public boolean isSolid(){
		return true;
	}
}
