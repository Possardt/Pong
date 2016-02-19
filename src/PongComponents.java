import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import javax.swing.Timer;

public class PongComponents extends JPanel implements ActionListener, KeyListener{
	private int ballx = 900;
	private int bally = 400;
	private int directionballx, directionbally = 0;
	private int ballSpeed = 3;
	private int playerOnex, playerOney = 100;
	private int playerTwox = 1738;
	private int playerTwoy = 100;
	private int paddleSpeed = 5;
	private boolean upPressedPlayerOne, downPressedPlayerOne, upPressedPlayerTwo, downPressedPlayerTwo = false;
	private static final long serialVersionUID = 1L;
	
	
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
		if(ballx >= 1720 || ballx <= 0){
			directionballx++;
			resetBall();
			}
		
		if(bally >= 800 || bally <= 0){directionbally++;}
		System.out.println("X: " + ballx + " , Y: " + bally);
		if(directionballx%2 == 0){
			ballx-=ballSpeed;
		}else{
			ballx+=ballSpeed;
		}
		
		if(directionbally%2 == 0){
			bally-=ballSpeed;
		}else{
			bally+=ballSpeed;
		}
		
		//moving player ones paddle
		if(upPressedPlayerOne){
			if(playerOney-paddleSpeed > 0){
				playerOney -= paddleSpeed;
			}
		}
		if(downPressedPlayerOne){
			if(playerOney + paddleSpeed + 140 < getHeight()){
				playerOney += paddleSpeed;
			}
		}
		
		//moving player two's paddle
		if(upPressedPlayerTwo){
			if(playerTwoy-paddleSpeed > 0){
				playerTwoy -= paddleSpeed;
			}
		}
		if(downPressedPlayerTwo){
			if(playerTwoy + paddleSpeed + 140 < getHeight()){
				playerTwoy += paddleSpeed;
			}
		}
		
		//repaint to show reflected changes
		repaint();
	}
	
	@Override
	public void paint(Graphics g){
		super.paintComponent(g);
		g.setColor(Color.WHITE);
	    g.fillOval(ballx, bally, 50, 50);
	    
	    //player one paddle
	    g.drawRect(playerOnex , playerOney, 40, 150);
	    g.fillRect(playerOnex, playerOney, 40, 150);
	    //player two paddle
	    g.drawRect(playerTwox , playerTwoy, 40, 150);
	    g.fillRect(playerTwox, playerTwoy, 40, 150);

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
		ballx = 900;
		bally = 400;
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
