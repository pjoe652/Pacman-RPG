package controller.model;

import java.util.ArrayList;

public class character {
	
	
	protected String characterName;
	protected String model;
	private String modelLocation;
	
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
		model = "Black Knight";
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
	public void setModel(String model) {
		this.model = model;
	}
	
	public String getModel() {
		return model;
	}
	//-------------------------------------	
	
	//----------------Hp-----------------
	public void addHp() {
		Hp++;
	}
	
	public void minusHp() {
		Hp--;
	}
	
	public int getHp() {
		return Hp;
	}
	//----------------------------------------
	
	//------------Speed---------------
	public void addSpeed() {
		speed = speed + 1;
	}
	
	public void minusSpeed() {
		speed -= 1;
	}
	
	public int getSpeed() {
		return speed;
	}
	//-----------------------------------------
	
	//------------PowerDuration--------------
	public void addPowerDuration() {
		powerDuration = powerDuration + 0.5;
	}
	
	public void minusPowerDuration() {
		powerDuration -= 0.5;
	}
	
	public double getPowerDuration() {
		return powerDuration;
	}
	//--------------------------------------
	
	//---------------Points----------------
	public void addPoints() {
		pointsAvailable = pointsAvailable + 2;
	}
	
	public void returnPoints(int points) {
		pointsAvailable += points;
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
	
	//---------------------GetModel---------------------
	public String getModelDirection(String direction) {
		if (this.model.equals("Black Knight")) {
			if (direction.equals("NONE")) {
				modelLocation = "character/BlackKnight/KnightStart.png";
			} else if (direction.equals("RIGHT")) {
				modelLocation =  "character/BlackKnight/KnightRight.gif";
			} else if (direction.equals("LEFT")) {
				modelLocation =  "character/BlackKnight/KnightLeft.gif";
			} else if (direction.equals("UP")) {
				modelLocation =  "character/BlackKnight/KnightUp.gif";
			} else if (direction.equals("DOWN")) {
				modelLocation =  "character/BlackKnight/KnightDown.gif";
			}
		} else if (this.model.equals("Female Knight")) {
			if (direction.equals("NONE")) {
				modelLocation = "character/FemaleKnight/FemaleKnightStart.png";
			} else if (direction.equals("RIGHT")) {
				modelLocation =  "character/FemaleKnight/FemaleKnightRight.gif";
			} else if (direction.equals("LEFT")) {
				modelLocation =  "character/FemaleKnight/FemaleKnightLeft.gif";
			} else if (direction.equals("UP")) {
				modelLocation =  "character/FemaleKnight/FemaleKnightUp.gif";
			} else if (direction.equals("DOWN")) {
				modelLocation =  "character/FemaleKnight/FemaleKnightDown.gif";
			}
		} else if (this.model.equals("Bearded Knight")) {
			if (direction.equals("NONE")) {
				modelLocation = "character/BeardedKnight/BeardedKnightStart.png";
			} else if (direction.equals("RIGHT")) {
				modelLocation =  "character/BeardedKnight/BeardedKnightRight.gif";
			} else if (direction.equals("LEFT")) {
				modelLocation =  "character/BeardedKnight/BeardedKnightLeft.gif";
			} else if (direction.equals("UP")) {
				modelLocation =  "character/BeardedKnight/BeardedKnightUp.gif";
			} else if (direction.equals("DOWN")) {
				modelLocation =  "character/BeardedKnight/BeardedKnightDown.gif";
			}
		}
		return modelLocation;
	}
	//---------------------------------------------
}
