package controller.view;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MultiPlayer {

	private Stage stage;
	
	private Pane root = new Pane();
	private Pane characterModel = new Pane();
	private Button btn = new Button("NEXT");
	public void init() {
		
		//root.setLayoutX(1080);
		//root.setLayoutY(800);
		
		displayCharacterModel();
		setButton();
		
		root.getChildren().addAll(characterModel, btn);
	}
	
	public void setButton() {
		btn.setTranslateX(800);
		btn.setTranslateY(700);
		btn.setMinWidth(100);
		//btn.setLayoutX(100);
		//btn.setLayoutY(100);
		btn.setOnAction((ActionEvent event)->{
			MultiPlayerStage mpstage = new MultiPlayerStage();
			stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			try {
				mpstage.mapGeneration(stage);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		
	}
	
	public void displayCharacterModel() {
		characterModel.setPrefSize(1040, 800);
		
		Image img1 = new Image("img/Coin.png");
		ImageView img1view = new ImageView(img1);
		img1view.setFitHeight(200);
		img1view.setFitWidth(200);
		img1view.setX(120);
		img1view.setY(250);
		Text img1name = new Text();
		img1name.setText("Peter");
		img1name.setFill(Color.BLUE);
		img1name.setX(180);
		img1name.setY(500);
		img1name.setFont(Font.font("Aral", FontWeight.BOLD, 20));
		
		Image img2 = new Image("img/Coin.png");
		ImageView img2view = new ImageView(img2);
		img2view.setFitHeight(200);
		img2view.setFitWidth(200);
		img2view.setX(430);
		img2view.setY(250);
		Text img2name = new Text();
		img2name.setText("Dennis");
		img2name.setFill(Color.RED);
		img2name.setX(490);
		img2name.setY(500);
		img2name.setFont(Font.font("Aral", FontWeight.BOLD, 20));
		
		Image img3 = new Image("img/Coin.png");
		ImageView img3view = new ImageView(img3);
		img3view.setFitHeight(200);
		img3view.setFitWidth(200);
		img3view.setX(760);
		img3view.setY(250);
		Text img3name = new Text();
		img3name.setText("MOFO");
		img3name.setFill(Color.GREEN);
		img3name.setX(820);
		img3name.setY(500);
		img3name.setFont(Font.font("Aral", FontWeight.BOLD, 20));
		
		//characterModel.setPadding(new Insets(10,10,10,10));
		characterModel.getChildren().add(img1view);
		characterModel.getChildren().add(img1name);
		characterModel.getChildren().add(img2view);
		characterModel.getChildren().add(img2name);
		characterModel.getChildren().add(img3view);
		characterModel.getChildren().add(img3name);
		
		
	}
	
	public void run (Stage stage) {
		
		init();
		
		this.stage = stage;
		
		Scene scene = new Scene(root);
		this.stage.setScene(scene);
		this.stage.show();
	}
	
	
}
