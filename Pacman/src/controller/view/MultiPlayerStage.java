package controller.view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;

import controller.model.LevelData;
import controller.model.MusicPlayer;
import controller.model.character;
import controller.model.enemies;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class MultiPlayerStage {
	/*
	 * This class creates multiplayer mode
	 */

	private HashMap<KeyCode, Boolean> keys = new HashMap<KeyCode, Boolean>();
	private ArrayList<Node> coins = new ArrayList<Node>();
	private ArrayList<Node> platforms = new ArrayList<Node>();
	private ArrayList<Node> hearts = new ArrayList<Node>();

	private Pane appRoot = new Pane();
	private Pane gameRoot = new Pane();
	private Pane uiRoot = new Pane();
	private Pane userRoot = new Pane();
	private Pane enemyRoot = new Pane();
	private Pane popupRoot = new Pane();

	private int lives = 2;
	private boolean createHearts = true;

	private static int Score = 0;
	private int CoinsCollected = 0;

	private int levelWidth;

	Node entityCreated;

	// User direction control
	private String direction = "NONE";
	private String prevDirection = "Stationary";
	private int directionSet = 0;

	// Enemy Player1 direction control
	private String direction2 = "NONE";
	private String prevDirection2 = "Stationary";
	private int directionSet2 = 0;

	// Enemy Player2 direction control
	private String direction3 = "NONE";
	private String prevDirection3 = "Stationary";
	private int directionSet3 = 0;

	private double userX, user2X, user3X;
	private double userY, user2Y, user3Y;

	private boolean running = true;
	private boolean playerDead = false;
	private boolean playerHasDied = false;

	public Stage stage;

	// Count Down From 3 to 0
	private final Integer startTime = 3;
	private Integer currentTime = startTime;
	private StackPane display = new StackPane();
	Timeline timer = new Timeline();
	private StackPane displayTime = new StackPane();

	// Timer count from 180 to 0
	private final Integer playTime = 180;
	private Integer gameTime = playTime;
	private static int TIME = 0;
	private int count = 0;
	Timeline time = new Timeline();

	// Get the maze details from LevelData
	LevelData map = new LevelData();
	private int maze[][] = map.getLevel999();
	private int checkMaze[][] = maze;

	// Characters of the game
	private character userOne = multiplayerControl.characterOne;
	private enemies userTwo = multiplayerControl.characterTwo;
	private enemies userThree = multiplayerControl.characterThree;

	private Node user;
	private Node user2 = userTwo.userEnemy();
	private Node user3 = userThree.userEnemy();
	enemies red = new enemies();
	Node redEnemy = red.redEnemy();
	enemies blue = new enemies();
	Node blueEnemy = blue.blueEnemy();
	enemies green = new enemies();
	Node greenEnemy = green.greenEnemy();
	enemies purple = new enemies();
	Node purpleEnemy = purple.purpleEnemy();

	private boolean initEnemies = false;
	private int initUsers = 0;

	// AI movement
	private Point p;
	private Point p2;
	private ArrayList<Point> store = new ArrayList<Point>();
	private ArrayList<Point> store2 = new ArrayList<Point>();
	private int greenx, greeny, purplex, purpley;
	private int moveUp = 0;
	private int moveRight = 0;
	private int check = 0;
	private int pcheck = 0;
	private int pmoveUp = 0;
	private int pmoveRight = 0;

	// Time pauses upon user's input
	private boolean timePaused = false;

	// select next level depends on the result of the game
	levelSelect select = new levelSelect();

	// Power Pellets
	private boolean swordHeld = false;
	private int swordGet = 0;
	Timeline swordTime = new Timeline();
	private final Integer swordStart = 60;
	private Integer swordCount = swordStart;
	private ArrayList<Node> swords = new ArrayList<Node>();

	// Death animation
	Timeline deathTime = new Timeline();
	Timeline enemyTime = new Timeline();
	private final Integer deathStart = 20;
	private Integer deathCount = deathStart;
	private final Integer enemyStart = 20;
	private Integer enemyCount = enemyStart;
	private double playerOpacity = 1;
	private boolean deathComplete = false;
	private boolean enemyDeathOn = false;
	private MusicPlayer mp = new MusicPlayer();
	private int coinCount = coins.size();
	private int playCount = 0;
	private Stage menuStage = new Stage();
	private Scene sceneRoot;
	private Scene menuScene;
	private int playery, playerx;
	private Stage musicStage = new Stage();

	// Initializes the gameplay area
	private void initContent() {
		mp.stopBGM();
		mp.multi();
		Image bg = new Image("img/floor.png");

		ImageView bgView = new ImageView(bg);
		bgView.setFitWidth(1080);
		bgView.setFitHeight(800);

		String scoreString = Integer.toString(getScore());

		StackPane scoreValue = createMenu(100, 30, 850, 15, scoreString, Color.WHITE);

		StackPane menuScore = createMenuRectangle(250, 40, 760, 10);

		StackPane menuScoreLabel = createMenuText(770, 17, "Score: ", Color.WHITE);

		StackPane Lives = createMenuRectangle(300, 40, 60, 10);

		StackPane LivesLabel = createMenuText(70, 17, "Lives: ", Color.WHITE);

		if (createHearts) {
			for (int i = 0; i < lives; i++) {
				Node heart = createImage(40, 40, 160 + i * 50, 12, "img/heart.png");
				hearts.add(heart);
			}
		}

		playerDead = false;

		// Transfer matrices into 40*40 blocks
		levelWidth = maze[0].length * 40;
		if (coins.isEmpty()) {
			for (int i = 0; i < maze.length; i++) {
				for (int j = 0; j < maze[i].length; j++) {
					switch (maze[i][j]) {
					case 0:
						break;
					case 1:
						Node Walls = createCharacter(j * 40, i * 40, 40, 40, Color.BLACK);
						Node Tile = createImage(40, 40, j * 40, i * 40, "img/tile2.png");
						platforms.add(Walls);
						break;
					case 2:
						Node coin = createImage(40, 40, j * 40, i * 40, "img/Coin.png");
						coins.add(coin);
						break;
					case 3:
						Node sword = createImage(40, 40, j * 40, i * 40, "img/sword.png");
						swords.add(sword);
						break;
					case 10:
						Node fence = createImage(40, 40, j * 40, i * 40, "img/electricFence.png");
						break;
					}
				}
			}
		}

		String user1Character = userOne.getModelDirection("NONE");
		String user2Enemy = userTwo.getEnemyDirection();
		String user3Enemy = userThree.getEnemyDirection();

		// Create characters in the game
		// Initialise
		user = createKnight(522, 722, user1Character);

		user2.setTranslateX(520);
		user2.setTranslateY(360);
		user3.setTranslateX(440);
		user3.setTranslateY(360);
		greenEnemy.setTranslateX(600);
		greenEnemy.setTranslateY(360);
		greenEnemy.getProperties().put("alive", true);
		purpleEnemy.setTranslateX(360);
		purpleEnemy.setTranslateY(360);
		purpleEnemy.getProperties().put("alive", true);

		if (!initEnemies) {
			enemyRoot.getChildren().addAll(user2, user3, greenEnemy, purpleEnemy);
			initEnemies = true;
		}

		user.setOpacity(1);

		user.translateXProperty().addListener((obs, old, newValue) -> {
			int offset = newValue.intValue();

			if (offset > 640 && offset < levelWidth - 640) {
				gameRoot.setLayoutX(-(offset - 640));
			}
		});

		if (!playerHasDied) {
			setTime();
		}

		appRoot.getChildren().addAll(bgView, Lives, LivesLabel, gameRoot, userRoot, enemyRoot, uiRoot, popupRoot,
				menuScore, menuScoreLabel, scoreValue);

	}

	// Reset game when user dies
	private void clearGame() {

		if (lives == 0) {
			running = false;
			timePaused = true;
			stopGameTime();
			mp.stopMulti();
			mp.gameOverSFX();
			levelSelect select = new levelSelect();
			Score = 0;
			select.levelFailed(stage);
		} else {
			running = true;

			userRoot.getChildren().clear();
			uiRoot.getChildren().clear();
			appRoot.getChildren().clear();
			gameRoot.getChildren().removeAll(hearts);

			direction = "NONE";
			deathComplete = false;
			currentTime = 3;
			count = 0;
			stopPlayerDeathTime();
			resetTime();
			resetGameTime();
		}

	}

	// Continuously updates character interactions
	private void update() {
		if (currentTime < -1 && timePaused == false) {

			if (count == 0) {
				setGameTime();
				count++;
			}

			// Capture the position of the player
			// Update to checkMaze for BFS to find the player
			double x = user.getTranslateX();
			double y = user.getTranslateY();
			int newx = (int) (x / 40);
			int newy = (int) (y / 40);
			playery = newy;
			playerx = newx;

			checkMaze[newy][newx] = 9;
			greenMovement();

			checkMaze[newy][newx] = 9;
			purpleMovement();

			directionSelect();
			directionSelectPlayer2();
			directionSelectPlayer3();

			// Removes Coins when collected
			for (Node coin : coins) {
				if (user.getBoundsInParent().intersects(coin.getBoundsInParent())) {
					mp.coinSFX();
					coin.getProperties().put("alive", false);
				}
			}

			// Removes swords when collected
			for (Node sword : swords) {
				if (user.getBoundsInParent().intersects(sword.getBoundsInParent())) {
					sword.getProperties().put("alive", false);
					swordGet = 1;
					resetSwordTime();
				}
			}
			for (Iterator<Node> it = swords.iterator(); it.hasNext();) {
				Node sword = it.next();
				if (!(Boolean) sword.getProperties().get("alive")) {
					it.remove();
					gameRoot.getChildren().remove(sword);
				}
			}

			// Start sword use time
			if (swordGet == 1) {
				mp.swordGetSFX();
				swordGet = 0;
				swordHeld = true;
				setSwordTime();
			}

			// Sword usage
			if (swordHeld == true) {
				swordUse();
			}

			// Checks if player is killed
			if (!playerDead && (!swordHeld)) {
				if (!playerDead) {
					if (user.getBoundsInParent().intersects(user2.getBoundsInParent())
							|| user.getBoundsInParent().intersects(user3.getBoundsInParent())
							|| user.getBoundsInParent().intersects(greenEnemy.getBoundsInParent())
							|| user.getBoundsInParent().intersects(purpleEnemy.getBoundsInParent())) {
						running = false;
						// playerDies();
						setPlayerDeath();
						if (deathComplete) {
							playerDead = true;
							clearGame();
							if (!running) {
								timePaused = true;
								return;
							}
							lives--;
							initContent();
						}
					}
				}
			}

			// Removes coin if collected
			for (Iterator<Node> it = coins.iterator(); it.hasNext();) {
				Node coin = it.next();

				if (!(Boolean) coin.getProperties().get("alive")) {
					it.remove();
					CoinsCollected += 1;
					Score += 50;
					String ScoreString = Integer.toString(Score);
					StackPane scoreValue = createMenu(100, 30, 850, 15, ScoreString, Color.WHITE);
					appRoot.getChildren().add(scoreValue);
					gameRoot.getChildren().remove(coin);
				}
			}

			// Fades away when all coins collected
			// max coins = 213
			if (CoinsCollected == 213) {
				mp.coinBagSFX();
				mp.stopMulti();
				stopGameTime();
				timePaused = true;
				try {
					running = false;
					Parent parent = FXMLLoader.load(getClass().getResource("MultiVictoryScreen.fxml"));
					Scene scene = new Scene(parent);
					stage.setScene(scene);
					stage.show();

				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	// Creates walls
	private Node createImage(int h, int w, int x, int y, String link) {

		Image img = new Image(link);
		ImageView imageView = new ImageView(img);
		imageView.setFitHeight(h);
		imageView.setFitWidth(w);
		imageView.setX(x);
		imageView.setY(y);
		imageView.getProperties().put("alive", true);

		gameRoot.getChildren().add(imageView);
		return imageView;

	}

	// Creates user
	private Node createCharacter(int x, int y, int w, int h, Color color) {

		Rectangle entity = new Rectangle(w, h);
		entity.setTranslateX(x);
		entity.setTranslateY(y);
		entity.setFill(color);
		entity.getProperties().put("alive", true);

		gameRoot.getChildren().add(entity);

		return entity;

	}

	// Create knight user
	private Node createKnight(double x, double y, String direction) {

		Image knight = new Image(direction);
		ImageView knightView = new ImageView(knight);
		knightView.setTranslateX(x);
		knightView.setTranslateY(y);
		knightView.setFitHeight(36);
		knightView.setFitWidth(36);

		knightView.getProperties().put("alive", true);

		userRoot.getChildren().add(knightView);

		return knightView;
	}

	private void createDeathPopup(int h, int w, double x, double y, String link) {

		Image img = new Image(link);
		ImageView imageView = new ImageView(img);
		imageView.setFitHeight(h);
		imageView.setFitWidth(w);
		imageView.setX(x);
		imageView.setY(y);
		imageView.getProperties().put("alive", true);

		popupRoot.getChildren().add(imageView);

	}

	// Creates menu label
	private StackPane createMenu(int w, int h, int x, int y, String string, Color color) {
		Rectangle rectangle = new Rectangle(w, h);
		rectangle.setFill(Color.BLACK);
		rectangle.setStroke(Color.WHITE);
		rectangle.setArcWidth(10.0);
		rectangle.setArcHeight(10.0);
		Text text = new Text();
		text.setFill(color);
		text.setText(string);
		text.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
		StackPane stack = new StackPane();
		stack.getChildren().addAll(rectangle, text);
		stack.setLayoutX(x);
		stack.setLayoutY(y);

		return stack;
	}

	// Creates menu box
	private StackPane createMenuRectangle(int w, int h, int x, int y) {

		Rectangle rectangle = new Rectangle(w, h);
		rectangle.setFill(Color.BLACK);
		rectangle.setStroke(Color.WHITE);
		rectangle.setArcWidth(10.0);
		rectangle.setArcHeight(10.0);
		StackPane stack = new StackPane();
		stack.getChildren().add(rectangle);
		stack.setLayoutX(x);
		stack.setLayoutY(y);

		return stack;
	}

	// Creates menu text
	private StackPane createMenuText(int x, int y, String string, Color color) {

		Text text = new Text();
		text.setFill(color);
		text.setText(string);
		text.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
		StackPane stack = new StackPane();
		stack.getChildren().add(text);
		stack.setLayoutX(x);
		stack.setLayoutY(y);

		return stack;
	}

	// Confirms key press
	private boolean isPressed(KeyCode key) {
		return keys.getOrDefault(key, false);
	}

	// Check sword use
	private void swordUse() {
		if (user.getBoundsInParent().intersects(user2.getBoundsInParent())) {
			mp.slashSFX();
			enemyKilledScore();
			running = false;
			setEnemyDeath(user2);
			enemyRoot.getChildren().remove(user2);
			// user2 = createEnemy(520, 360, userTwo.getEnemyDirection());
			user2.setTranslateX(600);
			user2.setTranslateY(360);
			enemyRoot.getChildren().add(user2);

			count = 0;
		}
		if (user.getBoundsInParent().intersects((user3.getBoundsInParent()))) {
			mp.slashSFX();
			enemyKilledScore();
			running = false;
			setEnemyDeath(user3);
			enemyRoot.getChildren().remove(user3);
			// user3 = createEnemy(520, 360, userThree.getEnemyDirection());
			user3.setTranslateX(600);
			user3.setTranslateY(360);
			enemyRoot.getChildren().add(user3);

			count = 0;
		}
		if (user.getBoundsInParent().intersects(greenEnemy.getBoundsInParent())) {
			mp.slashSFX();
			enemyKilledScore();
			running = false;
			setEnemyDeath(greenEnemy);
			enemyRoot.getChildren().remove(greenEnemy);
			greenEnemy.setTranslateX(600);
			greenEnemy.setTranslateY(360);
			enemyRoot.getChildren().add(greenEnemy);
			count = 0;
		}
		if (user.getBoundsInParent().intersects(purpleEnemy.getBoundsInParent())) {
			mp.slashSFX();
			enemyKilledScore();
			running = false;
			setEnemyDeath(purpleEnemy);
			enemyRoot.getChildren().remove(purpleEnemy);
			purpleEnemy.setTranslateX(600);
			purpleEnemy.setTranslateY(360);
			enemyRoot.getChildren().add(purpleEnemy);
			count = 0;
		}

	}

	public void mapGeneration(Stage map) throws Exception {
		// ---------------Menu--------------
		menuStage.setTitle("PAUSE");
		menuStage.initStyle(StageStyle.TRANSPARENT);

		menuScene = new Scene(menuSetUp());
		menuScene.getStylesheets().add("/controller/view/InGameMenu.css");
		menuStage.setOpacity(0.9);
		menuStage.initModality(Modality.APPLICATION_MODAL);
		menuStage.initOwner(stage);

		menuScene.setOnKeyPressed(event -> {
			if (event.getCode() == KeyCode.P) {
				time.play();
				swordTime.play();
				timePaused = false;
				if (currentTime != -2) {
					timer.play();
				}
				menuStage.close();
			}

		});
		// ---------------volume option----------------------
		musicStage.setTitle("Option");
		musicStage.setOpacity(0.9);
		musicStage.initModality(Modality.APPLICATION_MODAL);
		musicStage.initOwner(menuStage);
		VBox slider = this.mp.option();
		Scene musicScene = new Scene(slider);
		musicScene.setOnKeyPressed(e -> {
			if (e.getCode() == KeyCode.ESCAPE) {
				musicStage.close();
			}
		});
		musicStage.setScene(musicScene);

		// ---------------Initialize-------------------
		initContent();
		this.stage = map;
		sceneRoot = new Scene(appRoot);

		sceneRoot.setOnKeyPressed(event -> {

			keys.put(event.getCode(), true);

			/*
			 * Detect keyboard input of P, ESCAPE, PgDn and PgUp
			 * 
			 * The game will be paused if P is pressed until further instruction, The user
			 * will not have the control of the character nor the ghost will move.
			 * 
			 * A confirmation dialog will be displayed if Esc is pressed and if the result
			 * is yes, then return back to the main welcome stage
			 * 
			 * Time will be skipped to 0 if PgDn is pressed, in our game, if game time is 0,
			 * a Game Over page will be displayed. Therefore, pressing this button means
			 * ending the game with a loss.
			 * 
			 * Victory Screen will be displayed if PgUp is pressed.
			 */

			if (event.getCode() == KeyCode.ESCAPE) {
				time.pause();
				timer.pause();
				timePaused = true;
				Alert warning = new Alert(AlertType.CONFIRMATION);
				warning.setTitle("Confirmation");
				warning.setHeaderText("Are you sure to exit?");

				ButtonType no = new ButtonType("No");
				ButtonType yes = new ButtonType("Yes");

				warning.getButtonTypes().setAll(yes, no);

				Optional<ButtonType> result = warning.showAndWait();
				if (result.get() == yes) {
					Parent parent;
					mp.stopMulti();
					mp.BGM();
					running = false;
					timePaused = true;
					try {
						parent = FXMLLoader.load(getClass().getResource("Welcome.fxml"));
						Scene scene2 = new Scene(parent);
						stage.setScene(scene2);
						stage.show();
					} catch (IOException e) {
						e.printStackTrace();
					}

				} else {
					time.play();
					timePaused = false;
					if (currentTime != -2) {
						timer.play();
					}
				}
			}
			if (event.getCode() == KeyCode.P) {
				time.pause();
				timer.pause();
				swordTime.pause();
				timePaused = true;
				menuStage.setScene(menuScene);
				menuStage.show();
			}
			if (event.getCode() == KeyCode.PAGE_DOWN) {
				gameTime = 0;
			}
			if (event.getCode() == KeyCode.PAGE_UP) {
				mp.stopMulti();
				mp.coinBagSFX();
				running = false;
				timePaused = true;
				stopGameTime();
				Parent parent;
				try {
					parent = FXMLLoader.load(getClass().getResource("MultiVictoryScreen.fxml"));
					Scene scene = new Scene(parent);
					stage.setScene(scene);
					stage.show();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
			}
		});

		sceneRoot.setOnKeyReleased(event -> keys.put(event.getCode(), false));

		map.setScene(sceneRoot);
		map.show();

		AnimationTimer timer = new AnimationTimer() {
			@Override
			public void handle(long now) {
				if (running) {
					update();
				}
			}
		};

		timer.start();
	}

	// ---------------------------------------Timer--------------------------------------------------
	/*
	 * Timers are paused if p or esc is pressed or when the player loses a life
	 */

	/*
	 * This is a count down timer from 3 to 0 which is displayed at the start of the
	 * game Character will not be able to move nor the enemies until the timer has
	 * reached 0
	 */

	public void setTime() {

		timer.setCycleCount(Timeline.INDEFINITE);
		if (timer != null) {
			timer.stop();
		}

		KeyFrame frame = new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				display = createMenu(200, 68, 440, 350, currentTime.toString(), Color.WHITE);
				uiRoot.getChildren().add(display);
				currentTime--;
				if (currentTime == 0) {

					// display.setText("START");
				}
				if (currentTime == -1) {

					display = createMenu(200, 68, 440, 350, "Start!", Color.WHITE);
					uiRoot.getChildren().add(display);
				}
				if (currentTime == -2) {
					timer.stop();
					uiRoot.getChildren().clear();
				}

			}
		});

		timer.getKeyFrames().add(frame);

		timer.playFromStart();

	}

	public void resetTime() {

		timer.getKeyFrames().clear();

		timer.playFromStart();
	}

	/*
	 * This is a 3 minute game timer, counts from 180 to 0 This timer starts right
	 * after count down timer has reached 0 It stops when the time is 0 and displays
	 * TIMES UP, then a GAMEOVER scene will be displayed.
	 */
	public void setGameTime() {

		time.setCycleCount(Timeline.INDEFINITE);

		if (time != null) {
			time.stop();
		}

		KeyFrame frame = new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				uiRoot.getChildren().clear();
				displayTime = createMenu(100, 40, 490, 10, gameTime.toString(), Color.WHITE);
				uiRoot.getChildren().add(displayTime);
				gameTime--;
				TIME++;
				if (gameTime == -1) {
					time.stop();
					mp.stopMulti();
					mp.gameOverSFX();
					displayTime = createMenu(100, 40, 490, 10, "Times up!", Color.RED);
					uiRoot.getChildren().add(displayTime);
					levelSelect select = new levelSelect();
					select.levelFailed(stage);
					running = false;
					timePaused = true;
				}

			}
		});

		time.getKeyFrames().add(frame);

		time.playFromStart();
	}

	public void resetGameTime() {

		time.getKeyFrames().clear();

		time.play();
	}

	public void stopGameTime() {

		time.getKeyFrames().clear();

		time.stop();
	}

	// ------------------------------------SWORD----------------------------------
	// Check sword use
	public void setSwordTime() {

		setKillable();

		swordTime.setCycleCount(Timeline.INDEFINITE);

		if (swordTime != null) {
			swordTime.stop();
		}

		KeyFrame swordFrame = new KeyFrame(Duration.seconds(0.1), new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				swordCount--;
				if (swordCount == 30) {

				}
				if ((swordCount <= 30) && (swordCount % 2 == 1)) {
					user.setOpacity(0.5);
				} else if ((swordCount <= 30) && (swordCount % 2 == 0)) {
					user.setOpacity(1);
				}
				if (swordCount == -1) {
					swordTime.getKeyFrames().clear();
					swordTime.stop();
					setUnkillable();
					swordHeld = false;
					mp.swordBreakSFX();
					swordCount = 60;
				}
			}
		});

		swordTime.getKeyFrames().add(swordFrame);
		swordTime.playFromStart();
	}

	public void resetSwordTime() {

		swordTime.getKeyFrames().clear();

		swordTime.stop();

		swordCount = 60;
	}

	public void stopSwordTime() {

		swordTime.getKeyFrames().clear();

		swordTime.stop();
	}

	public void playSwordTime() {

		swordTime.getKeyFrames().clear();

		swordTime.play();
	}

	public void setKillable() {

		double enemyUser2X;
		double enemyUser2Y;
		double enemyUser3X;
		double enemyUser3Y;
		double enemyGreenX;
		double enemyGreenY;
		double enemyPurpleX;
		double enemyPurpleY;

		// Track
		enemyUser2X = user2.getTranslateX();
		enemyUser2Y = user2.getTranslateY();
		enemyUser3X = user3.getTranslateX();
		enemyUser3Y = user3.getTranslateY();
		enemyGreenX = greenEnemy.getTranslateX();
		enemyGreenY = greenEnemy.getTranslateY();
		enemyPurpleX = purpleEnemy.getTranslateX();
		enemyPurpleY = purpleEnemy.getTranslateY();

		enemyRoot.getChildren().clear();

		// User 2
		user2 = red.swordGetUser();
		user2.setTranslateX(enemyUser2X);
		user2.setTranslateY(enemyUser2Y);
		enemyRoot.getChildren().add(user2);

		// User 3
		user3 = red.swordGetUser();
		user3.setTranslateX(enemyUser3X);
		user3.setTranslateY(enemyUser3Y);
		enemyRoot.getChildren().add(user3);

		// Green Enemy
		greenEnemy = green.swordGet();
		greenEnemy.setTranslateX(enemyGreenX);
		greenEnemy.setTranslateY(enemyGreenY);
		enemyRoot.getChildren().add(greenEnemy);

		// Purple Enemy
		purpleEnemy = purple.swordGet();
		purpleEnemy.setTranslateX(enemyPurpleX);
		purpleEnemy.setTranslateY(enemyPurpleY);
		enemyRoot.getChildren().add(purpleEnemy);
	}

	public void setUnkillable() {

		double enemyUser2X;
		double enemyUser2Y;
		double enemyUser3X;
		double enemyUser3Y;
		double enemyGreenX;
		double enemyGreenY;
		double enemyPurpleX;
		double enemyPurpleY;

		// Track
		enemyUser2X = user2.getTranslateX();
		enemyUser2Y = user2.getTranslateY();
		enemyUser3X = user3.getTranslateX();
		enemyUser3Y = user3.getTranslateY();
		enemyGreenX = greenEnemy.getTranslateX();
		enemyGreenY = greenEnemy.getTranslateY();
		enemyPurpleX = purpleEnemy.getTranslateX();
		enemyPurpleY = purpleEnemy.getTranslateY();

		enemyRoot.getChildren().clear();

		// User 2
		// user2 = createEnemy(enemyUser2X, enemyUser2Y, userTwo.getEnemyDirection());
		user2 = userTwo.userEnemy();
		user2.setTranslateX(enemyUser2X);
		user2.setTranslateY(enemyUser2Y);
		enemyRoot.getChildren().add(user2);

		// User 3
		// user3 = createEnemy(enemyUser3X, enemyUser3Y, userThree.getEnemyDirection());
		user3 = userThree.userEnemy();
		user3.setTranslateX(enemyUser3X);
		user3.setTranslateY(enemyUser3Y);
		enemyRoot.getChildren().add(user3);

		// Green Enemy
		greenEnemy = green.greenEnemy();
		greenEnemy.setTranslateX(enemyGreenX);
		greenEnemy.setTranslateY(enemyGreenY);
		enemyRoot.getChildren().add(greenEnemy);

		// Purple Enemy
		purpleEnemy = purple.purpleEnemy();
		purpleEnemy.setTranslateX(enemyPurpleX);
		purpleEnemy.setTranslateY(enemyPurpleY);
		enemyRoot.getChildren().add(purpleEnemy);
	}

	// ------------------------------------PLAYERDEATH----------------------------------
	private void playerDies() {
		userX = user.getTranslateX();
		userY = user.getTranslateY();
		userRoot.getChildren().clear();
		user = createKnight(userX, userY, userOne.getModelDeath());
	}

	public void setPlayerDeath() {

		if (playCount == 0) {
			mp.deadSFX();
			playCount++;
		}

		playerDies();

		stopGameTime();

		deathTime.setCycleCount(Timeline.INDEFINITE);

		if (deathTime != null) {
			deathTime.stop();
		}

		KeyFrame playerFrame = new KeyFrame(Duration.seconds(0.1), new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// running = false;
				deathCount--;
				if ((deathCount <= 20) && (deathCount % 2 == 0)) {
					playerOpacity -= 0.1;
					user.setOpacity(playerOpacity);
				}
				if (deathCount == -1) {
					deathTime.getKeyFrames().clear();
					deathTime.stop();
					running = true;
					deathComplete = true;
					deathCount = 20;
					playerOpacity = 1;
					// user.setOpacity(playerOpacity);

				}
			}
		});

		deathTime.getKeyFrames().add(playerFrame);
		deathTime.playFromStart();

	}

	public void stopPlayerDeathTime() {
		deathTime.getKeyFrames().clear();
		deathTime.stop();
		deathCount = 20;
		playCount = 0;
	}

	// ------------------------------------ENEMYDEATH----------------------------------
	private void enemyDies(Node enemy) {
		double enemyX;
		double enemyY;
		if (enemy == user2) {
			enemyX = user2.getTranslateX();
			enemyY = user2.getTranslateY();
			popupRoot.getChildren().remove(user2);
			createDeathPopup(40, 40, enemyX, enemyY, red.getEnemyDeath());
			// enemyDies = createImage(40, 40, enemyX, enemyY, red.getEnemyDeath());
		} else if (enemy == user3) {
			enemyX = user3.getTranslateX();
			enemyY = user3.getTranslateY();
			popupRoot.getChildren().remove(user3);
			createDeathPopup(40, 40, enemyX, enemyY, blue.getEnemyDeath());
		} else if (enemy == greenEnemy) {
			enemyX = greenEnemy.getTranslateX();
			enemyY = greenEnemy.getTranslateY();
			popupRoot.getChildren().remove(greenEnemy);
			createDeathPopup(40, 40, enemyX, enemyY, blue.getEnemyDeath());
		} else if (enemy == purpleEnemy) {
			enemyX = purpleEnemy.getTranslateX();
			enemyY = purpleEnemy.getTranslateY();
			popupRoot.getChildren().remove(purpleEnemy);
			createDeathPopup(40, 40, enemyX, enemyY, blue.getEnemyDeath());
		}
	}

	public void setEnemyDeath(Node enemy) {

		enemyDeathOn = false;

		enemyDies(enemy);

		stopGameTime();
		stopSwordTime();

		enemyTime.setCycleCount(Timeline.INDEFINITE);

		if (enemyTime != null) {
			enemyTime.stop();
		}

		KeyFrame enemyFrame = new KeyFrame(Duration.seconds(0.1), new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// running = false;
				enemyCount--;

				if (enemyCount == -1) {
					enemyTime.getKeyFrames().clear();
					enemyTime.stop();
					running = true;
					enemyCount = 20;
					popupRoot.getChildren().clear();
					setSwordTime();

				}
			}
		});

		enemyTime.getKeyFrames().add(enemyFrame);
		enemyTime.playFromStart();

	}

	public void stopEnemyDeathTime() {
		enemyTime.getKeyFrames().clear();
		enemyTime.stop();
		deathCount = 20;
	}

	public void enemyKilledScore() {
		Score += 500;
		String ScoreString = Integer.toString(Score);
		StackPane scoreValue = createMenu(100, 30, 850, 15, ScoreString, Color.WHITE);
		appRoot.getChildren().add(scoreValue);
	}

	// --------------------------------------------------------------
	/*
	 * Getter methods that are useful for the game
	 */
	public int getScore() {
		return Score;
	}

	public void resetScore() {
		Score = 0;
	}

	public int getTime() {
		return TIME;
	}

	public void resetTIME() {
		TIME = 0;
	}

	private int[][] getCheckMaze() {

		return checkMaze;
	}

	// ------------------------------ShortestPath--------------------------------
	/*
	 * We have used BFS as our path-finding algorithm to find the shortest path to
	 * get to the player. The following is similar to what we have done last year in
	 * 202 for dijkstra, except this maze is an Unweighted 2D graph, which we have
	 * to find the edges. The purpose of the Point class is to store positions of x
	 * and y. Then we blindly search for possible edges and then enqueue them
	 * Dequeue a point to look for more possible edges until the destination is
	 * reached Point p will then contain all of the positions which then can be used
	 * by the ghosts
	 */

	/*
	 * AI in multiplayer are set to be somewhat stupid. Both slimes have the same
	 * behaviours which they will circle around their assigned corner unless they
	 * can reach the player in 8 moves or less This design is created because we
	 * wanted the spotlight of the game mode to be focused on users rather than on
	 * the AI
	 */

	public static class Point {
		int x;
		int y;
		Point parent;

		public Point(int x, int y, Point parent) {
			this.x = x;
			this.y = y;
			this.parent = parent;

		}

		public Point getParent() {
			return this.parent;
		}

		public String toString() {
			return "x = " + x + " y = " + y;
		}
	}

	public static Queue<Point> q = new LinkedList<Point>();

	public Point getPathBFS(int x, int y) {

		int[][] ar = new int[20][27];

		ar = getCheckMaze();

		q.add(new Point(x, y, null));

		while (!q.isEmpty()) {
			Point p = q.remove();

			if (ar[p.x][p.y] == 9) {
				for (int i = 0; i < checkMaze.length; i++) {
					for (int j = 0; j < checkMaze[0].length; j++) {

						if (ar[i][j] == -1 || ar[i][j] == 9) {
							ar[i][j] = 0;
						}
					}
				}

				q.clear();
				return p;
			}

			if (isFree(p.x + 1, p.y, ar.length, ar[0].length, ar)) {
				ar[p.x][p.y] = -1;
				Point nextP = new Point(p.x + 1, p.y, p);
				q.add(nextP);
			}

			if (isFree(p.x - 1, p.y, ar.length, ar[0].length, ar)) {
				ar[p.x][p.y] = -1;
				Point nextP = new Point(p.x - 1, p.y, p);
				q.add(nextP);
			}

			if (isFree(p.x, p.y + 1, ar.length, ar[0].length, ar)) {
				ar[p.x][p.y] = -1;
				Point nextP = new Point(p.x, p.y + 1, p);
				q.add(nextP);
			}

			if (isFree(p.x, p.y - 1, ar.length, ar[0].length, ar)) {
				ar[p.x][p.y] = -1;
				Point nextP = new Point(p.x, p.y - 1, p);
				q.add(nextP);
			}

		}

		for (int i = 0; i < checkMaze.length; i++) {
			for (int j = 0; j < checkMaze[0].length; j++) {
				if (ar[i][j] == -1) {
					ar[i][j] = 0;
				}
			}
		}

		return null;
	}

	public static boolean isFree(int x, int y, int lengthX, int lengthY, int[][] maze) {
		if ((x >= 0 && x < lengthX) && (y >= 0 && y < lengthY) && (maze[x][y] == 0 || maze[x][y] == 9 || maze[x][y] == 2
				|| maze[x][y] == 3 || maze[x][y] == 8 || maze[x][y] == 7 || maze[x][y] == 10)) {

			return true;
		}
		return false;
	}

	public Point findCorner(int x, int y) {

		int[][] ar = new int[20][27];

		ar = checkMaze;

		q.add(new Point(x, y, null));

		while (!q.isEmpty()) {
			Point p = q.remove();

			if (ar[p.x][p.y] == 8) {
				for (int i = 0; i < checkMaze.length; i++) {
					for (int j = 0; j < checkMaze[0].length; j++) {
						if (ar[i][j] == -1) {
							ar[i][j] = 0;
						}
					}
				}
				q.clear();
				return p;
			}

			if (isFree(p.x + 1, p.y, ar.length, ar[0].length, ar)) {
				ar[p.x][p.y] = -1;
				Point nextP = new Point(p.x + 1, p.y, p);
				q.add(nextP);
			}

			if (isFree(p.x - 1, p.y, ar.length, ar[0].length, ar)) {
				ar[p.x][p.y] = -1;
				Point nextP = new Point(p.x - 1, p.y, p);
				q.add(nextP);
			}

			if (isFree(p.x, p.y + 1, ar.length, ar[0].length, ar)) {
				ar[p.x][p.y] = -1;
				Point nextP = new Point(p.x, p.y + 1, p);
				q.add(nextP);
			}

			if (isFree(p.x, p.y - 1, ar.length, ar[0].length, ar)) {
				ar[p.x][p.y] = -1;
				Point nextP = new Point(p.x, p.y - 1, p);
				q.add(nextP);
			}

		}

		for (int i = 0; i < checkMaze.length; i++) {
			for (int j = 0; j < checkMaze[0].length; j++) {
				if (ar[i][j] == -1) {
					ar[i][j] = 0;
				}
			}
		}
		return null;
	}

	public void greenMovement() {
		greenx = (int) (greenEnemy.getTranslateX() / 40);
		greeny = (int) (greenEnemy.getTranslateY() / 40);

		if (greenEnemy.getTranslateX() == 600 && greenEnemy.getTranslateY() == 360) {
			checkMaze[3][25] = 8;
			check = 0;
		}
		if (greenEnemy.getTranslateX() == 1000 && greenEnemy.getTranslateY() == 120) {
			checkMaze[6][15] = 8;
			checkMaze[11][19] = 0;
			checkMaze[3][25] = 0;
			check = 1;
		} else if (greenEnemy.getTranslateX() == 600 && greenEnemy.getTranslateY() == 240) {
			checkMaze[6][15] = 0;
			checkMaze[11][19] = 8;
			checkMaze[3][25] = 0;
			check = 2;
		} else if (greenEnemy.getTranslateX() == 760 && greenEnemy.getTranslateY() == 440) {
			checkMaze[6][15] = 0;
			checkMaze[11][19] = 0;
			checkMaze[3][25] = 8;
			check = 0;
		}

		Point point;
		if (greenEnemy.getTranslateX() % 40 == 0 && greenEnemy.getTranslateY() % 40 == 0) {

			p = getPathBFS(greeny, greenx);
			if (check == 0) {
				checkMaze[3][25] = 8;
				checkMaze[6][15] = 0;
				checkMaze[11][19] = 0;
			} else if (check == 1) {
				checkMaze[6][15] = 8;
				checkMaze[3][25] = 0;
				checkMaze[11][19] = 0;
			} else if (check == 2) {
				checkMaze[11][19] = 8;
				checkMaze[6][15] = 0;
				checkMaze[3][25] = 0;
			}

			if (p != null) {
				while (p.getParent() != null) {
					store.add(p);
					p = p.getParent();
				}

				point = null;
				if (store.size() < 8) {
					for (int i = 0; i < store.size(); i++) {
						point = store.get(i);
						if (point.x == greeny && point.y == greenx && i != 0) {
							point = store.get(i - 1);
							break;
						}

					}

				} else {

					store.clear();

					p = findCorner(greeny, greenx);
					if (p != null) {
						while (p.getParent() != null) {
							store.add(p);
							p = p.getParent();
						}
						for (int i = 0; i < store.size(); i++) {
							point = store.get(i);
							if (point.x == greeny && point.y == greenx && i != 0) {
								point = store.get(i - 1);
								break;
							}
						}
					}
				}

				p = point;

				if (p != null) {
					if (p.x > greeny) {
						moveUp = 1;
					} else if (p.x < greeny) {
						moveUp = 2;
					} else if (p.x == greenEnemy.getTranslateY() / 40.0) {
						moveUp = 0;
					}
					if (p.y > greenx) {
						moveRight = 1;
					} else if (p.y < greenx) {
						moveRight = 2;
					} else if (p.y == greenEnemy.getTranslateX() / 40.0) {
						moveRight = 0;
					}
				}
			}
		}
		if (moveUp == 1) {
			movegreenY(1);
		} else if (moveUp == 2) {
			movegreenY(-1);
		}
		if (moveRight == 1) {
			movegreenX(1);
		} else if (moveRight == 2) {
			movegreenX(-1);
		}

		greenx = 0;
		greeny = 0;
		store.clear();
	}

	private void movegreenY(int value) {
		boolean movingDown = value > 0;
		int abs = Math.abs(value);
		for (int i = 0; i < abs; i++) {
			if (movingDown) {
				greenEnemy.setTranslateY(greenEnemy.getTranslateY() + 1);

			} else {
				greenEnemy.setTranslateY(greenEnemy.getTranslateY() - 1);
			}
		}
	}

	private void movegreenX(int value) {
		boolean movingRight = value > 0;

		for (int i = 0; i < Math.abs(value); i++) {
			if (movingRight) {
				greenEnemy.setTranslateX(greenEnemy.getTranslateX() + 1);

			} else {
				greenEnemy.setTranslateX(greenEnemy.getTranslateX() - 1);

			}
		}
	}

	// -------------------------------------------------
	public Point findOtherCorner(int x, int y) {

		int[][] ar = new int[20][27];

		ar = checkMaze;

		q.add(new Point(x, y, null));

		while (!q.isEmpty()) {
			Point p = q.remove();

			if (ar[p.x][p.y] == 7) {
				for (int i = 0; i < checkMaze.length; i++) {
					for (int j = 0; j < checkMaze[0].length; j++) {
						if (ar[i][j] == -1) {
							ar[i][j] = 0;
						}
					}
				}
				q.clear();
				return p;
			}

			if (isFree(p.x + 1, p.y, ar.length, ar[0].length, ar)) {
				ar[p.x][p.y] = -1;
				Point nextP = new Point(p.x + 1, p.y, p);
				q.add(nextP);
			}

			if (isFree(p.x - 1, p.y, ar.length, ar[0].length, ar)) {
				ar[p.x][p.y] = -1;
				Point nextP = new Point(p.x - 1, p.y, p);
				q.add(nextP);
			}

			if (isFree(p.x, p.y + 1, ar.length, ar[0].length, ar)) {
				ar[p.x][p.y] = -1;
				Point nextP = new Point(p.x, p.y + 1, p);
				q.add(nextP);
			}

			if (isFree(p.x, p.y - 1, ar.length, ar[0].length, ar)) {
				ar[p.x][p.y] = -1;
				Point nextP = new Point(p.x, p.y - 1, p);
				q.add(nextP);
			}

		}

		for (int i = 0; i < checkMaze.length; i++) {
			for (int j = 0; j < checkMaze[0].length; j++) {
				if (ar[i][j] == -1) {
					ar[i][j] = 0;
				}
			}
		}
		return null;
	}

	public void purpleMovement() {
		purplex = (int) (purpleEnemy.getTranslateX() / 40);
		purpley = (int) (purpleEnemy.getTranslateY() / 40);

		if (purpleEnemy.getTranslateX() == 360 && purpleEnemy.getTranslateY() == 360) {
			checkMaze[3][1] = 7;
			pcheck = 0;
		}
		if (purpleEnemy.getTranslateX() == 40 && purpleEnemy.getTranslateY() == 120) {
			checkMaze[8][1] = 7;
			checkMaze[8][7] = 0;
			checkMaze[3][1] = 0;
			pcheck = 1;
		} else if (purpleEnemy.getTranslateX() == 40 && purpleEnemy.getTranslateY() == 320) {
			checkMaze[8][1] = 0;
			checkMaze[8][7] = 7;
			checkMaze[3][1] = 0;
			pcheck = 2;
		} else if (purpleEnemy.getTranslateX() == 280 && purpleEnemy.getTranslateY() == 320) {
			checkMaze[8][1] = 0;
			checkMaze[8][7] = 0;
			checkMaze[3][1] = 7;
			pcheck = 0;
		}

		Point point;
		if (purpleEnemy.getTranslateX() % 40 == 0 && purpleEnemy.getTranslateY() % 40 == 0) {

			p2 = getPathBFS(purpley, purplex);
			if (pcheck == 0) {
				checkMaze[3][1] = 7;
				checkMaze[8][1] = 0;
				checkMaze[8][7] = 0;
			} else if (pcheck == 1) {
				checkMaze[8][1] = 7;
				checkMaze[3][1] = 0;
				checkMaze[8][7] = 0;
			} else if (pcheck == 2) {
				checkMaze[8][7] = 7;
				checkMaze[3][1] = 0;
				checkMaze[8][1] = 0;
			}

			if (p2 != null) {
				while (p2.getParent() != null) {
					store2.add(p2);
					p2 = p2.getParent();
				}

				point = null;
				if (store2.size() < 8) {
					for (int i = 0; i < store2.size(); i++) {
						point = store2.get(i);
						if (point.x == purpley && point.y == purplex && i != 0) {
							point = store2.get(i - 1);
							break;
						}

					}

				} else {

					store2.clear();

					p2 = findOtherCorner(purpley, purplex);
					if (p2 != null) {
						while (p2.getParent() != null) {
							store2.add(p2);
							p2 = p2.getParent();
						}
						for (int i = 0; i < store2.size(); i++) {
							point = store2.get(i);
							if (point.x == purpley && point.y == purplex && i != 0) {
								point = store2.get(i - 1);
								break;
							}
						}
					}
				}

				p2 = point;

				if (p2 != null) {
					if (p2.x > purpley) {
						pmoveUp = 1;
					} else if (p2.x < purpley) {
						pmoveUp = 2;
					} else if (p2.x == purpleEnemy.getTranslateY() / 40.0) {
						pmoveUp = 0;
					}
					if (p2.y > purplex) {
						pmoveRight = 1;
					} else if (p2.y < purplex) {
						pmoveRight = 2;
					} else if (p2.y == purpleEnemy.getTranslateX() / 40.0) {
						pmoveRight = 0;
					}
				}
			}
		}
		if (pmoveUp == 1) {
			movepurpleY(1);
		} else if (pmoveUp == 2) {
			movepurpleY(-1);
		}
		if (pmoveRight == 1) {
			movepurpleX(1);
		} else if (pmoveRight == 2) {
			movepurpleX(-1);
		}

		purplex = 0;
		purpley = 0;
		store2.clear();
	}

	private void movepurpleY(int value) {
		boolean movingDown = value > 0;
		int abs = Math.abs(value);
		for (int i = 0; i < abs; i++) {
			if (movingDown) {
				purpleEnemy.setTranslateY(purpleEnemy.getTranslateY() + 1);

			} else {
				purpleEnemy.setTranslateY(purpleEnemy.getTranslateY() - 1);
			}
		}
	}

	private void movepurpleX(int value) {
		boolean movingRight = value > 0;

		for (int i = 0; i < Math.abs(value); i++) {
			if (movingRight) {
				purpleEnemy.setTranslateX(purpleEnemy.getTranslateX() + 1);

			} else {
				purpleEnemy.setTranslateX(purpleEnemy.getTranslateX() - 1);

			}
		}
	}
	// ---------------------------------------------------------------------------------

	// -------------------------------------Menu-------------------------------------------

	public VBox menuSetUp() {
		Button music = new Button("Music");
		music.setOnKeyPressed(event -> {
			if (event.getCode() == KeyCode.ENTER) {
				musicStage.show();
			}
		});

		music.setOnAction(event -> {
			musicStage.show();
		});

		Button retry = new Button("Retry");

		retry.setOnAction((ActionEvent event) -> {
			try {
				running = false;
				this.mp.stopMulti();
				menuStage.close();
				resetScore();
				MultiPlayerStage mps = new MultiPlayerStage();
				mps.mapGeneration(stage);

			} catch (Exception e) {
				e.printStackTrace();
			}
		});

		retry.setOnKeyPressed((KeyEvent event) -> {
			if (event.getCode() == KeyCode.ENTER) {
				try {
					running = false;
					this.mp.stopMulti();
					menuStage.close();
					resetScore();
					MultiPlayerStage mps = new MultiPlayerStage();
					mps.mapGeneration(stage);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		Button exit = new Button("Exit");
		exit.setOnAction((ActionEvent event) -> {
			Parent parent;
			menuStage.close();
			this.mp.BGM();
			this.mp.stopMulti();
			running = false;
			timePaused = true;
			try {
				parent = FXMLLoader.load(getClass().getResource("Welcome.fxml"));
				Scene scene2 = new Scene(parent);
				stage.setScene(scene2);
				stage.show();
			} catch (IOException e) {
				e.printStackTrace();
			}
		});

		exit.setOnKeyPressed((KeyEvent event) -> {
			if (event.getCode() == KeyCode.ENTER) {
				Parent parent;
				menuStage.close();
				this.mp.BGM();
				this.mp.stopMulti();
				running = false;
				timePaused = true;
				try {
					parent = FXMLLoader.load(getClass().getResource("Welcome.fxml"));
					Scene scene2 = new Scene(parent);
					stage.setScene(scene2);
					stage.show();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});

		Label menuName = new Label();
		menuName.setText("Menu");

		VBox subMenu = new VBox();
		subMenu.setPrefHeight(315);
		subMenu.setPrefWidth(300);

		menuName.setMinWidth(subMenu.getPrefWidth());
		menuName.setMinHeight(80);

		subMenu.setSpacing(5);

		music.setMinWidth(subMenu.getPrefWidth());
		music.setMinHeight(80);

		subMenu.setSpacing(5);

		retry.setMinWidth(subMenu.getPrefWidth());
		retry.setMinHeight(80);

		subMenu.setSpacing(5);

		exit.setMinWidth(subMenu.getPrefWidth());
		exit.setMinHeight(80);

		subMenu.getChildren().addAll(menuName, music, retry, exit);
		return subMenu;
	}

	// ------------------------Direction Control Of player----------------------
	public void directionSelect() {
		int speed = 2;
		int negativeSpeed = 0 - speed;

		prevDirection = direction;
		// Direction select

		if (isPressed(KeyCode.UP) && user.getTranslateY() >= 5) {
			direction = "UP";
		}

		if (isPressed(KeyCode.LEFT) && user.getTranslateX() >= 5) {
			direction = "LEFT";
		}

		if (isPressed(KeyCode.RIGHT) && user.getTranslateX() + 40 <= levelWidth - 5) {
			direction = "RIGHT";
		}

		if (isPressed(KeyCode.DOWN) && user.getTranslateY() + 40 >= 5) {
			direction = "DOWN";
		}
		if (isPressed(KeyCode.UP) && (isPressed(KeyCode.RIGHT))) {
			direction = "UP_RIGHT";
		}
		if (isPressed(KeyCode.UP) && (isPressed(KeyCode.LEFT))) {
			direction = "UP_LEFT";
		}
		if (isPressed(KeyCode.DOWN) && (isPressed(KeyCode.RIGHT))) {
			direction = "DOWN_RIGHT";
		}
		if (isPressed(KeyCode.DOWN) && (isPressed(KeyCode.LEFT))) {
			direction = "DOWN_LEFT";
		}
		if (isPressed(KeyCode.P)) {
			direction = "NONE";
		}

		if (!prevDirection.equals(direction)) {
			directionSet = 0;
		}

		// Direction Move
		if (direction.equals("UP")) {
			if (directionSet == 0) {
				changeDirection(userOne.getModelDirection(direction));
				directionSet = 1;
			}
			moveuserY(negativeSpeed);
		} else if (direction.equals("LEFT")) {
			if (directionSet == 0) {
				changeDirection(userOne.getModelDirection(direction));
				directionSet = 1;
			}
			moveuserX(negativeSpeed);
		} else if (direction.equals("RIGHT")) {
			if (directionSet == 0) {
				changeDirection(userOne.getModelDirection(direction));
				directionSet = 1;
			}
			moveuserX(speed);
		} else if (direction.equals("DOWN")) {
			if (directionSet == 0) {
				changeDirection(userOne.getModelDirection(direction));
				directionSet = 1;
			}
			moveuserY(speed);
		} else if (direction.equals("UP_RIGHT")) {
			moveuserY(negativeSpeed);
			moveuserX(speed);
		} else if (direction.equals("UP_LEFT")) {
			moveuserY(negativeSpeed);
			moveuserX(negativeSpeed);
		} else if (direction.equals("DOWN_RIGHT")) {
			moveuserY(speed);
			moveuserX(speed);
		} else if (direction.equals("DOWN_LEFT")) {
			moveuserY(speed);
			moveuserX(negativeSpeed);
		}
	}

	// Movement of user
	private void moveuserX(int value) {
		boolean movingRight = value > 0;

		for (int i = 0; i < Math.abs(value); i++) {
			for (Node platform : platforms) {
				if (user.getBoundsInParent().intersects(platform.getBoundsInParent())) {
					if (movingRight) {
						if (user.getTranslateX() + 36 == platform.getTranslateX()) {

							user.setTranslateX(user.getTranslateX() - 1);
							return;
						}
					} else {
						if (user.getTranslateX() == platform.getTranslateX() + 40) {

							user.setTranslateX(user.getTranslateX() + 1);
							return;
						}
					}
				}
			}
			user.setTranslateX(user.getTranslateX() + (movingRight ? 1 : -1));
			if (user.getTranslateX() + 36 == 1080.0) {
				user.setTranslateX(0);
			} else if (user.getTranslateX() == 0) {
				user.setTranslateX(1036.0);
			}

		}
	}

	private void moveuserY(int value) {
		boolean movingDown = value > 0;

		for (int i = 0; i < Math.abs(value); i++) {
			for (Node platform : platforms) {
				if (user.getBoundsInParent().intersects(platform.getBoundsInParent())) {
					if (movingDown) {
						if (user.getTranslateY() + 36 == platform.getTranslateY()) {

							user.setTranslateY(user.getTranslateY() - 1);
							return;
						}
					} else {
						if (user.getTranslateY() == platform.getTranslateY() + 40) {

							user.setTranslateY(user.getTranslateY() + 1);
							return;
						}
					}
				}
			}
			if (playery == 6 && playerx == 13 && movingDown) {
				return;

			}
			user.setTranslateY(user.getTranslateY() + (movingDown ? 1 : -1));
		}
	}

	private void changeDirection(String direction) {
		userX = user.getTranslateX();
		userY = user.getTranslateY();
		userRoot.getChildren().clear();
		user = createKnight(userX, userY, direction);

	}
	// ------------------------------------------------------------------------------------

	public void directionSelectPlayer2() {
		int speed = 2;
		int negativeSpeed = 0 - speed;

		prevDirection2 = direction2;
		// Direction select

		if (isPressed(KeyCode.W) && user2.getTranslateY() >= 5) {
			direction2 = "UP";
		}

		if (isPressed(KeyCode.A) && user2.getTranslateX() >= 5) {
			direction2 = "LEFT";
		}

		if (isPressed(KeyCode.D) && user2.getTranslateX() + 40 <= levelWidth - 5) {
			direction2 = "RIGHT";
		}

		if (isPressed(KeyCode.S) && user.getTranslateY() + 40 >= 5) {
			direction2 = "DOWN";
		}
		if (isPressed(KeyCode.W) && (isPressed(KeyCode.D))) {
			direction2 = "UP_RIGHT";
		}
		if (isPressed(KeyCode.W) && (isPressed(KeyCode.A))) {
			direction2 = "UP_LEFT";
		}
		if (isPressed(KeyCode.S) && (isPressed(KeyCode.D))) {
			direction2 = "DOWN_RIGHT";
		}
		if (isPressed(KeyCode.S) && (isPressed(KeyCode.A))) {
			direction2 = "DOWN_LEFT";
		}
		if (isPressed(KeyCode.P)) {
			direction2 = "NONE";
		}

		if (!prevDirection2.equals(direction2)) {
			directionSet2 = 0;
		}

		// Direction Move
		if (direction2.equals("UP")) {
			// if (directionSet2 == 0) {
			// changeDirection2("character/KnightUp.gif");
			// directionSet2 = 1;
			// }
			moveuser2Y(negativeSpeed);
		} else if (direction2.equals("LEFT")) {
			// if (directionSet2 == 0) {
			// changeDirection2("character/KnightLeft.gif");
			// directionSet2 = 1;
			// }
			moveuser2X(negativeSpeed);
		} else if (direction2.equals("RIGHT")) {
			// if (directionSet2 == 0) {
			// changeDirection2("character/KnightRight.gif");
			// directionSet2 = 1;
			// }
			moveuser2X(speed);
		} else if (direction2.equals("DOWN")) {
			// if (directionSet2 == 0) {
			// changeDirection2("character/KnightDown.gif");
			// directionSet2 = 1;
			// }
			moveuser2Y(speed);
		} else if (direction2.equals("UP_RIGHT")) {
			moveuser2Y(negativeSpeed);
			moveuser2X(speed);
		} else if (direction2.equals("UP_LEFT")) {
			moveuser2Y(negativeSpeed);
			moveuser2X(negativeSpeed);
		} else if (direction2.equals("DOWN_RIGHT")) {
			moveuser2Y(speed);
			moveuser2X(speed);
		} else if (direction2.equals("DOWN_LEFT")) {
			moveuser2Y(speed);
			moveuser2X(negativeSpeed);
		}
	}

	// Movement of user
	private void moveuser2X(int value) {
		boolean movingRight = value > 0;

		for (int i = 0; i < Math.abs(value); i++) {
			for (Node platform : platforms) {
				if (user2.getBoundsInParent().intersects(platform.getBoundsInParent())) {
					if (movingRight) {
						if (user2.getTranslateX() + 36 == platform.getTranslateX()) {

							user2.setTranslateX(user2.getTranslateX() - 1);
							return;
						}
					} else {
						if (user2.getTranslateX() == platform.getTranslateX() + 40) {

							user2.setTranslateX(user2.getTranslateX() + 1);
							return;
						}
					}
				}
			}
			user2.setTranslateX(user2.getTranslateX() + (movingRight ? 1 : -1));
			if (user2.getTranslateX() + 36 == 1080.0) {
				user2.setTranslateX(0);
			} else if (user2.getTranslateX() == 0) {
				user2.setTranslateX(1036.0);
			}

		}
	}

	private void moveuser2Y(int value) {
		boolean movingDown = value > 0;

		for (int i = 0; i < Math.abs(value); i++) {
			for (Node platform : platforms) {
				if (user2.getBoundsInParent().intersects(platform.getBoundsInParent())) {
					if (movingDown) {
						if (user2.getTranslateY() + 36 == platform.getTranslateY()) {

							user2.setTranslateY(user2.getTranslateY() - 1);
							return;
						}
					} else {
						if (user2.getTranslateY() == platform.getTranslateY() + 40) {

							user2.setTranslateY(user2.getTranslateY() + 1);
							return;
						}
					}
				}
			}
			user2.setTranslateY(user2.getTranslateY() + (movingDown ? 1 : -1));
		}
	}

	// private void changeDirection2(String direction) {
	// user2X = user2.getTranslateX();
	// user2Y = user2.getTranslateY();
	// user2Root.getChildren().clear();
	// user2 = createKnight2(user2X, user2Y, direction);
	//
	// }

	public void directionSelectPlayer3() {
		int speed = 2;
		int negativeSpeed = 0 - speed;

		prevDirection3 = direction3;
		// Direction select

		if (isPressed(KeyCode.U) && user3.getTranslateY() >= 5) {
			direction3 = "UP";
		}

		if (isPressed(KeyCode.H) && user3.getTranslateX() >= 5) {
			direction3 = "LEFT";
		}

		if (isPressed(KeyCode.K) && user3.getTranslateX() + 40 <= levelWidth - 5) {
			direction3 = "RIGHT";
		}

		if (isPressed(KeyCode.J) && user3.getTranslateY() + 40 >= 5) {
			direction3 = "DOWN";
		}
		if (isPressed(KeyCode.U) && (isPressed(KeyCode.K))) {
			direction3 = "UP_RIGHT";
		}
		if (isPressed(KeyCode.U) && (isPressed(KeyCode.H))) {
			direction3 = "UP_LEFT";
		}
		if (isPressed(KeyCode.J) && (isPressed(KeyCode.K))) {
			direction3 = "DOWN_RIGHT";
		}
		if (isPressed(KeyCode.J) && (isPressed(KeyCode.H))) {
			direction3 = "DOWN_LEFT";
		}
		if (isPressed(KeyCode.P)) {
			direction3 = "NONE";
		}

		// if (!prevDirection3.equals(direction3)) {
		// directionSet3 = 0;
		// }

		// Direction Move
		if (direction3.equals("UP")) {
			// if (directionSet3 == 0) {
			// changeDirection3("character/KnightUp.gif");
			// directionSet3 = 1;
			// }
			moveuser3Y(negativeSpeed);
		} else if (direction3.equals("LEFT")) {
			// if (directionSet3 == 0) {
			// changeDirection3("character/KnightLeft.gif");
			// directionSet3 = 1;
			// }
			moveuser3X(negativeSpeed);
		} else if (direction3.equals("RIGHT")) {
			// if (directionSet3 == 0) {
			// changeDirection3("character/KnightRight.gif");
			// directionSet3 = 1;
			// }
			moveuser3X(speed);
		} else if (direction3.equals("DOWN")) {
			// if (directionSet3 == 0) {
			// changeDirection3("character/KnightDown.gif");
			// directionSet3 = 1;
			// }
			moveuser3Y(speed);
		} else if (direction3.equals("UP_RIGHT")) {
			moveuser3Y(negativeSpeed);
			moveuser3X(speed);
		} else if (direction3.equals("UP_LEFT")) {
			moveuser3Y(negativeSpeed);
			moveuser3X(negativeSpeed);
		} else if (direction3.equals("DOWN_RIGHT")) {
			moveuser3Y(speed);
			moveuser3X(speed);
		} else if (direction3.equals("DOWN_LEFT")) {
			moveuser3Y(speed);
			moveuser3X(negativeSpeed);
		}
	}

	// Movement of user
	private void moveuser3X(int value) {
		boolean movingRight = value > 0;

		for (int i = 0; i < Math.abs(value); i++) {
			for (Node platform : platforms) {
				if (user3.getBoundsInParent().intersects(platform.getBoundsInParent())) {
					if (movingRight) {
						if (user3.getTranslateX() + 36 == platform.getTranslateX()) {

							user3.setTranslateX(user3.getTranslateX() - 1);
							return;
						}
					} else {
						if (user3.getTranslateX() == platform.getTranslateX() + 40) {

							user3.setTranslateX(user3.getTranslateX() + 1);
							return;
						}
					}
				}
			}
			user3.setTranslateX(user3.getTranslateX() + (movingRight ? 1 : -1));
			if (user3.getTranslateX() + 36 == 1080.0) {
				user3.setTranslateX(0);
			} else if (user3.getTranslateX() == 0) {
				user3.setTranslateX(1036.0);
			}

		}
	}

	private void moveuser3Y(int value) {
		boolean movingDown = value > 0;

		for (int i = 0; i < Math.abs(value); i++) {
			for (Node platform : platforms) {
				if (user3.getBoundsInParent().intersects(platform.getBoundsInParent())) {
					if (movingDown) {
						if (user3.getTranslateY() + 36 == platform.getTranslateY()) {

							user3.setTranslateY(user3.getTranslateY() - 1);
							return;
						}
					} else {
						if (user3.getTranslateY() == platform.getTranslateY() + 40) {

							user3.setTranslateY(user3.getTranslateY() + 1);
							return;
						}
					}
				}
			}
			user3.setTranslateY(user3.getTranslateY() + (movingDown ? 1 : -1));
		}
	}

	// private void changeDirection3(String direction) {
	// user3X = user3.getTranslateX();
	// user3Y = user3.getTranslateY();
	// user3Root.getChildren().clear();
	// user3 = createKnight3(user3X, user3Y, direction);
	//
	// }
}
