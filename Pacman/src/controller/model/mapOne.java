package controller.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Optional;

import controller.main;
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
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
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
import javafx.util.Duration;

public class mapOne extends map {

	private HashMap<KeyCode, Boolean> keys = new HashMap<KeyCode, Boolean>();
    private ArrayList<Node> coins = new ArrayList<Node>();
    private ArrayList<Node> platforms = new ArrayList<Node>();
 
    private Pane appRoot = new Pane();
    private Pane gameRoot = new Pane();
    private Pane uiRoot = new Pane();
    

    private Node user;
    
    private int Score = 0;
    private int CoinsCollected = 0;

    private int levelWidth;
    
     Node entityCreated;
     
    private String direction = "NONE";
    
    private boolean running = true;
    
    public Stage stage;
    private final Integer startTime = 3;
    private Integer currentTime = startTime;
    private Label display = new Label();
    
    private final Integer playTime = 120;
    private Integer gameTime = playTime;
    private Label displayTime = new Label();
    private int count = 0;
    Timeline time = new Timeline();
    Timeline timer = new Timeline();
    
        
    //Initializes the gameplay area
    private void initContent() {
//        Rectangle bg = new Rectangle(1020, 768);
        Image bg = new Image("img/floor.png");
        ImageView bgView = new ImageView(bg);
        StackPane scoreValue = createMenu(100, 30, 800, 10, "0", Color.WHITE);
        StackPane menuScore = createMenuRectangle(250, 40, 710, 5);
        StackPane menuScoreLabel = createMenuText(720, 12, "Score: ", Color.WHITE);
       
        
        levelWidth = LevelData.LEVEL1[0].length() * 60;

        for (int i = 0; i < LevelData.LEVEL1.length; i++) {
            String line = LevelData.LEVEL1[i];
            for (int j = 0; j < line.length(); j++) {
                switch (line.charAt(j)) {
                    case '0':
                        break;
                    case '1':
                    	Node Walls = createCharacter(j*60, i*60, 60, 60, Color.BLACK);
                        Node platform = createEntity(j*60, i*60);
                        platforms.add(Walls);
                        break;
                    case '2':
                        Node coin = createCoin(j*60, i*60);
                        coins.add(coin);
                        break;
                }
            }
        }
        
        user = createCharacter(490, 670, 40, 40, Color.BLUE);

        user.translateXProperty().addListener((obs, old, newValue) -> {
            int offset = newValue.intValue();

            if (offset > 640 && offset < levelWidth - 640) {
                gameRoot.setLayoutX(-(offset - 640));
            }
        });
        
        
        
        
        StackPane countDown = new StackPane();
        countDown.getChildren().add(display);
        countDown.setLayoutX(460);
        countDown.setLayoutY(300);
        
        StackPane gameTime = new StackPane();
        gameTime.getChildren().add(displayTime);
        gameTime.setLayoutX(460);
        gameTime.setLayoutY(10);
        
        appRoot.getChildren().addAll(bgView, gameRoot, countDown, uiRoot, menuScore, menuScoreLabel, scoreValue, gameTime);
       
        setTime();
        
        
        
        
    }
   

    //Continuously updates character interactions
    private void update() {
		if (currentTime < 0) {

			if (count == 0) {
				setGameTime();
				count++;
			}
			
			
			
			int speed = player.getSpeed();
			int negativeSpeed = 0 - speed;

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
			if (isPressed(KeyCode.P)) {
				direction = "NONE";
			}
			
	        if (direction.equals("UP")) {
	        	moveuserY(negativeSpeed);
	        } else if (direction.equals("LEFT")) {
	        	moveuserX(negativeSpeed);
	        } else if (direction.equals("RIGHT")) {
	        	moveuserX(speed);
	        } else if (direction.equals("DOWN")) {
	        	moveuserY(speed);
	        }

			// Removes Coins when collected
			for (Node coin : coins) {
				if (user.getBoundsInParent().intersects(coin.getBoundsInParent())) {
					coin.getProperties().put("alive", false);
				}
			}

			for (Iterator<Node> it = coins.iterator(); it.hasNext();) {
				Node coin = it.next();
				if (!(Boolean) coin.getProperties().get("alive")) {
					it.remove();
					CoinsCollected += 1;
					Score += 50 * player.getScoreMultiplier();
					String ScoreString = Integer.toString(Score);
					StackPane scoreValue = createMenu(100, 30, 800, 10, ScoreString, Color.WHITE);
					appRoot.getChildren().add(scoreValue);
					gameRoot.getChildren().remove(coin);
				}

			}

			// Fades away when all coins collected
			if (CoinsCollected == 86) {
				player.addPoints();
				// FadeTransition causes addPoints error, temporarily stopped

				// FadeTransition fadeOut = new FadeTransition(Duration.seconds(2), appRoot);
				// fadeOut.setToValue(1);
				// fadeOut.setToValue(0.0);

				// fadeOut.setOnFinished((ActionEvent event)->{

				levelSelect select = new levelSelect();
				select.levelFinish(stage);
				// });
				// fadeOut.play();
			}
			
			//---------------------
			double x = user.getTranslateX();
			double y = user.getTranslateY();
			
			
		}

	}
    
    


    //Movement of user
    private void moveuserX(int value) {
        boolean movingRight = value > 0;

        for (int i = 0; i < Math.abs(value); i++) {
            for (Node platform : platforms) {
                if (user.getBoundsInParent().intersects(platform.getBoundsInParent())) {
                    if (movingRight) {
                        if (user.getTranslateX() + 40 == platform.getTranslateX()) {
                        	user.setTranslateX(user.getTranslateX() - 2);
                            return;
                        }
                    }
                    else {
                        if (user.getTranslateX() == platform.getTranslateX() + 60) {
                        	user.setTranslateX(user.getTranslateX() + 2);
                            return;
                        }
                    }
                }
            }
            user.setTranslateX(user.getTranslateX() + (movingRight ? 1 : -1));
            
        }
    }
    
    

    private void moveuserY(int value) {
        boolean movingDown = value > 0;

        for (int i = 0; i < Math.abs(value); i++) {
            for (Node platform : platforms) {
                if (user.getBoundsInParent().intersects(platform.getBoundsInParent())) {
                    if (movingDown) {
                        if (user.getTranslateY() + 40 == platform.getTranslateY()) {
                        	user.setTranslateY(user.getTranslateY() - 2);
                            return;
                        }
                    }
                    else {
                        if (user.getTranslateY() == platform.getTranslateY() + 60) {
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
    private Node createEntity(int x, int y) {
    	
    	Image img = new Image("img/tile2.png");
    	ImageView imageView = new ImageView(img);
    	imageView.setFitHeight(60);
    	imageView.setFitWidth(60);
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
        
//    	Image img = new Image("img/naruto.gif");
//    	ImageView imageView = new ImageView(img);
//    	imageView.setFitHeight(60);
//    	imageView.setFitWidth(60);
//    	imageView.setX(x);
//    	imageView.setY(y);
//    	imageView.getProperties().put("alive", true);
//    	
//    	gameRoot.getChildren().add(imageView);
//        
        
        return entity;
        
       // return imageView;
    }
    
    //Creates coins
    private Node createCoin(int x, int y) {
    	Image img = new Image("https://zippy.gfycat.com/DarlingAcceptableHapuku.gif");
    	ImageView imageView = new ImageView(img);
    	imageView.setFitHeight(60);
    	imageView.setFitWidth(60);
    	imageView.setX(x);
    	imageView.setY(y);
    	imageView.getProperties().put("alive", true);
    	
    	gameRoot.getChildren().add(imageView);
    	return imageView;
    	
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

    public void mapGeneration(Stage map) throws Exception {
    	
    	Stage menuStage = new Stage();
    	menuStage.setTitle("PAUSE");
    	
    	Button skip = new Button("skip");
    	
    	skip.setOnAction((ActionEvent event) ->{
    		player.addPoints();
    		levelSelect select = new levelSelect();
			select.levelFinish(stage);
    		menuStage.close();
    	});    	
    	
        Button resume = new Button("resume");
        
        resume.setOnAction((ActionEvent event)->{
        	time.play();
        	timer.play();
        	menuStage.close();
        });
        
        Button retry = new Button("retry");
        
        retry.setOnAction((ActionEvent event) ->{
        	try {
        		menuStage.close();
        		levelSelect select = new levelSelect();
        		select.selectLevel(stage);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
        });        
        
        VBox subMenu = new VBox();
        subMenu.getChildren().addAll(resume, retry, skip);    	
        Scene menuScene = new Scene(subMenu, 200, 200);
        
        menuScene.setOnKeyPressed(event -> {
        	if (event.getCode() == KeyCode.P) {
        		time.play();
        		timer.play();
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
        	
        	/*Detect keyboard input of P and ESCAPE
        	 * If ESCAPE is detected, show dialog to confirm exit
        	 * If P is detected, the game will be paused until new interaction has
        	 * been detected.
        	 *         	 */
        	if (event.getCode() == KeyCode.ESCAPE) {
        		Alert warning = new Alert(AlertType.CONFIRMATION);
    			warning.setTitle("Confirmation");
    			warning.setHeaderText("Are you sure to exit?");

    			ButtonType no = new ButtonType("No");
    			ButtonType yes = new ButtonType("Yes");

    			warning.getButtonTypes().setAll(yes, no);

    			Optional<ButtonType> result = warning.showAndWait();
    			if (result.get() == yes) {
    				stage.close();
    			}
        	}
        	if (event.getCode() == KeyCode.P) {
        		time.pause();
        		timer.pause();
        		menuStage.setScene(menuScene);
        		menuStage.show();
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
    	display.setFont(Font.font("Verdana", 150));
    	display.setTextFill(Color.RED);
    	
    	KeyFrame frame = new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				display.setText(currentTime.toString());
				currentTime--;
				if (currentTime < -1) {
					timer.stop();
					display.setText("");
				}

			}
    	});
    	
    	timer.getKeyFrames().add(frame);
    	
    	
    	
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
    	
    	displayTime.setFont(Font.font("Verdana", 20));
    	displayTime.setTextFill(Color.YELLOW);
    	
    	KeyFrame frame = new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				
				displayTime.setText(gameTime.toString());
				gameTime--;
				if (gameTime == 0) {
					time.stop();
					displayTime.setText("TIMES UP");
				}
				
				

				
			}
    	});
    	
    	time.getKeyFrames().add(frame);
    	
    	
    	
    	time.playFromStart();
    }
    
    
    
	
}
