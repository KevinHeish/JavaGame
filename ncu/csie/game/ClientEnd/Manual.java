package ncu.csie.game.ClientEnd;


import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;



public class Manual extends JFrame{
	private JPanel panel;
	private int width , height;
	private ImageIcon image;
	private JLabel imgRules;
	
	
	public Manual(int w , int h)
	{
		width = w; 
		height = h;
		
		panel = new JPanel();
		image = new ImageIcon("res/textures/rules.png");
		imgRules = new JLabel();
		imgRules.setIcon(image);
		
		setting();
	}
	
	public void setting()
	{
		this.setResizable(false);
		this.setSize(width ,height);
		this.setVisible(true);
		this.add(panel, BorderLayout.CENTER);
		
		panel.add(imgRules);
		panel.setVisible(true);
		
		this.addWindowListener(new WindowAdapter(){
		     public void windowClosing(WindowEvent event){
		    	 dispose();
		     }
		});
	}
	
}
