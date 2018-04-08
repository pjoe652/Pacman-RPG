package controller.view;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Scanner;

import controller.model.level;
import controller.model.map;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class upgradeControl extends characterControl {

	private Stage stage;
	
	//Lives
	private int currentHp;
	@FXML private Label Hp;
	private String health;
	
	//Speed
	private int currentSpeed;
	@FXML private Label speed;
	private String quickness;
	
	//PowerDuration
	@FXML private Label powerDuration;
	private double time;
	private String duration;
	
	//Points
	private int currentPoints;
	@FXML private Label points;
	private String pt;
	
	//Achievement
	@FXML private Label achievement;
	private int unlockedAchievement;
	private String Achieve;
	
	//ScoreMultiplier
	@FXML private Label scoreMultiplier;
	private double multiplier;
	private String Multiplier;
	
	//PowerPellet
	@FXML private Label extraPowerPellet;
	private int power;
	private String pellet;
	
	
	//-------------------Attributes---------------------------
	@FXML
	public void handleHpButton(ActionEvent event) {
		if (player.getPoints() >= 3) {
			player.addHp();
			displayHp();
			
			player.deductPoints();
			player.deductPoints();
			player.deductPoints();
			displayPoints();
		}
	}
	
	@FXML
	public void handleSpButton(ActionEvent event) {
		if (player.getPoints() > 0) { 
			player.addSpeed();
			displaySpeed();	
			
			player.deductPoints();
			displayPoints();
		}				
	}
	
	@FXML
	public void handlePowerButton(ActionEvent event) {
		if (player.getPoints() > 0) { 
			player.addPowerDuration();
			displayPowerDuration();	
			
			player.deductPoints();
			displayPoints();
		}				
	}
	
	//----------------------------------------------------------------
	
	@FXML
	public void handleContinueButton(ActionEvent event) throws Exception {
		
		level lvl = new level();
		lvl.setLevel();
		System.out.print(lvl.getLevel());
		
		levelSelect select = new levelSelect();
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		select.selectLevel(stage);
		
	}
	
	
		
	//-------------------------------Initialize------------------------------------------------
	
	@FXML
	public void initialize() throws Exception {
		
		/*
		 * This initializes the amount of lives
		 * */		 
		displayHp();		
		/*
		 * This initializes the speed
		 */
		displaySpeed();
		/*
		 * This initializes the duration of power pellet
		 */
		displayPowerDuration();
		/*
		 * This initializes the points available for upgrade
		 */
		displayPoints();
		/*
		 * This initializes the number of achievements unlocked
		 */
		displayNumberOfAchievement();
		/*
		 * This initializes the number of score multiplier
		 */
		displayScoreMultiplier();
		/*
		 * This initializes the number of extra power pellets
		 */
		displayExtraPowerPellet();
	}	
	//-------------------------------------------------------------------------------------------
	
	//------------------------display-------------------------------------
	public void displayPoints() {
		currentPoints = player.getPoints();
		pt = Integer.toString(currentPoints);
		points.setText(pt);
	}
	
	public void displayHp() {
		currentHp = player.getHp();
		health = Integer.toString(currentHp);
		Hp.setText(health);
	}
	
	public void displaySpeed() {
		currentSpeed = player.getSpeed();
		quickness = Integer.toString(currentSpeed);
		speed.setText(quickness);
	}
	
	public void displayPowerDuration() {
		time = player.getPowerDuration();
		duration = Double.toString(time);
		powerDuration.setText(duration + "s");
	}
	
	//-------------------------------------------------------------
	
	//--------------------------Perks--------------------------------
	public void displayNumberOfAchievement() {
		unlockedAchievement = player.getNumberOfAchievements();
		Achieve = Integer.toString(unlockedAchievement);
		achievement.setText(Achieve);		
	}
	
	public void displayScoreMultiplier() {
		multiplier = player.getScoreMultiplier();
		Multiplier = Double.toString(multiplier);
		scoreMultiplier.setText(Multiplier);		
	}
	
	public void displayExtraPowerPellet() {
		power = player.getExtraPowerPellet();
		pellet = Integer.toString(power);
		extraPowerPellet.setText(pellet);		
	}
	//--------------------------------------------------------------------
	
	
	
	
	
	
}
