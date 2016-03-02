package ryanPossardt;

public class CollisionDetection {
	
	private boolean topHit(Ball ball, Paddle paddle){
		boolean topHit = true;
		int delta = ball.getBally() - paddle.getPaddleY() + 25;
		if (delta <=0 || delta >= 70){
			topHit = false;
		}
		return topHit;
	}
	
	private boolean bottomHit(Ball ball, Paddle paddle){
		boolean bottomHit = true;
		int delta = ball.getBally()-paddle.getPaddleY() + 25;
		if (delta < 70 || delta >= 140){
			bottomHit = false;
		}
		return bottomHit;
	}
	
	public void hitPaddle(Ball ball, Paddle paddle){
		//if ball hits paddle
		if(paddle.getPaddleX() < 100){
			if((paddle.getPaddleX() - ball.getBallx() > -40) && (topHit(ball, paddle) || bottomHit(ball,paddle))){
				if (topHit(ball, paddle)){
					ball.setBallSpeed(ball.getBallSpeed() + 1);					//increase speed
					ball.setDirectionballx(ball.getDirectionballx() + 1);		//invert x direction
					if (!ball.yDirectionIsEven()){
						ball.setDirectionbally(ball.getDirectionbally() +1);		//y direction up
					}

				}
				if (bottomHit(ball, paddle)){
					ball.setBallSpeed(ball.getBallSpeed() + 1);					//increase speed
					ball.setDirectionballx(ball.getDirectionballx() + 1);		//invert x direction
					if (ball.yDirectionIsEven()){
						ball.setDirectionbally(ball.getDirectionbally() +1);		//y direction down
					}
				}
			}
		}else{		//
			if((paddle.getPaddleX() - ball.getBallx() < 50) && (topHit(ball, paddle) || bottomHit(ball,paddle))){
				if (topHit(ball, paddle)){
					ball.setBallSpeed(ball.getBallSpeed() + 1);					//increase speed
					ball.setDirectionballx(ball.getDirectionballx() + 1);		//invert x direction
					if (!ball.yDirectionIsEven()){
						ball.setDirectionbally(ball.getDirectionbally() +1);		//y direction up
					}

				}
				if (bottomHit(ball, paddle)){
					ball.setBallSpeed(ball.getBallSpeed() + 1);					//increase speed
					ball.setDirectionballx(ball.getDirectionballx() + 1);		//invert x direction
					if (ball.yDirectionIsEven()){
						ball.setDirectionbally(ball.getDirectionbally() +1);		//y direction down
					}
				}
			}
		}
	}
	

	
}
