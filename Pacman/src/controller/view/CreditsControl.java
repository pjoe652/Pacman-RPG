package controller.view;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class CreditsControl extends characterControl {
	/*
	 * This class controls Credits.fxml
	 */

	// If Next is clicked, the screen returns back to intro
	@FXML
	public void handleNextButton(ActionEvent event) throws Exception {
		player.resetHp();
		player.resetSpeed();
		player.resetPower();
		player.resetScore();
		player.setLevel(1);
		Parent parent = FXMLLoader.load(getClass().getResource("Welcome.fxml"));
		Scene scene = new Scene(parent);
		Stage introStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		introStage.setScene(scene);
		introStage.show();
	}

	@FXML
	public void handleNextKeyPressed(KeyEvent event) throws IOException {
		if (event.getCode() == KeyCode.ENTER) {
			player.resetHp();
			player.resetSpeed();
			player.resetPower();
			player.setLevel(1);
			Parent parent = FXMLLoader.load(getClass().getResource("Welcome.fxml"));
			Scene scene = new Scene(parent);
			Stage introStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			introStage.setScene(scene);
			introStage.show();
		}
	}

}
