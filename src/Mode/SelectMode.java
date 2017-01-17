package Mode;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import Controller.Canvas;
import Object.AbsObject;


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
		
		AbsObject getObject;
		
		for(int i = 0; i < list.size(); i++){
			list.get(i).setSelected(false);
		}
		
		for(int i = 0; i < list.size(); i++){
			if(list.get(i).isSelected(x,y)==true){
				getObject = list.get(i);
				list.get(i).setSelected(true);
				list.remove(i);
				canvas.addObject(getObject);
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
		
		if(currentSelect!=null)
			currentSelect.setLocation(x,y);
		
		canvas.revalidate();
		canvas.repaint();
	}
	
}
