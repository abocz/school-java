import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class BankerGui {
	private JLabel idLabel = new JLabel("Account ID", SwingConstants.RIGHT);
	private JLabel nameLabel = new JLabel("Name", SwingConstants.RIGHT);
	private JTextField idField = new JTextField(10);
	private JTextField nameField = new JTextField(10);
	private JButton openB = new JButton("Open Account");
	private JButton closeB = new JButton("Close Account");
	private JTextField displayField = new JTextField(10);
	private AccountManager acctManager;
	
	public BankerGui(AccountManager aMan){
		JFrame bankFrame = new JFrame();
		bankFrame.setSize(480, 311);
		bankFrame.setLocationRelativeTo(null);
		bankFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		bankFrame.setTitle("Bank Manager Portal");
	
		bankFrame.setLayout(new GridLayout(2,1));
		GridLayout row1 = new GridLayout(1,3);
		GridLayout row2 = new GridLayout(2,1);
		GridLayout row3 = new GridLayout(1,2);
		GridLayout row4 = new GridLayout(2,1);
		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();
		JPanel panel3 = new JPanel();
		JPanel panel4 = new JPanel();
		JPanel panel5 = new JPanel();
		panel1.setLayout(row1);
		panel2.setLayout(row2);
		panel3.setLayout(row3);
		panel4.setLayout(row2);
		panel5.setLayout(row4);
		panel1.add(new BankLogo());
		panel2.add(idLabel);
		panel2.add(nameLabel);
		panel4.add(idField);
		panel4.add(nameField);
		panel1.add(panel2);
		panel1.add(panel4);
		panel3.add(openB);
		panel3.add(closeB);
		panel5.add(panel3);
		panel5.add(displayField);
		openB.addActionListener(new ButtonListener());
		closeB.addActionListener(new ButtonListener());
		bankFrame.add(panel1);
		bankFrame.add(panel5);
		acctManager = aMan;
		
		
		
		
		bankFrame.setVisible(true);
	}
	private class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e){

			String op = e.getActionCommand();
			int tempID;
			String displayText;
			String tempName;
			double number1;
			
			if(op.equals("Open Account")){
				idField.setText("");
				tempName = nameField.getText();
				tempID = acctManager.openAccount(tempName, .018);
				displayText = Integer.toString(tempID);
				displayField.setText("Your account was successfully created. Account name: " + nameField.getText() + " and ID number:  " +displayText);
			}
			if(op.equals("Close Account")){
				try{
					number1 = Double.parseDouble(idField.getText());
				}catch (NumberFormatException n){
					displayField.setText("Illegal data in the Account ID field, please re-enter ID.");
					return;
				}
				tempID = Integer.parseInt(idField.getText());
				try {
					acctManager.closeAccount(tempID);
					idField.setText("");
					nameField.setText("");
					displayField.setText("Account " + tempID +" was deleted.");
				} catch (NoSuchAccountException e1) {
					displayField.setText("That account ID "+tempID+" does not exist!");
					return;
				}
			}
			}
		}
	
}
