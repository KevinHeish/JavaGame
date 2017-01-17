package Object;

import java.awt.Graphics;

public abstract class AbsObject {
	public abstract void draw(Graphics g);
	public boolean isSelected(int x , int y){
		return false;
	}
	public void setSelected(boolean t){}
	public Port getPort(int x , int y){
		return null;
	}
	public void setLocation(int x ,int y){}
}
