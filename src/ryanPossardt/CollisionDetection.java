package ryanPossardt;

public class CollisionDetection {
	public void hitPaddle(Ball ball, Paddle paddle, int xBase){
		//if ball hits paddle
		if((ball.getBallx() - xBase < 40) && (paddle.getPaddleY() - ball.getBally() < 0)){
			System.out.println("collision detected");
			ball.setDirectionballx(ball.getDirectionballx() + 1);
		}

	}
}
