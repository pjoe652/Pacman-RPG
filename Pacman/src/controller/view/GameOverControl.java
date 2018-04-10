package controller.view;

import controller.model.character;
import controller.model.mapOne;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class GameOverControl {
	
	
	@FXML Label score = new Label();
	
	
	@FXML
	public void initialize() {
		
		displayScore();
	}
	
	@FXML
	public void handleRetryButton(ActionEvent event) throws Exception {
		levelSelect select = new levelSelect();
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		select.selectLevel(stage);	
	}
	
	
	public void displayScore() {
		mapOne score = new mapOne();
		int intScore = score.getScore();
		String strScore = Integer.toString(intScore);
		this.score.setText(strScore);
		
		score.resetScore();
		
	}
	
	
	
	
	
}
