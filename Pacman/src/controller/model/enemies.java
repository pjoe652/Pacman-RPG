package controller.model;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class enemies {

//	private Rectangle red;
//	private Rectangle blue;
//	private Rectangle green;
//	private Rectangle purple;
	
	private Rectangle red;
	private Rectangle blue;
	private Rectangle green;
	private Rectangle purple;
	
	protected String model; 
	private String modelLocation;
	
	public enemies() {
		model = "Blue";
	}
	
	public void setModel(String model) {
		
		this.model = model;
	}
	
	public String getModel() {
		
		return this.model;
	}

	public Node redEnemy() {
//		red = new Rectangle(40, 40);
//		red.setFill(Color.RED);
		Image redImage = new Image("character/SlimeEnemy/GreenSlime.gif");
		ImageView red = new ImageView(redImage);
		red.setFitHeight(40);
		red.setFitWidth(40);
		return red;
	}

	public Node blueEnemy() {
//		blue = new Rectangle(40, 40);
//		blue.setFill(Color.BLUE);
		Image blueImage = new Image("character/SlimeEnemy/GreenSlime.gif");
		ImageView blue = new ImageView(blueImage);
		blue.setFitHeight(40);
		blue.setFitWidth(40);
		return blue;
	}
	
	public Node greenEnemy() {
//		green = new Rectangle(40, 40);
//		green.setFill(Color.GREEN);
		Image greenImage = new Image("character/SlimeEnemy/GreenSlime.gif");
		ImageView green = new ImageView(greenImage);
		green.setFitHeight(40);
		green.setFitWidth(40);
		return green;
	}
	
	public Node purpleEnemy() {
//		purple = new Rectangle(40, 40);
//		purple.setFill(Color.PURPLE);
		Image purpleImage = new Image("character/SlimeEnemy/GreenSlime.gif");
		ImageView purple = new ImageView(purpleImage);
		purple.setFitHeight(40);
		purple.setFitWidth(40);
		return purple;
	}
	
	public Node enemyDeath() {
		Image image = new Image("character/SlimeEnemy/enemyDeath.png");
		ImageView imageDeath = new ImageView(image);
		imageDeath.setFitHeight(40);
		imageDeath.setFitWidth(40);
		return imageDeath;
	}
	
//	public Node greenEnemy() {
//		Image monster = new Image("character/KnightUp.gif");
//		ImageView monsterView = new ImageView(monster);
//		monsterView.setX(40);
//		monsterView.setY(40);
//		return monsterView;
//	}

//	public Node getRedEnemy() {
//		return red;
//	}
//	public Node getBlueEnemy() {
//		return blue;
//	}
//	public Node getGreenEnemy() {
//		return green;
//	}
	
	public Node swordGet() {
		Image killableImage = new Image("character/SlimeEnemy/RedSlime.gif");
		ImageView killable = new ImageView(killableImage);
		killable.setFitHeight(40);
		killable.setFitWidth(40);
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
//		purple = new Rectangle(40, 40);
//		purple.setFill(Color.PURPLE);
		Image multiplayerImage = new Image(modelLocation);
		ImageView multiplayerImageView = new ImageView(multiplayerImage);
		multiplayerImageView.setFitHeight(40);
		multiplayerImageView.setFitWidth(40);
		return multiplayerImageView;
	}
}
