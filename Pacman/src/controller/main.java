package controller;


import java.io.IOException;

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

public class main extends Application {
	@FXML Stage primaryStage;
	@FXML AnchorPane rootPane;
	
	@Override
	public void start(Stage primaryStage) throws Exception{
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Dungeon and Pacman");		
		
		
		
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(main.class.getResource("view/root_buttons.fxml"));
		Parent root = loader.load();
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();
		
		
	}
	
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
//	public void loadNextScene() {
//		try {
//			FXMLLoader loader = new FXMLLoader();
//			loader.setLocation(main.class.getResource("view/root_buttons.fxml"));
//			Parent root = loader.load();
//			Scene scene = new Scene(root);
//			primaryStage.setScene(scene);
//			primaryStage.show();		
//			
//			
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
	
	
	
	public void initialize() {
		
	}
	
	
	

	public static void main(String[] args) {
		launch(args);
	}
}
