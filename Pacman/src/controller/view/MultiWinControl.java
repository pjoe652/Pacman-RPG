package controller.view;

import java.io.IOException;

import controller.model.MusicPlayer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class MultiWinControl extends characterControl {
	/*
	 * This class controls MultiVictoryScreen
	 */

	private MusicPlayer music = new MusicPlayer();
	@FXML
	private Label score;
	private int SCORE;
	private String Score;

	@FXML
	public void handleNextButton(ActionEvent event) throws IOException {
		music.BGM();

		Parent parent = FXMLLoader.load(getClass().getResource("Welcome.fxml"));
		Scene scene = new Scene(parent);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
	}

	@FXML
	public void handleNextKeyPressed(KeyEvent event) throws IOException {
		if (event.getCode() == KeyCode.ENTER) {
			music.BGM();
			Parent parent = FXMLLoader.load(getClass().getResource("Welcome.fxml"));
			Scene scene = new Scene(parent);
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.setScene(scene);
			stage.show();
		}
	}

	@FXML
	public void initialize() {
		displayScore();
	}

	public void displayScore() {
		MultiPlayerStage mps = new MultiPlayerStage();
		SCORE = mps.getScore();
		mps.resetScore();
		Score = Integer.toString(SCORE);
		score.setText(Score);
	}
}
