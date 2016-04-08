package ryanPossardt;
import java.io.InputStream;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

public class Sound {
	public void leftPaddleHitSound(){
		try{
			InputStream inputStream = getClass().getResourceAsStream("leftPaddleHit.wav");
			AudioStream audioStream = new AudioStream(inputStream);
			AudioPlayer.player.start(audioStream);
		}catch(Exception e){
			System.out.println("Sound not played");
			e.printStackTrace();
		}
	}
	public void rightPaddleHitSound(){
		try{
			InputStream inputStream = getClass().getResourceAsStream("rightPaddleHit.wav");
			AudioStream audioStream = new AudioStream(inputStream);
			AudioPlayer.player.start(audioStream);
		}catch(Exception e){
			System.out.println("Sound not played");
			e.printStackTrace();
		}
	}
	public void playScoreSound(){
		try{
			InputStream inputStream = getClass().getResourceAsStream("scoreSound.wav");
			AudioStream audioStream = new AudioStream(inputStream);
			AudioPlayer.player.start(audioStream);
		}catch(Exception e){
			System.out.println("Sound not played");
			e.printStackTrace();
		}
	}
	public void playWallHitSound(){
		try{
			InputStream inputStream = getClass().getResourceAsStream("wallHit.wav");
			AudioStream audioStream = new AudioStream(inputStream);
			AudioPlayer.player.start(audioStream);
		}catch(Exception e){
			System.out.println("Sound not played");
			e.printStackTrace();
		}
	}
	
}
