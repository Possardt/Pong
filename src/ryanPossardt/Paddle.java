package ryanPossardt;

public class Paddle {
	
	private int paddleX,paddleY;
	private int paddleSpeed;
	private int score = 0;
	private int paddleSizeX = 40;
	private int paddleSizeY = 150;
	
	public int getPaddleSizeX() {
		return paddleSizeX;
	}

	public void setPaddleSizeX(int paddleSizeX) {
		this.paddleSizeX = paddleSizeX;
	}

	public int getPaddleSizeY() {
		return paddleSizeY;
	}

	public void setPaddleSizeY(int paddleSizeY) {
		this.paddleSizeY = paddleSizeY;
	}

	public Paddle(int x, int y){
		this.paddleX = x;
		this.paddleY = y;
		paddleSpeed = 5;
	}
	
	public int getScore() {
		return score;
	}
	
	public void addGoal() {
		score++;
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

	public float getPaddleCenterY() {
		return this.paddleY + this.paddleSizeY/2;
	}

	public float getPaddleCenterX() {
		return this.paddleX + this.paddleSizeX/2;
	}
}
