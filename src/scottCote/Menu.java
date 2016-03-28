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
	private Ball ballOne = new Ball();
	private Ball ballTwo = new Ball();
	CollisionDetection collisionDetection = new CollisionDetection();

	

	
	public Menu(){
		setSingplePlayerGameRunning(false);
		setTwoPlayerGameRunning(false);
		setBackground(Color.BLACK);
		setFocusable(true);
		addKeyListener(this);
		repaint();
		
		Timer timer = new Timer(1000/60, this);
		timer.start();
		
		ballOne.setBallx(400);
		ballOne.setBally(500);
		ballOne.setBallSpeedX(6);
		ballOne.setBallSpeedY(6);
		
		ballTwo.setBallx(700);
		ballTwo.setBally(300);
		ballTwo.setBallSpeedX(-6);
		ballTwo.setBallSpeedY(-6);
	}
	
	public void step(){
		
		if(collisionDetection.verticalWallHit(ballOne)){
			ballOne.setDirectionballx(ballOne.getDirectionballx() + 1);
		}
		if(collisionDetection.horizontalWallHit(ballOne)){
			ballOne.setDirectionbally(ballOne.getDirectionbally() + 1);
		}
		if(ballOne.getDirectionballx()%2 == 0){
			ballOne.setBallx(ballOne.getBallx() - ballOne.getBallSpeedX());
		}else{
			ballOne.setBallx(ballOne.getBallx() + ballOne.getBallSpeedX());
		}
		if(ballOne.getDirectionbally()%2 == 0){
			ballOne.setBally(ballOne.getBally() - ballOne.getBallSpeedY());
		}else{
			ballOne.setBally(ballOne.getBally() + ballOne.getBallSpeedY());
		}
		
		if(collisionDetection.verticalWallHit(ballTwo)){
			ballTwo.setDirectionballx(ballTwo.getDirectionballx() + 1);
		}
		if(collisionDetection.horizontalWallHit(ballTwo)){
			ballTwo.setDirectionbally(ballTwo.getDirectionbally() + 1);
		}
		if(ballTwo.getDirectionballx()%2 == 0){
			ballTwo.setBallx(ballTwo.getBallx() - ballTwo.getBallSpeedX());
		}else{
			ballTwo.setBallx(ballTwo.getBallx() + ballTwo.getBallSpeedX());
		}
		if(ballTwo.getDirectionbally()%2 == 0){
			ballTwo.setBally(ballTwo.getBally() - ballTwo.getBallSpeedY());
		}else{
			ballTwo.setBally(ballTwo.getBally() + ballTwo.getBallSpeedY());
		}
		
		if (collisionDetection.ballOnBallHit(ballOne, ballTwo)){
			ballOne.setDirectionballx(ballOne.getDirectionballx() + 1);
			ballOne.setDirectionbally(ballOne.getDirectionbally() + 1);
			ballTwo.setDirectionballx(ballTwo.getDirectionballx() + 1);
			ballTwo.setDirectionbally(ballTwo.getDirectionbally() + 1);
		}
		
		repaint();
	}
	
	@Override
	public void paint(Graphics g){
		super.paintComponent(g);
		g.setColor(Color.WHITE);
		Font titleFont = new Font("Bauhaus 93", Font.BOLD, 150);
		Font byFont = new Font("Bauhaus 93", Font.PLAIN, 40);
		Font controlFont = new Font("Bauhaus 93", Font.BOLD, 50);
		g.setFont(titleFont);
	    String title = "PONG";
	    String byOne = "by: Eric, Scott, Dan, and Ryan";
	    String byTwo = "CSE 2102";
	    String newSinglePlayerGame = "(N) New Two Player Game";
	    String newTwoPlayerGame = "(S) New Single Player Game";
	    String exitApp = "(X) Exit Game";
	    g.drawString(title, 140, 140);
	    g.setFont(byFont);
	    g.drawString(byOne, 140, 215);
	    g.drawString(byTwo, 140, 250);
	    g.setFont(controlFont);
	    g.drawString(newSinglePlayerGame, 140, 340);
	    g.drawString(newTwoPlayerGame, 140, 400);
	    g.drawString(exitApp, 140, 460);
	    
	    g.fillOval((int)ballOne.getBallx(), (int)ballOne.getBally(), 50, 50);
	    g.fillOval((int)ballTwo.getBallx(), (int)ballTwo.getBally(), 50, 50);
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
	
	public void actionPerformed(ActionEvent arg0) {
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

}
