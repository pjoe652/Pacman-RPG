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
	private ArrayList<Integer> pathx = new ArrayList<Integer>();
	private ArrayList<Integer> pathy = new ArrayList<Integer>();
	
	public enemies() {
		redEnemy();
		
	}
	
	public Node redEnemy() {
		red = new Rectangle(40, 40);
		//entity.setTranslateX(490);
		//entity.setTranslateY(150);
		red.setFill(Color.RED);
		//entity.getProperties().put("alive", true);				
		return red;	
	}
	
	public Node getRedEnemy() {
		return red;
	}
	
	
	
	

}
