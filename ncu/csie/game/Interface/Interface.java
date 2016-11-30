package ncu.csie.game.Interface;
import java.awt.Container;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
public class Interface {
	Area object1;
	Area object2;
	Area player1;
	Area player2;
	Area player3;
	Area player4;
	Area smallmap;
	
	public void addInnterface(JFrame frame,int width,int height)
	{
		object1=new Area(0,height-60,60,60,new ImageIcon(""));
		object2=new Area(60,height-60,60,60,new ImageIcon(""));
		smallmap=new Area(width-120,height-120,120,120,new ImageIcon(""));
		player1=new Area(width-155,height-120,30,30,new ImageIcon(""));
		player2=new Area(width-120,height-155,30,30,new ImageIcon(""));
		player3=new Area(width-85,height-155,30,30,new ImageIcon(""));
		player4=new Area(width-50,height-155,30,30,new ImageIcon(""));
		Container cp=frame.getContentPane();
		//cp.setLayout(null);
		cp.add(object1.label);
		cp.add(object2.label);
		cp.add(smallmap.label);
		cp.add(player1.label);
		cp.add(player2.label);
		cp.add(player3.label);
		cp.add(player4.label);
	
	}
}
