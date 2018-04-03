package controller.view;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import controller.main;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class welcomeControl {
	
	
	
	@FXML
	public void handleStartButton(ActionEvent event) {
		//Switch to the menu scene
		try {
			
			Parent parent = FXMLLoader.load(getClass().getResource("menu_buttons.fxml"));
			
			Scene scene = new Scene(parent);
			Stage menuStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			menuStage.setScene(scene);
			menuStage.show();
			
			
		} catch (IOException e) {
			e.printStackTrace();
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
	
	
	
	
	
}
