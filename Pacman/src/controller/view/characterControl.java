package controller.view;

import java.io.IOException;
import java.util.Optional;

import controller.model.character;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class characterControl {

	/*
	 * This class controls characterCreation.fxml
	 */

	@FXML
	protected TextField getName;

	@FXML
	private Circle blackKnight;
	@FXML
	private Circle female;
	@FXML
	private Circle beardKnight;

	@FXML
	public static character player = new character();

	/*
	 * When Enter is pressed, it immediately collects data from the text field. If
	 * null is detected, an error dialog will pop notifying the player that it is
	 * not recognized Else, the name of the player will then be stored
	 */
	@FXML
	public void handleEnterButton(ActionEvent event) {

		if (getName.getText().isEmpty()) {
			Alert warning = new Alert(AlertType.ERROR);
			warning.setTitle("Error");
			warning.setHeaderText("Input name cannot be recognised");
			warning.setContentText("Please do not leave the character name empty");
			warning.showAndWait();
		} else {
			String name = getName.getText();
			player.setName(name);
		}
	}

	@FXML
	public void handleEnterKeyPressed(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {
			if (getName.getText().isEmpty()) {
				Alert warning = new Alert(AlertType.ERROR);
				warning.setTitle("Error");
				warning.setHeaderText("Input name cannot be recognised");
				warning.setContentText("Please do not leave the character name empty");
				warning.showAndWait();
			} else {
				// setCharacterName(getName.getText());
				String name = getName.getText();
				player.setName(name);
			}
		}
	}

	/*
	 * When Next is pressed, if no inputs are detected, default name and model will
	 * be chosen for the player Then the screen switches to story
	 */
	@FXML
	public void handleNextButton(ActionEvent event) throws Exception {

		if (player.getName() == null) {
			player.setName("Pacman");
		}

		if (player.getName() != null && player.getModel() != "") {
			levelOne lvlOne = new levelOne();
			lvlOne.displayDialogue(event);
		}

	}

	@FXML
	public void handleNextKeyPressed(KeyEvent event) throws Exception {
		if (event.getCode() == KeyCode.ENTER) {
			if (player.getName() == null) {
				player.setName("Pacman");
			}

			if (player.getName() != null && player.getModel() != "") {
				levelOne lvlOne = new levelOne();
				lvlOne.displayDialogueKeyPressed(event);
			}
		}
	}

	// These following functions upon clicked or selected
	// will set skin model for the player
	@FXML
	public void handleBlackKnightClickedButton() {
		player.setModel("Black Knight");
		blackKnight.setOpacity(1);
		female.setOpacity(0);
		beardKnight.setOpacity(0);
	}

	@FXML
	public void handleBlackKnightKeyPressed(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {
			player.setModel("Black Knight");
			blackKnight.setOpacity(1);
			female.setOpacity(0);
			beardKnight.setOpacity(0);
		}
	}

	@FXML
	public void handleFemaleKeyPressed(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {
			player.setModel("Female Knight");
			blackKnight.setOpacity(0);
			female.setOpacity(1);
			beardKnight.setOpacity(0);
		}
	}

	@FXML
	public void handleFemaleClickedButton() {
		player.setModel("Female Knight");
		blackKnight.setOpacity(0);
		female.setOpacity(1);
		beardKnight.setOpacity(0);
	}

	@FXML
	public void handleBeardedKnightClickedButton() {
		player.setModel("Bearded Knight");
		blackKnight.setOpacity(0);
		female.setOpacity(0);
		beardKnight.setOpacity(1);
	}

	@FXML
	public void handleBeardedKnightKeyPressed(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {
			player.setModel("Bearded Knight");
			blackKnight.setOpacity(0);
			female.setOpacity(0);
			beardKnight.setOpacity(1);
		}
	}

	// This function switches screen back to intro
	@FXML
	public void handleReturnButton(ActionEvent event) throws Exception {
		Parent parent = FXMLLoader.load(getClass().getResource("Menu.fxml"));
		Scene scene = new Scene(parent);
		Stage introStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		introStage.setScene(scene);
		introStage.show();
	}

	@FXML
	public void handleReturnKeyPressed(KeyEvent event) throws IOException {
		if (event.getCode() == KeyCode.ENTER) {
			Parent parent = FXMLLoader.load(getClass().getResource("Menu.fxml"));
			Scene scene = new Scene(parent);
			Stage introStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			introStage.setScene(scene);
			introStage.show();
		}

	}

}
