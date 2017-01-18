package Object;

import java.awt.Color;
import java.awt.Graphics;

public class GeneLine extends BasicLine{
	private int retX , retY;
	
    
	public GeneLine(Port init, Port end) {
		super(init, end);
		calculateDirection();
	}

	@Override
	public void draw(Graphics g) {
		calculateDirection();
		int[] x = {end.getX(), end.getX() , end.getX()+retX};
        int[] y = {end.getY(), end.getY()+retY , end.getY()};
       
        
		g.setColor(Color.BLACK);
		g.drawLine(init.getX() , init.getY() , end.getX(), end.getY());
		
		g.fillPolygon(x, y, 3);
	}
	
	private void calculateDirection()
	{
		int diffX = end.getX() - init.getX();
		int diffY = end.getY() - init.getY();
		
		
		if(diffY * diffX < 0){	
			if(diffX < 0){
				retX = +10;
				retY = -10;
				
			}
			else{
				retX = -10;
				retY = +10;
				
			}
		}
		else{
			if(diffX > 0){
				retX = -10;
				retY = -10;
				
			}
			else{
				retX = +10;
				retY = +10;
				
			}
		}
		
	}
}
