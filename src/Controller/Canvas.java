package Controller;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;


import Button.State;
import Mode.Mode;
import Object.AbsObject;

public class Canvas extends JPanel {
	public ArrayList<AbsObject> objectList;
	public Mode currentMode;
	
	public Canvas(int width, int height){
		this.setSize(width, height);
		this.setVisible(true);
		this.setBackground(Color.white);
		objectList = new ArrayList<AbsObject>();
		assert this!=null;
	}
	
	public void addObject(AbsObject object)
	{
		objectList.add(object);
	}
	
	public void setMode(Mode state){
		currentMode = state;
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
