import javax.swing.JFrame;
import java.awt.BorderLayout;

import scottCote.*;
import danielMarciniec.*;
import ericVanHeel.GameOver;
import ericVanHeel.Pause;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		start();
	}
	public static void gameRunning(PongComponents p, Pause pm, JFrame j, Menu m, GameOver go){
		while (!p.getIsPaused()){
			if (p.getGameOver()){
				go.setGameOver(true);
				while(!go.getRematch()){
					if(go.getMenu()){
						j.dispose();
						start();
					}
				}
			}
			//System.out.println("Game Running");
			//pause shield will not appear unless there is a system function here...
			System.out.print("");
		}
		System.out.println("Game Paused");
		pm.setGamePaused(true);
		pm.setVisible(true);
		pm.setFocusable(true);
		while(!pm.getResume()){
			if(pm.getMenu()){
				j.dispose();
				start();
				System.out.println("Return to Main");
			}
		}
		pm.setGamePaused(false);
		p.requestFocusInWindow();
		p.resumeGame();
		gameRunning(p, pm, j, m, go);
	}
	
	public static void start(){
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
		GameOver go = new GameOver(false);
		Pause pm = new Pause(false);

		while (!gameStarted) {
			singlePlayer = m.getSinglePlayerGameStatus();
			twoPlayer = m.getTwoPlayerGameStatus();
			if (twoPlayer) {
				j.remove(m);
				j.repaint();
				PongComponents p = new PongComponents();
				j.add(p, BorderLayout.CENTER);
				j.setSize(1800, 900);
				j.setVisible(true);
				p.requestFocusInWindow();
				gameStarted = true;
				gameRunning(p, pm, j, m, go);
			}
			if (singlePlayer) {
				j.remove(m);
				j.repaint();
				CPUComponents cp = new CPUComponents();
				j.add(cp, BorderLayout.CENTER);
				j.setSize(1800, 900);
				j.setVisible(true);
				cp.requestFocusInWindow();
				gameStarted = true;
			} 
		}
	}
}

