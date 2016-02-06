import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import javax.swing.Timer;

public class PongComponents extends JPanel implements ActionListener, KeyListener{
	private int ballx,bally = 500;
	private int directionballx, directionbally = 0;
	private int playerOnex, playerOney = 100;
	private int paddleSpeed = 5;
	private boolean upPressed, downPressed = false;
	private static final long serialVersionUID = 1L;
	
	
	//constructor
	public PongComponents(){
		//setBackground(Color.BLACK);
		
		setFocusable(true);
		addKeyListener(this);
		
		Timer timer = new Timer(1000/60, this);
		timer.start();
	}
	
	//method for moving objects
	public void step(){
		//moving the ball on the court
		if(ballx==1700 || ballx==0){directionballx++;}
		
		if(bally==800 || bally==0){directionbally++;}
		
		if(directionballx%2 == 0){
			ballx-=1;
		}else{
			ballx+=1;
		}
		
		if(directionbally%2 == 0){
			bally-=1;
		}else{
			bally+=1;
		}
		
		//moving player ones paddle
		if(upPressed){
			if(playerOney-paddleSpeed > 0){
				playerOney -= 5;
				System.out.println("playeroney: " + playerOney + "getHeight(): " +getHeight());
			}
		}
		if(downPressed){
			if(playerOney + paddleSpeed + 140 < getHeight()){
				playerOney += 5;
			}
		}
		
		//repaint to show reflected changes
		repaint();
	}
	
	@Override
	public void paint(Graphics g){
		super.paintComponent(g);
		g.setColor(Color.BLACK);
	    g.fillOval(ballx, bally, 50, 50);
	    g.drawRect(playerOnex , playerOney, 40, 150);
	    g.fillRect(playerOnex, playerOney, 40, 150);
	    
	}

	@Override
	public void keyPressed(KeyEvent k) {
		if(k.getKeyCode() == KeyEvent.VK_UP){
			upPressed = true;
		}
		if(k.getKeyCode() == KeyEvent.VK_DOWN){
			downPressed = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent k) {
		if(k.getKeyCode() == KeyEvent.VK_UP){
			upPressed = false;
		}
		if(k.getKeyCode() == KeyEvent.VK_DOWN){
			downPressed = false;
		}
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
