package application.Play;

import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import application.Card.Card;
import application.Games.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

//import per animazioni bot
import javafx.animation.PauseTransition;
import javafx.util.Duration;


public class SingleGameBoardController extends Board {
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		game = new SingleGame(InsertCodeController.file);
		current = game.currentPlayer();
		nextAlive = game.nextPlayerAlive();
		
		currentPlayer.setText(current.getUsername());						// label current player
		
		healthBar.setStyle("-fx-accent: green;");							// healthBar verde
		
		healthPoints.setText("" + current.getHealthPoints() + "  HP");
		healthBar.setProgress((double)current.getHealthPoints()/current.MAXHP);
		
		if(current.equals(nextAlive)) {					// il giocatore attuale è il vincitore (partita terminata)
			playCardButton.setText("FINE");
			infoLabel.setTextFill(Color.LIGHTGREEN);
			infoLabel.setText("HAI VINTO!");
			nextPlayerTitle.setText("Vincitore:");
			nextPlayerTitle.setTextFill(Color.LIGHTGREEN);
			nextPlayer.setText(current.getUsername());
			nextPlayer.setTextFill(Color.LIGHTGREEN);
			
			while(game.getPlayers().size()!=1) {			// se i giocatori eliminati non sono stati rimossi, li rimuove
				game.nextTurn();
				game.removePlayer();
				System.out.println(game.getTurn());
			}
		} else if(current.getHealthPoints()<=0) {		// il giocatore attuale è stato eliminato (abbandona la partita)
			nextPlayer.setText(nextAlive.getUsername());						// label next player (mostra il prossimo giocatore vivo)
			isOut = true;
			healthPoints.setText("0  HP");
			healthBar.setProgress(0.0);
			playCardButton.setText("ABBANDONA");
			infoLabel.setTextFill(Color.RED);
			infoLabel.setText("Sei stato eliminato:  clicca su ABBANDONA per uscire dalla partita");
		
		} else {										// il giocatore attuale è in partita (partita in corso)
			nextPlayer.setText(nextAlive.getUsername());						// label next player (mostra il prossimo giocatore vivo)
			isOut = false;
			healthPoints.setText("" + current.getHealthPoints());
			healthBar.setProgress((double)current.getHealthPoints()/current.MAXHP);
			hand = current.getHand();
			images = new ArrayList<>();
			for(Card c : hand) {
				ImageView iv = new ImageView();
				iv.setImage(c.getPicture());
				iv.setFitWidth(230);
				iv.setPreserveRatio(true);
				images.add(iv);
			}
			cards.getItems().addAll(images);
			
			cards.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<ImageView>() {
				@Override
				public void changed(ObservableValue<? extends ImageView> arg0, ImageView arg1, ImageView arg2) {
					selectedImage = cards.getSelectionModel().getSelectedItem();
				}
			});
		}
		
		// TODO: BOT non funziona
		/*
		if(current.getUsername().startsWith("BOT")) {
			PauseTransition pause = new PauseTransition(Duration.seconds(20));
			pause.play();
			selectedImage = images.get(hand.indexOf(botCard()));
			// evidenziare carta selezionata
			pause.play();
			playCardButton.fire();
		}
		*/
	}
	
	@Override
	protected void nextPlayerBoard() throws IOException {			// carica la schermata del prossimo giocatore (e salva la partita)
		game.nextTurn();
		game.save();
		stage = (Stage)(playCardButton.getScene().getWindow());
		  //IMPORTANTE RICORDA IL ../ nell'URL DEL FXML
		  FXMLLoader Loader=new FXMLLoader(SingleGameBoardController.class.getResource("SingleGameBoard.fxml"));
		  root = (Parent) Loader.load();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.show();
	}
	
	@Override
	protected void endGame() throws IOException {					// carica la classifica della partita
		game.save();
		stage = (Stage)(saveAndExitButton.getScene().getWindow());
    	FXMLLoader Loader=new FXMLLoader(GameScoreBoardController.class.getResource("../Play/GameScoreBoard.fxml"));
    	root = (Parent) Loader.load();
    	scene = new Scene(root);
    	stage.setScene(scene);
    	stage.show();
	}
}
