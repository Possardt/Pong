package ryanPossardt;

public class Ball {
	private float ballx = 900;
	private float bally = 400;
	private int directionballx, directionbally = 0;
	private float ballSpeedX = 3;
	private float ballSpeedY = 3;
	private Paddle lastPaddleHit;
	//lastPaddleHit is to be used for powerups, when we need to determine which paddle
	//	to apply the powerup to.
	
	public Paddle getLastPaddleHit() {
		return lastPaddleHit;
	}

	public void setLastPaddleHit(Paddle lastPaddleHit) {
		//System.out.println(lastPaddleHit.getPaddleX());
		this.lastPaddleHit = lastPaddleHit;
	}

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
	public float getBallCenterX(){
		return (getBallx() + 25);
	}
	public float getBallCenterY(){
		return (getBally() + 25);
	}
	
	public double getTravelAngle(){
		double angle = Math.toDegrees(Math.atan2(ballSpeedY , ballSpeedX));
		return angle;
	}
	
	public double getSpeedMagnitude(){
		double speed = Math.pow(Math.pow(ballSpeedY, 2)+Math.pow(ballSpeedX, 2), .5);
		return speed;
	}
	
}
