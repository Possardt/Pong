import javax.swing.JFrame;
import java.awt.BorderLayout;

import scottCote.*;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		JFrame j = new JFrame("Pong");
		j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		j.setLayout(new BorderLayout());
		
		//This code adds the menu panel and exits/starts game. Starting game
		//breaks the paddle movement :(
		
		Menu m = new Menu();
		while (m.isGameRunning() == false){
			j.add(m);
			j.setSize(1800, 900);
			j.setVisible(true);
		}
		j.remove(m);
		j.repaint();
		
		PongComponents p = new PongComponents();
		j.add(p, BorderLayout.CENTER);
		j.setSize(1800, 900);
		j.setVisible(true);
		p.requestFocusInWindow();
	}

}

