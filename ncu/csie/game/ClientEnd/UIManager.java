package ncu.csie.game.ClientEnd;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class UIManager {

	private GameHandler handler;
	private ArrayList<UIObject> objects;
	
	
	public UIManager(GameHandler handler){
		this.handler = handler;
		objects = new ArrayList<UIObject>();
	}
	
	public void tick(){
		for(int i =0 ; i<objects.size();i++){
			objects.get(i).tick();
		}
	}
	
	public void render(Graphics g){
		for(int i =0 ; i<objects.size();i++){
			objects.get(i).render(g);
		}
	}
	
	public void onMouseMove(MouseEvent evt){
		for(int i =0 ; i<objects.size();i++){
			objects.get(i).onMouseMove(evt);
		}
	}
	
	public void onMouseRelease(MouseEvent evt){
		for(int i =0 ; i<objects.size();i++){
			objects.get(i).onMouseRelease(evt);
		}
	}
	
	public void addObject(UIObject obj){
		objects.add(obj);
	}
	
	public void removeObject(UIObject obj){
		objects.remove(obj);
	}

	public GameHandler getHandler() {
		return handler;
	}

	public void setHandler(GameHandler handler) {
		this.handler = handler;
	}

	public ArrayList<UIObject> getObjects() {
		return objects;
	}

	public void setObjects(ArrayList<UIObject> objects) {
		this.objects = objects;
	}
	
}
