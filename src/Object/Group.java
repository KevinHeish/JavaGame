package Object;

import java.awt.Graphics;
import java.util.ArrayList;

public class Group extends AbsObject{
	private ArrayList<AbsObject> groupObject;
	
	public Group()
	{
		groupObject = new ArrayList<AbsObject>();
	}
	
	
	@Override
	public void draw(Graphics g) {
		
	}
}
