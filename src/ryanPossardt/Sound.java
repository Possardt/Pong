package ryanPossardt;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

public class Sound {
	public void playSound(){
		try{
			InputStream inputStream = getClass().getResourceAsStream("ballBounce.wav");
			AudioStream audioStream = new AudioStream(inputStream);
			AudioPlayer.player.start(audioStream);
		}catch(Exception e){
			System.out.println("Sound not played");
			e.printStackTrace();
		}
	}
	
	
	
}
