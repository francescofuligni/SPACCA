package application.Play;

import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import application.Card.Card;
import application.Games.*;
import application.MainMenu.MainMenuController;
import application.Player.PlayerInGame;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.stage.Stage;


public class BoardController implements Initializable{

	private Stage stage;
	private Scene scene;
	private Parent root;
	
	@FXML
	private Label currentPlayer, nextPlayer, healthPoints;

	@FXML
	private Button saveAndExit;
	
	@FXML
	private ListView<ImageView> hand;
	
	@FXML
	private ProgressBar healthBar;

	Game game = new SingleGame(InsertCodeController.file);
		
	private PlayerInGame current = game.currentPlayer();
	private PlayerInGame next = game.nextPlayer();
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		currentPlayer.setText(current.getUsername());
		nextPlayer.setText(next.getUsername());
		healthPoints.setText("" + current.getHealthPoints());
		
		healthBar.setStyle("-fx-accent: red;");							// healthBar rossa
		healthBar.setProgress(current.getHealthPoints()/current.MAXHP);
		
		ArrayList<Card> cards = current.getHand();
		ImageView[] iv = new ImageView[cards.size()];
		for(int i=0; i<cards.size(); i++)
			iv[i] = new ImageView(cards.get(i).getPicture());
		hand.getItems().addAll(iv);
	}
	
	
	
	@FXML
	public void save() throws IOException {
		game.save();
		stage = (Stage)(saveAndExit.getScene().getWindow());
		  //IMPORTANTE RICORDA IL ../ nell'URL DEL FXML
		  FXMLLoader Loader=new FXMLLoader(MainMenuController.class.getResource("MainMenu.fxml"));
		  root = (Parent) Loader.load();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.show();
	}
}
