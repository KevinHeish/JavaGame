package Mode;

import java.awt.event.MouseEvent;

import Controller.Canvas;
import Object.Usecase;

public class UsecaseMode extends Mode{

	public UsecaseMode(Canvas canvas) {
		super(canvas);
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Usecase object = new Usecase(e.getX() , e.getY());		
		canvas.addObject(object);
		
		canvas.revalidate();
		canvas.repaint();
	}
	
}
