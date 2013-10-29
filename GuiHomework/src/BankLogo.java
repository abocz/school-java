import java.awt.*;

import javax.swing.*;
public class BankLogo extends JPanel {
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		
		g.setColor(Color.lightGray);
		g.fillRoundRect(0, 0, 140, 135, 20, 20);
		g.setColor(Color.darkGray);
		g.fillRect(60, 28, 80, 20);
		g.fillRect(30, 50, 20, 85);
		g.setColor(Color.black);
		g.fillRect(15, 15, 70, 50);
		g.setColor(new Color(255,100,000));
		g.fillRect(10, 10, 70, 50);
		g.setColor(new Color(000,154,205));
		g.fillRoundRect(16, 67, 87, 58, 11, 11);
		g.setColor(Color.black);
		g.drawRoundRect(16, 67, 87, 58, 11, 11);
		Font font = new Font("Georgia",Font.ITALIC+Font.BOLD,22);
		g.setFont(font);
		g.drawString("Sure", 20, 87);
		g.drawString("Bank", 30, 105);
		g.drawString("Inc.", 39, 123);	
		g.setColor(new Color(255,100,000));
		g.drawString("Sure", 17, 85);
		g.drawString("Bank", 27, 103);
		g.drawString("Inc.", 36, 121);		
	}
}
