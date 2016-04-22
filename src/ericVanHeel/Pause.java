package ericVanHeel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Pause extends JPanel implements ActionListener, KeyListener
{
	private volatile boolean gamePaused, menu, resume = false;
	private static final long serialVersionUID = 1L;
	JFrame j = new JFrame("Pause Menu");
	KeyEvent k;
	
	public Pause(boolean b){
		j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		j.setLayout(new BorderLayout());
		
		j.add(this, BorderLayout.CENTER);
		j.setSize(600, 300);
		j.setVisible(false);

		setBackground(Color.BLACK);
		j.setFocusable(false);
		j.addKeyListener(this);
		repaint();
		
		Timer timer = new Timer(1000/70, this);
		timer.start();
	}
	
	public void step(){
		repaint();
	}
	
	public boolean getGamePaused(){
		return gamePaused;
	}
	
	public void setGamePaused(boolean b){
		gamePaused = b;
		j.setVisible(b);
		j.setFocusable(b);
		resume = false;
	}
	
	@Override
	public void paint(Graphics g){
		super.paintComponent(g);
		g.setColor(Color.WHITE);
		Font titleFont = new Font("Bauhaus 93", Font.BOLD, 40);
		Font optionsFont = new Font ("Bauhaus 93", Font.BOLD, 25);
		g.setFont(titleFont);
		String title = "Game Paused";
		String resumeGame = "(R) Resume Game";
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
			j.dispose();
			gamePaused = false;
			resume = true;
		}
		if(k.getKeyCode() == KeyEvent.VK_M){
			j.dispose();
			gamePaused = false;
			menu = true;
		}
		if(k.getKeyCode() == KeyEvent.VK_X){
			System.exit(0);
		}
	}
	
	public boolean getResume(){
		return resume;
	}
	
	public boolean getMenu(){
		return menu;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void actionPerformed(ActionEvent e){
		if(!resume && !menu)
			step();
	}
}