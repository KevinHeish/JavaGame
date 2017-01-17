package Object;

import java.awt.Color;
import java.awt.Graphics;

public class CompLine extends BasicLine{
	private int retX , retY;
	
	
	public CompLine(Port init, Port end) {
		super(init, end);
		calculateDirection();
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(Color.BLACK);
		g.drawLine(init.getX() , init.getY() , end.getX(), end.getY());
		calculateDirection();
		g.fillRect(retX, retY, 8, 8);
	}
	
	
	private void calculateDirection()
	{
		int diffX = end.getX() - init.getX();
		int diffY = end.getY() - init.getY();
		
		
		if(diffY * diffX < 0){	
			if(diffX < 0){
				retX = end.getX();
				retY = end.getY()-5;
			}
			else{
				retX = end.getX()-5;
				retY = end.getY();
			}
		}
		else{
			if(diffX > 0){
				retX = end.getX()-5;
				retY = end.getY()-5;
			}
			else{
				retX = end.getX();
				retY = end.getY();
			}
		}
		
	}
}
