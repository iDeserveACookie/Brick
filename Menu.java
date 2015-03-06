import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;


public class Menu {

	
	public static void main(String[] args){
		JFrame frame = new JFrame("Falling Boxes");
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setSize(dim);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		BoxDropper bd = new BoxDropper(frame);
		frame.add(bd);
		frame.setVisible(true);
		
	}
}