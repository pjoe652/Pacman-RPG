package controller.view;


import java.io.IOException;

import java.util.Optional;
import java.util.ResourceBundle;

import controller.main;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;
import javafx.util.Duration;

public class welcomeControl {
	
	
	
	@FXML
	public void handleStartButton(ActionEvent event) throws Exception {
		//Switch to the menu scene
		
		
			//music();
			Parent parent = FXMLLoader.load(getClass().getResource("menu_buttons.fxml"));			
			
			Scene scene = new Scene(parent);
			Stage menuStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			menuStage.setScene(scene);
			menuStage.show();
			
			
						
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
	public void handleSkipButton(ActionEvent event) throws Exception {
		
		levelSelect select = new levelSelect();
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		select.selectLevel(stage);
		
	}
	

	
//	public void music() throws Exception {
//		
//		AudioClip sound = new AudioClip(getClass().getResource("/se1.wav").toString());
//		
//		sound.play();
//		
//	}
	
	
	
	
	
	
	
	
}
