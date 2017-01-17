package Button;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import Controller.UMLEditor;
import Mode.GeneralizationMode;

public class GeneralizationLineButton extends JButton implements ActionListener{

	private UMLEditor controller;
	private GeneralizationMode mode;
	public GeneralizationLineButton(UMLEditor controller)
	{
		this.controller = controller;
		addActionListener(this);
		mode = new GeneralizationMode(this.controller.getCanvas());
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		int buttonSize = controller.getButtonList().size();
		
		for(int i = 0 ;  i < buttonSize;i++){
			controller.getButtonList().get(i).setEnabled(true);
		}
		
		setEnabled(false);
		controller.getCanvas().setState(State.GENERALIZATION_LINE);
		controller.getCanvas().addMouseListener(mode);
	}

}
