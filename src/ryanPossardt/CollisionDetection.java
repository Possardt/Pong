
package ryanPossardt;

public class CollisionDetection {
	
	private String lastHit = null;
	private Sound sounds = new Sound();
	private float ballVsPaddleDY;
	
	public float calculateYBallSpeed(){
		float bs = Math.abs(ballVsPaddleDY/10);
		return bs;
	}
	//to get angle between ball/paddle to eventually make collision between ball and corner of paddle
	public double calculateAngleBetweenPoints(Ball b, Paddle p){
		double xDiff = b.getBallCenterX() - (p.getPaddleX() + 40);
		double yDiff = b.getBallCenterY() - p.getPaddleY();
		return Math.atan2(xDiff , yDiff);
	}
	
	public double getDistanceBetweenTwoPoints(double x1, double y1 ,double x2, double y2){
		double xDiff = x2 - x1;
		double yDiff = y2 - y1;
		return Math.sqrt(xDiff * xDiff + yDiff * yDiff);
	}
		
	public boolean topHit(Ball ball, Paddle paddle){
		boolean topHit = false;
		float ballVsPaddleDX = Math.abs(ball.getBallCenterX() - paddle.getPaddleCenterX());
		ballVsPaddleDY = Math.abs(ball.getBallCenterY() - paddle.getPaddleCenterY());
		float ballRadius = ball.getDiameter()/2;
		if (ballVsPaddleDX <= (ballRadius + paddle.getPaddleSizeX()/2) 
				&& ball.getBallCenterY() <= paddle.getPaddleCenterY()
				&& ballVsPaddleDY <= (ballRadius + paddle.getPaddleSizeY()/2)){
			topHit = true;
			lastHit = "paddle";
		}
		return topHit;
	}
	
	public boolean bottomHit(Ball ball, Paddle paddle){
		boolean bottomHit = false;
		float ballVsPaddleDX = Math.abs(ball.getBallCenterX() - paddle.getPaddleCenterX());
		ballVsPaddleDY = Math.abs(ball.getBallCenterY() - paddle.getPaddleCenterY());
		float ballRadius = ball.getDiameter()/2;
		if (ballVsPaddleDX <= (ballRadius + paddle.getPaddleSizeX()/2)
				&& ball.getBallCenterY() > paddle.getPaddleCenterY()
				&& ballVsPaddleDY <= (ballRadius + paddle.getPaddleSizeY()/2)){
			bottomHit = true;
			lastHit = "paddle";
		}
		return bottomHit;
	}
			
	public void hitPaddle(Ball ball, Paddle paddle){
		//float delta = ball.getBally()-paddle.getPaddleY() + 25;
		//if ball hits paddle
		if(paddle.getPaddleX() < 100){
		
			if((paddle.getPaddleX() - ball.getBallx() > -40) && (topHit(ball, paddle) || bottomHit(ball,paddle))){
				ball.setLastPaddleHit(paddle);
				if (topHit(ball, paddle)){
					ball.setBallSpeedX(ball.getBallSpeedX() + 1);					//increase speed
					ball.setBallSpeedY(calculateYBallSpeed());
					ball.setDirectionballx(ball.getDirectionballx() + 1);		//invert x direction
					if (!ball.yDirectionIsEven()){
						ball.setDirectionbally(ball.getDirectionbally() +1);		//y direction up
					}

				}
				if (bottomHit(ball, paddle)){
					ball.setBallSpeedX(ball.getBallSpeedX() + 1);					//increase speed
					ball.setBallSpeedY(calculateYBallSpeed());
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
					ball.setBallSpeedY(calculateYBallSpeed());
					ball.setDirectionballx(ball.getDirectionballx() + 1);		//invert x direction
					if (!ball.yDirectionIsEven()){
						ball.setDirectionbally(ball.getDirectionbally() +1);		//y direction up
					}

				}
				if (bottomHit(ball, paddle)){
					ball.setBallSpeedX(ball.getBallSpeedX() + 1);					//increase speed
					ball.setBallSpeedY(calculateYBallSpeed());
					ball.setDirectionballx(ball.getDirectionballx() + 1);		//invert x direction
					if (ball.yDirectionIsEven()){
						ball.setDirectionbally(ball.getDirectionbally() +1);		//y direction down
					}
				}
			}
		}
	}
	
	public boolean leftWallHit(Ball ball){
		boolean leftWallHit = false;
		if (ball.getBallCenterX() < ball.getDiameter()/2 && lastHit != "left"){
			leftWallHit = true;
			lastHit = "left";
		}
		return leftWallHit;
	}
	
	public boolean rightWallHit(Ball ball){
		boolean rightWallHit = false;
		if (ball.getBallCenterX() >= 1776 - ball.getDiameter()/2 && lastHit != "right"){
			rightWallHit = true;
			lastHit = "right";
		}
		return rightWallHit;
	}
		
	public boolean topWallHit(Ball ball){
		boolean topWallHit = false;
		if (ball.getBallCenterY() <= ball.getDiameter()/2 && lastHit != "top"){
			topWallHit = true;
			lastHit = "top";
		}
		return topWallHit;
	}
	
	public boolean bottomWallHit(Ball ball){
		boolean bottomWallHit = false;
		if (ball.getBallCenterY() >= 840 - ball.getDiameter()/2 && lastHit != "bottom"){
			bottomWallHit = true;
			lastHit ="bottom";
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
	
	public boolean verticalWallHit(Ball ball){
		boolean verticalWallHit = false;
		if (leftWallHit(ball) || rightWallHit(ball)){
			verticalWallHit = true;
		}
		return verticalWallHit;
	}
}
