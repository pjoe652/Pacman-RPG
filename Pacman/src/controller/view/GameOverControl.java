package controller.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class GameOverControl extends characterControl {
	/*
	 * This class controls GameOver.fxml
	 */
	// When Retry is clicked, the screen switches back to the corresponding level
	@FXML
	public void handleRetryButton(ActionEvent event) throws Exception {
		levelSelect select = new levelSelect();
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		select.selectLevel(stage);
	}

	@FXML
	public void handleRetryKeyPressed(KeyEvent event) throws Exception {
		if (event.getCode() == KeyCode.ENTER) {
			levelSelect select = new levelSelect();
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			select.selectLevel(stage);
		}

	}

}
