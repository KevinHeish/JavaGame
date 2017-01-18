package Controller;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;


import Button.State;
import Mode.Mode;
import Object.AbsObject;

public class Canvas extends JPanel {
	private ArrayList<AbsObject> objectList;
	private State state;
	
	
	public Canvas(int width, int height){
		this.setSize(width, height);
		this.setVisible(true);
		this.setBackground(Color.white);
		state = State.SELECT;
		objectList = new ArrayList<AbsObject>();

		assert this!=null;
	}
	
	public void addObject(AbsObject object)
	{
		objectList.add(object);
	}
	
	public ArrayList<AbsObject> getObjectList()
	{
		return objectList;
	}
	
	
	public void setState(State state)
	{
		int count = this.getMouseListeners().length;
		
		
		if(count != 0){
			this.removeMouseListener(this.getMouseListeners()[0]);
		}
		count = this.getMouseMotionListeners().length;
		
		if(count != 0){
			this.removeMouseMotionListener(this.getMouseMotionListeners()[0]);
		}
		
		
		this.state = state;
	}
	
	
	@Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        
        for(int i = 0; i < objectList.size();i++){
        	objectList.get(i).draw(g);
        }
    }
}
