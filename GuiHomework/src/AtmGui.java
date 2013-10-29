import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


  
public class AtmGui {
	private JLabel label1 = new JLabel("Account ID", SwingConstants.RIGHT);
	private JLabel label2 = new JLabel("Amount", SwingConstants.RIGHT);
	private JTextField idField = new JTextField(10);
	private JTextField amountField = new JTextField(10);
	private JTextField displayField = new JTextField(25);
	private JButton withdrawB = new JButton("Withdrawal");
	private JButton depositB = new JButton("Deposit");
	private JButton balanceB = new JButton("Balance");
	private AccountManager acctManager;
	
	public AtmGui(AccountManager aMan){
		JFrame atmFrame = new JFrame();
		atmFrame.setSize(450, 450);
		atmFrame.setLocationRelativeTo(null);
		atmFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		atmFrame.setTitle("ATM Portal");		
		GridLayout masterGrid = new GridLayout(3,1);
		GridLayout grid1 = new GridLayout(1,3);
		GridLayout grid2 = new GridLayout(1,3);
		GridLayout grid3 = new GridLayout(1,1);
		GridLayout grid4 = new GridLayout(2,1);
		JPanel rowPanel1 = new JPanel();
		rowPanel1.setLayout(grid1);
		JPanel rowPanel2 = new JPanel();
		rowPanel2.setLayout(grid2);
		grid2.setHgap(12);
		JPanel rowPanel3 = new JPanel();
		rowPanel3.setLayout(grid3);
		JPanel jPan11 = new JPanel();
		JPanel jPan12 = new JPanel();
		jPan12.setLayout(grid4);
		JPanel jPan13 = new JPanel();
		jPan13.setLayout(grid4);
		withdrawB.addActionListener(new ButtonListener());
		depositB.addActionListener(new ButtonListener());
		balanceB.addActionListener(new ButtonListener());
		jPan12.add(label1);
		jPan12.add(label2);
		jPan13.add(idField);
		jPan13.add(amountField);
		rowPanel1.add(new BankLogo());
		rowPanel1.add(jPan12);
		rowPanel1.add(jPan13);
		rowPanel2.add(withdrawB);
		rowPanel2.add(depositB);
		rowPanel2.add(balanceB);
		rowPanel3.add(displayField);
		atmFrame.setLayout(masterGrid);
		atmFrame.add(rowPanel1);
		atmFrame.add(rowPanel2);
		atmFrame.add(rowPanel3);
		acctManager = aMan;
		
		atmFrame.setVisible(true);
	}
	private class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			String op = e.getActionCommand();
			int tempID = 0;
			double tempAmount = 0;
			double number1;
			Account acct;
			try{
				number1 = Double.parseDouble(idField.getText());
			}catch (NumberFormatException n){
				displayField.setText("Illegal data in the Account ID Field, please re-enter ID.");
				return;
			}
			//Withdraw Event
			if(op.equals("Withdrawal")){
				try{
					tempID = Integer.parseInt(idField.getText());
				}catch (NumberFormatException n){
					displayField.setText("Illegal data in the Account ID Field, please re-enter ID.");
					return;
				}
				try{
					tempAmount = Double.parseDouble(amountField.getText());
				}catch (NumberFormatException n){
					displayField.setText("Illegal data in the Amount Field, please re-enter ID.");
					return;
				}
				try {
					acct = acctManager.getAccount(tempID);
				} catch (NoSuchAccountException e1) {
					displayField.setText("That account ID "+tempID+" does not exist!");
					return;
				}				
				try {
					acct.withdraw(tempAmount);
				} catch (InsufficientFundsException e1) {
					displayField.setText("Insufficient Funds in account!  Check your balance and try again.");
					return;
				}
				displayField.setText("The amount of "+tempAmount+" was withdrawn from Account ID:"+tempID);
			}
			//Deposit Event
			if(op.equals("Deposit")){
				try{
					tempID = Integer.parseInt(idField.getText());
				}catch (NumberFormatException n){
					displayField.setText("Illegal data in the Account ID Field, please re-enter ID.");
					return;
				}
				try {
					acct = acctManager.getAccount(tempID);
				} catch (NoSuchAccountException e1) {
					displayField.setText("That account ID "+tempID+" does not exist!");
					return;
				}
				try{
					tempAmount = Double.parseDouble(amountField.getText());
				}catch (NumberFormatException n){
					displayField.setText("Illegal data in the Amount Field, please re-enter ID.");
					return;
				}
				acct.deposit(tempAmount);
				displayField.setText("The amount of "+tempAmount+" was deposited to Account ID:"+tempID);
			}
			//Balance Event
			if(op.equals("Balance")){
				amountField.setText("");
				try{
					tempID = Integer.parseInt(idField.getText());
				}catch (NumberFormatException n){
					displayField.setText("Illegal data in the Account ID Field, please re-enter ID.");
					return;
				}
				try {
					acct = acctManager.getAccount(tempID);
				} catch (NoSuchAccountException e1) {
					displayField.setText("That account ID "+tempID+" does not exist!");
					return;
				}
				displayField.setText("The balance of "+acct.getOwner()+"'s account is: "+acct.getBalance());
			}
			}
		}
}
