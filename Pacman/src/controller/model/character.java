package controller.model;

import java.util.ArrayList;

public class character {
	
	
	protected String characterName;
	protected char gender;
	
	protected ArrayList<String> unlockedAchievement = new ArrayList<String>();
	
	//Attributes
	protected int Hp;
	protected int speed;
	protected double powerDuration;
	protected int pointsAvailable;
	
	
	//Gain through achievement
	//Perks
	protected double scoreMultiplier;
	protected int extraPowerPellets;
	
	//Skins
	protected int skin;
	
	
	
	
	//Constructor
	public character() {
		Hp = 3;
		speed = 2;
		powerDuration = 5;
		pointsAvailable = 0;
		scoreMultiplier = 1.0;
		extraPowerPellets = 0;
		gender = ' ';
	}
	
	//------------Name--------------------
	public void setName(String name) {
		characterName = name;
	}
	
	public String getName() {
		return characterName;
	}
	//------------------------------------
	
	//-------------Gender-------------------
	public void setGender(char gender) {
		this.gender = gender;
	}
	
	public char getGender() {
		return gender;
	}
	//-------------------------------------	
	
	//----------------Hp-----------------
	public void addHp() {
		Hp++;
	}
	
	public int getHp() {
		return Hp;
	}
	//----------------------------------------
	
	//------------Speed---------------
	public void addSpeed() {
		speed = speed + 1;
	}
	
	public int getSpeed() {
		return speed;
	}
	//-----------------------------------------
	
	//------------PowerDuration--------------
	public void addPowerDuration() {
		powerDuration = powerDuration + 0.5;
	}
	
	public double getPowerDuration() {
		return powerDuration;
	}
	//--------------------------------------
	
	//---------------Points----------------
	public void addPoints() {
		pointsAvailable = pointsAvailable + 2;
	}
	
	public void deductPoints() {
		pointsAvailable--;
	}
	
	public int getPoints() {
		return pointsAvailable;
	}
	//----------------------------------------
	
	//--------------ScoreMultiplier------------
	public void setScoreMultiplier() {
		scoreMultiplier = scoreMultiplier + 0.2;
	}
	
	public double getScoreMultiplier() {
		return scoreMultiplier;
	}
	//-----------------------------------------
	
	//--------------ExtraPellet------------------
	public void setExtraPowerPellet() {
		extraPowerPellets++;
	}
	
	public int getExtraPowerPellet() {
		return extraPowerPellets;
	}
	//--------------------------------------------
	
	//------------------achievement-----------------
	public void addAchievement(String achievement) {
		unlockedAchievement.add(achievement);
	}
	
	public ArrayList<String> getAchievements(){
		return unlockedAchievement;
	}
	
	public int getNumberOfAchievements() {
		return unlockedAchievement.size();
	}	
	//---------------------------------------------
	
	//---------------------Skin---------------------
	public void setSkin() {
		
		
		
	}
}
