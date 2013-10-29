import java.awt.*;
import javax.swing.*;
public class CircleFrame {
	
	public CircleFrame(){
		JFrame myFrame = new JFrame();
		myFrame.setSize(800,800);
		myFrame.setLocationRelativeTo(null);
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myFrame.setTitle("Lab 16_2");
		
		myFrame.add(new CirclePanel());
		
		myFrame.setVisible(true);
	}
	
	public static void main(String[] args){
		new CircleFrame();
	}
}
