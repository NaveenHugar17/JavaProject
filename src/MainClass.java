import java.awt.Color;

import javax.swing.JFrame;

public class MainClass {

	public static void main(String[] args) {
		JFrame frame=new JFrame();
		GameArea game=new GameArea();
		
		frame.setBounds(0, 0, 700, 600);
		frame.setBackground(Color.GRAY);
		frame.setVisible(true);
		frame.setTitle("Snake Game");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.add(game);
	}

}
