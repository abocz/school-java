import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class CirclePanel extends JPanel {
private int clicked;
private int x;
private int y;
	public CirclePanel(){
		this.addMouseListener(new MouseHandler());
	}
	
	public class MouseHandler extends MouseAdapter{
		
		public void mouseClicked(MouseEvent event) {
			x = event.getX();
			y = event.getY();
			clicked = event.getButton();
			repaint();
		}
	}
	
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		g.setColor(Color.black);
		if(clicked == 1){
			g.drawOval(x - 12, y - 12, 24, 24);
		}
		
		if(clicked == 3){
			g.fillOval(x - 12, y - 12, 24, 24);
		}
		
	}
}
