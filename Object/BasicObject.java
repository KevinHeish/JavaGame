package Object;

import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JLabel;

public class BasicObject extends AbsObject{
	protected int x , y;
	protected int width;
	protected int height;
	protected Port[] ports;
	protected boolean isSelected;
	protected String name;
	private JLabel textArea;
	
	public BasicObject(int x , int y)
	{
		this.x = x;
		this.y = y;
		this.width = 0;
		this.height = 0;
		ports = new Port[4];
		isSelected = false;
		textArea = new JLabel();
	}
	

	@Override
	public void draw(Graphics g) {
		
	}
	
	@Override
	public boolean isSelected(int x , int y){
		if( x >= this.x && y >= this.y && x<=this.x + width && y <=this.y + height){
			return true;
		}
		else
			return false;
	}
	
	@Override
	public void setSelected(boolean result)
	{
		isSelected = result;
	}
	
	@Override
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
	
	@Override
	public void move(int diffX , int diffY)
	{
		this.x += diffX;
		this.y += diffY;
		
		ports[0].setLocation(x-2 + (width/2) , y-2);
		ports[1].setLocation(x-2 + width  , y + (height/2) -2);
		ports[2].setLocation(x-2 + (width/2) , y + height-2);
		ports[3].setLocation(x-2 , y + (height/2)-2 );
		
		textArea.setBounds(this.x,this.y, 50, 20);
	}
	
	
	
	@Override
	public int getX()
	{
		return x;
	}
	
	@Override
	public int getY()
	{
		return y;
	}
	
	@Override
	public int getWidth()
	{
		return width;
	}
	
	
	@Override
	public int getHeight()
	{
		return height;
	}
	
	@Override
	public void setName(String s)
	{
		name = s;
		textArea.setText(name);
	}
	@Override
	public JLabel getNameArea()
	{
		return textArea;
	}
}
