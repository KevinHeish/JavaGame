package Object;

import java.awt.Color;
import java.awt.Graphics;

public class BasicLine extends AbsObject{
	protected Port init , end;
	
	public BasicLine(Port init , Port end){
		this.init = init;
		this.end = end;
	}
	
	@Override
	public void draw(Graphics g) {
		
	}
	
}
