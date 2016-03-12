import javax.swing.JFrame;
import java.awt.BorderLayout;

import scottCote.*;
import danielMarciniec.*;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		JFrame j = new JFrame("Pong");
		j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		j.setLayout(new BorderLayout());
		
		Menu m = new Menu();
		while (m.isTwoPlayerGameRunning() == false && m.isSinglePlayerGameRunning() == false){
			j.add(m, BorderLayout.CENTER);
			j.setSize(1800, 900);
			j.setVisible(true);
			m.requestFocusInWindow();
			
		}
		j.remove(m);
		j.repaint();
		
		if (m.isTwoPlayerGameRunning()) {
			PongComponents p = new PongComponents();
			j.add(p, BorderLayout.CENTER);
			j.setSize(1800, 900);
			j.setVisible(true);
			p.requestFocusInWindow();
		}
		if (m.isSinglePlayerGameRunning()) {
			CPUComponents p = new CPUComponents();
			j.add(p, BorderLayout.CENTER);
			j.setSize(1800, 900);
			j.setVisible(true);
			p.requestFocusInWindow();
		}
		
	}

}

