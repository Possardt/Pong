package ryanPossardt;

public class Ball {
	private float ballx = 900;
	private float bally = 400;
	private int directionballx, directionbally = 0;
	private float ballSpeedX = 3;
	private float ballSpeedY = 3;
	
	public float getBallSpeedX() {
		return ballSpeedX;
	}

	public void setBallSpeedX(float ballSpeedX) {
		this.ballSpeedX = ballSpeedX;
	}

	public float getBallSpeedY() {
		return ballSpeedY;
	}

	public void setBallSpeedY(float ballSpeedY) {
		this.ballSpeedY = ballSpeedY;
	}

	public Ball(){
		
	}
	
	public float getBallx() {
		return ballx;
	}
	public void setBallx(float ballx) {
		this.ballx = ballx;
	}
	public float getBally() {
		return bally;
	}
	public void setBally(float bally) {
		this.bally = bally;
	}
	public int getDirectionballx() {
		return directionballx;
	}
	public void setDirectionballx(int directionballx) {
		this.directionballx = directionballx;
	}
	public int getDirectionbally() {
		return directionbally;
	}
	public boolean yDirectionIsEven(){
		boolean isEven = false;
		if (directionbally%2 == 0){
			isEven = true;
		}
		return isEven;
	}
	public void setDirectionbally(int directionbally) {
		this.directionbally = directionbally;
	}
	
}
