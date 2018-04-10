package controller.view;

import java.io.IOException;

import controller.model.level;
import controller.model.levelOne;
import controller.model.mapOne;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.util.Duration;

public class levelSelect extends characterControl {
	
	private Stage currentStage;
	
	public void selectLevel(Stage stage) throws Exception {
		
		currentStage = stage;
		
		level lvl = new level();
		System.out.print("current level: " + lvl.getLevel());
		Parent parent = FXMLLoader.load(getClass().getResource("loading.fxml"));
		Scene scene = new Scene(parent);
		currentStage.setScene(scene);
		currentStage.show();
		
		PauseTransition ps = new PauseTransition(Duration.seconds(1));
		ps.setOnFinished((ActionEvent event) ->{
			switch (lvl.getLevel()) {
			case 1:
				mapOne levelOne = new mapOne();
				try {
					levelOne.mapGeneration(currentStage);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			default:
				System.out.println("ERROR");
				break;

			}
		});
		ps.play();
			
	}
	
	//Level Completion
	public void levelFinish(Stage stage) {
		currentStage = stage;
		
		try {
			Parent parent = FXMLLoader.load(getClass().getResource("upgrade.fxml"));
			Scene scene = new Scene(parent);

			currentStage.setScene(scene);
			currentStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	//Level Failed
	public void levelFailed(Stage stage) {
		currentStage = stage;
		
		try {
			Parent parent = FXMLLoader.load(getClass().getResource("GameOver.fxml"));
			Scene scene = new Scene(parent);

			currentStage.setScene(scene);
			currentStage.show();
						
		} catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	
	
	
}
