package ryanPossardt;


public class PowerUp {
	private int powerUpSelector = 0;
	private Paddle affectedPaddle;
	
	public void getRandomPowerUp(Ball b, Paddle p ){
		double selector = Math.random();
		if (selector < .50){
			shrinkPaddle(p);
			affectedPaddle = p;
			powerUpSelector = 1;
		}else{
			//shrink ball
			shrinkBall(b);
			powerUpSelector =  2;
		}
	}
	
	public void undoPowerUp(Ball b){
		if(powerUpSelector == 1){
			unShrinkPaddle(affectedPaddle);
		}else if(powerUpSelector == 2){
			unShrinkBall(b);
		}
		powerUpSelector = 0;
		
	}
	
	public void shrinkPaddle(Paddle p){
		p.setPaddleSizeY(p.getPaddleSizeY() / 2);
	}
	
	public void unShrinkPaddle(Paddle p){
		p.setPaddleSizeY(p.getPaddleSizeY() * 2);
	}
	
	public void shrinkBall(Ball b){
		b.setDiameter(b.getDiameter() / 2);
	}
	
	public void unShrinkBall(Ball b){
		b.setDiameter(b.getDiameter() * 2);
	}
}
