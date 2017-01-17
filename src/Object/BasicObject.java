package Object;

import java.awt.Graphics;

public class BasicObject extends AbsObject{
	protected int x , y;
	protected int width;
	protected int height;
	protected Port[] ports;
	protected boolean isSelected;
	
	public BasicObject(int x , int y)
	{
		this.x = x;
		this.y = y;
		this.width = 0;
		this.height = 0;
		ports = new Port[4];
		isSelected = false;
	}
	

	@Override
	public void draw(Graphics g) {
		
	}
	
	public boolean isSelected(int x , int y){
		if( x >= this.x && y >= this.y && x<=this.x + width && y <=this.y + height){
			return true;
		}
		else
			return false;
	}
	
	public void setSelected(boolean result)
	{
		isSelected = result;
	}
	
	public boolean getSelected()
	{
		return isSelected;
	}
	
	protected void setPort()
	{
		ports[0] = new Port(x-2 + (width/2) , y-2);
		ports[1] = new Port(x-2 + width  , y + (height/2) -2);
		ports[2] = new Port(x-2 + (width/2) , y + height-2);
		ports[3] = new Port(x-2 , y + (height/2)-2 );
	}
	
	@Override 
	public Port getPort(int x , int y)
	{
		int currentMax = 99999;
		int index = -1;		
		
		for(int i = 0 ; i < 4 ; i++){
			int distance = Math.abs(x - ports[i].getX()) + Math.abs(y - ports[i].getY());
			if(currentMax > distance){
				currentMax = distance;
				index = i;
			}
		}
		return ports[index];
	}
	
	@Override
	public void setLocation(int x , int y)
	{
		this.x = x;
		this.y = y;
		
		
		ports[0].setLocation(x-2 + (width/2) , y-2);
		ports[1].setLocation(x-2 + width  , y + (height/2) -2);
		ports[2].setLocation(x-2 + (width/2) , y + height-2);
		ports[3].setLocation(x-2 , y + (height/2)-2 );
	}
	
}
