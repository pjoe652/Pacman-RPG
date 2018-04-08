package controller.model;

import controller.view.characterControl;

public class level extends characterControl {

	
	private static int level = 1;
	
	public void setLevel() {
		level++;
	}
	
	public int getLevel() {
		return level;
	}
	
}
