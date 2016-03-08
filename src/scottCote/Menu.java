package scottCote;

import java.awt.Color;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;
import java.awt.Font;
import java.awt.Graphics;

public class Menu extends JPanel implements KeyListener {
	
	private static final long serialVersionUID = 1L;
	private boolean isGameRunning;

	public Menu(){
		setGameRunning(false);
		setBackground(Color.BLACK);
		setFocusable(true);
		addKeyListener(this);
		repaint();
	}
	
	@Override
	public void paint(Graphics g){
		super.paintComponent(g);
		g.setColor(Color.WHITE);
		Font font = new Font("Times New Roman", Font.PLAIN, 60);
		g.setFont(font);
	    String title = "PONG - Classic";
	    String by = "by: Eric, Scott, Dan, and Ryan";
	    String newGame = "New Game (N)";
	    String exitApp = "Exit Game (X)";
	    g.drawString(title, 140, 140);
	    g.drawString(by, 140, 210);
	    g.drawString(newGame, 140, 280);
	    g.drawString(exitApp, 140, 350);
	}
	
	@Override
	public void keyPressed(KeyEvent ke) {
		if(ke.getKeyCode() == KeyEvent.VK_N){
			setGameRunning(true);
		}
		if(ke.getKeyCode() == KeyEvent.VK_X){
			System.exit(0);
		}
	}
	
	@Override
	public void keyReleased(KeyEvent ke) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyTyped(KeyEvent ke) {
		// TODO Auto-generated method stub
	}

	public boolean isGameRunning() {
		return isGameRunning;
	}

	public void setGameRunning(boolean status) {
		this.isGameRunning = status;
	}

}
