package Object;

import java.awt.Graphics;
import java.util.ArrayList;

public class Group extends AbsObject{
	private ArrayList<AbsObject> groupObject;
	private boolean isSelected;
	protected int x , y;
	protected int width;
	protected int height;
	
	
	public Group(){
		groupObject = new ArrayList<AbsObject>();
		isSelected = false;
		setInfo();
	}
	
	
	@Override
	public void draw(Graphics g) {
		for(int i = 0 ; i < groupObject.size() ; i++){
			groupObject.get(i).draw(g);
		}
	}
	
	public void add(AbsObject obj)
	{
		groupObject.add(obj);
		setInfo();
	}
	
	public boolean isEmpty()
	{
		return groupObject.isEmpty();
	}
	
	@Override
	public boolean isSelected(int x , int y){

		
		if( x >= this.x && y >= this.y && x <= this.x + width && y <= this.y + height){
			return true;
		}
		else{
			return false;
		}

	}
	
	@Override
	public boolean getSelected()
	{
		return isSelected;
	}
	
	@Override
	public void setSelected(boolean t){
		isSelected = t;
		
		for(int i = 0 ; i < groupObject.size() ; i++){
			groupObject.get(i).setSelected(t);
		}
	}
	
	@Override
	public void setLocation(int x ,int y){
		for(int i = 0 ; i < groupObject.size() ; i++){
			groupObject.get(i).setLocation(groupObject.get(i).getX() + x 
					, groupObject.get(i).getY() + y);
		}
		
	}
	
	@Override
	public void move(int x ,int y){
		for(int i = 0 ; i < groupObject.size() ; i++){
			groupObject.get(i).move(x , y);
		}
		setInfo();
	}
	
	private void setInfo()
	{
		int minX = 9999 , maxX = -1 , minY = 9999 , maxY = -1 ;
		
		for(int i = 0 ; i < groupObject.size(); i++)
		{
			if(groupObject.get(i).getX() < minX){
				minX = groupObject.get(i).getX();
			}
			if(groupObject.get(i).getY() < minY){
				minY = groupObject.get(i).getY();
			}
			if(groupObject.get(i).getX() + groupObject.get(i).getWidth() > maxX){
				maxX = groupObject.get(i).getX()+ groupObject.get(i).getWidth();
			}
			if(groupObject.get(i).getY() + groupObject.get(i).getHeight() > maxY){
				maxY = groupObject.get(i).getY()+ groupObject.get(i).getHeight();
			}
		}
		
		x = minX;
		y = minY;
		
		width = maxX - minX;
		height = maxY - minY;
		
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
	
	public int size()
	{
		return groupObject.size();
	}
	
	
	public AbsObject get(int index)
	{
		return groupObject.get(index);
	}
}
