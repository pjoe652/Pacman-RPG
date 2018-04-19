package controller.view;

import java.util.HashMap;

import controller.model.Story;
import controller.model.level;
import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class levelThree extends level {
	/*
	 * This class displays dialogues for Boss level
	 */

	private HashMap<KeyCode, Boolean> keys = new HashMap<KeyCode, Boolean>();

	private int line = 0;
	private int x = 1;
	private int y = 1;

	private Pane appRoot = new Pane();
	private Pane gameRoot = new Pane();
	private Pane uiRoot = new Pane();
	private Pane speechRoot = new Pane();
	private Pane bgRoot = new Pane();

	Timeline timelineX = new Timeline();
	Timeline timelineY = new Timeline();

	Stage storyStage;

	private boolean keyPressed = false;
	private boolean running = true;

	// Initializes the gameplay area
	private void initContent() {
		// Background set
		Image bg = new Image("character/SlimeBoss/SlimeBoss1.png");
		ImageView bgView = new ImageView(bg);
		bgView.setFitWidth(1120);
		bgView.setFitHeight(840);
		bgRoot.getChildren().add(bgView);
		// Gamebox Set
		appRoot.setPrefSize(1080, 800);
		// Speech box set
		Image Speech = new Image("lvlOneImg/SpeechBox.png");
		ImageView SpeechView = new ImageView(Speech);
		// Initialize text
		Text text = new Text();
		gameRoot.getChildren().add(text);

		appRoot.getChildren().addAll(bgRoot, SpeechView, gameRoot, uiRoot, speechRoot);

	}

	// Skip to next message on key press
	private void update() {

		// Dialogue reading
		if (line == Story.ScriptBoss.length - 1) {
			line++;

			FadeTransition fadeOut = new FadeTransition(Duration.seconds(1), appRoot);
			fadeOut.setToValue(0.0);

			fadeOut.setOnFinished((ActionEvent event) -> {
				levelSelect select = new levelSelect();
				try {
					select.selectLevel(storyStage);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});

			fadeOut.play();
		}

		if (isPressed(KeyCode.ENTER) && (keyPressed == false) && (line < Story.ScriptBoss.length - 1)) {

			speechRoot.getChildren().clear();
			Text scriptWords = setText(Story.ScriptBoss[line]);
			Text characterWords = setCharacter(Story.ScriptBoss[line]);
			speechRoot.getChildren().add(scriptWords);
			speechRoot.getChildren().add(characterWords);
			line++;
			keyPressed = true;

		}
		if (isPressed(KeyCode.CONTROL) && (line < Story.ScriptBoss.length - 1)) {

			speechRoot.getChildren().clear();
			Text scriptWords = setText(Story.ScriptData[line]);
			Text characterWords = setCharacter(Story.ScriptData[line]);
			speechRoot.getChildren().add(scriptWords);
			speechRoot.getChildren().add(characterWords);
			line++;

		}
		if (!isPressed(KeyCode.ENTER) && (keyPressed == true)) {
			keyPressed = false;
		}

		// Events
		if (line == 5) {
			shakeStage();
			bgRoot.getChildren().clear();
			Image slimeBossBlur2 = new Image("character/SlimeBoss/SlimeBossBlur2.png");
			ImageView bgView = new ImageView(slimeBossBlur2);
			bgView.setFitWidth(1080);
			bgView.setFitHeight(800);
			bgRoot.getChildren().add(bgView);

		}
		if (line == 6) {
			bgRoot.getChildren().clear();
			Image slimeBossBlur1 = new Image("character/SlimeBoss/SlimeBossBlur.png");
			ImageView bgView = new ImageView(slimeBossBlur1);
			bgView.setFitWidth(1080);
			bgView.setFitHeight(800);
			bgRoot.getChildren().add(bgView);
		}
		if (line == 7) {
			shakeStage();
		}
		if (line == 8) {
			bgRoot.getChildren().clear();
			Image slimeBoss2 = new Image("character/SlimeBoss/SlimeBoss2.png");
			ImageView bgView = new ImageView(slimeBoss2);
			bgView.setFitWidth(1080);
			bgView.setFitHeight(800);
			bgRoot.getChildren().add(bgView);
		}
		if (line == 9) {
			stopShake();
		}
		if (line == 12) {
			// stopShake();
			bgRoot.getChildren().clear();
			Image slimeBoss3 = new Image("character/SlimeBoss/SlimeBoss3.png");
			ImageView bgView = new ImageView(slimeBoss3);
			bgView.setFitWidth(1080);
			bgView.setFitHeight(800);
			bgRoot.getChildren().add(bgView);
		}

	}

	// Sets speech for character
	private Text setText(String string) {

		// Set Character dialogue
		String script = string.substring(1);
		Text text = new Text();
		text.setFill(Color.WHITE);
		text.setText(script);
		text.setFont(Font.font("Verdana", 20));
		text.setTranslateX(50);
		text.setTranslateY(575);

		return text;

	}

	// Identifies and sets texts for character
	private Text setCharacter(String string) {
		// Set Character name
		String characterName = null;
		String character = string.substring(0, 1);
		if (character.equals("0")) {
			characterName = "";
		} else if (character.equals("1")) {
			characterName = player.getName();
		} else if (character.equals("2")) {
			characterName = "???";
		}
		Text name = new Text();
		name.setFill(Color.WHITE);

		name.setText(characterName);
		name.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
		name.setTranslateX(90);
		name.setTranslateY(493);

		return name;

	}

	// Confirms key press
	private boolean isPressed(KeyCode key) {
		return keys.getOrDefault(key, false);
	}

	// Shakes screen (OPTIONAL)
	public void shakeStage() {

		timelineX.setCycleCount(Timeline.INDEFINITE);

		KeyFrame frameX = new KeyFrame(Duration.seconds(0.1), new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent t) {
				if (x == 0) {
					bgRoot.setTranslateX(bgRoot.getTranslateX() + 10);
					x = 1;
				} else {
					bgRoot.setTranslateX(bgRoot.getTranslateX() - 10);
					x = 0;
				}
			}
		});

		timelineX.getKeyFrames().add(frameX);
		timelineX.playFromStart();

		timelineY.setCycleCount(Timeline.INDEFINITE);

		KeyFrame frameY = new KeyFrame(Duration.seconds(0.1), new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent t) {
				if (y == 0) {
					bgRoot.setTranslateY(bgRoot.getTranslateY() + 10);
					y = 1;
				} else {
					bgRoot.setTranslateY(bgRoot.getTranslateY() - 10);
					y = 0;
				}
			}
		});

		timelineY.getKeyFrames().add(frameY);
		timelineY.playFromStart();
	}

	private void stopShake() {

		timelineX.getKeyFrames().clear();
		timelineX.stop();
		timelineY.getKeyFrames().clear();
		timelineY.stop();
	}

	public void displayDialogue(ActionEvent event2) throws Exception {
		initContent();

		Scene scene = new Scene(appRoot);
		scene.setOnKeyPressed(event -> keys.put(event.getCode(), true));
		scene.setOnKeyReleased(event -> keys.put(event.getCode(), false));

		storyStage = (Stage) ((Node) event2.getSource()).getScene().getWindow();
		storyStage.setScene(scene);
		storyStage.show();

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

	public void displayDialogueKeyPressed(KeyEvent event3) throws Exception {
		if (event3.getCode() == KeyCode.ENTER) {
			initContent();

			Scene scene = new Scene(appRoot);
			scene.setOnKeyPressed(event -> keys.put(event.getCode(), true));
			scene.setOnKeyReleased(event -> keys.put(event.getCode(), false));

			storyStage = (Stage) ((Node) event3.getSource()).getScene().getWindow();
			storyStage.setScene(scene);
			storyStage.show();

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

	}
}
