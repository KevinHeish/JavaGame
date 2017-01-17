package Button;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import Controller.UMLEditor;
import Mode.SelectMode;

public class SelectButton extends JButton implements ActionListener{
	
	private UMLEditor controller;
	private SelectMode mode;
	
	public SelectButton(UMLEditor controller)
	{
		this.controller = controller;
		addActionListener(this);
		mode = new SelectMode(this.controller.getCanvas());
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		int buttonSize = controller.getButtonList().size();
		
		for(int i = 0 ;  i < buttonSize;i++){
			controller.getButtonList().get(i).setEnabled(true);
		}
		
		setEnabled(false);
		controller.getCanvas().setState(State.SELECT);
		controller.getCanvas().addMouseListener(mode);
		controller.getCanvas().addMouseMotionListener(mode);
	}

}
