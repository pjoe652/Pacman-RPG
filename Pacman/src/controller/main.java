package controller;

import controller.model.MusicPlayer;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class main extends Application {
	@FXML
	Stage primaryStage;
	@FXML
	AnchorPane rootPane;

	@Override
	public void start(Stage primaryStage) throws Exception {
		// Set title
		primaryStage.setTitle("Dungeon and Pacman");

		// This creates the Intro
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(main.class.getResource("view/Welcome.fxml"));
		Parent root = loader.load();
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.sizeToScene();
		primaryStage.show();

		// Plays BGM
		MusicPlayer musicplayer = new MusicPlayer();
		musicplayer.BGM();
		musicplayer.initVolume();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
