package controller.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import controller.main;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Duration;


public class menuControl {
	
	
	
	@FXML
	public void handleSinglePlayerButton(ActionEvent event) {
		
		try {
			Parent parent = FXMLLoader.load(getClass().getResource("characterCreation.fxml"));
			Scene scene = new Scene(parent);			
			Stage characterStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			characterStage.setScene(scene);
			characterStage.show();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	@FXML
	public void handleReturnButton(ActionEvent event) throws Exception {
		//Return to the welcome scene
		
			
			Parent parent = FXMLLoader.load(getClass().getResource("root_buttons.fxml"));			
			Scene scene = new Scene(parent);
			Stage menuStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			menuStage.setScene(scene);
			menuStage.show();	
	}
	
	



	
}
