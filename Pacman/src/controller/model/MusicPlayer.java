package controller.model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class MusicPlayer {
	
	private static File file2 = new File("src/sfx/Emotional.mp3");
	private static Media emotion = new Media(file2.toURI().toString());
	private static MediaPlayer musicplayer2 = new MediaPlayer(emotion);
	
	private static File file = new File("src/sfx/FMA.mp3");
	private static Media FMA = new Media(file.toURI().toString());
	private static MediaPlayer musicplayer = new MediaPlayer(FMA);
	
	private final static List<MediaPlayer> playlist = new ArrayList<>();
	private static int count = 0;
	public MusicPlayer() {
		addMusic();
	}
	
	public void addMusic() {
		playlist.add(musicplayer);
		playlist.add(musicplayer2);
	}
	
	
	public void playMusic() {
		playlist.get(count).setVolume(0.0); //0.05
		playlist.get(count).play();
		
	}
	
	public void stopMusic() {
		playlist.get(count).stop();
		count++;
		if (count == 2) {
			count = 0;
		}
	}
	
}
