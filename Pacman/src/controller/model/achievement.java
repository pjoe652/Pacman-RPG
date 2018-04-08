package controller.model;

import java.util.ArrayList;


public class achievement{
	
	private ArrayList<String> achievement = new ArrayList<String>();
	
	public achievement() {
		String[] list = {"MonsterHunter", "ScoreBreaker", "Perfectionist"};
		int length = list.length;
		
		for (int i = 0; i < length; i++) {
			achievement.add(list[i]);
		}		
	}
	
	public ArrayList<String> getAchievementAvailable(){
		return achievement;
	}
	
}
