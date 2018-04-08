package controller.view;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Scanner;

import controller.main;
import controller.model.character;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Duration;


public class menuControl {
	
	
	@FXML
	public void handleSinglePlayerKeyPressed(KeyEvent event) throws IOException {
		switch (event.getCode()) {
			case ENTER:
				Parent parent = FXMLLoader.load(getClass().getResource("characterCreation.fxml"));
				Scene scene = new Scene(parent);
				Stage characterStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
				characterStage.setScene(scene);
				break;
		}
	}
	
	@FXML
	public void handleReturnKeyPressed(KeyEvent event) throws IOException {
		switch (event.getCode()) {
			case ENTER:
				Parent parent = FXMLLoader.load(getClass().getResource("root_buttons.fxml"));
				Scene scene = new Scene(parent);
				Stage characterStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
				characterStage.setScene(scene);
				break;
		}
	}
	
	
	
	@FXML
	public void handleSinglePlayerButton(ActionEvent event) throws IOException {
		Parent parent = FXMLLoader.load(getClass().getResource("characterCreation.fxml"));
		Scene scene = new Scene(parent);
		Stage characterStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		characterStage.setScene(scene);
		
		
	}
	
	@FXML
	public void handleLoadButton(ActionEvent event) throws Exception {
		
		Parent parent = FXMLLoader.load(getClass().getResource("loadingScreen.fxml"));			
		Scene scene = new Scene(parent);
		Stage loadStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		loadStage.setScene(scene);
		loadStage.show();	
		
		
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
