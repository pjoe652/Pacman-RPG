package controller.view;

import java.io.IOException;
import controller.model.*;
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
	
	

	@FXML protected TextField getName;
	
	@FXML public static character player = new character();
	
	@FXML
	public void handleEnterButton(ActionEvent event) {
		
		if (getName.getText().isEmpty()) {
			Alert warning = new Alert(AlertType.ERROR);
			warning.setTitle("Error");
			warning.setHeaderText("Input name cannot be recognised");
			warning.setContentText("Please do not leave the character name empty");
			warning.showAndWait();
		} else {
			//setCharacterName(getName.getText());
			String name = getName.getText();
			player.setName(name);
			System.out.println("Entered name is: \"" + player.getName() + "\"");
		}
	}
	
	@FXML
	public void handleNextButton(ActionEvent event) throws Exception {
		
		
			if (player.getGender() == ' ') {
				Alert warning = new Alert(AlertType.ERROR);
				warning.setTitle("Error");
				warning.setHeaderText("No character model detected");
				warning.setContentText("Please select a character model");
				warning.showAndWait();
			}
			if (player.getGender() != ' ' && player.getName() == null) {
				Alert warning = new Alert(AlertType.CONFIRMATION);
				warning.setTitle("Confirmation");
				warning.setHeaderText("Are you sure to have a default name? \n Default name: Andrew Chen");
				
				ButtonType no = new ButtonType("No");
				ButtonType yes = new ButtonType("Yes");

				warning.getButtonTypes().setAll(yes, no);				
				Optional<ButtonType> result = warning.showAndWait();
				if (result.get() == yes) {
					//setCharacterName("Andrew Chan");
					player.setName("Andrew Chen");
				}				
				
			}			
			
			if (player.getName() != null && player.getGender() != ' ') {
				
				Alert warning = new Alert(AlertType.CONFIRMATION);
				warning.setTitle("Confirmation");
				warning.setHeaderText("Confirm your character detail");
				warning.setContentText(" character name: " + player.getName() + "\n gender: " + player.getGender() + "\n");
				ButtonType Return = new ButtonType("Return");
				ButtonType Accept = new ButtonType("Accept");

				warning.getButtonTypes().setAll(Accept, Return);				
				Optional<ButtonType> result = warning.showAndWait();
				
				if (result.get() == Accept) {
//					Parent parent = FXMLLoader.load(getClass().getResource("Intro.fxml"));
//					Scene scene = new Scene(parent);
//					Stage introStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//					introStage.setScene(scene);
//					introStage.show();	
					
					levelOne lvlOne = new levelOne();
					lvlOne.displayDialogue(event);
					
				}
				
			}
			
		
		
		
	}
	
	@FXML
	public void handleClickedButton() {
		Alert warning = new Alert(AlertType.CONFIRMATION);
		warning.setTitle("Confirmation");
		warning.setHeaderText("Please click yes to confirm");
		ButtonType no = new ButtonType("No");
		ButtonType yes = new ButtonType("Yes");

		warning.getButtonTypes().setAll(yes, no);				
		Optional<ButtonType> result = warning.showAndWait();
		
		if (result.get() == yes) {
			player.setGender('M');
			System.out.println("Selected male");
		}
	}
	
	@FXML
	public void handleFemaleClickedButton() {
		Alert warning = new Alert(AlertType.CONFIRMATION);
		warning.setTitle("Confirmation");
		warning.setHeaderText("Please click yes to confirm");
		ButtonType no = new ButtonType("No");
		ButtonType yes = new ButtonType("Yes");

		warning.getButtonTypes().setAll(yes, no);				
		Optional<ButtonType> result = warning.showAndWait();
		
		if (result.get() == yes) {
			player.setGender('F');
			System.out.println("Selected female");
		}
	}
	
	@FXML
	public void handleReturnButton(ActionEvent event) throws Exception {
		Parent parent = FXMLLoader.load(getClass().getResource("menu_buttons.fxml"));
		Scene scene = new Scene(parent);
		Stage introStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		introStage.setScene(scene);
		introStage.show();
	}
	
	
	
	
		
}








