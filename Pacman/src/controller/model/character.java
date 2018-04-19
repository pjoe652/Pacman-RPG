package controller.model;

public class character {
	/*
	 * This class contains aspects of a character
	 */

	// Character Name
	protected String characterName;

	// Character model
	protected String model;
	private String modelLocation;

	// Attributes
	protected int Hp;
	protected int speed;
	protected double powerDuration;
	protected int pointsAvailable;
	public int score;
	protected int level = 1;

	// Constructor
	public character() {
		Hp = 3;
		speed = 2;
		powerDuration = 5;
		pointsAvailable = 0;
		model = "Black Knight";
		score = 0;
	}

	// ------------Level--------------------
	public void setLevel(int level) {
		this.level = level;
	}

	public int getLevel() {
		return this.level;
	}

	// -----------------TotalScore---------------
	public void setScore(int score) {
		this.score += score;
	}

	public int getScore() {
		return score;
	}

	public void resetScore() {
		score = 0;
	}

	// ------------Name--------------------
	public void setName(String name) {
		characterName = name;
	}

	public String getName() {
		return characterName;
	}
	// ------------------------------------

	// -------------Gender-------------------
	public void setModel(String model) {
		this.model = model;
	}

	public String getModel() {
		return model;
	}
	// -------------------------------------

	// ----------------Hp-----------------
	public void addHp() {
		Hp++;
	}

	public void minusHp() {
		Hp--;
	}

	public int getHp() {
		return Hp;
	}

	public void resetHp() {
		Hp = 3;
	}
	// ----------------------------------------

	// ------------Speed---------------
	public void addSpeed() {
		speed = speed + 1;
	}

	public void minusSpeed() {
		speed -= 1;
	}

	public int getSpeed() {
		return speed;
	}

	public void resetSpeed() {
		speed = 2;
	}
	// -----------------------------------------

	// ------------PowerDuration--------------
	public void addPowerDuration() {
		powerDuration = powerDuration + 0.5;
	}

	public void minusPowerDuration() {
		powerDuration -= 0.5;
	}

	public double getPowerDuration() {
		return powerDuration;
	}

	public void resetPower() {
		powerDuration = 5.0;
	}
	// --------------------------------------

	// ---------------Points----------------
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
	// -----------------------------------------

	// ---------------------GetModel---------------------
	// This method changes the character model in game depending on the
	// player's orientation as well as the chosen skin model
	public String getModelDirection(String direction) {
		if (this.model.equals("Black Knight")) {
			if (direction.equals("NONE")) {
				modelLocation = "character/BlackKnight/KnightStart.png";
			} else if (direction.equals("RIGHT")) {
				modelLocation = "character/BlackKnight/KnightRight.gif";
			} else if (direction.equals("LEFT")) {
				modelLocation = "character/BlackKnight/KnightLeft.gif";
			} else if (direction.equals("UP")) {
				modelLocation = "character/BlackKnight/KnightUp.gif";
			} else if (direction.equals("DOWN")) {
				modelLocation = "character/BlackKnight/KnightDown.gif";
			}
		} else if (this.model.equals("Female Knight")) {
			if (direction.equals("NONE")) {
				modelLocation = "character/FemaleKnight/FemaleKnightStart.png";
			} else if (direction.equals("RIGHT")) {
				modelLocation = "character/FemaleKnight/FemaleKnightRight.gif";
			} else if (direction.equals("LEFT")) {
				modelLocation = "character/FemaleKnight/FemaleKnightLeft.gif";
			} else if (direction.equals("UP")) {
				modelLocation = "character/FemaleKnight/FemaleKnightUp.gif";
			} else if (direction.equals("DOWN")) {
				modelLocation = "character/FemaleKnight/FemaleKnightDown.gif";
			}
		} else if (this.model.equals("Bearded Knight")) {
			if (direction.equals("NONE")) {
				modelLocation = "character/BeardedKnight/BeardedKnightStart.png";
			} else if (direction.equals("RIGHT")) {
				modelLocation = "character/BeardedKnight/BeardedKnightRight.gif";
			} else if (direction.equals("LEFT")) {
				modelLocation = "character/BeardedKnight/BeardedKnightLeft.gif";
			} else if (direction.equals("UP")) {
				modelLocation = "character/BeardedKnight/BeardedKnightUp.gif";
			} else if (direction.equals("DOWN")) {
				modelLocation = "character/BeardedKnight/BeardedKnightDown.gif";
			}
		}
		return modelLocation;
	}

	public String getModelDeath() {
		if (this.model.equals("Black Knight")) {
			modelLocation = "character/BlackKnight/KnightDeath.gif";
		} else if (this.model.equals("Female Knight")) {
			modelLocation = "character/FemaleKnight/FemaleKnightDeath.gif";
		} else if (this.model.equals("Bearded Knight")) {
			modelLocation = "character/BeardedKnight/BeardedKnightDeath.gif";
		}
		return modelLocation;
	}
	// ---------------------------------------------
}
