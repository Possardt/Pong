package ryanPossardt;

public class Paddle {
	
	private int paddleX,paddleY;
	private int paddleSpeed;
	
	public Paddle(int x, int y){
		this.paddleX = x;
		this.paddleY = y;
		paddleSpeed = 5;
	}
	
	public int getPaddleX() {
		return paddleX;
	}

	public void setPaddleX(int paddleX) {
		this.paddleX = paddleX;
	}

	public int getPaddleY() {
		return paddleY;
	}

	public void setPaddleY(int paddleY) {
		this.paddleY = paddleY;
	}

	public int getPaddleSpeed() {
		return paddleSpeed;
	}

	public void setPaddleSpeed(int paddleSpeed) {
		this.paddleSpeed = paddleSpeed;
	}
}
