package Object;

import java.awt.Color;
import java.awt.Graphics;

public class Port {
	private int x , y;
	
	public Port(int x , int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public void draw(Graphics g)
	{
		g.setColor(Color.black);
		g.drawRect(x, y, 5, 5);
	}
	
}
