package controller.view;

import java.io.IOException;
import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class characterControl {

	@FXML protected static String characterName;
	@FXML protected TextField getName;
	
	
	@FXML
	public void handleEnterButton(ActionEvent event) {
		
		if (getName.getText().isEmpty()) {
			Alert warning = new Alert(AlertType.ERROR);
			warning.setTitle("Error");
			warning.setHeaderText("Input name cannot be recognised");
			warning.setContentText("Please do not leave the character name empty");
			warning.showAndWait();
		} else {
			setCharacterName(getName.getText());
			System.out.println("Entered name is: \"" + characterName + "\"");
		}
		
		
		
	}
	
	@FXML
	public void handleNextButton(ActionEvent event) {
		
		try {
			if (getCharacterName() == null) {
				Alert warning = new Alert(AlertType.CONFIRMATION);
				warning.setTitle("Confirmation");
				warning.setHeaderText("Are you sure to have a default name? \n Default name: Andrew Chan");
				
				ButtonType no = new ButtonType("No");
				ButtonType yes = new ButtonType("Yes");

				warning.getButtonTypes().setAll(yes, no);				
				Optional<ButtonType> result = warning.showAndWait();
				if (result.get() == yes) {
					setCharacterName("Andrew Chan");				
				}				
				
			}
			
			if (getCharacterName() != null) {
				Parent parent = FXMLLoader.load(getClass().getResource("Intro.fxml"));
				Scene scene = new Scene(parent);
				Stage introStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
				introStage.setScene(scene);
				introStage.show();	
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	public void handleReturnButton(ActionEvent event) throws Exception {
		Parent parent = FXMLLoader.load(getClass().getResource("menu_buttons.fxml"));
		Scene scene = new Scene(parent);
		Stage introStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		introStage.setScene(scene);
		introStage.show();
	}
	
	public String getCharacterName() {
		return characterName;
	}
	
	public void setCharacterName(String characterName) {
		characterControl.characterName = characterName;
	}
	
	
	
}








