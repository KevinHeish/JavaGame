package Mode;

import java.awt.event.MouseEvent;

import Controller.Canvas;
import Object.ClassObject;

public class ClassMode extends Mode{
	public ClassMode(Canvas canvas) {
		super(canvas);
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		ClassObject object = new ClassObject(e.getX() , e.getY());		
		canvas.addObject(object);
		
		canvas.revalidate();
		canvas.repaint();
	}

}
