package ncu.csie.game.ClientEnd;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import java.awt.GridLayout;
import javax.swing.BoxLayout;

public class DialogPassLinkData extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField tfIP;
	private JTextField tfPort;
	private JTextField tfRoomName;
	private JButton okButton;
	private JButton cancelButton;
	

	/**
	 * Create the dialog.
	 */
	public DialogPassLinkData() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblServerIp = new JLabel("Server IP");
			lblServerIp.setBounds(43, 42, 76, 15);
			contentPanel.add(lblServerIp);
		}
		{
			JLabel lblServerPort = new JLabel("Server Port");
			lblServerPort.setBounds(43, 96, 76, 15);
			contentPanel.add(lblServerPort);
		}

		{
			tfIP = new JTextField();
			tfIP.setText("127.0.0.1");
			tfIP.setBounds(141, 39, 96, 21);
			contentPanel.add(tfIP);
			tfIP.setColumns(10);
		}
		{
			tfPort = new JTextField();
			tfPort.setText("8080");
			tfPort.setBounds(141, 93, 96, 21);
			contentPanel.add(tfPort);
			tfPort.setColumns(10);
		}
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	public String getInputIP(){
		return tfIP.getText();
	}
	
	public String getInputPort(){
		return tfPort.getText();
	}
	
	public String getRoomName(){
		return tfRoomName.getText();
	}

	public JButton getOkButton() {
		return okButton;
	}
	public JButton getCancelButton() {
		return cancelButton;
	}
}
