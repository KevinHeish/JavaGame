package Mode;

import java.awt.event.MouseEvent;
import java.util.ArrayList;

import Controller.Canvas;
import Object.AbsObject;
import Object.AssoLine;
import Object.Port;

public class AssociationMode extends Mode{
	private int count;
	private Port firstPort;
	private AbsObject select;
	
	public AssociationMode(Canvas canvas) {
		super(canvas);
		count = 0;
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		
		
		ArrayList<AbsObject> list = canvas.getObjectList();
		
		for(int i = 0 ; i < list.size(); i++){
			if(list.get(i).isSelected(x, y)==true){
				select = list.get(i);
				firstPort = list.get(i).getPort(x, y);
				count++;
				break;
			}
		}
		
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		
		
		if(firstPort == null)
			return;
		
		Port secondPort = null;
		
		ArrayList<AbsObject> list = canvas.getObjectList();
		
		for(int i = 0 ; i < list.size(); i++){
			if(list.get(i).isSelected(x, y)==true){
				if(select.equals(list.get(i)))
					return;
				secondPort = list.get(i).getPort(x, y);
				count++;
				break;
			}
		}
		
		if(count == 2){
			AssoLine line = new AssoLine(firstPort , secondPort);
			canvas.addObject(line);
			
		}
		
		firstPort = null;
		count = 0;
		canvas.revalidate();
		canvas.repaint();
	}
	
	
}
