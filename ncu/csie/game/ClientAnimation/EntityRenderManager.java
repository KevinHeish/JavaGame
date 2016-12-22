package ncu.csie.game.ClientAnimation;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Comparator;

import ncu.csie.game.ClientEnd.GameHandler;
import ncu.csie.game.entities.Entity;
import ncu.csie.game.entities.creatures.Player;
import ncu.csie.game.worlds.Handler;

public class EntityRenderManager {
	private GameHandler handler;
	//private PlayerRender[] players;
	private ArrayList<EntityRender> entities;
	
	public EntityRenderManager(GameHandler handler)
	{
		this.handler = handler;
		entities = new ArrayList<EntityRender>();
	}
	
	
	private Comparator<EntityRender> renderSorter = new Comparator<EntityRender>() {
		@Override
		public int compare(EntityRender a, EntityRender b){
			if(a!=null && b!=null){
				if(a.getY()+ a.getHeight() < b.getY()+b.getHeight()){
					return -1;
				}
			}
			return 1;
		}
	};
	
	public void add(EntityRender render)
	{
		entities.add(render);
	}

	public void tick(){
		try{
			for(int i=0; i< entities.size(); ++i){
				EntityRender e = entities.get(i);
				e.tick();
			}
			entities.sort(renderSorter);
		}
		catch(Exception em)
		{
			System.out.println(em);
		}
	}
	
	public void render(Graphics g){
		try{
			for(EntityRender e: entities){
				e.render(g);
			}	
		}catch(Exception em)
		{
			System.out.println(em);
		}
	}
	
	public ArrayList<EntityRender> getEntities()
	{
		return entities;
	}
}
