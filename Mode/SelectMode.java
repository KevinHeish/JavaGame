package Mode;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import Controller.Canvas;
import Object.AbsObject;
import Object.Group;


public class SelectMode extends Mode implements MouseMotionListener{
	private AbsObject currentSelect;
	private int pressedX , pressedY;

	
	public SelectMode(Canvas canvas) {
		super(canvas);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		
		ArrayList<AbsObject> list = canvas.getObjectList();
		
		
		for(int i = 0; i < list.size(); i++){
			list.get(i).setSelected(false);
		}
		
		for(int i = 0; i < list.size(); i++){
			if(list.get(i).isSelected(x,y)==true){
				list.get(i).setSelected(true);
				break;
			}
		}
		
		canvas.revalidate();
		canvas.repaint();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		
		ArrayList<AbsObject> list = canvas.getObjectList();
		
		pressedX = x;
		pressedY = y;
		
		for(int i = 0; i < list.size(); i++){
			if(list.get(i).isSelected(x,y)==true){
				currentSelect = list.get(i);
				break;
			}
			else
				currentSelect = null;
		}
		
	}

	
	@Override
	public void mouseReleased(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		
		if(currentSelect!=null){
			if (Math.abs(e.getX() - pressedX) > 1 && Math.abs(e.getY() - pressedY) > 1){
				currentSelect.setLocation(x,y);
			}
		}
		else{
			ArrayList<AbsObject> list = canvas.getObjectList();
			
			if (pressedX > x) {
				int temp = x;
				x = pressedX;
				pressedX = temp;
			}
			if (pressedY > y) {
				int temp = y;
				y = pressedY;
				pressedY = temp;
			}

			
			for(int i = 0; i < list.size();i++)
			{
			
				if(list.get(i).getX() > pressedX && list.get(i).getY() > pressedY 
						&& list.get(i).getX() + list.get(i).getWidth() < x 
						&& list.get(i).getY() + list.get(i).getHeight() < y)
				{
					list.get(i).setSelected(true);
				}
				else
					list.get(i).setSelected(false);
			}
			
		}
		
		canvas.revalidate();
		canvas.repaint();
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		
		if(currentSelect!=null && isMultiSelect()==false){
			int tempX = pressedX;
			int tempY = pressedY;
			
			currentSelect.move(x-tempX, y-tempY);
			pressedX = x;
			pressedY = y;
		}
		else
		{
			
			ArrayList<AbsObject> list = canvas.getObjectList();
			
			int tempX = pressedX;
			int tempY = pressedY;
			
			for(int i = 0; i < list.size();i++)
			{
				if(list.get(i).getSelected()==true){
					list.get(i).move(x-tempX, y-tempY);
					pressedX = x;
					pressedY = y;
				}
			}
		}
		
		canvas.revalidate();
		canvas.repaint();
	}
	
	public boolean isMultiSelect()
	{
		ArrayList<AbsObject> list = canvas.getObjectList();
		int count = 0;
	
		
		for(int i = 0; i < list.size();i++)
		{
			if(list.get(i).getSelected()==true){
				count++;
				if(count >=2)return true;
			}
		}
		return false;
	}
	
	
}
