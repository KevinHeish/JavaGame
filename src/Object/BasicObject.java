package Object;

import java.awt.Graphics;

public class BasicObject extends AbsObject{
	protected int x , y;
	protected int width;
	protected int height;
	protected Port[] ports;
	protected boolean isChoosed;
	
	public BasicObject(int x , int y)
	{
		this.x = x;
		this.y = y;
		this.width = 0;
		this.height = 0;
		ports = new Port[4];
		isChoosed = false;
	}
	

	@Override
	public void draw(Graphics g) {
		
	}
	
}
