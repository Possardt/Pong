import javax.swing.JFrame;
import java.awt.BorderLayout;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		JFrame j = new JFrame("Pong");
		j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		j.setLayout(new BorderLayout());
		
		PongComponents p = new PongComponents();
		j.add(p, BorderLayout.CENTER);
		
		j.setSize(1800, 900);
		j.setVisible(true);
		
	}

}
