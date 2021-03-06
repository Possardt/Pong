package danielMarciniec;

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

public class CPUComponents extends JPanel implements ActionListener, KeyListener{
	private Ball ball = new Ball();
	private boolean upPressedPlayerOne, downPressedPlayerOne, upPressedPlayerTwo, downPressedPlayerTwo, isPaused, gameOver = false;
	private static final long serialVersionUID = 1L;
	private Paddle player1 = new Paddle(0,350);
	private Paddle player2 = new Paddle(1737,350);
	CollisionDetection collisionDetection = new CollisionDetection();
	Sound sounds = new Sound();
	PowerUpBox powerUpBox = new PowerUpBox();
	PowerUp powerUp = new PowerUp();
	private int gameTimer = 1;
	private boolean powerUpModeEnabled;
	float btempx, btempy;
	
	
	//constructor
	public CPUComponents(){
		setBackground(Color.BLACK);
		
		setFocusable(true);
		addKeyListener(this);
		
		Timer timer = new Timer(1000/70, this);
		timer.start();
		
		//initial ball speed and location random
		ball.setBallx(getRandomLocation(650, 1050));
		ball.setBally(getRandomLocation(50,750));
		ball.setBallSpeedX(getRandomXSpeed());
		ball.setBallSpeedY(getRandomSpeed());
		
		powerUpModeEnabled = Menu.powerUpModeEnabled;
		powerUpBox.setEnabled(false);		//initialize as false, change if needed.
	}
	
	//method for moving objects
	public void step(){
		
		//move ball x and y once per step
		ball.setBallx(ball.getBallx() + ball.getBallSpeedX());
		ball.setBally(ball.getBally() + ball.getBallSpeedY());
		
		//player 1 score
		if(collisionDetection.rightWallHit(ball)){
			player1.addGoal();
			if(player1.getScore() == 5){
				gameOver = true;
				sounds.playGameOverSound();
				try {
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			resetBall();
			}
		//player 2 score
		if(collisionDetection.leftWallHit(ball)){
			player2.addGoal();
			if(player2.getScore() == 5){
				gameOver = true;
				sounds.playGameOverSound();
				try {
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
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
				ball.setBallSpeedY(collisionDetection.calculateYBallSpeed() * -1);
			}else{
				ball.setBallSpeedY(collisionDetection.calculateYBallSpeed());
			}
			ball.setLastPaddleHit(player1);
			ball.setNextPaddleHit(player2);
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
			ball.setNextPaddleHit(player1);
			sounds.rightPaddleHitSound();
		}
		
		startMove();
		endMove();
		
		//moving player's paddle
		//up pressed
		if(upPressedPlayerOne){
			if(player1.getPaddleY()- player1.getPaddleSpeed() > 0){
				player1.setPaddleY(player1.getPaddleY()- player1.getPaddleSpeed()) ;
			}
		}
		//down pressed
		if(downPressedPlayerOne){
			if(player1.getPaddleY() + player1.getPaddleSpeed() + player1.getPaddleSizeY() < getHeight()){
				player1.setPaddleY(player1.getPaddleY() + player1.getPaddleSpeed());
			}
		}
		
		//moving CPU's paddle
		if(upPressedPlayerTwo){
			if(player2.getPaddleY() - player2.getPaddleSpeed() > 0){
				player2.setPaddleY(player2.getPaddleY() - player2.getPaddleSpeed());
			}
		}
		if(downPressedPlayerTwo){
			if(player2.getPaddleY() + player2.getPaddleSpeed() + player2.getPaddleSizeY() < getHeight()){
				player2.setPaddleY(player2.getPaddleY() + player2.getPaddleSpeed());
			}
		}
		
		if(isPaused){
			ball.setBallSpeedX(0);
			ball.setBallSpeedY(0);
			//System.out.println(btempx+", "+btempy);
		}
		
		//checking to see if ball hit someone's paddle
		//collisionDetection.hitPaddle(ball, player1);
		//collisionDetection.hitPaddle(ball, player2);
		
		
		//repaint to show reflected changes
		repaint();
	}
	
	@Override
	public void paint(Graphics g){
		super.paintComponent(g);
		g.setColor(Color.WHITE);
		Font font = new Font("Bauhaus 93", Font.PLAIN, 60);
		g.setFont(font);
	    g.fillOval((int)ball.getBallx(), (int)ball.getBally(), ball.getDiameter(), ball.getDiameter());

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
	    	powerUp.undoPowerUp(ball);
	    	}
	    //powerup generator
	    if(powerUpBox.isEnabled()){

	    	//powerUp.setNewLocation();
	    	if(powerUpBox.getPowerUpTimer() < powerUpBox.getPowerUpLength()){
	    		g.drawRect(powerUpBox.getxLocation(), powerUpBox.getyLocation(), 50, 50);
	    		g.fillRect(powerUpBox.getxLocation(), powerUpBox.getyLocation(), 50, 50);
	    		g.setColor(Color.BLACK);
	    		String powerUpLetter = "P";
	    		g.drawString(powerUpLetter, powerUpBox.getCenterX() - 20, powerUpBox.getCenterY() + 20);
	    		g.setColor(Color.WHITE);
	    		//System.out.println("enabled and timer: " + powerUpBox.getPowerUpTimer());
	    	} else {
	    		g.clearRect(powerUpBox.getxLocation(), powerUpBox.getyLocation(), 50, 50);
	    		disablePowerUpBox();
	    	}
	    	//detection for collision between ball and power up box.
			if(collisionDetection.powerUpBoxHit(ball, powerUpBox)){
				sounds.playPowerUpSound();
				g.clearRect(powerUpBox.getxLocation(), powerUpBox.getyLocation(), 50, 50);
				disablePowerUpBox();
				powerUp.getRandomPowerUp(ball);
			}
	    	powerUpBox.setPowerUpTimer(powerUpBox.getPowerUpTimer() + 1);
	    }
	}

	//player paddle movement
	@Override
	public void keyPressed(KeyEvent k) {
		if(k.getKeyCode() == KeyEvent.VK_W || k.getKeyCode() == KeyEvent.VK_UP){
			upPressedPlayerOne = true;
		}
		if(k.getKeyCode() == KeyEvent.VK_S || k.getKeyCode() == KeyEvent.VK_DOWN){
			downPressedPlayerOne = true;
		}
		if(k.getKeyCode() == KeyEvent.VK_ESCAPE){
			isPaused = true;
			btempx = ball.getBallSpeedX();
			btempy = ball.getBallSpeedY();
		}
	}
	
	@Override
	public void keyReleased(KeyEvent k) {
		if(k.getKeyCode() == KeyEvent.VK_W || k.getKeyCode() == KeyEvent.VK_UP){
			upPressedPlayerOne = false;
		}
		if(k.getKeyCode() == KeyEvent.VK_S || k.getKeyCode() == KeyEvent.VK_DOWN){
			downPressedPlayerOne = false;
		}
	}
	
	//CPU paddle movement
	public void startMove() {
		float delta = ball.getBally() - player2.getPaddleCenterY();
		double speed = Math.sqrt((ball.getBallSpeedX()*ball.getBallSpeedX()) + (ball.getBallSpeedY()*ball.getBallSpeedY())) + Math.abs(delta) - player2.getPaddleSpeed();
		if(delta < 0) {speed = -1*speed;}
		if(ball.getBallSpeedY() < 0) {	//ball moving up
			if(ball.getBallSpeedX() < 0){	//ball moving left
				if(speed < -1*player2.getPaddleSizeY()/2 && player2.getPaddleY() > 300) {upPressedPlayerTwo = true;}
				if(speed > 1.25*player2.getPaddleSizeY()/2 && player2.getPaddleY() < 600 - player2.getPaddleSizeY()) {downPressedPlayerTwo = true;}
			}else{	//ball moving right
				if(speed < .15*player2.getPaddleSizeY()/2 && player2.getPaddleY() > 0) {upPressedPlayerTwo = true;}
				if(speed > player2.getPaddleSizeY()/2 && player2.getPaddleY() < 900 - player2.getPaddleSizeY()) {downPressedPlayerTwo = true;}
			}
		}else{	//ball moving down
			if(ball.getBallSpeedX() < 0){	//ball moving left
				if(speed < -1.25*player2.getPaddleSizeY()/2 && player2.getPaddleY() > 300) {upPressedPlayerTwo = true;}
				if(speed > player2.getPaddleSizeY()/2 && player2.getPaddleY() < 600 - player2.getPaddleSizeY()) {downPressedPlayerTwo = true;}
			}else{	//ball moving right
				if(speed < -1*player2.getPaddleSizeY()/2 && player2.getPaddleY() > 0) {upPressedPlayerTwo = true;}
				if(speed > -.15*player2.getPaddleSizeY()/2 && player2.getPaddleY() < 900 - player2.getPaddleSizeY()) {downPressedPlayerTwo = true;}
			}
		}
		if(ball.getBallSpeedX() < 0){	//ball moving left
			if(player2.getPaddleY() < 300) {downPressedPlayerTwo = true;}	//paddle near top
			if(player2.getPaddleY() > 600 - player2.getPaddleSizeY()){upPressedPlayerTwo = true;}	//paddle near bottom
		}
	}
	
	public void endMove() {
		if(player2.getPaddleY() <= 0) {upPressedPlayerTwo = false;}	//paddle at top
		if(player2.getPaddleY() >= 900 - player2.getPaddleSizeY()){downPressedPlayerTwo = false;}	//paddle at bottom
		float delta = ball.getBally() - player2.getPaddleCenterY();
		double speed = Math.sqrt((ball.getBallSpeedX()*ball.getBallSpeedX()) + (ball.getBallSpeedY()*ball.getBallSpeedY())) + Math.abs(delta) - player2.getPaddleSpeed();
		if(delta < 0) {speed = -1*speed;}
		if(ball.getBallSpeedY() < 0){	//ball moving up
			if(ball.getBallSpeedX() < 0){	//ball moving left
				if(speed > .5*player2.getPaddleSizeY()/2 || player2.getPaddleY() <= 300) {upPressedPlayerTwo = false;}
				if(speed < player2.getPaddleSizeY()/2 || player2.getPaddleY() >= 600 - player2.getPaddleSizeY()) {downPressedPlayerTwo = false;}
			}else{	//ball moving right
				if(speed > .95*player2.getPaddleSizeY()/2) {upPressedPlayerTwo = false;}
				if(speed <= player2.getPaddleSizeY()/2) {downPressedPlayerTwo = false;}
			}
		}else{	//ball moving down
			if(ball.getBallSpeedX() < 0){	//ball moving left
				if(speed > -1*player2.getPaddleSizeY()/2 || player2.getPaddleY() <= 300) {upPressedPlayerTwo = false;}
				if(speed < .5*player2.getPaddleSizeY()/2 || player2.getPaddleY() >= 600 - player2.getPaddleSizeY()) {downPressedPlayerTwo = false;}
			}else{	//ball moving right
				if(speed >= -1*player2.getPaddleSizeY()/2) {upPressedPlayerTwo = false;}
				if(speed < -.95*player2.getPaddleSizeY()/2) {downPressedPlayerTwo = false;}
			}
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
		//need to set and move ball randomly off reset
		ball.setBallx(getRandomLocation(650, 1050));
		ball.setBally(getRandomLocation(50,750));
		ball.setBallSpeedX(getRandomXSpeed());
		ball.setBallSpeedY(getRandomSpeed());
	}
	
	private int getRandomSpeed(){
		double speed = (Math.random()*5)+3;
		long sign = Math.round((Math.random()*2)-1);
		if (sign < 0){
			speed = speed * sign;
		}
		return (int)speed;
	}
	
	private int getRandomXSpeed(){
		double speed = (Math.random() * 6) + 3;
		if(speed < 5)
			return getRandomXSpeed();
		return (int)speed;
	}
	
	private int getRandomLocation(int min, int max){
		double location = (Math.random()*(max-min)) + min;
		System.out.println(location);
		return (int)location;
	}
	
	public void resumeGame(){
		isPaused = false;
		ball.setBallSpeedX(btempx);
		ball.setBallSpeedY(btempy);
		//System.out.println("Game Resumed");
	}
	
	public boolean getIsPaused(){
		return isPaused;
	}
	public boolean getGameOver(){
		return gameOver;
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		gameTimer++;
		//System.out.println(gameTimer);
		step();
	}
	
	public void disablePowerUpBox(){
		powerUpBox.setEnabled(false);
		powerUpBox.setPowerUpTimer(0);
		powerUpBox.setNewLocation();
	}
	
}