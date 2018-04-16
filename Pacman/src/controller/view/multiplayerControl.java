package controller.view;

import controller.model.*;
import controller.view.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class multiplayerControl {
	
	private Stage stage;
	
	//Character
	public static character characterOne = new character();
	@FXML private ImageView playerOne = new ImageView();
	private int playerOneRotate = 0;
	@FXML private ImageView playerTwo = new ImageView();
	public static enemies characterTwo = new enemies();
	private int playerTwoRotate = 1;
	@FXML private ImageView playerThree = new ImageView();
	public static enemies characterThree = new enemies();
	private int playerThreeRotate = 1;
	
	
	
	@FXML
	public void initialize () throws Exception {
		
		displayCharacter(characterOne, playerOne);
		displayEnemy(characterTwo, playerTwo);
		displayEnemy(characterThree, playerThree);
		
	}
	
	@FXML public void handleNextButtonCharOne () {
		if (playerOneRotate == 0) {
			characterOne.setModel("Black Knight");
			displayCharacter(characterOne, playerOne);
			playerOneRotate++;
		} else if (playerOneRotate == 1) {
			characterOne.setModel("Female Knight");
			displayCharacter(characterOne, playerOne);
			playerOneRotate++;
		} else if (playerOneRotate == 2) {
			characterOne.setModel("Bearded Knight");
			displayCharacter(characterOne, playerOne);
			playerOneRotate = 0;
		}
	}
	
	@FXML public void handleMinusButtonCharOne () {
		if (playerOneRotate == 0) {
			characterOne.setModel("Black Knight");
			displayCharacter(characterOne, playerOne);
			playerOneRotate = 2;
		} else if (playerOneRotate == 1) {
			characterOne.setModel("Female Knight");
			displayCharacter(characterOne, playerOne);
			playerOneRotate--;
		} else if (playerOneRotate == 2) {
			characterOne.setModel("Bearded Knight");
			displayCharacter(characterOne, playerOne);
			playerOneRotate--;
		}
	}
	
	
	
	@FXML public void handleNextButtonCharTwo () {
		if (playerTwoRotate == 0) {
			characterTwo.setModel("Blue");
			displayEnemy(characterTwo, playerTwo);
			playerTwoRotate++;
		} else if (playerTwoRotate == 1) {
			characterTwo.setModel("Light Blue");
			displayEnemy(characterTwo, playerTwo);
			playerTwoRotate++;
		} else if (playerTwoRotate == 2) {
			characterTwo.setModel("Pink");
			displayEnemy(characterTwo, playerTwo);
			playerTwoRotate++;
		} else if (playerTwoRotate == 3) {
			characterTwo.setModel("Purple");
			displayEnemy(characterTwo, playerTwo);
			playerTwoRotate++;
		} else if (playerTwoRotate == 4) {
			characterTwo.setModel("Yellow");
			displayEnemy(characterTwo, playerTwo);
			playerTwoRotate = 0;
		}

	}
	
		@FXML public void handleMinusButtonCharTwo () {
			if (playerTwoRotate == 0) {
				characterTwo.setModel("Blue");
				displayEnemy(characterTwo, playerTwo);
				playerTwoRotate = 4;
			} else if (playerTwoRotate == 1) {
				characterTwo.setModel("Light Blue");
				displayEnemy(characterTwo, playerTwo);
				playerTwoRotate--;
			} else if (playerTwoRotate == 2) {
				characterTwo.setModel("Pink");
				displayEnemy(characterTwo, playerTwo);
				playerTwoRotate--;
			} else if (playerTwoRotate == 3) {
				characterTwo.setModel("Purple");
				displayEnemy(characterTwo, playerTwo);
				playerTwoRotate--;
			} else if (playerTwoRotate == 4) {
				characterTwo.setModel("Yellow");
				displayEnemy(characterTwo, playerTwo);
				playerTwoRotate--;
			}
		}
	
		@FXML public void handleNextButtonCharThree () {
			if (playerThreeRotate == 0) {
				characterThree.setModel("Blue");
				displayEnemy(characterThree, playerThree);
				playerThreeRotate++;
			} else if (playerThreeRotate == 1) {
				characterThree.setModel("Light Blue");
				displayEnemy(characterThree, playerThree);
				playerThreeRotate++;
			} else if (playerThreeRotate == 2) {
				characterThree.setModel("Pink");
				displayEnemy(characterThree, playerThree);
				playerThreeRotate++;
			} else if (playerThreeRotate == 3) {
				characterThree.setModel("Purple");
				displayEnemy(characterThree, playerThree);
				playerThreeRotate++;
			} else if (playerThreeRotate == 4) {
				characterThree.setModel("Yellow");
				displayEnemy(characterThree, playerThree);
				playerThreeRotate = 0;
			}
		}
	
		@FXML public void handleMinusButtonCharThree () {
			if (playerThreeRotate == 0) {
				characterThree.setModel("Blue");
				displayEnemy(characterThree, playerThree);
				playerThreeRotate = 4;
			} else if (playerThreeRotate == 1) {
				characterThree.setModel("Light Blue");
				displayEnemy(characterThree, playerThree);
				playerThreeRotate--;
			} else if (playerThreeRotate == 2) {
				characterThree.setModel("Pink");
				displayEnemy(characterThree, playerThree);
				playerThreeRotate--;
			} else if (playerThreeRotate == 3) {
				characterThree.setModel("Purple");
				displayEnemy(characterThree, playerThree);
				playerThreeRotate--;
			} else if (playerThreeRotate == 4) {
				characterThree.setModel("Yellow");
				displayEnemy(characterThree, playerThree);
				playerThreeRotate--;
			}
		}
		
		@FXML public void handleNextButton (ActionEvent event) {
			MultiPlayerStage mpstage = new MultiPlayerStage();
			stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			try {
				mpstage.mapGeneration(stage);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	private void displayCharacter(character playerNumber, ImageView selectView) {
		Image character = new Image(playerNumber.getModelDirection("DOWN"));
		selectView.setImage(character);
	}
	
	private void displayEnemy(enemies characterNumber, ImageView selectView) {
		Image enemy = new Image(characterNumber.getEnemyDirection());
		selectView.setImage(enemy);
	}

}
