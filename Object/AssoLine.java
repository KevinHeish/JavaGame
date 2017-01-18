package Object;

import java.awt.Color;
import java.awt.Graphics;

public class AssoLine extends BasicLine{
	public AssoLine(Port init, Port end) {
		super(init, end);
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(Color.BLACK);
		
		g.drawLine(init.getX() , init.getY() , end.getX(), end.getY());
	}
}
