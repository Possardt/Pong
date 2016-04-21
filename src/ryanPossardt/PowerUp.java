package ryanPossardt;

/*
	Class for powerups.  A random number is generated and based on what value(0.0 to 1.0)
	it is, it selects a power up.
	powerups include two methods, one to apply it, and one to unapply it.
	The ball keeps track of what padde was last hit (lastPaddleHit)
		and what paddle is the opponents paddle(nextPaddleHit)
*/

public class PowerUp {
	private int powerUpSelector = 0;
	private Paddle affectedPaddle;
	
	public void getRandomPowerUp(Ball b){
		double selector = Math.random();
		System.out.println("rand chosen" + selector);
		if (selector < .25){
			shrinkPaddle(b.getNextPaddleHit());
			affectedPaddle = b.getNextPaddleHit();
			powerUpSelector = 1;
		}else if(selector <.5){
			//shrink ball
			shrinkBall(b);
			powerUpSelector =  2;
		}else if(selector <.75 ){
			slowPaddle(b.getNextPaddleHit());
			powerUpSelector = 3;
			affectedPaddle = b.getNextPaddleHit();
		}else if(selector < 1){
			fastPaddle(b.getLastPaddleHit());
			powerUpSelector = 4;
			affectedPaddle = b.getLastPaddleHit();
		}
		
	}
	
	public void undoPowerUp(Ball b){
		if(powerUpSelector == 1){
			unShrinkPaddle(affectedPaddle);
		}else if(powerUpSelector == 2){
			unShrinkBall(b);
		}else if(powerUpSelector == 3){
			unSlowPaddle(affectedPaddle);
		}else if(powerUpSelector == 4){
			unFastPaddle(affectedPaddle);
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
	
	public void slowPaddle(Paddle p){
		p.setPaddleSpeed(p.getPaddleSpeed() / 2);
	}
	
	public void unSlowPaddle(Paddle p){
		p.setPaddleSpeed((p.getPaddleSpeed() * 2) + 1);
	}
	
	public void fastPaddle(Paddle p){
		p.setPaddleSpeed(p.getPaddleSpeed() * 2);
	}
	
	public void unFastPaddle(Paddle p){
		p.setPaddleSpeed(p.getPaddleSpeed() / 2);
	}
	
	
}
