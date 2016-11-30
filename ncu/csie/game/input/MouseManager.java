package ncu.csie.game.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import ncu.csie.game.ui.UIManager;

public class MouseManager implements MouseListener, MouseMotionListener{

	
	private boolean leftPressed, rightPressed;
	private int mouseX, mouseY;
	private UIManager uiManager;
	
	public MouseManager(){
		
	}
	
	public void setUIManager(UIManager uiManager){
		this.uiManager = uiManager;
	}
	
	
	// Getters
	public boolean isLeftPressed(){
		return leftPressed;
	}
	
	public boolean isRightPressed(){
		return rightPressed;
	}
	
	public int getMouseX(){
		return mouseX;
	}
	
	public int getMouseY(){
		return mouseY;
	}
	
	// Implemented methods
	@Override
	public void mouseDragged(MouseEvent evt) {
		
		
	}

	@Override
	public void mouseMoved(MouseEvent evt) {
		mouseX = evt.getX();
		mouseY = evt.getY();	
		
		if(uiManager != null){
			uiManager.onMouseMove(evt);
		}
	}

	@Override
	public void mouseClicked(MouseEvent evt) {
		
		
	}

	@Override
	public void mouseEntered(MouseEvent evt) {
		
		
	}

	@Override
	public void mouseExited(MouseEvent evt) {
		
		
	}

	@Override
	public void mousePressed(MouseEvent evt) {
		if(evt.getButton() == MouseEvent.BUTTON1){
			leftPressed = true;
		}
		else if(evt.getButton() == MouseEvent.BUTTON3){
			rightPressed = true;
		}
		
		
	}

	@Override
	public void mouseReleased(MouseEvent evt) {
		
		if(evt.getButton() == MouseEvent.BUTTON1){
			leftPressed = false;
		}
		else if(evt.getButton() == MouseEvent.BUTTON3){
			rightPressed = false;
		}
		
		if(uiManager != null){
			uiManager.onMouseRelease(evt);
		}
	}

}
