package scottCote;

import java.awt.Color;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;
import java.awt.Font;
import java.awt.Graphics;

public class Menu extends JPanel implements KeyListener {
	
	private static final long serialVersionUID = 1L;
	private volatile boolean isSinglePlayerGameRunning;
	private volatile boolean isTwoPlayerGameRunning;

	public Menu(){
		setSingplePlayerGameRunning(false);
		setTwoPlayerGameRunning(false);
		setBackground(Color.BLACK);
		setFocusable(true);
		addKeyListener(this);
		repaint();
	}
	
	@Override
	public void paint(Graphics g){
		super.paintComponent(g);
		g.setColor(Color.WHITE);
		Font titleFont = new Font("Bauhaus 93", Font.PLAIN, 100);
		Font byFont = new Font("Bauhaus 93", Font.PLAIN, 40);
		Font controlFont = new Font("Bauhaus 93", Font.PLAIN, 60);
		g.setFont(titleFont);
	    String title = "PONG";
	    String byOne = "by: Eric, Scott, Dan, and Ryan";
	    String byTwo = "CSE 2102";
	    String newSinglePlayerGame = "(N) New Two Player Game";
	    String newTwoPlayerGame = "(S) New Single Player Game";
	    String exitApp = "(X) Exit Game";
	    g.drawString(title, 140, 140);
	    g.setFont(byFont);
	    g.drawString(byOne, 140, 210);
	    g.drawString(byTwo, 140, 250);
	    g.setFont(controlFont);
	    g.drawString(newSinglePlayerGame, 140, 340);
	    g.drawString(newTwoPlayerGame, 140, 400);
	    g.drawString(exitApp, 140, 460);
	}
	
	@Override
	public void keyPressed(KeyEvent ke) {
		if(ke.getKeyCode() == KeyEvent.VK_N){
			setTwoPlayerGameRunning(true);
		}
		if(ke.getKeyCode() == KeyEvent.VK_X){
			System.exit(0);
		}
		if(ke.getKeyCode() == KeyEvent.VK_S){
			setSingplePlayerGameRunning(true);
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

	public boolean isSinglePlayerGameRunning() {
		return isSinglePlayerGameRunning;
	}

	public void setSingplePlayerGameRunning(boolean status) {
		this.isSinglePlayerGameRunning = status;
	}
	
	public boolean isTwoPlayerGameRunning() {
		return isTwoPlayerGameRunning;
	}

	public void setTwoPlayerGameRunning(boolean status) {
		this.isTwoPlayerGameRunning = status;
	}

}
