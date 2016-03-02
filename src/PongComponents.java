import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import javax.swing.Timer;

import ryanPossardt.*;
import ericVanHeel.*;
import scottCote.*;
import danielMarciniec.*;

public class PongComponents extends JPanel implements ActionListener, KeyListener{
	private Ball ball = new Ball();
	private boolean upPressedPlayerOne, downPressedPlayerOne, upPressedPlayerTwo, downPressedPlayerTwo = false;
	private static final long serialVersionUID = 1L;
	private Paddle player1 = new Paddle(0,100);
	private Paddle player2 = new Paddle(1737,100);
	CollisionDetection collisionDetection = new CollisionDetection();
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
		if(ball.getBallx() >= 1720 || ball.getBallx() <= 0){
			ball.setDirectionballx(ball.getDirectionballx() + 1);
			resetBall();
			}
		
		if(ball.getBally() >= 800 || ball.getBally() <= 0){
			ball.setDirectionbally(ball.getDirectionbally() + 1);
		}
		//System.out.println("Player one x: "+ player1.getPaddleX() +", player one y: "+ player1.getPaddleY() +" X: " + ball.getBallx() + " , Y: " + ball.getBally());
		//System.out.println("Player two x: " + player2.getPaddleX() + ", Player two y: " + player2.getPaddleY());
		if(ball.getDirectionballx()%2 == 0){
			ball.setBallx(ball.getBallx() - ball.getBallSpeed());
		}else{
			ball.setBallx(ball.getBallx() + ball.getBallSpeed());
		}
		
		if(ball.getDirectionbally()%2 == 0){
			ball.setBally(ball.getBally() - ball.getBallSpeed());
		}else{
			ball.setBally(ball.getBally() + ball.getBallSpeed());
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
	    g.fillOval(ball.getBallx(), ball.getBally(), 50, 50);
	    
	    //player one paddle
	    g.drawRect(player1.getPaddleX() , player1.getPaddleY(), 40, 150);
	    g.fillRect(player1.getPaddleX() , player1.getPaddleY(), 40, 150);
	    //player two paddle
	    g.drawRect(player2.getPaddleX() , player2.getPaddleY(), 40, 150);
	    g.fillRect(player2.getPaddleX() , player2.getPaddleY(), 40, 150);

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
		ball.setBallx(900);
		ball.setBally(400);
		ball.setBallSpeed(3);
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
