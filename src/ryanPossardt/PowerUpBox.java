package ryanPossardt;

public class PowerUpBox {
	
	private int xLocation = (int)(Math.random() * 1000);
	private int yLocation = (int)(Math.random() * 1000);
	private boolean isEnabled = false;
	private int powerUpTimer = 0;
	private int powerUpLength = 300;
	
	public PowerUpBox(){
		setNewLocation();
	}
	
	
	public int getPowerUpTimer() {
		return powerUpTimer;
	}

	public void setPowerUpTimer(int powerUpTimer) {
		this.powerUpTimer = powerUpTimer;
	}

	public int getPowerUpLength() {
		return powerUpLength;
	}

	public void setPowerUpLength(int powerUpLength) {
		this.powerUpLength = powerUpLength;
	}

	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	public int getxLocation() {
		return xLocation;
	}

	public void setxLocation(int xLocation) {
		this.xLocation = xLocation;
	}

	public int getyLocation() {
		return yLocation;
	}

	public void setyLocation(int yLocation) {
		this.yLocation = yLocation;
	}

	public boolean isEnabled(){
		return isEnabled;
	}
	
	public int getCenterX(){
		return xLocation + 25;
	}
	
	public int getCenterY(){
		return yLocation + 25;
	}
	
	public void setNewLocation(){
		xLocation = (int)(Math.random() * 1000) + 300;
		yLocation = (int)(Math.random() * 1000) + 100;
		System.out.println("new location x: " + xLocation + ", new location y: " + yLocation);
		//we don't want the powerup box to appear off the map.
		if (yLocation > 600 || xLocation > 1600){this.setNewLocation();}
	}
}
