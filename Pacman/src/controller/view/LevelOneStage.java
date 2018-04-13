package controller.view;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Queue;

import controller.main;
import controller.model.LevelData;
import controller.model.enemies;
import controller.model.level;
import controller.view.levelSelect;
import controller.view.upgradeControl;
import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class LevelOneStage extends level {

	private HashMap<KeyCode, Boolean> keys = new HashMap<KeyCode, Boolean>();
    private ArrayList<Node> coins = new ArrayList<Node>();
    private ArrayList<Node> platforms = new ArrayList<Node>();
    private ArrayList<Node> hearts = new ArrayList<Node>();
 
    private Pane appRoot = new Pane();
    private Pane gameRoot = new Pane();
    private Pane uiRoot = new Pane();
    private Pane userRoot = new Pane();
    
    private double userX;
    private double userY;

    private Node user;
    
    private int lives = 3;
    private boolean createHearts = true;
    
    private static int Score = 0;
    private int CoinsCollected = 0;

    private int levelWidth;
    
     Node entityCreated;
     
    private String direction = "NONE";
    private String prevDirection = "Stationary";
    private int directionSet = 0;
    
    private boolean running = true;
    private boolean playerDead = false;
    private boolean playerHasDied = false;
    
    public Stage stage;
    private final Integer startTime = 3;
    private Integer currentTime = startTime;
    private StackPane display = new StackPane();
    
    private final Integer playTime = 120;
    private Integer gameTime = playTime;
    private static int TIME = 0;
    
    private StackPane displayTime = new StackPane();
    private int count = 0;
    Timeline time = new Timeline();
    Timeline timer = new Timeline();
    LevelData map = new LevelData();
    private int maze[][] = map.getLevel1();
    private int checkMaze[][] = maze;
    private int prevx = 0;
    private int prevy = 0;
    
    enemies red = new enemies();
	Node redEnemy = red.redEnemy();
	private Point p;

	private ArrayList<Point> store = new ArrayList<Point>();
	private int redx, redy;
	private boolean timePaused = false;
	levelSelect select = new levelSelect();
	private double checkCount = 0.0;
    
	private boolean swordHeld = false;
	private int swordGet = 0;
    Timeline swordTime = new Timeline();
    private final Integer swordStart = 60;
    private Integer swordCount = swordStart;
    private ArrayList<Node> swords = new ArrayList<Node>();
    
    private int coinCount = coins.size();
    
    //Initializes the gameplay area
    private void initContent() {
//        Rectangle bg = new Rectangle(1020, 768);
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
        		Node heart = createImage(40, 40, 160 + i * 50, 12, "img/heart.png" );
            	hearts.add(heart);
        	}
        }
        
//        coinSFX();
        
       
        playerDead = false;
        
        levelWidth = maze[0].length * 40;
        if (coins.isEmpty()) {
            for (int i = 0; i < maze.length; i++) {
            	for (int j = 0; j < maze[i].length; j++) {
            		switch(maze[i][j]) {
            		case 0: break;
            		case 1: Node Walls = createCharacter(j*40, i*40, 40, 40, Color.BLACK);
            				Node Tile = createImage(40, 40, j*40, i*40, "img/tile2.png");
            				platforms.add(Walls);
            				break;
            		case 2: Node coin = createImage(40, 40, j*40, i*40, "img/Coin.png");
            				coins.add(coin);
            				break;	
			//new
            		case 3: Node sword = createImage(40, 40, j*40, i*40, "img/sword.png");
            				swords.add(sword);
            		}
            	}
            }
        }

        
        
//        user = createCharacter(482, 662, 56, 56, Color.BLUE);
        user = createKnight(522, 722, "character/KnightStart.png");
        redEnemy.setTranslateX(520);
        redEnemy.setTranslateY(360);
        redEnemy.getProperties().put("alive", true);
        gameRoot.getChildren().add(redEnemy);
        //red = createCharacter(490, 150, 40, 40, Color.RED);
        //red.translateXProperty()
        
        
        
        user.translateXProperty().addListener((obs, old, newValue) -> {
            int offset = newValue.intValue();

            if (offset > 640 && offset < levelWidth - 640) {
                gameRoot.setLayoutX(-(offset - 640));
            }
        });
        
        if(!playerHasDied) {
        	setTime();
        }
        
        appRoot.getChildren().addAll(bgView, Lives, LivesLabel, gameRoot, userRoot, uiRoot, menuScore, menuScoreLabel, scoreValue);     

        
    }
    
    //Reset game when user dies
    private void clearGame() {

		if (lives == 0) {
			levelSelect select = new levelSelect();
			select.levelFailed(stage);
		}

		userRoot.getChildren().clear();
		uiRoot.getChildren().clear();
    	appRoot.getChildren().clear();
    	gameRoot.getChildren().removeAll(user, redEnemy);
    	gameRoot.getChildren().removeAll(hearts);
    	
    	
    	currentTime = 3;
    	count = 0;
    	resetTime();
    	resetGameTime();
    }
   

    //Continuously updates character interactions
    private void update() {
		if (currentTime < -1 && timePaused == false) {

			if (count == 0) {
				setGameTime();
				count++;
			}
			


				
			double x = user.getTranslateX();
        	double y = user.getTranslateY();
        	int newx = (int) (x/60);
        	int newy = (int) (y/60);
        	
        	
			checkMaze[newy][newx] = 9;
			
			if (prevx != newx || prevy != newy) {
				checkMaze[prevy][prevx] = 0;
			}
			
			redx = (int)(redEnemy.getTranslateX()/60);
			redy = (int)(redEnemy.getTranslateY()/60);

			//p = getPathBFS(redy, redx);
			if (p != null) {
				while (p.getParent() != null) {
					store.add(p);
					System.out.println(p);
					p = p.getParent();
				}

				Point point = null;
				for (int i = 0; i < store.size(); i++) {
					point = store.get(i);

					if (point.x == redy && point.y == redx && i != 0) {
						point = store.get(i - 1);
						break;
					}

				}
				if (redEnemy.getTranslateX() % 60 == 0 && redEnemy.getTranslateY() % 60 == 0) {
					
				}
				p = point;
				if (p != null) {
					if (p.x > redy) {
						moveredY(1);

						//moveredY(3);
					} else if (p.x < redy) {
				
							moveredY(-1);
						
					}
					if (p.y > redx) {
						
							moveredX(1);
						
					} else if (p.y < redx) {
					
							moveredX(-1);
						
					}
				}
				
			}
			// }

			redx = (int) (redEnemy.getTranslateX() / 60);
			redy = (int) (redEnemy.getTranslateY() / 60);

			prevx = newx;
			prevy = newy;
			store.clear();
			
			int speed = player.getSpeed();
			int negativeSpeed = 0 - speed;
			
			prevDirection = direction;
			coinCount = coins.size();

			//Direction select
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
			
			
			//Direction Move
	        if (direction.equals("UP")) {
	        	if (directionSet == 0) {
	        		changeDirection("character/KnightUp.gif");
	        		directionSet = 1;
	        	}
	        	moveuserY(negativeSpeed);
	        } else if (direction.equals("LEFT")) {
	        	if (directionSet == 0) {
	        		changeDirection("character/KnightLeft.gif");
	        		directionSet = 1;
	        	}
	        	moveuserX(negativeSpeed);
	        } else if (direction.equals("RIGHT")) {
	        	if (directionSet == 0) {
	        	changeDirection("character/KnightRight.gif");
	        		directionSet = 1;
	        	}
	        	moveuserX(speed);	        	
	        } else if (direction.equals("DOWN")) {
	        	if (directionSet == 0) {
	        		changeDirection("character/KnightDown.gif");
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

			// Removes Coins when collected
			for (Node coin : coins) {
				if (user.getBoundsInParent().intersects(coin.getBoundsInParent())) {
					coin.getProperties().put("alive", false);
					
				}
			}
			
			
			//Removes swords when collected
			for (Node sword : swords) {
				if (user.getBoundsInParent().intersects(sword.getBoundsInParent())) {
					sword.getProperties().put("alive", false);
					swordGet = 1;
					stopSwordTime();
					
				}
			}
	        for (Iterator<Node> it = swords.iterator(); it.hasNext(); ) {
	            Node sword = it.next();
	            if (!(Boolean)sword.getProperties().get("alive")) {
	                it.remove();
	                gameRoot.getChildren().remove(sword);
	            }
	        }
	        
	        //Start sword use time
	        if(swordGet == 1) {
	        	swordGet = 0;
	        	swordHeld = true;
	        	setSwordTime();
	        }
	        
	        //Sword usage
	        if (swordHeld == true) {
	        	swordUse();
	        }
			
	        //Checks if player is killed
	        if(!playerDead && (!swordHeld)) {
	        if(!playerDead) {
	        	if (user.getBoundsInParent().intersects(redEnemy.getBoundsInParent())) {
	        		playerDead = true;
	        		clearGame();
	            	lives--;
	        		initContent();
	        	}	
	        }
	        
	        
	        
	        //Removes coin if collected
	        }
			for (Iterator<Node> it = coins.iterator(); it.hasNext();) {
				Node coin = it.next();

				if (!(Boolean) coin.getProperties().get("alive")) {
					it.remove();
					CoinsCollected += 1;
					Score += 50 * player.getScoreMultiplier();
					String ScoreString = Integer.toString(Score);
					StackPane scoreValue = createMenu(100, 30, 850, 15, ScoreString, Color.WHITE);					
					appRoot.getChildren().add(scoreValue);
					gameRoot.getChildren().remove(coin);	
					
				}

			}
			
//			if (coinCount != coins.size()) {
//				coinSFX();
//			}

			// Fades away when all coins collected
			if (CoinsCollected == 150) {
				coinBagSFX();
				levelSelect select = new levelSelect();
				try {	
					select.levelClear(stage);
				} catch (IOException e) {
					e.printStackTrace();
				}

			}

		}

	}
    
    private void moveredY(int value) {
		boolean movingDown = value > 0;
		int abs = Math.abs(value);
		for (int i = 0; i < abs; i++) {
			if (movingDown) {
				redEnemy.setTranslateY(redEnemy.getTranslateY() + 1);

			} else {
				redEnemy.setTranslateY(redEnemy.getTranslateY() - 1);
			}
		}
	}

	private void moveredX(int value) {
		boolean movingRight = value > 0;

		for (int i = 0; i < Math.abs(value); i++) {
			if (movingRight) {
				redEnemy.setTranslateX(redEnemy.getTranslateX() + 1);

			} else {
				redEnemy.setTranslateX(redEnemy.getTranslateX() - 1);

			}
		}
	}
    

    private void changeDirection(String direction) {
    	userX = user.getTranslateX();
    	userY = user.getTranslateY();
    	userRoot.getChildren().clear();
    	user = createKnight(userX, userY, direction);
    	
    }
    
    //Movement of user
    private void moveuserX(int value) {
        boolean movingRight = value > 0;

        for (int i = 0; i < Math.abs(value); i++) {
            for (Node platform : platforms) {
                if (user.getBoundsInParent().intersects(platform.getBoundsInParent())) {
                    if (movingRight) {
                        if (user.getTranslateX() + 36 == platform.getTranslateX()) {
                        	
                        	user.setTranslateX(user.getTranslateX() - 2);
                            return;
                        }
                    }
                    else {
                        if (user.getTranslateX() == platform.getTranslateX() + 40) {
                        	
                        	user.setTranslateX(user.getTranslateX() + 2);
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
                        	
                        	user.setTranslateY(user.getTranslateY() - 2);
                            return;
                        }
                    }
                    else {
                        if (user.getTranslateY() == platform.getTranslateY() + 40) {
                        	
                        	user.setTranslateY(user.getTranslateY() + 2);
                            return;
                        }
                    }
                }
            }
            user.setTranslateY(user.getTranslateY() + (movingDown ? 1 : -1));
        }
    }
    
    

    //Creates walls
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
    	
    //Creates user
    private Node createCharacter(int x, int y, int w, int h, Color color) {
    	
        Rectangle entity = new Rectangle(w, h);
        entity.setTranslateX(x);
        entity.setTranslateY(y);
        entity.setFill(color);
        entity.getProperties().put("alive", true);

        gameRoot.getChildren().add(entity);
        

        return entity;
        
    }
    
    //Create knight user
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
    
    //Creates menu label
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
    
    //Creates menu box
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
    
    //Creates menu text
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

    //Confirms key press
    private boolean isPressed(KeyCode key) {
        return keys.getOrDefault(key, false);
    }
    
    //Check sword use
    private void swordUse() {
    	if (user.getBoundsInParent().intersects(redEnemy.getBoundsInParent())) {
    		gameRoot.getChildren().remove(redEnemy);
    	}
    }

//	private void coinSFX() {
//		URL path;
//		AudioClip coinSFX;
//		
//		path = getClass().getResource("/sfx/coin.wav");
//		coinSFX = new AudioClip(path.toString());
//		coinSFX.play();
//		
//    }
	
	private void coinBagSFX() {
		URL path;
		AudioClip coinSFX;
		
		path = getClass().getResource("/sfx/coinBag.wav");
		coinSFX = new AudioClip(path.toString());
		coinSFX.setVolume(0.2);
		coinSFX.play();
		
    }
    
    public void mapGeneration(Stage map) throws Exception {
    	
    	Stage menuStage = new Stage();
		menuStage.setTitle("PAUSE");
		menuStage.initStyle(StageStyle.TRANSPARENT);

		Button skip = new Button("skip");

		skip.setOnAction((ActionEvent event) -> {
			levelSelect select = new levelSelect();
			try {
				select.levelClear(stage);
			} catch (IOException e) {
				e.printStackTrace();
			}
			menuStage.close();
		});

		Button resume = new Button("resume");
		resume.setOpacity(1.0);
		resume.setOnAction((ActionEvent event) -> {
			time.play();
			timePaused = false;
			if (currentTime != -2) {
				timer.play();
			}
			menuStage.close();
		});

		Button retry = new Button("retry");

		retry.setOnAction((ActionEvent event) -> {
			try {
				menuStage.close();
				levelSelect select = new levelSelect();

				select.selectLevel(stage);

			} catch (Exception e) {
				e.printStackTrace();
			}
		});

		Button failTest = new Button("failTest");

		failTest.setOnAction((ActionEvent event) -> {
			try {
				menuStage.close();
				select.levelFailed(stage);

			} catch (Exception e) {
				e.printStackTrace();
			}
		});

		Label menuName = new Label();
		menuName.setText("Menu");
		HBox insertMenu = new HBox();
		insertMenu.setPadding(new Insets(10, 10, 10, 75));
		insertMenu.getChildren().add(menuName);

		VBox subMenu = new VBox();
		subMenu.setPrefHeight(300);
		subMenu.setPrefWidth(200);

		resume.setMinWidth(subMenu.getPrefWidth());
		resume.setMinHeight(40);

		skip.setMinWidth(subMenu.getPrefWidth());
		skip.setMinHeight(40);

		retry.setMinWidth(subMenu.getPrefWidth());
		retry.setMinHeight(40);

		failTest.setMinWidth(subMenu.getPrefWidth());
		failTest.setMinHeight(40);

		Button printMap = new Button("print map");
		printMap.setOnAction((ActionEvent event) -> {
			for (int i = 0; i < 13; i++) {
				for (int j = 0; j < 17; j++) {
					System.out.print(maze[i][j] + ",");
				}
				System.out.print("\n");
			}
			System.out.print("\n");
		});

		subMenu.getChildren().addAll(insertMenu, resume, retry, skip, failTest, printMap);
		Scene menuScene = new Scene(subMenu);
		menuScene.getStylesheets().add("/controller/model/menu.css");

		menuStage.setOpacity(0.7);

		menuScene.setOnKeyPressed(event -> {
			if (event.getCode() == KeyCode.P) {
				time.play();
				timePaused = false;
				if (currentTime != -2) {
					timer.play();
				}
				menuStage.close();
			}

		});

		menuStage.initModality(Modality.APPLICATION_MODAL);
		menuStage.initOwner(stage);
        
		initContent();
		this.stage = map;
		Scene scene = new Scene(appRoot);

		scene.setOnKeyPressed(event -> {

			keys.put(event.getCode(), true);

			/*
			 * Detect keyboard input of P and ESCAPE If ESCAPE is detected, show dialog to
			 * confirm exit If P is detected, the game will be paused until new interaction
			 * has been detected.
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
					try {
						parent = FXMLLoader.load(getClass().getResource("root_buttons.fxml"));
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
				timePaused = true;
				menuStage.setScene(menuScene);
				menuStage.show();
			}
			if (event.getCode() == KeyCode.PAGE_DOWN) {
				gameTime = 0;
			}
		});

		scene.setOnKeyReleased(event -> keys.put(event.getCode(), false));

		map.setScene(scene);
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
    
    
   
    //---------------------------------------Timer--------------------------------------------------
    /*
     * This is a count down timer from 3 to 0 which is displayed at the start of the game
     * Character will not be able to move nor the enemies until the timer has reached 0
     */
    
    public void setTime() {
    	
    	
    	timer.setCycleCount(Timeline.INDEFINITE);    	
    	if (timer != null) {
    		timer.stop();
    	}    	
    	
    	KeyFrame frame = new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				display = createMenu(200, 68, 440 , 350, currentTime.toString(), Color.WHITE);
				uiRoot.getChildren().add(display);
				currentTime--;
				if (currentTime == 0) {
					
					//display.setText("START");
				}
				if (currentTime == -1) {
					
					display = createMenu(200, 68, 440 , 350, "Start!", Color.WHITE);
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
     * This is a 2 minute game timer, counts from 120 to 0
     * This timer starts right after count down timer has reached 0
     * It stops when the time is 0 and displays TIMES UP, then a GAMEOVER scene will be displayed.
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
				displayTime = createMenu(100, 40, 490 , 10, gameTime.toString(), Color.WHITE);
				uiRoot.getChildren().add(displayTime);
				gameTime--;
				TIME++;
				if (gameTime == -1) {
					time.stop();
					displayTime = createMenu(100, 40, 490 , 10, "Times up!", Color.RED);
					uiRoot.getChildren().add(displayTime);
					levelSelect select = new levelSelect();
					select.levelFailed(stage);
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
    
	public void setSwordTime() {

		swordTime.setCycleCount(Timeline.INDEFINITE);

		if (swordTime != null) {
			swordTime.stop();
		}

		KeyFrame swordFrame = new KeyFrame(Duration.seconds(0.1), new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				swordCount--;
				if ((swordCount <= 30) && (swordCount % 2 == 1)) {		
					user.setOpacity(0.5);		
				} else if ((swordCount <= 30) && (swordCount % 2 == 0)){		
					user.setOpacity(1);		
				}
				if (swordCount == -1) {
					swordTime.getKeyFrames().clear();
					swordTime.stop();
					swordHeld = false;
					System.out.println("Sword has broken");
					swordCount = 60;
				}
			}
		});

		swordTime.getKeyFrames().add(swordFrame);
		swordTime.playFromStart();
	}
	
	public void stopSwordTime() {
		swordTime.getKeyFrames().clear();
		swordTime.stop();
		swordCount = 60;
	}
  //--------------------------------------------------------------
    
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
    
    //-----------------------------------AI MOVEMENT-----------------------------------------
    
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

		int[][] ar = new int[13][17];

		ar = getCheckMaze();

		q.add(new Point(x, y, null));

		while (!q.isEmpty()) {
			Point p = q.remove();

			if (ar[p.x][p.y] == 9) {
				for (int i = 0; i < 13; i++) {
					for (int j = 0; j < 17; j++) {
						if (ar[i][j] == -1 || ar[i][j] == 9) {
							ar[i][j] = 0;
						}
					}
				}
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
		return null;
	}

	private int[][] getCheckMaze() {
		
		return checkMaze;
	}

	public static boolean isFree(int x, int y, int lengthX, int lengthY, int[][] maze) {
		if ((x >= 0 && x < lengthX) && (y >= 0 && y < lengthY)
				&& (maze[x][y] == 0 || maze[x][y] == 9 || maze[x][y] == 2)) {
			return true;
		}
		return false;
	}
    
    
    
    
}
    
    
   