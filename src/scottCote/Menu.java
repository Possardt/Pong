package scottCote;

import java.awt.Color;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;
import javax.swing.Timer;

import java.awt.Font;
import java.awt.Graphics;

import ryanPossardt.*;

public class Menu extends JPanel implements ActionListener, KeyListener {
	
	private static final long serialVersionUID = 1L;
	private volatile boolean isSinglePlayerGameRunning;
	private volatile boolean isTwoPlayerGameRunning;
	public static boolean powerUpModeEnabled;
	private Ball ballOne = new Ball();
	private int ballSize = 50;
	CollisionDetection collisionDetection = new CollisionDetection();
	Sound sound = new Sound();

	

	
	public Menu(){
		//sound.playMenuSound();
		setSingplePlayerGameRunning(false);
		setTwoPlayerGameRunning(false);
		setBackground(Color.BLACK);
		setFocusable(true);
		addKeyListener(this);
		repaint();
		
		Timer timer = new Timer(1000/60, this);
		timer.start();
		
		ballOne.setBallx(700);
		ballOne.setBally(700);
		ballOne.setBallSpeedX(-5);
		ballOne.setBallSpeedY(6);
		
	}
	
	public void step(){
		
		//move ball one
		ballOne.setBallx(ballOne.getBallx() + ballOne.getBallSpeedX());
		ballOne.setBally(ballOne.getBally() + ballOne.getBallSpeedY());

		if (ballSize <500 ) {
			if(collisionDetection.verticalWallHit(ballOne, ballSize)){
				sound.playWallHitSound();
				ballOne.setBallSpeedX(ballOne.getBallSpeedX()*-1);
				ballSize += 10;
				ballOne.setDiameter(ballSize);
			}
			if(collisionDetection.horizontalWallHit(ballOne, ballSize)){
				sound.playWallHitSound();
				ballOne.setBallSpeedY(ballOne.getBallSpeedY()*-1);
				ballSize +=10;
				ballOne.setDiameter(ballSize);
			}
		}
		
		repaint();
	}
	
	@Override
	public void paint(Graphics g){
		super.paintComponent(g);
		Font titleFont = new Font("Bauhaus 93", Font.BOLD, 150);
		Font byFont = new Font("Bauhaus 93", Font.PLAIN, 40);
		Font controlFont = new Font("Bauhaus 93", Font.BOLD, 50);
		Font powerUpXFont = new Font("Bauhaus 93", Font.PLAIN, 150);
		g.setFont(titleFont);
	    String title = "PONG";
	    String byOne = "by: Eric, Scott, Dan, and Ryan";
	    String byTwo = "CSE 2102";
	    String newSinglePlayerGame = "(N) New Two Player Game";
	    String newTwoPlayerGame = "(S) New Single Player Game";
	    String exitApp = "(X) Exit Game";
	    String powerUp = "(P) POWERUP MODE";
	    String powerUpX = "X";
	    g.setColor(Color.RED);
	    g.fillOval((int)ballOne.getBallx(), (int)ballOne.getBally(), ballSize ,ballSize);
	    g.setColor(Color.WHITE);
	    g.drawString(title, 140, 140);
	    g.setFont(byFont);
	    g.drawString(byOne, 140, 215);
	    g.drawString(byTwo, 140, 250);
	    g.setFont(controlFont);
	    g.drawString(newSinglePlayerGame, 140, 340);
	    g.drawString(newTwoPlayerGame, 140, 400);
	    g.drawString(exitApp, 140, 460);
	    g.drawString(powerUp, 1250,140);
	    if (!powerUpModeEnabled){
	    	g.setFont(powerUpXFont);
	    	g.drawString(powerUpX, 1400, 160);
	    }


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
		if(ke.getKeyCode() == KeyEvent.VK_P){
			if (!powerUpModeEnabled){
				setPowerUpMode(true);
			}else{
				setPowerUpMode(false);
			}

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
	
	public void actionPerformed(ActionEvent arg0) {
		if (ballSize <500 && !isSinglePlayerGameRunning && !isTwoPlayerGameRunning)
			step();
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
	
	private void setPowerUpMode(boolean status){
		this.powerUpModeEnabled = status;
	}
	
}
