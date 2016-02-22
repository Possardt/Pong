package ryanPossardt;

public class CollisionDetection {
	public void hitPaddle(Ball ball, Paddle paddle){
		//if ball hits paddle
		if(paddle.getPaddleX() < 100){
			if((paddle.getPaddleX() - ball.getBallx() > -40) && (paddle.getPaddleY() - ball.getBally() < 0)){
				System.out.println("collision detected");
				ball.setDirectionballx(ball.getDirectionballx() + 1);
			}
		}else{
			if((paddle.getPaddleX() - ball.getBallx() < 40) && (paddle.getPaddleY() - ball.getBally() < 0)){
				System.out.println("collision detected");
				ball.setDirectionballx(ball.getDirectionballx() + 1);
			}
		}

	}
}
