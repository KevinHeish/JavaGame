package ncu.csie.game.entities;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Comparator;

import ncu.csie.game.entities.creatures.Player;
import ncu.csie.game.worlds.Handler;

public class EntityManager {
	
	private Handler handler;
	private ArrayList<Entity> entities;
	
	
	public EntityManager(Handler handler, ArrayList<Player> player){
		this.handler = handler;
		entities = new ArrayList<Entity>();
		
		for(int i = 0; i< player.size(); i++)
			addEntity(player.get(i));
	
	}
	public void tick()
	{
		for(int i = 0; i< entities.size() ; i++)
			entities.get(i).tick();
	}
	
	
	public void addEntity(Entity e){
		entities.add(e);
	}
	
	//GETTERS SETTERS
	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public ArrayList<Entity> getEntities() {
		return entities;
	}

	public void setEntities(ArrayList<Entity> entities) {
		this.entities = entities;
	}
	
	
	
}
