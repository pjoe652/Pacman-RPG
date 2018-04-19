package controller.view;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class levelSelect extends characterControl {
	/*
	 * This class is used to display story/level/victory/gameOver
	 */

	private Stage currentStage;

	private boolean lvlOneMade = false;
	private boolean lvlTwoMade = false;
	private boolean lvlThreeMade = false;

	public void selectLevel(Stage stage) throws Exception {

		currentStage = stage;
		LevelOneStage lvlStage = new LevelOneStage();
		LevelTwoStage lvlStage2 = new LevelTwoStage();
		LevelThreeStage lvlStage3 = new LevelThreeStage();

		if ((player.getLevel() == 1) && (!lvlOneMade)) {
			lvlOneMade = true;
			try {
				lvlStage.mapGeneration(currentStage);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if ((player.getLevel() == 2) && (!lvlTwoMade)) {
			lvlTwoMade = true;
			try {
				lvlStage2.mapGeneration(currentStage);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if ((player.getLevel() == 3) && (!lvlThreeMade)) {
			lvlThreeMade = true;
			try {
				lvlStage3.mapGeneration(currentStage);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("ERROR SELECT LEVEL");
		}

	}
	
	//This function displays story depending on the current level
	public void selectStory(ActionEvent event) throws Exception {
		levelTwo lvlTwo = new levelTwo();
		levelThree lvlThree = new levelThree();
		FinishGame finalStory = new FinishGame();
		switch (player.getLevel()) {
		case 2:
			lvlTwo.displayDialogue(event);
			break;
		case 3:
			lvlThree.displayDialogue(event);
			break;
		default:
			finalStory.displayDialogue(event);
			break;
		}

	}

	public void selectStoryKeyPressed(KeyEvent event) throws Exception {
		levelTwo lvlTwo = new levelTwo();
		levelThree lvlThree = new levelThree();
		FinishGame finalStory = new FinishGame();
		switch (player.getLevel()) {
		case 2:
			lvlTwo.displayDialogueKeyPressed(event);
			break;
		case 3:
			lvlThree.displayDialogueKeyPressed(event);
			break;
		default:
			finalStory.displayDialogueKeyPressed(event);
			break;
		}
	}

	// Level Clear
	public void levelClear(Stage stage) throws IOException {
		Parent parent = FXMLLoader.load(getClass().getResource("VictoryScreen.fxml"));
		Scene scene = new Scene(parent);
		currentStage = stage;
		currentStage.setScene(scene);
		currentStage.show();
	}

	// Level Completion
	public void levelFinish(Stage stage) throws IOException {
		currentStage = stage;

		Parent parent = FXMLLoader.load(getClass().getResource("upgrade.fxml"));
		Scene scene = new Scene(parent);

		currentStage.setScene(scene);
		currentStage.show();

	}

	// Level Failed
	public void levelFailed(Stage stage) {
		currentStage = stage;

		try {
			Parent parent = FXMLLoader.load(getClass().getResource("GameOver.fxml"));
			Scene scene = new Scene(parent);

			currentStage.setScene(scene);
			currentStage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
