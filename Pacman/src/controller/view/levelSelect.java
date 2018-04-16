package controller.view;

import java.io.IOException;

import controller.model.level;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.util.Duration;

public class levelSelect extends characterControl {
	
	private Stage currentStage;
	
	@FXML private Label score;
	@FXML private Label time;
	private Integer TIME;
	private String Time;
	private int SCORE;
	private String Score;
	
	public void selectLevel(Stage stage) throws Exception {
		
		currentStage = stage;
		LevelOneStage lvlStage = new LevelOneStage();
		LevelTwoStage lvlStage2 = new LevelTwoStage();
		level lvl = new level();
		//System.out.print("current level: " + lvl.getLevel());
//		Parent parent = FXMLLoader.load(getClass().getResource("loading.fxml"));
//		Scene scene = new Scene(parent);
//		currentStage.setScene(scene);
//		currentStage.show();
		
		PauseTransition ps = new PauseTransition(Duration.seconds(0));
		ps.setOnFinished((ActionEvent event) ->{
			switch (lvl.getLevel()) {
			case 1:
				
				try {
					lvlStage.mapGeneration(currentStage);
//					SCORE = lvlStage.getScore();
					lvlStage.resetScore();
//					TIME = lvlStage.getTime();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 2:
				try {
					lvlStage2.mapGeneration(currentStage);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			default:
				System.out.println("ERROR");
				break;

			}
		});
		ps.play();
			
	}
	
	public void selectStory(ActionEvent event) throws Exception {
		levelTwo lvlTwo = new levelTwo();
		levelThree lvlThree = new levelThree();
		level lvl = new level();
		System.out.print("current level: " + lvl.getLevel());
		switch(lvl.getLevel()) {
		case 2:
			lvlTwo.displayDialogue(event);
			break;
		case 3:
			lvlThree.displayDialogue(event);
			break;
		default: System.out.println("ERROR"); break;
		}
		
	}
	
	//Level Clear
	@FXML
	public void levelClear(Stage stage) throws IOException {
		level lvl = new level();
		switch(lvl.getLevel()) {
		case 1: 
			Parent parent = FXMLLoader.load(getClass().getResource("VictoryScreen.fxml"));
			Scene scene = new Scene(parent);
			currentStage = stage;			
			currentStage.setScene(scene);
			currentStage.show();
			break;
		default: System.out.println("ERROR");		
		}
		
		
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
