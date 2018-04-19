package controller.view;

import java.io.IOException;

import controller.model.MusicPlayer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class menuControl {
	/*
	 * This class controls Menu.fxml
	 */

	private Stage optionStage = new Stage();

	@FXML
	public void handleSinglePlayerKeyPressed(KeyEvent event) throws IOException {

		if (event.getCode() == KeyCode.ENTER) {
			Parent parent = FXMLLoader.load(getClass().getResource("characterCreation.fxml"));
			Scene scene = new Scene(parent);
			Stage characterStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			characterStage.setScene(scene);
			characterStage.show();

		}
	}

	@FXML
	public void handleReturnKeyPressed(KeyEvent event) throws IOException {
		if (event.getCode() == KeyCode.ENTER) {
			Parent parent = FXMLLoader.load(getClass().getResource("Welcome.fxml"));
			Scene scene = new Scene(parent);
			Stage characterStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			characterStage.setScene(scene);
			characterStage.show();
		}
	}

	@FXML
	public void handleMultiPlayerKeyPressed(KeyEvent event) throws IOException {
		if (event.getCode() == KeyCode.ENTER) {
			Parent parent = FXMLLoader.load(getClass().getResource("multiplayer.fxml"));
			Scene scene = new Scene(parent);
			Stage characterStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			characterStage.setScene(scene);
			characterStage.show();
		}
	}

	@FXML
	public void handleSinglePlayerButton(ActionEvent event) throws IOException {
		Parent parent = FXMLLoader.load(getClass().getResource("characterCreation.fxml"));
		Scene scene = new Scene(parent);
		Stage characterStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		characterStage.setScene(scene);
		characterStage.show();
	}

	@FXML
	public void handleReturnButton(ActionEvent event) throws Exception {
		// Return to the welcome scene

		Parent parent = FXMLLoader.load(getClass().getResource("Welcome.fxml"));
		Scene scene = new Scene(parent);
		Stage menuStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		menuStage.setScene(scene);
		menuStage.show();
	}

	@FXML
	public void handleMultiPlayerButton(ActionEvent event) throws IOException {

		Parent parent = FXMLLoader.load(getClass().getResource("multiplayer.fxml"));
		Scene scene = new Scene(parent);
		Stage menuStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		menuStage.setScene(scene);
		menuStage.show();

	}

	@FXML
	public void handleOptionKeyPressed(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {
			optionStage.setTitle("Option");
			optionStage.setOpacity(0.9);
			optionStage.initModality(Modality.APPLICATION_MODAL);
			optionStage.initOwner((Stage) ((Node) event.getSource()).getScene().getWindow());

			MusicPlayer volume = new MusicPlayer();
			Scene scene = new Scene(volume.option());
			scene.setOnKeyPressed(e -> {
				if (e.getCode() == KeyCode.ESCAPE) {
					optionStage.close();
				}
			});
			optionStage.setScene(scene);
			optionStage.show();
		}
	}

	@FXML
	public void handleOptionButton(ActionEvent event) {
		optionStage.setTitle("Option");
		optionStage.setOpacity(0.9);
		optionStage.initModality(Modality.APPLICATION_MODAL);
		optionStage.initOwner((Stage) ((Node) event.getSource()).getScene().getWindow());

		MusicPlayer volume = new MusicPlayer();
		Scene scene = new Scene(volume.option());
		scene.setOnKeyPressed(e -> {
			if (e.getCode() == KeyCode.ESCAPE) {
				optionStage.close();
			}
		});
		optionStage.setScene(scene);
		optionStage.show();
	}

}
