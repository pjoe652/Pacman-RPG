package controller.model;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class enemies {

	private Rectangle red;
	private Rectangle blue;
	private Rectangle green;
	private Rectangle purple;

	public Node redEnemy() {
		red = new Rectangle(40, 40);
		red.setFill(Color.RED);
		return red;
	}

	public Node blueEnemy() {
		blue = new Rectangle(40, 40);
		blue.setFill(Color.BLUE);
		return blue;
	}
	
	public Node greenEnemy() {
		green = new Rectangle(40, 40);
		green.setFill(Color.GREEN);
		return green;
	}
	
	public Node purpleEnemy() {
		purple = new Rectangle(40, 40);
		purple.setFill(Color.PURPLE);
		return purple;
	}
	
//	public Node greenEnemy() {
//		Image monster = new Image("character/KnightUp.gif");
//		ImageView monsterView = new ImageView(monster);
//		monsterView.setX(40);
//		monsterView.setY(40);
//		return monsterView;
//	}

	public Node getRedEnemy() {
		return red;
	}
	public Node getBlueEnemy() {
		return blue;
	}
	
	public Node getGreenEnemy() {
		return green;
	}
}
