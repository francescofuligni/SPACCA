package application.Play;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;

import application.Admin.BOTDIFF;
import application.Card.Card;
import application.Card.NormalCard;
import application.Card.SpecialCard;
import application.Games.*;
import application.MainMenu.MainMenuController;
import application.Player.PlayerInGame;
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
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public abstract class Board implements Initializable {
	
	protected Stage stage;
	protected Scene scene;
	protected Parent root;
	
	protected ArrayList<ImageView> images;
	protected ArrayList<Card> hand;
	protected ImageView selectedImage;
	protected boolean isOut;
	protected SingleGame game;
	protected PlayerInGame current;
	protected PlayerInGame nextAlive;
	
	@FXML
	protected Label currentPlayer, nextPlayer, healthPoints, infoLabel, nextPlayerTitle;

	@FXML
	protected Button saveAndExitButton, infoBoardButton, playCardButton;
	
	@FXML
	protected ListView<ImageView> cards;
	
	@FXML
	protected ProgressBar healthBar;
	
	
	@FXML
	public void infoBoardDisplay(ActionEvent event) {
		Alert info = new Alert(AlertType.INFORMATION);
		info.setTitle("Tabellone HP");
		info.setHeaderText(game.printPlayers());		// giocatori in partita
		info.setContentText(game.printEliminated());	// giocatori eliminati
		info.showAndWait();
	}
	
	@FXML
	public void playCard() throws IOException {
		if(current.equals(nextAlive)) {
			endGame();
		} else if(isOut) {	
			game.removePlayer();
			nextPlayerBoard();
			
		} else {
			if(selectedImage == null) {
				Alert noCardSelected = new Alert(AlertType.ERROR);
				noCardSelected.setTitle("ERRORE!");
				noCardSelected.setHeaderText("SELEZIONA UNA CARTA");
				noCardSelected.setContentText("Prima di giocare devi selezionare una carta");
				noCardSelected.showAndWait();
				
			}else if(current.hasImprevisti() && (hand.get(images.indexOf(selectedImage)).getCode()>16 || hand.get(images.indexOf(selectedImage)).getCode()<13) ){
				infoLabel.setTextFill(Color.VIOLET);
				infoLabel.setText("Controlla le tue carte:  se hai un imprevisto sei costretto a giocarlo");
				Alert playImprevisto = new Alert(AlertType.ERROR);
				playImprevisto.setTitle("ERRORE!");
				playImprevisto.setHeaderText("HAI UN IMPREVISTO DA GIOCARE");
				playImprevisto.setContentText("Se hai un imprevisto sei costretto a giocarlo");
				playImprevisto.showAndWait();
				
			} else {
				Card c = current.getCard(images.indexOf(selectedImage));
				c.effect(game);
				nextPlayerBoard();
			}
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
	
	
	protected Card botCard() {			// metodo per la giocata del bot
		if(current.hasImprevisti())
			for(Card c: hand)
				if(c.getCode()<=16 && c.getCode()>=13)
					return c;
		
		if(game.getDifficulty().equals(BOTDIFF.DIFFICILE)) {
			boolean flag = true;
			int imax = 0;
			for(int i=0; i<hand.size(); i++) {
				if(hand.get(i) instanceof SpecialCard) {
					return hand.get(i);
				} else if(flag) {
					imax = i;
					flag = false;
				} else if(Math.abs(((NormalCard)hand.get(imax)).getDamage())<Math.abs(((NormalCard)hand.get(i)).getDamage())) {
					imax = i;
				}
			}
			return hand.get(imax);
		} else {
			Random rand = new Random();
			return hand.get(rand.nextInt(hand.size()));
		}
	}
	
	
	// metodi astratti
	@Override
	public abstract void initialize(URL arg0, ResourceBundle arg1);
	
	protected abstract void nextPlayerBoard() throws IOException;
	
	protected abstract void endGame() throws IOException;
}
