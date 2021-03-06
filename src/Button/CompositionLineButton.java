package Button;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import Controller.UMLEditor;
import Mode.CompositionMode;
import Mode.UsecaseMode;

public class CompositionLineButton extends JButton implements ActionListener{

	private UMLEditor controller;
	private CompositionMode mode;
	public CompositionLineButton(UMLEditor controller)
	{
		this.controller = controller;
		addActionListener(this);
		mode = new CompositionMode(this.controller.getCanvas());
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		int buttonSize = controller.getButtonList().size();
		
		for(int i = 0 ;  i < buttonSize;i++){
			controller.getButtonList().get(i).setEnabled(true);
		}
		
		setEnabled(false);
		controller.getCanvas().setState(State.COMPOSITION_LINE);
		controller.getCanvas().addMouseListener(mode);
	}

}
