package controller;


import java.io.File;
import java.io.IOException;

import controller.model.MusicPlayer;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.effect.BlurType;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

public class main extends Application {
	@FXML Stage primaryStage;
	@FXML AnchorPane rootPane;
	
	@Override
	public void start(Stage primaryStage) throws Exception{
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Dungeon and Pacman");		
		
		
		
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(main.class.getResource("view/Welcome.fxml"));
		Parent root = loader.load();
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.sizeToScene();
		primaryStage.show();
		
		MusicPlayer musicplayer = new MusicPlayer();
		musicplayer.playMusic();
		
	}
	
//	public void initialize(){
//		
//		mediaview.setMediaPlayer(player);
//	}
	
//	public void fadeOut() {
//		
//		FadeTransition fade = new FadeTransition();
//		fade.setDuration(Duration.millis(1000));
//		fade.setNode(rootPane);
//		fade.setFromValue(1);
//		fade.setFromValue(0);
//		
//		fade.setOnFinished((ActionEvent event)->{
//			loadNextScene();
//		});
//		
//		fade.play();	
//		
//	}
//	
	
	public Stage getStage() {
		return primaryStage;
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
