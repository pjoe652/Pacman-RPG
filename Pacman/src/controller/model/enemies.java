package controller.model;

import java.util.ArrayList;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class enemies {
	
	private Node red;
	
	
	public enemies() {
		red = redEnemy();
		
	}
	
	public Node redEnemy() {
		Rectangle entity = new Rectangle(40, 40);
		entity.setTranslateX(490);
		entity.setTranslateY(670);
		entity.setFill(Color.RED);
		entity.getProperties().put("alive", true);				
		return entity;	
	}
	
	public Node getRedEnemy() {
		return red;
	}
	
	
	
	public void redPathLogic(double x, double y, ArrayList<Node> platforms) {
		
		
		
	}
	
	
	
}
