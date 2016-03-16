import javax.swing.JFrame;
import java.awt.BorderLayout;

import scottCote.*;
import danielMarciniec.*;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		JFrame j = new JFrame("Pong");
		j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		j.setLayout(new BorderLayout());
		
		boolean singlePlayer;
		boolean twoPlayer;
		boolean gameStarted = false;
		
		Menu m = new Menu();
		j.add(m, BorderLayout.CENTER);
		j.setSize(1800, 900);
		j.setVisible(true);
		m.requestFocusInWindow();

		while (!gameStarted) {
			singlePlayer = m.isSinglePlayerGameRunning();
			twoPlayer = m.isTwoPlayerGameRunning();
			if (twoPlayer) {
				j.remove(m);
				j.repaint();
				PongComponents p = new PongComponents();
				j.add(p, BorderLayout.CENTER);
				j.setSize(1800, 900);
				j.setVisible(true);
				p.requestFocusInWindow();
				gameStarted = true;
			}
			if (singlePlayer) {
				j.remove(m);
				j.repaint();
				CPUComponents p = new CPUComponents();
				j.add(p, BorderLayout.CENTER);
				j.setSize(1800, 900);
				j.setVisible(true);
				p.requestFocusInWindow();
				gameStarted = true;
			} 
		} 	
	}
}

