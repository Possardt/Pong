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
	private volatile boolean singlePlayerGameEnabled;
	private volatile boolean twoPlayerGameEnabled;
	public static boolean powerUpModeEnabled;
	private Ball ballOne = new Ball();
	private int ballSize = 500;
	CollisionDetection collisionDetection = new CollisionDetection();
	Sound sound = new Sound();
	
	public Menu(){
		//sound.playMenuSound();
		setSinglePlayerGameStatus(false);
		setTwoPlayerGameStatus(false);
		setBackground(Color.BLACK);
		setFocusable(true);
		addKeyListener(this);
		repaint();
		
		Timer timer = new Timer(1000/60, this);
		timer.start();
		
		ballOne.setBallx(200);
		ballOne.setBally(200);
		ballOne.setBallSpeedX(-8);
		ballOne.setBallSpeedY(3);
	}
	
	public void step(){
		
		ballOne.setBallx(ballOne.getBallx() + ballOne.getBallSpeedX());
		ballOne.setBally(ballOne.getBally() + ballOne.getBallSpeedY());

		if (ballSize >50 ) {
			if(collisionDetection.verticalWallHit(ballOne, ballSize)){
				sound.playWallHitSound();
				ballOne.setBallSpeedX(ballOne.getBallSpeedX()*-1);
				ballSize -= 15;
				ballOne.setDiameter(ballSize);
			}
			if(collisionDetection.horizontalWallHit(ballOne, ballSize)){
				sound.playWallHitSound();
				ballOne.setBallSpeedY(ballOne.getBallSpeedY()*-1);
				ballSize -=15;
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
	    String title = "PONG";
	    String byOne = "by: Eric, Scott, Dan, and Ryan";
	    String byTwo = "CSE 2102";
	    String newSinglePlayerGame = "(N) New Two Player Game";
	    String newTwoPlayerGame = "(S) New Single Player Game";
	    String exitApp = "(X) Exit Game";
	    String powerUp = "(P) POWERUP MODE";
	    String powerUpEnabled = "POWER UP MODE ENABLED";

	    g.setColor(Color.RED);
	    g.fillOval((int)ballOne.getBallx(), (int)ballOne.getBally(), ballSize ,ballSize);
	    
		g.setFont(titleFont);
	    g.setColor(Color.WHITE);
	    g.drawString(title, 140, 140);
	    
	    g.setFont(byFont);
	    g.drawString(byOne, 140, 215);
	    g.drawString(byTwo, 140, 250);
	    
	    g.setFont(controlFont);
	    g.drawString(newSinglePlayerGame, 140, 340);
	    g.drawString(newTwoPlayerGame, 140, 400);
	    g.drawString(exitApp, 140, 460);
	    
	    g.setColor(Color.BLACK);
	    g.drawString(powerUp, 700,550);
	    
	    if (powerUpModeEnabled){
	    	g.setColor(Color.WHITE);
	    	g.drawString(powerUpEnabled, 1050, 100);
	    }


	}
	
	
	@Override
	public void keyPressed(KeyEvent ke) {
		if(ke.getKeyCode() == KeyEvent.VK_N){
			setTwoPlayerGameStatus(true);
		}
		if(ke.getKeyCode() == KeyEvent.VK_X){
			System.exit(0);
		}
		if(ke.getKeyCode() == KeyEvent.VK_S){
			setSinglePlayerGameStatus(true);
		}
		if(ke.getKeyCode() == KeyEvent.VK_P){
			if (!powerUpModeEnabled){
				setPowerUpMode(true);
				sound.playPowerUpSound();
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
		if (ballSize >50 && !singlePlayerGameEnabled && !twoPlayerGameEnabled)
			step();
	}

	public boolean getSinglePlayerGameStatus() {
		return singlePlayerGameEnabled;
	}

	public void setSinglePlayerGameStatus(boolean status) {
		this.singlePlayerGameEnabled = status;
	}
	
	public boolean getTwoPlayerGameStatus() {
		return twoPlayerGameEnabled;
	}

	public void setTwoPlayerGameStatus(boolean status) {
		this.twoPlayerGameEnabled = status;
	}
	
	private void setPowerUpMode(boolean status){
		this.powerUpModeEnabled = status;
	}
	
}
