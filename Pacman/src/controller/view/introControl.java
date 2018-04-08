package controller.view;

import javafx.event.ActionEvent;
import controller.model.*;
import controller.view.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import controller.view.characterControl;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class introControl extends characterControl {

	@FXML private Label sceneOne;	
	
	
	@FXML
	public void initialize() throws Exception {
		
		String name = player.getName();
		System.out.println(name);
		
		sceneOne.setText("Mother: Wake up, " + name + " Your father has come back to the village!\n" +	
		"Mother: The Diamond class Adventurer!\n" + name
		 + ": Really!? I can't wait to see him. It's been 12 years since he left to seek his adventurous life."
		
				
				);
	}
	
	@FXML
	public void handleSkipButton(ActionEvent event) throws Exception { 
		Parent parent = FXMLLoader.load(getClass().getResource("upgrade.fxml"));
		Scene scene = new Scene(parent);
		Stage introStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		introStage.setScene(scene);
		introStage.show();	
		
	}
	
		
	
}
