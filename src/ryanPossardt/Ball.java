package ryanPossardt;

public class Ball {
	private int ballx = 900;
	private int bally = 400;
	private int directionballx, directionbally = 0;
	private int ballSpeed = 3;
	public Ball(){
		
	}
	
	public int getBallx() {
		return ballx;
	}
	public void setBallx(int ballx) {
		this.ballx = ballx;
	}
	public int getBally() {
		return bally;
	}
	public void setBally(int bally) {
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
	public int getBallSpeed() {
		return ballSpeed;
	}
	public void setBallSpeed(int ballSpeed) {
		this.ballSpeed = ballSpeed;
	}
}
