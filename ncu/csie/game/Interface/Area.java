package ncu.csie.game.Interface;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Area {
	JLabel label;
	ImageIcon image; 
	public Area(int x,int y,int width,int height,ImageIcon initial_image)
	{
		image=initial_image;
		label=new JLabel(image);
		label.setOpaque(true);
        label.setBackground(Color.WHITE);
        label.setBounds(x,y,width,height);;
        label.setBorder(BorderFactory.createLineBorder(Color.black));
	}
	public void set_image(int image_id)
	{
		switch(image_id)
		{
		case 1:
			image=new ImageIcon("");
			break;
		case 2:
			break;
		case 3:
			break;
		case 4:
			break;
		case 5:
			break;
		case 6:
			break;
		default:
			break;
			
			
		}
	}
}
