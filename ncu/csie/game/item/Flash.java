package ncu.csie.game.item;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import ncu.csie.game.worlds.Handler;
import ncu.csie.game.entities.Entity;
import ncu.csie.game.entities.creatures.Player;
import ncu.csie.game.gfx.Assets;

public class Flash extends Item{

	public Flash(Handler handler, float x, float y, int width, int height,int id) {
		super(handler, x, y, width, height);
		this.id = id;
		
	}

	@Override
	public boolean effect(int playerId) {
		// 0 up , 1 right ,2 down , 3 left
		int flashDistance = 550;
		String direction = handler.getWorld().getPlayers().get(playerId).getDirection();
		Player character= handler.getWorld().getPlayers().get(playerId);
		
		for(int i = 0 ; i < 4 ; i++)
		{
			if(direction == key[i])
			{
				character.setxMove(diffX[i] * flashDistance);
				character.setyMove(diffY[i] * flashDistance);
				return true;
			}
		}
		return true;
	}
	
}
