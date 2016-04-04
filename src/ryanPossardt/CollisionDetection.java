
package ryanPossardt;

public class CollisionDetection {
	
	private String lastHit = null;
	private Sound sounds = new Sound();
	
	public float calculateYBallSpeed(float delta){
		float temp = Math.abs(delta - 70)/10;
		return temp;
	}
	//to get angle between ball/paddle to eventually make collision between ball and corner of paddle
	public double calculateAngleBetweenPoints(Ball b, Paddle p){
		double xDiff = b.getBallCenterX() - (p.getPaddleX() + 40);
		double yDiff = b.getBallCenterY() - p.getPaddleY();
		return Math.atan2(xDiff , yDiff);
	}
		
	private boolean topHit(Ball ball, Paddle paddle){
		boolean topHit = true;
		float delta = ball.getBally() - paddle.getPaddleY();
		if (delta <=-50 || delta >= 50){
			topHit = false;
			System.out.println("Bottom hit, Delta: " + delta + ", Y speed should be: " + calculateYBallSpeed(delta));
		}
		return topHit;
	}
	
	private boolean bottomHit(Ball ball, Paddle paddle){
		boolean bottomHit = true;
		float delta = ball.getBally()-paddle.getPaddleY();
		if (delta < 50 || delta >= 150){
			bottomHit = false;
			System.out.println("Top hit, Delta: " + delta + ", Y speed should be: " + calculateYBallSpeed(delta));
		}
		return bottomHit;
	}
			
	public void hitPaddle(Ball ball, Paddle paddle){
		float delta = ball.getBally()-paddle.getPaddleY() + 25;

		//if ball hits paddle
		if(paddle.getPaddleX() < 100){
			double angle = calculateAngleBetweenPoints(ball,paddle);		
			//System.out.println("Angle between ball/paddle: " + angle);
			if((paddle.getPaddleX() - ball.getBallx() > -40) && (topHit(ball, paddle) || bottomHit(ball,paddle))){
				ball.setLastPaddleHit(paddle);
				if (topHit(ball, paddle)){
					ball.setBallSpeedX(ball.getBallSpeedX() + 1);					//increase speed
					ball.setBallSpeedY(calculateYBallSpeed(delta));
					ball.setDirectionballx(ball.getDirectionballx() + 1);		//invert x direction
					if (!ball.yDirectionIsEven()){
						ball.setDirectionbally(ball.getDirectionbally() +1);		//y direction up
					}

				}
				if (bottomHit(ball, paddle)){
					ball.setBallSpeedX(ball.getBallSpeedX() + 1);					//increase speed
					ball.setBallSpeedY(calculateYBallSpeed(delta));
					ball.setDirectionballx(ball.getDirectionballx() + 1);		//invert x direction
					if (ball.yDirectionIsEven()){
						ball.setDirectionbally(ball.getDirectionbally() +1);		//y direction down
					}
				}
				ball.setLastPaddleHit(paddle);
				sounds.leftPaddleHitSound();;
			}
		}else{		//
			if((paddle.getPaddleX() - ball.getBallx() < 50) && (topHit(ball, paddle) || bottomHit(ball,paddle))){
				ball.setLastPaddleHit(paddle);
				sounds.rightPaddleHitSound();
				if (topHit(ball, paddle)){
					ball.setBallSpeedX(ball.getBallSpeedX() + 1);					//increase speed
					ball.setBallSpeedY(calculateYBallSpeed(delta));
					ball.setDirectionballx(ball.getDirectionballx() + 1);		//invert x direction
					if (!ball.yDirectionIsEven()){
						ball.setDirectionbally(ball.getDirectionbally() +1);		//y direction up
					}

				}
				if (bottomHit(ball, paddle)){
					ball.setBallSpeedX(ball.getBallSpeedX() + 1);					//increase speed
					ball.setBallSpeedY(calculateYBallSpeed(delta));
					ball.setDirectionballx(ball.getDirectionballx() + 1);		//invert x direction
					if (ball.yDirectionIsEven()){
						ball.setDirectionbally(ball.getDirectionbally() +1);		//y direction down
					}
				}
			}
		}
	}
	
	private boolean leftWallHit(Ball ball, int ballSize){
		boolean leftWallHit = false;
		if (ball.getBallCenterX() < ball.getDiameter()/2 && lastHit != "left"){
			leftWallHit = true;
			lastHit = "left";
		}
		return leftWallHit;
	}
	
	private boolean rightWallHit(Ball ball, int ballSize){
		boolean rightWallHit = false;
		if (ball.getBallCenterX() >= 1776 - ball.getDiameter()/2 && lastHit != "right"){
			rightWallHit = true;
			lastHit = "right";
		}
		return rightWallHit;
	}
		
	private boolean topWallHit(Ball ball, int ballSize){
		boolean topWallHit = false;
		if (ball.getBallCenterY() <= ball.getDiameter()/2 && lastHit != "top"){
			topWallHit = true;
			lastHit = "top";
		}
		return topWallHit;
	}
	
	private boolean bottomWallHit(Ball ball, int ballSize){
		boolean bottomWallHit = false;
		if (ball.getBallCenterY() >= 840 - ball.getDiameter()/2 && lastHit != "bottom"){
			bottomWallHit = true;
			lastHit ="bottom";
		}
		return bottomWallHit;
	}
	
	public boolean horizontalWallHit(Ball ball, int ballSize){
		boolean horizontalWallHit = false;
		if (topWallHit(ball, ballSize) || bottomWallHit(ball, ballSize)){
			horizontalWallHit = true;
		}
		return horizontalWallHit;
	}	
	
	public boolean verticalWallHit(Ball ball, int ballSize){
		boolean verticalWallHit = false;
		if (leftWallHit(ball, ballSize) || rightWallHit(ball, ballSize)){
			verticalWallHit = true;
		}
		return verticalWallHit;
	}
}
