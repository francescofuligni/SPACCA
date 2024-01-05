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
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;


public class BoardController implements Initializable{
	private ImageView selectedImage;
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	private ArrayList<ImageView> images;
	private ArrayList<Card> cards;
	
	@FXML
	private Label currentPlayer, nextPlayer, healthPoints;

	@FXML
	private Button saveAndExitButton;
	
	@FXML
	private ListView<ImageView> hand;
	
	@FXML
	private ProgressBar healthBar;
	
	@FXML
    private Button playCardButton;

	private Game game = new SingleGame(InsertCodeController.file);
		
	private PlayerInGame current = game.currentPlayer();
	private PlayerInGame next = game.nextPlayer();
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		currentPlayer.setText(current.getUsername());
		nextPlayer.setText(next.getUsername());
		healthPoints.setText("" + current.getHealthPoints());
		
		healthBar.setStyle("-fx-accent: red;");							// healthBar rossa
		healthBar.setProgress(current.getHealthPoints()/current.MAXHP);
		
		cards = current.getHand();
		images = new ArrayList<>();
		for(Card c : cards) {
			ImageView iv = new ImageView();
			iv.setImage(c.getPicture());
			iv.setFitWidth(150);
			iv.setPreserveRatio(true);
			images.add(iv);
		}
		hand.getItems().addAll(images);
		
		hand.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<ImageView>() {
			@Override
			public void changed(ObservableValue<? extends ImageView> arg0, ImageView arg1, ImageView arg2) {
				selectedImage = hand.getSelectionModel().getSelectedItem();
			}
		});
	}
	
	@FXML
	public void playCard(ActionEvent e) throws IOException {
		
		if(selectedImage == null) {
			Alert noCardSelected = new Alert(AlertType.ERROR);
			noCardSelected.setTitle("ERRORE!");
			noCardSelected.setHeaderText("ERRORE: SELEZIONA UNA CARTA");
			noCardSelected.setContentText("Prima di giocare devi selezionare una carta");
			noCardSelected.showAndWait();
		} else {
			Card c = cards.remove(images.indexOf(selectedImage));
			c.effect(game);
			game.nextTurn();
			
			game.save();
			stage = (Stage)(playCardButton.getScene().getWindow());
			  //IMPORTANTE RICORDA IL ../ nell'URL DEL FXML
			  FXMLLoader Loader=new FXMLLoader(BoardController.class.getResource("Board.fxml"));
			  root = (Parent) Loader.load();
			  scene = new Scene(root);
			  stage.setScene(scene);
			  stage.show();
		}
	}
	
	
	
	@FXML
	public void saveAndExit(ActionEvent e) throws IOException {
		game.save();
		stage = (Stage)(saveAndExitButton.getScene().getWindow());
		  //IMPORTANTE RICORDA IL ../ nell'URL DEL FXML
		  FXMLLoader Loader=new FXMLLoader(MainMenuController.class.getResource("MainMenu.fxml"));
		  root = (Parent) Loader.load();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.show();
	}
}
