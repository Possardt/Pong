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
	PowerUp powerUp = new PowerUp();
	private int gameTimer = 1;
	
	//constructor
	public PongComponents(){
		setBackground(Color.BLACK);
		
		setFocusable(true);
		addKeyListener(this);
		
		Timer timer = new Timer(1000/60, this);
		timer.start();

	}
	
	//method for moving objects
	public void step(){
		//moving the ball on the court
		if(ball.getBallx() >= 1726){
			player1.addGoal();
			ball.setDirectionballx(ball.getDirectionballx() + 1);
			resetBall();
			}
		if(ball.getBallx() <= 0){
			player2.addGoal();
			ball.setDirectionballx(ball.getDirectionballx() + 1);
			resetBall();
			}
		
		if(ball.getBally() >= 793 || ball.getBally() <= 0){
			ball.setDirectionbally(ball.getDirectionbally() + 1);
		}
		//System.out.println("Player one x: "+ player1.getPaddleX() +", player one y: "+ player1.getPaddleY() +" X: " + ball.getBallx() + " , Y: " + ball.getBally());
		//System.out.println("Player two x: " + player2.getPaddleX() + ", Player two y: " + player2.getPaddleY());
		if(ball.getDirectionballx()%2 == 0){
			ball.setBallx(ball.getBallx() - ball.getBallSpeedX());
		}else{
			ball.setBallx(ball.getBallx() + ball.getBallSpeedX());
		}
		
		if(ball.getDirectionbally()%2 == 0){
			ball.setBally(ball.getBally() - ball.getBallSpeedY());
		}else{
			ball.setBally(ball.getBally() + ball.getBallSpeedY());
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
		
		//checking to see if ball hit someones paddle
		collisionDetection.hitPaddle(ball, player1);
		collisionDetection.hitPaddle(ball, player2);
		
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
	    g.drawRect(884, 0, 10, 100);
	    g.fillRect(884, 0, 10, 100);
	    g.drawRect(884, 150, 10, 100);
	    g.fillRect(884, 150, 10, 100);
	    g.drawRect(884, 300, 10, 100);
	    g.fillRect(884, 300, 10, 100);
	    g.drawRect(884, 450, 10, 100);
	    g.fillRect(884, 450, 10, 100);
	    g.drawRect(884, 600, 10, 100);
	    g.fillRect(884, 600, 10, 100);
	    g.drawRect(884, 750, 10, 100);
	    g.fillRect(884, 750, 10, 100);
	    
	    //score
	    String p1score = Integer.toString(player1.getScore());
	    String p2score = Integer.toString(player2.getScore());
	    g.drawString(p1score, 784, 70);
	    g.drawString(p2score, 974, 70);

	    //player one paddle
	    g.drawRect(player1.getPaddleX() , player1.getPaddleY(), player1.getPaddleSizeX(), player1.getPaddleSizeY());
	    g.fillRect(player1.getPaddleX() , player1.getPaddleY(), player1.getPaddleSizeX(), player1.getPaddleSizeY());
	    //player two paddle
	    g.drawRect(player2.getPaddleX() , player2.getPaddleY(), player2.getPaddleSizeX(), player2.getPaddleSizeY());
	    g.fillRect(player2.getPaddleX() , player2.getPaddleY(), player2.getPaddleSizeX(), player2.getPaddleSizeY());
	    if(Menu.powerUpModeEnabled){
	    	if((gameTimer % 500) == 0){powerUpBox.setEnabled(true);}
	    }
	    //powerup generator
	    if(powerUpBox.isEnabled()){
	    	//powerUp.setNewLocation();
	    	if(powerUpBox.getPowerUpTimer() < powerUpBox.getPowerUpLength()){
	    		g.drawRect(powerUpBox.getxLocation(), powerUpBox.getyLocation(), 50, 50);
	    		g.fillRect(powerUpBox.getxLocation(), powerUpBox.getyLocation(), 50, 50);
	    		//System.out.println("enabled and timer: " + powerUpBox.getPowerUpTimer());
	    	} else {
	    		g.clearRect(powerUpBox.getxLocation(), powerUpBox.getyLocation(), 50, 50);
	    		disablePowerUpBox();
	    	}
	    	//detection for collision between ball and power up box.
			if(collisionDetection.powerUpBoxHit(ball, powerUpBox)){
				g.clearRect(powerUpBox.getxLocation(), powerUpBox.getyLocation(), 50, 50);
				disablePowerUpBox();
				powerUp.shrinkBall(ball);
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
		ball.setDirectionballx(getRandomDirection());
		ball.setDirectionbally(getRandomDirection());
		ball.setBallx(884);
		ball.setBally(350);
		ball.setBallSpeedX(4);
		ball.setBallSpeedY(1);
	}
	
	public int getRandomDirection(){
		double direction = Math.random() * 10;
		double way = Math.random();
		if(way > .50)
			direction *= -1;
		System.out.println(direction);
		return (int)direction;
	}
	
	public int getRandomSpeed(){
		double speed = Math.random();
		speed = (speed * 10) % 5;
		return (int)speed;
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
