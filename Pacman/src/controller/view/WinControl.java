package controller.view;

import java.io.IOException;

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

public class WinControl extends characterControl {

	@FXML private Label score;
	private int SCORE;
	private String Score;
	

	
	@FXML public void handleNextButton(ActionEvent event) throws IOException {
		player.addPoints();
		Parent parent = FXMLLoader.load(getClass().getResource("upgrade.fxml"));
		Scene scene = new Scene(parent);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
	}
	
	@FXML
	public void handleNextKeyPressed(KeyEvent event) throws IOException {
		if (event.getCode() == KeyCode.ENTER) {
			player.addPoints();
			Parent parent = FXMLLoader.load(getClass().getResource("upgrade.fxml"));
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
		SCORE = player.getScore();
		//lvlOne.resetScore();
		Score = Integer.toString(SCORE);
		score.setText(Score);
	}
	
}
