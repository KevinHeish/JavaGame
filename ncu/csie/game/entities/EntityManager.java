package ncu.csie.game.entities;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Comparator;

import ncu.csie.game.Handler;
import ncu.csie.game.entities.creatures.Player;
import ncu.csie.game.item.Item;

public class EntityManager {
	
	private Handler handler;
	private Player player;
	
	private ArrayList<Entity> entities;
	private Comparator<Entity> renderSorter = new Comparator<Entity>() {
		@Override
		public int compare(Entity a, Entity b){
			if(a!=null && b!=null){
				if(a.getY()+ a.getHeight() < b.getY()+b.getHeight()){
					return -1;
				}
			}
			return 1;
		}
	};
	
	public EntityManager(Handler handler, Player player){

		this.handler = handler;
		this.player = player;
		entities = new ArrayList<Entity>();
		addEntity(player);
	
	}
	
	public void tick(){
		try{
			for(int i=0; i< entities.size(); ++i){
				Entity e = entities.get(i);
				e.tick();
			}	
		
			entities.sort(renderSorter);
		}
		catch(Exception em)
		{
			System.out.println("Sort");
			System.out.println(em);
		}
	}
	
	public void render(Graphics g){
		try{
			for(Entity e: entities){
				e.render(g);
			}	
		}catch(Exception em)
		{
			System.out.println("Render");
			System.out.println(em);
		}
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

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public ArrayList<Entity> getEntities() {
		return entities;
	}

	public void setEntities(ArrayList<Entity> entities) {
		this.entities = entities;
	}
	
	public void itemRemoved(int index)
	{
		Item result;
		result = player.GetBag(index);
		
		for(int i = 0 ; i < entities.size(); i++)
		{
			if(result == entities.get(i))
			{
				entities.remove(i);
			}
		}
	}
}
