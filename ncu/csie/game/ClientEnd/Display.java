package ncu.csie.game.ClientEnd;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Panel;

import javax.swing.JDialog;
import javax.swing.JFrame;



public class Display {
	
	private JFrame frame;
	private Panel panel;
	private Canvas canvas;	
	private String title;
	private int width, height;
	private DialogPassLinkData dialogPassLinkData;
	private DialogWaitForLink dialogWaitForLink;
	
	public Display(String title, int width, int height){
		this.title = title;
		this.width = width;
		this.height = height;
		createDisplay();
	}
	
	private void createDisplay(){
		
		frame = new JFrame(title);
		frame.setSize(width, height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		//FIXME
		//inter.addInnterface(frame, width, height);
		panel = new Panel();
		canvas = new Canvas();
		
		canvas.setPreferredSize(new Dimension(width, height));
		canvas.setMaximumSize(new Dimension(width, height));
		canvas.setMinimumSize(new Dimension(width, height));
		canvas.setFocusable(false);
		
		frame.add(panel);
		panel.add(canvas);
		frame.pack();
		
		dialogPassLinkData = new DialogPassLinkData();
		dialogPassLinkData.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		dialogWaitForLink = new DialogWaitForLink();
		dialogWaitForLink.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialogWaitForLink.getOkButton().setEnabled(false);
	}
	
	public DialogPassLinkData getDialogPassLinkData() {
		return dialogPassLinkData;
	}

	public DialogWaitForLink getDialogWaitForLink() {
		return dialogWaitForLink;
	}

	public Canvas getCanvas(){
		return canvas;
	}
	
	public JFrame getFrame(){
		return frame;
	}
	
}
