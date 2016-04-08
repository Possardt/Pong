package ryanPossardt;

public class PowerUp {
	public void shrinkPaddle(Paddle p){
		p.setPaddleSizeY(p.getPaddleSizeY() / 2);
	}
	
	public void shrinkBall(Ball b){
		b.setBallSize(b.getBallSize() / 2);
	}
	
	public void unshrinkBall(Ball b){
		b.setBallSize(b.getBallSize() * 2);
	}
}
