package controller.model;

import java.util.ArrayList;
import java.util.List;

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
	
	public boolean shortestPath(int maze[][], int redx, int redy, int playerx, int playery, List<Integer>path) {
		
		if (redx == playerx && redy == playery) {
			path.add(redx);
			path.add(redy);
			return true;
		}
		
		
		
		
		
		return false;
	}	
	
	
	
}
