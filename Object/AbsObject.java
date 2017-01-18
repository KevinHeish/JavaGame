package Object;

import java.awt.Graphics;

import javax.swing.JLabel;

public abstract class AbsObject {
	public abstract void draw(Graphics g);
	public boolean isSelected(int x , int y){
		return false;
	}
	public boolean getSelected(){return false;}
	public void setSelected(boolean t){}
	public Port getPort(int x , int y){
		return null;
	}
	public void setLocation(int x ,int y){}
	public int getX(){return -1;}
	public int getY(){return -1;}
	public int getWidth(){return -1;}
	public int getHeight(){return -1;}
	public void move(int diffX , int diffY){}
	public void setName(String s){}
	public JLabel getNameArea(){return null;}
}
