package ncu.csie.game.ClientEnd;

import java.awt.Graphics;

public abstract class State {
	
	private static State currentState = null;
	
	public static void setState(State state){
		currentState = state;
	}
	
	public static State getState(){
		return currentState;
	}
	
	//CLASS
	
	protected GameHandler handler;
	public State(GameHandler handler){
		this.handler = handler;
	}
	
	
	public abstract void tick();
	
	public abstract void render(Graphics g);

	public UIManager getUImanager(){
		return null;
	}
}
