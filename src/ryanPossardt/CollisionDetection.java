
package ryanPossardt;

public class CollisionDetection {
	//private Sound ballSound = new Sound();
	public float calculateYBallSpeed(float delta){
		float temp = Math.abs(delta - 70)/10;
		return temp;
	}
	//to get angle between ball/paddle to eventually make collision between ball and corner of paddle
	public double calculateAngleBetweenPoints(Ball b, Paddle p){
		double xDiff = b.getBallCenterX() - (p.getPaddleX() + 40);
		double yDiff = b.getBallCenterY() - p.getPaddleY();
		return Math.toDegrees(Math.atan2(xDiff , yDiff));
	}
	
	public double calculateAngleBetweenBalls(Ball ballOne, Ball ballTwo){
		double xDiff = Math.abs(ballOne.getBallCenterX() - ballTwo.getBallCenterX());
		double yDiff = Math.abs(ballOne.getBallCenterY() - ballTwo.getBallCenterY());
		double angle = Math.toDegrees(Math.atan2(xDiff, yDiff));
		return angle;
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
				//ballSound.playSound();
			}
		}else{		//
			if((paddle.getPaddleX() - ball.getBallx() < 50) && (topHit(ball, paddle) || bottomHit(ball,paddle))){
				ball.setLastPaddleHit(paddle);
				//ballSound.playSound();
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
	
	private boolean leftWallHit(Ball ball){
		boolean leftWallHit = false;
		if (ball.getBallCenterX() <= 25){
			leftWallHit = true;
		}
		return leftWallHit;
	}
	
	private boolean rightWallHit(Ball ball){
		boolean rightWallHit = false;
		if (ball.getBallCenterX() >= 1750){
			rightWallHit = true;
		}
		return rightWallHit;
	}
	
	public boolean verticalWallHit(Ball ball){
		boolean verticalWallHit = false;
		if (leftWallHit(ball) || rightWallHit(ball)){
			verticalWallHit = true;
		}
		return verticalWallHit;
	}
	
	private boolean topWallHit(Ball ball){
		boolean topWallHit = false;
		if (ball.getBallCenterY() <= 25){
			topWallHit = true;
		}
		return topWallHit;
	}
	
	private boolean bottomWallHit(Ball ball){
		boolean bottomWallHit = false;
		if (ball.getBallCenterY() >= 815){
			bottomWallHit = true;
		}
		return bottomWallHit;
	}
	
	public boolean horizontalWallHit(Ball ball){
		boolean horizontalWallHit = false;
		if (topWallHit(ball) || bottomWallHit(ball)){
			horizontalWallHit = true;
		}
		return horizontalWallHit;
	}
	
	public boolean ballOnBallHit(Ball ballOne, Ball ballTwo){
		boolean ballOnBallHit = false;
		float distance = (float)Math.pow((float)Math.pow(ballOne.getBallCenterX()-ballTwo.getBallCenterX(),2) + (float)Math.pow(ballOne.getBallCenterY()-ballTwo.getBallCenterY(),2),.5);
		if (distance <= 50){
			ballOnBallHit = true;
		}
		return ballOnBallHit;
	}
	

	
}
