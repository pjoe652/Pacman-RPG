package controller.model;

import java.io.File;
import java.net.URL;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class MusicPlayer {
	/*
	 * This class contains all of the music files that are used for the program
	 * including both sound effects and background music This class also creates the
	 * scene for volume option
	 */

	// Level 1 BGM
	private static File level1 = new File("src/sfx/Level1.mp3");
	private static Media levelOne = new Media(level1.toURI().toString());
	private static MediaPlayer musicPlayer1 = new MediaPlayer(levelOne);

	// Level 2 BGM
	private static File level2 = new File("src/sfx/Level2.mp3");
	private static Media levelTwo = new Media(level2.toURI().toString());
	private static MediaPlayer musicPlayer2 = new MediaPlayer(levelTwo);

	// Level 3 BGM
	private static File level3 = new File("src/sfx/Level3.mp3");
	private static Media levelThree = new Media(level3.toURI().toString());
	private static MediaPlayer musicPlayer3 = new MediaPlayer(levelThree);

	// BGM
	private static File bgm = new File("src/sfx/BGM.mp3");
	private static Media Bgm = new Media(bgm.toURI().toString());
	private static MediaPlayer musicPlayerbgm = new MediaPlayer(Bgm);

	// Multiplayer BGM
	private static File mp = new File("src/sfx/MultiPlayer.mp3");
	private static Media MP = new Media(mp.toURI().toString());
	private static MediaPlayer musicPlayerMulti = new MediaPlayer(MP);

	// Volume option
	private VBox optionRoot = new VBox();

	// This function initialises volumes for all songs
	public void initVolume() {
		musicPlayer1.setVolume(1);
		musicPlayer2.setVolume(1);
		musicPlayer3.setVolume(1);
		musicPlayerbgm.setVolume(1);
		musicPlayerMulti.setVolume(1);
	}

	// This function creates the entity of a volume option
	// which can be used to control the volume of bgms
	public VBox option() {
		Slider bgmSlider = new Slider();
		Slider inGameSlider = new Slider();

		Label option = new Label();
		option.setText("BGM: ");

		Label ingame = new Label();
		ingame.setText("In Game BGM: ");

		bgmSlider.setValue(musicPlayerbgm.getVolume() * 100);
		bgmSlider.valueProperty().addListener(new InvalidationListener() {

			@Override
			public void invalidated(Observable observable) {
				musicPlayerbgm.setVolume(bgmSlider.getValue() / 100);

			}

		});
		inGameSlider.setValue(musicPlayer1.getVolume() * 100);
		inGameSlider.valueProperty().addListener(new InvalidationListener() {

			@Override
			public void invalidated(Observable observable) {
				musicPlayer1.setVolume(inGameSlider.getValue() / 100);
				musicPlayer2.setVolume(inGameSlider.getValue() / 100);
				musicPlayer3.setVolume(inGameSlider.getValue() / 100);
				musicPlayerMulti.setVolume(inGameSlider.getValue() / 100);
			}

		});

		optionRoot.setPrefHeight(100);
		optionRoot.setPrefWidth(300);
		optionRoot.setPadding(new Insets(10, 10, 10, 10));
		optionRoot.getChildren().add(option);
		optionRoot.setSpacing(10);
		optionRoot.getChildren().add(bgmSlider);
		optionRoot.setSpacing(10);
		optionRoot.getChildren().add(ingame);
		optionRoot.setSpacing(10);
		optionRoot.getChildren().add(inGameSlider);
		return optionRoot;
	}

	// This function plays an in-game bgm respected to the current level
	public void playLevelBgm(int level) {
		switch (level) {
		case 1:
			musicPlayer1.setOnEndOfMedia(new Runnable() {
				public void run() {
					musicPlayer1.seek(Duration.ZERO);
				}
			});
			musicPlayer1.play();
			break;
		case 2:
			musicPlayer2.setOnEndOfMedia(new Runnable() {
				public void run() {
					musicPlayer2.seek(Duration.ZERO);
				}
			});
			musicPlayer2.play();
			break;
		case 3:
			musicPlayer3.setOnEndOfMedia(new Runnable() {
				public void run() {
					musicPlayer2.seek(Duration.ZERO);
				}
			});
			musicPlayer3.play();
		}
	}

	// This function stops the in-game bgm that's currently being played
	public void stopLevelBgm(int level) {
		switch (level) {
		case 1:
			musicPlayer1.stop();
			break;
		case 2:
			musicPlayer2.stop();
			break;
		case 3:
			musicPlayer3.stop();
		}
	}

	// This function plays a general bgm
	public void BGM() {
		musicPlayerbgm.setOnEndOfMedia(new Runnable() {
			public void run() {
				musicPlayerbgm.seek(Duration.ZERO);
			}
		});
		musicPlayerbgm.play();
	}

	// Stops BGM
	public void stopBGM() {
		musicPlayerbgm.stop();
	}

	// This function plays a bgm that's for multiplayer
	public void multi() {
		musicPlayerMulti.setOnEndOfMedia(new Runnable() {
			public void run() {
				musicPlayerMulti.seek(Duration.ZERO);
			}
		});
		musicPlayerMulti.play();
	}

	// Stops multiplayer bgm
	public void stopMulti() {
		musicPlayerMulti.stop();
	}

	// Sound effect for Game Over
	public void gameOverSFX() {
		URL path;
		AudioClip gameover;
		path = getClass().getResource("/sfx/gameover.wav");
		gameover = new AudioClip(path.toString());
		gameover.setVolume(1);
		gameover.play();
	}

	// Sound effect for Victory Screen
	public void coinBagSFX() {
		URL path;
		AudioClip coinSFX;

		path = getClass().getResource("/sfx/coinBag.wav");
		coinSFX = new AudioClip(path.toString());
		coinSFX.setVolume(0.2);
		coinSFX.play();
	}

	// Sound effect for Sword Break
	public void swordBreakSFX() {
		URL path;
		AudioClip swordBroke;
		path = getClass().getResource("/sfx/swordBreak.wav");
		swordBroke = new AudioClip(path.toString());
		swordBroke.setVolume(1);
		swordBroke.play();
	}

	// Sound effect for Sword Get
	public void swordGetSFX() {
		URL path;
		AudioClip swordGet;
		path = getClass().getResource("/sfx/swordGet.wav");
		swordGet = new AudioClip(path.toString());
		swordGet.setVolume(1);
		swordGet.play();
	}

	// Sound effect for Slashing
	public void slashSFX() {
		URL path;
		AudioClip slash;
		path = getClass().getResource("/sfx/slash.wav");
		slash = new AudioClip(path.toString());
		slash.setVolume(1);
		slash.play();
	}

	// Sound effect for player's death
	public void deadSFX() {
		URL path;
		AudioClip dead;
		path = getClass().getResource("/sfx/dead.wav");
		dead = new AudioClip(path.toString());
		dead.setVolume(0.1);
		dead.play();
	}

	public void coinSFX() {
		URL path;
		AudioClip coin;
		path = getClass().getResource("/sfx/Coin.wav");
		coin = new AudioClip(path.toString());
		coin.setVolume(0.05);
		coin.play();
	}

}
