package ncu.csie.game.ClientEnd;

import ncu.csie.game.gfx.GameCamera;

public class GameHandler {
	private Game game;
	
	public GameHandler(Game game){
		this.game = game;
	}
	
	public GameCamera getGameCamera(){
		return game.getGameCamera();
	}
	
	public KeyManager getKeyManager(){
		return game.getKeyManager();
	}
	
	public MouseManager getMouseManager(){
		return game.getMouseManager();
	}
	
	public int getWidth(){
		return game.getWidth();
	}

	public int getHeight(){
		return game.getHeight();
	}
	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	
}
