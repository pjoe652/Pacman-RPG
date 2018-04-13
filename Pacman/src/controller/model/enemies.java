package controller.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class enemies {

	private Rectangle red;
	private Rectangle blue;


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

	public Node getRedEnemy() {
		return red;
	}
	public Node getBlueEnemy() {
		return blue;
	}
}
