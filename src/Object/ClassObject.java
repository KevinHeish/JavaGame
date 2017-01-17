package Object;

import java.awt.Color;
import java.awt.Graphics;

public class ClassObject extends BasicObject{
	
	public ClassObject(int x, int y) {
		super(x, y);
		this.width = 80;
		this.height = 120;
	}
	
	public void setPort()
	{
		ports[0] = new Port(x + (width/2) , y);
		ports[1] = new Port(x + width  , y + (height/2) );
		ports[2] = new Port(x + (width/2) , y + height);
		ports[3] = new Port(x , y + (height/2) );
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(Color.GRAY);
        g.fillRect(x ,y , width, height);
        g.setColor(Color.BLACK);
        g.drawRect(x, y , width, height);
        g.drawLine(x , y + height/3 ,x + width ,y + height/3);
        g.drawLine(x , y + 2*height/3 ,x + width ,y + 2*height/3);
        
        if(isChoosed){
	        for(int i = 0 ; i < 4 ; i++){
	        	ports[i].draw(g);
	        }
        }
	}
	
}
