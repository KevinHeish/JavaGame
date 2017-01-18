package Button;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import Controller.UMLEditor;


public class ButtonGenerator{
	private JButton button;
	
	public ButtonGenerator(State state , UMLEditor controller){
		String path = "C:\\Users\\kennen\\workspace\\OO_UML\\src\\UML_Alpha\\icon\\";
				
		switch(state){
			case SELECT:
				button = new SelectButton(controller);
				path += "mouse.png";
				break;
			case ASSOCIATION_LINE:
				button = new AssociateLineButton(controller);
				path += "line1.png";
				break;
			case GENERALIZATION_LINE:
				button = new GeneralizationLineButton(controller);
				path += "line2.png";
				break;
			case COMPOSITION_LINE:
				button = new CompositionLineButton(controller);
				path += "line3.png";
				break;
			case CLASS:
				button = new ClassButton(controller);
				path += "obj1.png";
				break;
			case USE_CASE:
				button = new UseCaseButton(controller);
				path += "obj2.png";
				break;
		}
		
		button.setIcon(new ImageIcon(path));
	}
	
	public JButton getButton()
	{
		return button;
	}
}
