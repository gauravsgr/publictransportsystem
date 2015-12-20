import  sun.audio.*;    //import the sun.audio package
import  java.io.*;

class PlayWav
{
	static boolean loop=true;
	public void play()
	{
		while(true)
		{			
			try
			{
				if(!loop)
				{
					loop=true;
					break;
				}
				InputStream in = getClass().getResourceAsStream("powercall.wav");
				AudioStream as = new AudioStream(in); // Create an AudioStream object from the input stream.			
				AudioPlayer.player.start(as); // Use the static class member "player" from class AudioPlayer to play
				System.out.println("Played the music");
				Thread.currentThread().sleep(5080);
			}catch(Exception e){System.out.println("in PlayWav at "+e);}
		}
	}
}