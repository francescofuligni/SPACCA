package application.Play;

import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;

import application.Admin.BOTDIFF;
import application.Admin.CreateGameController;
import application.Card.Card;
import application.Card.NormalCard;
import application.Card.SpecialCard;
import application.Games.*;
import application.MainMenu.MainMenuController;
import application.Player.*;
import javafx.animation.PauseTransition;
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
import javafx.util.Duration;

public class BoardController implements Initializable {
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	private ArrayList<ImageView> images;
	private ArrayList<Card> hand;
	private boolean isOut;
	private ImageView selectedImage;
	
	@FXML
	private Label currentPlayer, nextPlayer, healthPoints, infoLabel, nextPlayerTitle;

	@FXML
	private Button saveAndExitButton, infoBoardButton, playCardButton;
	
	@FXML
	private ListView<ImageView> cards;
	
	@FXML
	private ProgressBar healthBar;

	private Game game;
		
	private PlayerInGame current = game.currentPlayer();
	private PlayerInGame nextAlive = game.nextPlayerAlive();
	
	public static boolean tournamentFlag; //flag per riconoscere la fine delle singole partite interne ai tornei
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		game = new SingleGame(InsertCodeController.file);
		
		tournamentFlag=false;
		
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
			tournamentFlag = true;
			
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
		
		/* // TODO: BOT non funziona
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
	
	
	private void nextPlayerBoard() throws IOException {			// carica la schermata del prossimo giocatore (e salva la partita)
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
	
	
	//differenziare il comportamento sulla base del tipo di game
	private void endGame() throws IOException {					// carica la classifica della partita
		game.save();
		stage = (Stage)(saveAndExitButton.getScene().getWindow());
    	FXMLLoader Loader=new FXMLLoader(GameScoreBoardController.class.getResource("../Play/GameScoreBoard.fxml"));
    	root = (Parent) Loader.load();
    	scene = new Scene(root);
    	stage.setScene(scene);
    	stage.show();
	}
	
	private Card botCard() {			// metodo per la giocata del bot
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
}
