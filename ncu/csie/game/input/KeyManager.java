package ncu.csie.game.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener{

	private boolean[] keys;
	public boolean up, down, left, right , item1 , item2, skill;
	
	public KeyManager() {
		keys = new boolean[256];
	}
	
	public void tick(){
		up = keys[KeyEvent.VK_UP];
		down = keys[KeyEvent.VK_DOWN];
		left = keys[KeyEvent.VK_LEFT];
		right = keys[KeyEvent.VK_RIGHT];
		item1 = keys[KeyEvent.VK_D];
		item2 = keys[KeyEvent.VK_F];
		skill = keys[KeyEvent.VK_G];
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;			
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
		
	}

}
