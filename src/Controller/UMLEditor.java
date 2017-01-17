package Controller;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Button.ButtonGenerator;
import Button.State;

public class UMLEditor {
	private JFrame frame;
	private Canvas canvas;
	private MenuBar menuBar;
	private Menu file, edit;
	private MenuItem group, ungroup, changeObjName;
	private JPanel buttonPanel;
	private ArrayList<JButton> buttonList;
	
	public UMLEditor(int width , int height){
		assert width >0;
		assert height >0;
		
		frame = new JFrame();
		frame.setSize(width, height);
		
		menuBar = new MenuBar();
		file = new Menu("File");
		edit = new Menu("Edit");
		group = new MenuItem("Group");
		ungroup = new MenuItem("UnGroup");
		changeObjName = new MenuItem("Change Object Name.");
		canvas = new Canvas(width-100 , height);
		buttonPanel = new JPanel();
		buttonPanel.setSize(100 , height);
		buttonPanel.setVisible(true);
		buttonList = new ArrayList<JButton>();
		
		initFrame();
	}
	
	private void initFrame()
	{
		assert canvas!=null;
		
		
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setMenuBar(menuBar);
		
		menuBar.add(file);
		menuBar.add(edit);
		edit.add(group);
		edit.add(ungroup);
		edit.add(changeObjName);
		
		frame.add(canvas, BorderLayout.CENTER);
		frame.add(buttonPanel, BorderLayout.WEST);
		buttonPanel.setLayout(new GridLayout(6, 1));
		
		for(State state : State.values()){
			JButton button;
			ButtonGenerator createButton = new ButtonGenerator(state, this);
			
			button = createButton.getButton();
			buttonPanel.add(button);
			buttonList.add(button);
		}
		
		frame.revalidate();
		frame.repaint();
	}
	
	public Canvas getCanvas()
	{
		return canvas;
	}
	
	public ArrayList<JButton> getButtonList()
	{
		return buttonList;
	}
}
