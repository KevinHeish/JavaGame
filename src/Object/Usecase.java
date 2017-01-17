package Object;

import java.awt.Color;
import java.awt.Graphics;

public class Usecase extends BasicObject{

	public Usecase(int x, int y) {
		super(x, y);
		this.width = 120;
		this.height = 80;
	}

	@Override
	public void draw(Graphics g) {    
        g.setColor(Color.GRAY);
        g.fillOval(x , y , width, height);
        g.setColor(Color.BLACK);
        g.drawOval(x , y , width, height);
        
        
	}
	
}
