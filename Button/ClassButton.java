package Button;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import Controller.UMLEditor;
import Mode.ClassMode;
import Mode.CompositionMode;

public class ClassButton extends JButton implements ActionListener{
	private UMLEditor controller;
	private ClassMode mode;
	public ClassButton(UMLEditor controller)
	{
		this.controller = controller;
		addActionListener(this);
		mode = new ClassMode(this.controller.getCanvas());
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		int buttonSize = controller.getButtonList().size();
		
		for(int i = 0 ;  i < buttonSize;i++){
			controller.getButtonList().get(i).setEnabled(true);
		}
		
		setEnabled(false);
		controller.getCanvas().setState(State.CLASS);
		controller.getCanvas().addMouseListener(mode);
		
	}

}
