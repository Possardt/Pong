package ericVanHeel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GameOver extends JPanel implements ActionListener, KeyListener {
	private volatile boolean b = false;
	private static final long serialVersionUID = 1L;
	JFrame j = new JFrame("Game Over");
	
	public GameOver(boolean b){
		j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		j.setLayout(new BorderLayout());
		
		j.add(this, BorderLayout.CENTER);
		j.setSize(600, 300);
		j.setVisible(false);
		
		j.setBackground(Color.BLACK);
		j.setFocusable(false);
		j.addKeyListener(this);
		
		Timer timer = new Timer(1000/60, this);
		timer.start();
	}
	
	public void setGameOver(boolean b){
		j.setVisible(b);
		j.setFocusable(b);
	}
	
	@Override
	public void paint(Graphics g){
		super.paintComponent(g);
		g.setColor(Color.WHITE);
		Font titleFont = new Font("Agency FB", Font.BOLD, 40);
		Font optionsFont = new Font ("Agency FB", Font.BOLD, 25);
		g.setFont(titleFont);
		String title = "Game Over";
		String resumeGame = "(R) Rematch";
		String menu = "(M) Main Menu";
		String quit = "(X) Exit Pong";
		g.drawString(title,  200, 60);
		g.setFont(optionsFont);
		g.drawString(resumeGame, 220, 110);
		g.drawString(menu, 220, 150);
		g.drawString(quit, 220, 190);
	}
	
	@Override
	public void keyPressed(KeyEvent k) {
		if(k.getKeyCode() == KeyEvent.VK_R){
			
		}
		if(k.getKeyCode() == KeyEvent.VK_M){
			
		}
		if(k.getKeyCode() == KeyEvent.VK_X){
			System.exit(0);
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}