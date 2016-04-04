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

public class CPUComponents extends JPanel implements ActionListener, KeyListener{
	private Ball ball = new Ball();
	private boolean upPressedPlayerOne, downPressedPlayerOne, upPressedPlayerTwo, downPressedPlayerTwo = false;
	private static final long serialVersionUID = 1L;
	private Paddle player1 = new Paddle(0,100);
	private Paddle player2 = new Paddle(1737,100);
	CollisionDetection collisionDetection = new CollisionDetection();
	//constructor
	public CPUComponents(){
		setBackground(Color.BLACK);
		
		setFocusable(true);
		addKeyListener(this);
		
		Timer timer = new Timer(1000/60, this);
		timer.start();
	}
	
	//method for moving objects
	public void step(){
		//moving the ball on the court
		if(ball.getBallx() >= 1720){
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
		
		startMove();
		endMove();
		
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
		
		//moving CPU's paddle
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
		
		//checking to see if ball hit someone's paddle
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
	    g.fillOval((int)ball.getBallx(), (int)ball.getBally(), 50, 50);

	    
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
	    g.drawRect(player1.getPaddleX() , player1.getPaddleY(), 40, 150);
	    g.fillRect(player1.getPaddleX() , player1.getPaddleY(), 40, 150);
	    //player two paddle
	    g.drawRect(player2.getPaddleX() , player2.getPaddleY(), 40, 150);
	    g.fillRect(player2.getPaddleX() , player2.getPaddleY(), 40, 150);

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
			float delta = (ball.getBally() - player2.getPaddleY()) - 50;
			double speed = Math.sqrt((ball.getBallSpeedX()*ball.getBallSpeedX()) + (ball.getBallSpeedY()*ball.getBallSpeedY())) + Math.abs(delta);
			if(delta < 0) {speed = speed * -1;}
			if(ball.getDirectionbally()%2 == 0) {	//ball moving up
				if(ball.getDirectionballx()%2 == 0){	//ball moving left
					if(speed < -125 && player2.getPaddleY() > 0) {upPressedPlayerTwo = true;}
					if(speed > 175 && player2.getPaddleY() < 700) {downPressedPlayerTwo = true;}
				}else{	//ball moving right
					if(speed < 0 && player2.getPaddleY() > 0) {upPressedPlayerTwo = true;}
					if(speed >= 100 && player2.getPaddleY() < 700) {downPressedPlayerTwo = true;}
				}
			}else{	//ball moving down
				if(ball.getDirectionballx()%2 == 0){	//ball moving left
					if(speed < -175 && player2.getPaddleY() > 0) {upPressedPlayerTwo = true;}
					if(speed > 125 && player2.getPaddleY() < 700) {downPressedPlayerTwo = true;}
				}else{	//ball moving right
					if(speed <= -100 && player2.getPaddleY() > 0) {upPressedPlayerTwo = true;}
					if(speed > 0 && player2.getPaddleY() < 700) {downPressedPlayerTwo = true;}
				}
			}
		}
		
		public void endMove() {
			if(player2.getPaddleY() <= 0) {upPressedPlayerTwo = false;}	//paddle at top
			if(player2.getPaddleY() >= 700){downPressedPlayerTwo = false;}	//paddle at bottom
			float delta = (ball.getBally() - player2.getPaddleY()) - 50;
			double speed = Math.sqrt((ball.getBallSpeedX()*ball.getBallSpeedX()) + (ball.getBallSpeedY()*ball.getBallSpeedY())) + Math.abs(delta);
			if(delta < 0) {speed = speed * -1;}
			if(ball.getDirectionbally()%2 == 0){	//ball moving up
				if(ball.getDirectionballx()%2 == 0){	//ball moving left
					if(speed > 100) {upPressedPlayerTwo = false;}
					if(speed < 150) {downPressedPlayerTwo = false;}
				}else{	//ball moving right
					if(speed > 85) {upPressedPlayerTwo = false;}
					if(speed < 100) {downPressedPlayerTwo = false;}
				}
			}else{	//ball moving down
				if(ball.getDirectionballx()%2 == 0){	//ball moving left
					if(speed > -150) {upPressedPlayerTwo = false;}
					if(speed < -100) {downPressedPlayerTwo = false;}
				}else{	//ball moving right
					if(speed > -100) {upPressedPlayerTwo = false;}
					if(speed < -85) {downPressedPlayerTwo = false;}
				}
			}
		}
	
	//reset ball after score
	public void resetBall(){
		ball.setBallx(900);
		ball.setBally(400);
		ball.setBallSpeedX(3);
		ball.setBallSpeedY(3);
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		step();
	}
	
}