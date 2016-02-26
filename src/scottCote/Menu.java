package scottCote;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Menu extends JPanel implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	private boolean isGameRunning;
	private JButton startButton;
	private JButton exitButton;

	public Menu(){
		setGameRunning(false);
		setBackground(Color.BLACK);
		setFocusable(true);
		
		this.startButton = new JButton("New Game");
		this.startButton.addActionListener(this);
		this.add(startButton);
		
		this.exitButton = new JButton("Exit");
		this.exitButton.addActionListener(this);
		this.add(exitButton);
	}
	@Override
	public void actionPerformed(ActionEvent e){
		Object source = e.getSource();
		if(source == this.exitButton){
			System.exit(0);
		}
		else if (source == this.startButton){
			setGameRunning(true);
			System.out.println(isGameRunning);
		}
	}

	public boolean isGameRunning() {
		return isGameRunning;
	}

	public void setGameRunning(boolean isGameRunning) {
		this.isGameRunning = isGameRunning;
	}

}
