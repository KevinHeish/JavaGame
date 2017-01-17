package Button;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import Controller.UMLEditor;
import Mode.AssociationMode;
import Mode.UsecaseMode;

public class AssociateLineButton extends JButton implements ActionListener{
	private UMLEditor controller;
	private AssociationMode mode;
	
	public AssociateLineButton(UMLEditor controller)
	{
		this.controller = controller;
		addActionListener(this);
		mode = new AssociationMode(this.controller.getCanvas());
	}
	
	@Override
	public void actionPerformed(ActionEvent e){
		int buttonSize = controller.getButtonList().size();
		
		for(int i = 0 ;  i < buttonSize;i++){
			controller.getButtonList().get(i).setEnabled(true);
		}
		
		
		setEnabled(false);
		controller.getCanvas().setMode(mode);
		
		controller.getCanvas().addMouseListener(mode);
		
	}
}
