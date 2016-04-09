import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

import ryanPossardt.*;
import scottCote.Menu;

public class PongComponents extends JPanel implements ActionListener, KeyListener{
	private Ball ball = new Ball();
	private boolean upPressedPlayerOne, downPressedPlayerOne, upPressedPlayerTwo, downPressedPlayerTwo = false;
	private static final long serialVersionUID = 1L;
	private Paddle player1 = new Paddle(0,100);
	private Paddle player2 = new Paddle(1737,100);
	CollisionDetection collisionDetection = new CollisionDetection();
	Sound sounds = new Sound();
	PowerUpBox powerUpBox = new PowerUpBox();
	private int gameTimer = 1;
	private boolean powerUpModeEnabled;
	
	
	//constructor
	public PongComponents(){
		setBackground(Color.BLACK);
		
		setFocusable(true);
		addKeyListener(this);
		
		Timer timer = new Timer(1000/60, this);
		timer.start();
		
		ball.setBallx(884);
		ball.setBally(350);
		ball.setBallSpeedX(getRandomSpeed());
		ball.setBallSpeedY(getRandomSpeed());
		
		powerUpModeEnabled = Menu.powerUpModeEnabled;
		powerUpBox.setEnabled(false);		//initialize as false, change if needed.
		

	}
	
	//method for moving objects
	public void step(){
		getRandomSpeed();

		//move ball x and y once per step
		ball.setBallx(ball.getBallx() + ball.getBallSpeedX());
		ball.setBally(ball.getBally() + ball.getBallSpeedY());
				
		//player 1 score
		if(collisionDetection.rightWallHit(ball)){
			player1.addGoal();
			resetBall();
			}
		//player 2 score
		if(collisionDetection.leftWallHit(ball)){
			player2.addGoal();
			resetBall();
			}
		//top or bottom wall bounce
		if(collisionDetection.horizontalWallHit(ball)){
			ball.setBallSpeedY(ball.getBallSpeedY()*-1);
		}
		
		//left paddle hit processing
		if(collisionDetection.topHit(ball, player1) || collisionDetection.bottomHit(ball, player1)){
			if (!powerUpModeEnabled){
				ball.setBallSpeedX(ball.getBallSpeedX()*-1 +1);
			}else{
				ball.setBallSpeedX(ball.getBallSpeedX()*-1);
			}
			if (collisionDetection.topHit(ball, player1)){
				ball.setBallSpeedY(collisionDetection.calculateYBallSpeed()*-1);
			}else{
				ball.setBallSpeedY(collisionDetection.calculateYBallSpeed());
			}
			ball.setLastPaddleHit(player1);
			sounds.leftPaddleHitSound();
		}
		
		//right paddle hit processing
		if(collisionDetection.topHit(ball, player2) || collisionDetection.bottomHit(ball, player2)){
			if (!powerUpModeEnabled){
				ball.setBallSpeedX(ball.getBallSpeedX()*-1 -1);
			}else{
				ball.setBallSpeedX(ball.getBallSpeedX()*-1);
			}
			if (collisionDetection.topHit(ball, player2)){
				ball.setBallSpeedY(collisionDetection.calculateYBallSpeed()*-1);
			}else{
				ball.setBallSpeedY(collisionDetection.calculateYBallSpeed());
			}
			ball.setLastPaddleHit(player2);
			sounds.rightPaddleHitSound();
		}
				
		//moving player ones paddle
		//up pressed
		if(upPressedPlayerOne){
			if(player1.getPaddleY()- player1.getPaddleSpeed() > 0){
				player1.setPaddleY(player1.getPaddleY()- player1.getPaddleSpeed()) ;
			}
		}
		//down pressed
		if(downPressedPlayerOne){
			if(player1.getPaddleY() + player1.getPaddleSpeed() + 140 < getHeight()){
				player1.setPaddleY(player1.getPaddleY() + player1.getPaddleSpeed());
			}
		}
		
		//moving player two's paddle
		if(upPressedPlayerTwo){
			if(player2.getPaddleY() - player2.getPaddleSpeed() > 0){
				player2.setPaddleY(player2.getPaddleY() - player2.getPaddleSpeed());
			}
		}
		if(downPressedPlayerTwo){
			if(player2.getPaddleY() + player2.getPaddleSpeed() + 140 < getHeight()){
				player2.setPaddleY(player2.getPaddleY() + player2.getPaddleSpeed());
			}
		}
				
		//repaint to show reflected changes
		repaint();
	}
	
	@Override
	public void paint(Graphics g){
		super.paintComponent(g);
		g.setColor(Color.WHITE);
		Font font = new Font("Bauhaus 93", Font.PLAIN, 60);
		g.setFont(font);
	    g.fillOval((int)ball.getBallx(), (int)ball.getBally(), ball.getBallSize(), ball.getBallSize());

	    //lines down center of court
	    //getWidth()/2 yields 889
	    g.fillRect(884, 0, 10, 100);
	    g.fillRect(884, 150, 10, 100);
	    g.fillRect(884, 300, 10, 100);
	    g.fillRect(884, 450, 10, 100);
	    g.fillRect(884, 600, 10, 100);
	    g.fillRect(884, 750, 10, 100);
	    
	    //score
	    String p1score = Integer.toString(player1.getScore());
	    String p2score = Integer.toString(player2.getScore());
	    g.drawString(p1score, 784, 70);
	    g.drawString(p2score, 974, 70);

	    //player one paddle
	    g.fillRect(player1.getPaddleX() , player1.getPaddleY(), player1.getPaddleSizeX(), player1.getPaddleSizeY());
	    //player two paddle
	    g.fillRect(player2.getPaddleX() , player2.getPaddleY(), player2.getPaddleSizeX(), player2.getPaddleSizeY());
	    

	    if(powerUpModeEnabled && (gameTimer % 1000) == 0){
	    	powerUpBox.setEnabled(true);
	    	}
	    //powerup generator
	    if(powerUpBox.isEnabled()){
	    	//powerUp.setNewLocation();
	    	if(powerUpBox.getPowerUpTimer() < powerUpBox.getPowerUpLength()){
	    		g.drawRect(powerUpBox.getxLocation(), powerUpBox.getyLocation(), 50, 50);
	    		g.fillRect(powerUpBox.getxLocation(), powerUpBox.getyLocation(), 50, 50);
	    		//System.out.println("enabled and timer: " + powerUpBox.getPowerUpTimer());
	    	} else {
	    		powerUpBox.setEnabled(false);
	    		g.clearRect(powerUpBox.getxLocation(), powerUpBox.getyLocation(), 50, 50);
	    		powerUpBox.setPowerUpTimer(0);
	    		powerUpBox.setNewLocation();
	    	}
	    	powerUpBox.setPowerUpTimer(powerUpBox.getPowerUpTimer() + 1);
	    }
	}

	@Override
	public void keyPressed(KeyEvent k) {
		//player one paddle movement
		if(k.getKeyCode() == KeyEvent.VK_W){
			upPressedPlayerOne = true;
		}
		if(k.getKeyCode() == KeyEvent.VK_S){
			downPressedPlayerOne = true;
		}
		
		//player two paddle movement
		if(k.getKeyCode() == KeyEvent.VK_UP){
			upPressedPlayerTwo = true;
		}
		if(k.getKeyCode() == KeyEvent.VK_DOWN){
			downPressedPlayerTwo = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent k) {
		//player one paddle movement
		if(k.getKeyCode() == KeyEvent.VK_W){
			upPressedPlayerOne = false;
		}
		if(k.getKeyCode() == KeyEvent.VK_S){
			downPressedPlayerOne = false;
		}
		
		//player two paddle movement
		if(k.getKeyCode() == KeyEvent.VK_UP){
			upPressedPlayerTwo = false;
		}
		if(k.getKeyCode() == KeyEvent.VK_DOWN){
			downPressedPlayerTwo = false;
		}
	}
	
	//reset ball after score
	public void resetBall(){
		sounds.playScoreSound();
		//hold ball in center before game starts
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//need to move ball randomly off reset
		ball.setBallx(884);
		ball.setBally(350);
		ball.setBallSpeedX(getRandomSpeed());
		ball.setBallSpeedY(getRandomSpeed());
	}
		
	public int getRandomSpeed(){
		double speed = (Math.random()*3)+3;
		long sign = Math.round((Math.random()*2)-1);
		if (sign < 0){
			speed = speed * sign;
		}
		return (int)speed;
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		gameTimer++;
		System.out.println(gameTimer);
		step();
	}
	
}
