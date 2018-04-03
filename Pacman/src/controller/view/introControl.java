package controller.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.lang.reflect.Field;

import controller.view.characterControl;
import javafx.scene.control.Label;

public class introControl extends characterControl {

	@FXML protected Label sceneOne;
	@FXML protected String name;
	
	public void getName() {
		name = getCharacterName();
	}
	
	@FXML
	public void initialize() throws Exception {
		
		
	
		sceneOne.setText("Mother: Wake up, " + characterName + " Your father has come back to the village!\n" +	
		"Mother: The Diamond class Adventurer!\n" +
		characterName + ": Really!? I can't wait to see him. It's been 12 years since he left to seek his adventurous life."
		
				
				);
	}
	
	
		
	
}
