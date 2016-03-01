package ryanPossardt;

public class CollisionDetection {
	public void hitPaddle(Ball ball, Paddle paddle){
		//if ball hits paddle
		if(paddle.getPaddleX() < 100){
			if((paddle.getPaddleX() - ball.getBallx() > -40) && yHit(ball, paddle)){
				System.out.println("collision detected");
				ball.setDirectionballx(ball.getDirectionballx() + 1);
				ball.setBallSpeed(ball.getBallSpeed() + 1);
			}
		}else{
			if((paddle.getPaddleX() - ball.getBallx() < 50) && yHit(ball, paddle)){
				System.out.println("collision detected");
				ball.setDirectionballx(ball.getDirectionballx() + 1);
				ball.setBallSpeed(ball.getBallSpeed() + 1);
			}
		}
	}
	
	private boolean yHit(Ball ball, Paddle paddle){
		boolean hit = true;
		int delta = ball.getBally()-paddle.getPaddleY() + 25;
		if (delta < 0 || delta > 140){
			hit = false;
		}
		return hit;
	}
	
}
