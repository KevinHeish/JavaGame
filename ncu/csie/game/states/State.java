package ncu.csie.game.states;

import java.awt.Graphics;
import ncu.csie.game.Handler;
import ncu.csie.game.ui.UIManager;

public abstract class State {
	
	private static State currentState = null;
	
	public static void setState(State state){
		currentState = state;
	}
	
	public static State getState(){
		return currentState;
	}
	
	//CLASS
	
	protected Handler handler;
	public State(Handler handler){
		this.handler = handler;
	}
	
	
	public abstract void tick();
	
	public abstract void render(Graphics g);

	public UIManager getUImanager() {
		// TODO Auto-generated method stub
		return null;
	}
}
