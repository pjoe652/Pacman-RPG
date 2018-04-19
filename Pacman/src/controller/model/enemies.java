package controller.model;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class enemies {
	/*
	 * This class contains the enemy models
	 */
	// Slime models
	protected String model;
	private String modelLocation;
	private Node returnNode;

	// Constructor
	public enemies() {
		model = "Blue";
	}

	// Set slime model
	public void setModel(String model) {

		this.model = model;
	}

	// Get slime model
	public String getModel() {

		return this.model;
	}

	// ------------------------------Enemy Model--------------------------
	// These following functions create in game slime models
	public Node redEnemy(boolean multiplayer) {
		Image redImage = new Image("character/SlimeEnemy/RedSlime.gif");
		ImageView red = new ImageView(redImage);
		if (multiplayer) {
			red.setFitHeight(36);
			red.setFitWidth(36);
		} else {
			red.setFitHeight(40);
			red.setFitWidth(40);
		}
		return red;
	}

	public Node blueEnemy(boolean multiplayer) {

		Image blueImage = new Image("character/SlimeEnemy/BlueSlime.gif");
		ImageView blue = new ImageView(blueImage);
		if (multiplayer) {
			blue.setFitHeight(36);
			blue.setFitWidth(36);
		} else {
			blue.setFitHeight(40);
			blue.setFitWidth(40);
		}
		return blue;
	}

	public Node greenEnemy(boolean multiplayer) {

		Image greenImage = new Image("character/SlimeEnemy/GreenSlime.gif");
		ImageView green = new ImageView(greenImage);
		if (multiplayer) {
			green.setFitHeight(36);
			green.setFitWidth(36);
		} else {
			green.setFitHeight(40);
			green.setFitWidth(40);
		}

		return green;
	}

	public Node purpleEnemy(boolean multiplayer) {

		Image purpleImage = new Image("character/SlimeEnemy/PurpleSlime.gif");
		ImageView purple = new ImageView(purpleImage);
		if (multiplayer) {
			purple.setFitHeight(36);
			purple.setFitWidth(36);
		} else {
			purple.setFitHeight(40);
			purple.setFitWidth(40);
		}

		return purple;
	}

	public Node pinkEnemy(boolean multiplayer) {

		Image pinkImage = new Image("character/SlimeEnemy/PinkSlime.gif");
		ImageView pink = new ImageView(pinkImage);
		if (multiplayer) {
			pink.setFitHeight(36);
			pink.setFitWidth(36);
		} else {
			pink.setFitHeight(40);
			pink.setFitWidth(40);
		}

		return pink;

	}

	public Node lightBlueEnemy(boolean multiplayer) {

		Image lightBlueImage = new Image("character/SlimeEnemy/LightBlueSlime.gif");
		ImageView lightBlue = new ImageView(lightBlueImage);
		if (multiplayer) {
			lightBlue.setFitHeight(36);
			lightBlue.setFitWidth(36);
		} else {
			lightBlue.setFitHeight(40);
			lightBlue.setFitWidth(40);
		}

		return lightBlue;

	}

	public Node yellowEnemy(boolean multiplayer) {

		Image yellowImage = new Image("character/SlimeEnemy/YellowSlime.gif");
		ImageView yellow = new ImageView(yellowImage);
		if (multiplayer) {
			yellow.setFitHeight(36);
			yellow.setFitWidth(36);
		} else {
			yellow.setFitHeight(40);
			yellow.setFitWidth(40);
		}

		return yellow;

	}

	// -------------------Model getter methods------------------
	public Node redEnemy() {
		return redEnemy(false);
	}

	public Node blueEnemy() {
		return blueEnemy(false);
	}

	public Node lightBlueEnemy() {
		return lightBlueEnemy(false);
	}

	public Node pinkEnemy() {
		return pinkEnemy(false);
	}

	public Node purpleEnemy() {
		return purpleEnemy(false);
	}

	public Node yellowEnemy() {
		return yellowEnemy(false);
	}

	public Node greenEnemy() {
		return greenEnemy(false);
	}

	public Node enemyDeath() {
		Image image = new Image("character/SlimeEnemy/enemyDeath.png");
		ImageView imageDeath = new ImageView(image);
		imageDeath.setFitHeight(40);
		imageDeath.setFitWidth(40);
		return imageDeath;
	}

	// Multiplayer model
	public Node userEnemy() {
		if (this.model.equals("Blue")) {
			returnNode = blueEnemy(true);
		} else if (this.model.equals("Light Blue")) {
			returnNode = lightBlueEnemy(true);
		} else if (this.model.equals("Pink")) {
			returnNode = pinkEnemy(true);
		} else if (this.model.equals("Purple")) {
			returnNode = purpleEnemy(true);
		} else if (this.model.equals("Yellow")) {
			returnNode = yellowEnemy(true);
		}
		return returnNode;
	}

	// When a sword(power pellet) is collected by the player
	// These methods turn all slimes into red slimes, indicating
	// the player that they are now killable
	public Node swordGet() {
		Image killableImage = new Image("character/SlimeEnemy/RedSlime.gif");
		ImageView killable = new ImageView(killableImage);
		killable.setFitHeight(40);
		killable.setFitWidth(40);
		return killable;
	}

	public Node swordGetUser() {
		Image killableImage = new Image("character/SlimeEnemy/RedSlime.gif");
		ImageView killable = new ImageView(killableImage);
		killable.setFitHeight(36);
		killable.setFitWidth(36);
		return killable;
	}

	public String getEnemyDirection() {
		if (this.model.equals("Blue")) {
			modelLocation = "character/SlimeEnemy/BlueSlime.gif";
		} else if (this.model.equals("Light Blue")) {
			modelLocation = "character/SlimeEnemy/LightBlueSlime.gif";
		} else if (this.model.equals("Pink")) {
			modelLocation = "character/SlimeEnemy/PinkSlime.gif";
		} else if (this.model.equals("Purple")) {
			modelLocation = "character/SlimeEnemy/PurpleSlime.gif";
		} else if (this.model.equals("Yellow")) {
			modelLocation = "character/SlimeEnemy/YellowSlime.gif";
		}

		return modelLocation;
	}

	public String getEnemyDeath() {
		modelLocation = "character/SlimeEnemy/enemyDeath.png";
		return modelLocation;
	}

	public Node multiplayerEnemy() {
		Image multiplayerImage = new Image(modelLocation);
		ImageView multiplayerImageView = new ImageView(multiplayerImage);
		multiplayerImageView.setFitHeight(40);
		multiplayerImageView.setFitWidth(40);
		return multiplayerImageView;
	}
}
