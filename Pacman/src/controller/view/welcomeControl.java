package controller.view;

import java.io.IOException;
import java.util.Optional;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class welcomeControl {
	/*
	 * This class controls Welcome.fxml
	 */

	@FXML
	public void handleStartButton(ActionEvent event) throws Exception {

		Parent parent = FXMLLoader.load(getClass().getResource("Menu.fxml"));
		Scene scene = new Scene(parent);
		Stage menuStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		menuStage.setScene(scene);
		menuStage.show();

	}

	@FXML
	public void handleStartOnKeyPressed(KeyEvent event) throws IOException {
		if (event.getCode() == KeyCode.ENTER) {
			Parent parent = FXMLLoader.load(getClass().getResource("Menu.fxml"));
			Scene scene = new Scene(parent);
			Stage menuStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			menuStage.setScene(scene);
			menuStage.show();
		}
	}

	@FXML
	public void handleExitButton(ActionEvent event) {

		Alert warning = new Alert(AlertType.CONFIRMATION);
		warning.setTitle("Confirmation");
		warning.setHeaderText("Are you sure to exit?");

		ButtonType no = new ButtonType("No");
		ButtonType yes = new ButtonType("Yes");

		warning.getButtonTypes().setAll(yes, no);

		Optional<ButtonType> result = warning.showAndWait();

		if (result.get() == yes) {
			Platform.exit();
			System.exit(0);
		}

	}

	@FXML
	public void handleExitOnKeyPressed(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {
			Alert warning = new Alert(AlertType.CONFIRMATION);
			warning.setTitle("Confirmation");
			warning.setHeaderText("Are you sure to exit?");

			ButtonType no = new ButtonType("No");
			ButtonType yes = new ButtonType("Yes");

			warning.getButtonTypes().setAll(yes, no);

			Optional<ButtonType> result = warning.showAndWait();

			if (result.get() == yes) {
				Platform.exit();
				System.exit(0);
			}
		}
	}

}
