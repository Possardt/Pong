package scottCote;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Menu extends JPanel implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	private boolean isGameRunning;
	private JButton startButton;
	private JButton exitButton;

	
	private GridBagConstraints getGBC(int x, int y){
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = x;
		gbc.gridy = y;
		return gbc;
	}

	public Menu(){
		setGameRunning(false);
		setBackground(Color.BLACK);
		setFocusable(true);
		this.setLayout(new GridBagLayout());
		

		
		this.startButton = new JButton("New Game");
		this.startButton.addActionListener(this);
		this.add(this.startButton, getGBC(0,1));
		
		this.exitButton = new JButton("Exit");
		this.exitButton.addActionListener(this);
		this.add(this.exitButton, getGBC(0,2));
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
