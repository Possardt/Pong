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
	double phi;
	double thetaOne;
	double thetaTwo;
	double vOne;
	double vTwo;
	double vPrimeXOne;
	double vPrimeYOne;
	double vPrimeXTwo;
	double vPrimeYTwo;
	boolean ballsCleared;
	boolean ballCollision;
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
		
		ballOne.setBallx(300);
		ballOne.setBally(300);
		ballOne.setBallSpeedX(6);
		ballOne.setBallSpeedY(8);
		
		ballTwo.setBallx(730);
		ballTwo.setBally(730);
		ballTwo.setBallSpeedX(-6);
		ballTwo.setBallSpeedY(-8);
	}
	
	public void step(){
		
		//move ball one
		ballOne.setBallx(ballOne.getBallx() + ballOne.getBallSpeedX());
		ballOne.setBally(ballOne.getBally() + ballOne.getBallSpeedY());
		//move ball two
		ballTwo.setBallx(ballTwo.getBallx() + ballTwo.getBallSpeedX());
		ballTwo.setBally(ballTwo.getBally() + ballTwo.getBallSpeedY());

		//if ball on ball collision occurs after move
		collisionDetection.setBallsClear(ballOne, ballTwo);
		if (collisionDetection.getBallsClear() && collisionDetection.ballOnBallHit(ballOne, ballTwo)){
			System.out.println("in a collision event!!!!!");
			phi = collisionDetection.calculateAngleBetweenBalls(ballOne, ballTwo);
			thetaOne = ballOne.getTravelAngle();
			System.out.println("ThetaOne: " + thetaOne);
			thetaTwo = ballTwo.getTravelAngle();
			System.out.println("ThetaTwo: " + thetaTwo);
			vOne = ballOne.getSpeedMagnitude();
			vTwo = ballTwo.getSpeedMagnitude();
			vPrimeXOne = vTwo*Math.cos(thetaTwo - phi)*Math.cos(phi) + vOne*Math.sin(thetaOne - phi)*Math.cos(phi + Math.PI/2);
			vPrimeYOne = vTwo*Math.sin(thetaTwo - phi)*Math.cos(phi) + vOne*Math.sin(thetaOne - phi)*Math.sin(phi + Math.PI/2);
			vPrimeXTwo = vOne*Math.cos(thetaOne - phi)*Math.cos(phi) + vTwo*Math.sin(thetaTwo - phi)*Math.cos(phi + Math.PI/2);
			vPrimeYTwo = vOne*Math.sin(thetaOne - phi)*Math.cos(phi) + vTwo*Math.sin(thetaTwo - phi)*Math.sin(phi + Math.PI/2);
			
			System.out.println("ball one speed: " + vOne);
			System.out.println("ball two speed: " + vTwo);
			
			System.out.println("b1x: " + ballOne.getBallSpeedX());
			System.out.println("b1xP: " + vPrimeXOne);
			if (ballOne.getBallSpeedX() * vPrimeXOne <= 0){
				System.out.println("Ball One Direction X Change");
				ballOne.setBallSpeedX((float) (vPrimeXOne * -1));
			}
			ballOne.setBallSpeedX((float) vPrimeXOne);
			
			System.out.println("b1y: " + ballOne.getBallSpeedY());
			System.out.println("b1yP: " + vPrimeYOne);
			if (ballOne.getBallSpeedY() * vPrimeYOne < 0){
				System.out.println("Ball One Direction Y Change");
				ballOne.setBallSpeedY((float) (vPrimeYOne * -1));
			}
			ballOne.setBallSpeedX((float) vPrimeYOne);
			
			System.out.println("b2x: " + ballTwo.getBallSpeedX());
			System.out.println("b2xP: " + vPrimeXTwo);
			if (ballTwo.getBallSpeedX() * vPrimeXTwo <= 0){
				System.out.println("Ball Two Direction X Change");
				ballTwo.setBallSpeedX((float) (vPrimeXTwo * -1));
			}
			ballOne.setBallSpeedY((float) vPrimeXTwo);
			
			System.out.println("b2y: " + ballTwo.getBallSpeedY());
			System.out.println("b2yP: " + vPrimeYTwo);
			if (ballTwo.getBallSpeedY() * vPrimeYTwo <= 0){
				System.out.println("Ball Two Direction Y Change");
				ballTwo.setBallSpeedY((float) (vPrimeYTwo * -1));
			}
			ballOne.setBallSpeedY((float) vPrimeYTwo);	
			
			System.out.println(ballOne.getSpeedMagnitude());
			System.out.println(ballTwo.getSpeedMagnitude());
			
		}else{	//if ball on wall collision occurs
			if(collisionDetection.verticalWallHit(ballOne)){
				ballOne.setBallSpeedX(ballOne.getBallSpeedX()*-1);
			}
			if(collisionDetection.horizontalWallHit(ballOne)){
				ballOne.setBallSpeedY(ballOne.getBallSpeedY()*-1);
			}

			if(collisionDetection.verticalWallHit(ballTwo)){
				ballTwo.setBallSpeedX(ballTwo.getBallSpeedX()*-1);
			}
			if(collisionDetection.horizontalWallHit(ballTwo)){
				ballTwo.setBallSpeedY(ballTwo.getBallSpeedY()*-1);
			}
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
	    g.setColor(Color.RED);
	    g.fillOval((int)ballOne.getBallx(), (int)ballOne.getBally(), 50, 50);
	    g.setColor(Color.BLUE);
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
