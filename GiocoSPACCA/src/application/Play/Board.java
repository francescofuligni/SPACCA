package application.Play;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import application.Main;
import application.Card.Card;
import application.Games.*;
import application.MainMenu.MainMenuController;
import application.Player.*;
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
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.application.Platform;

public abstract class Board implements Initializable {		// superclasse dei controller delle diverse modalità di competizione
	
	protected Stage stage;
	protected Scene scene;
	protected Parent root;
	
	protected ArrayList<ImageView> images;
	protected ArrayList<Card> hand;
	protected ImageView selectedImage;
	protected Game game;
	protected PlayerInGame current;
	protected PlayerInGame nextAlive;
	protected boolean isOut = false;
	private Thread botThread;
	
	// flag per bot
	private boolean exited = false;			// true se si esce salvando la partita
	private boolean infoBoard = false;		// true se si apre il tabellone HP
	
	@FXML
	protected AnchorPane anchorPane;
	@FXML
	protected Label currentPlayer;
	@FXML
	protected Label nextPlayer;
	@FXML
	protected Label healthPoints;
	@FXML
	protected Label infoLabel;
	@FXML
	protected Label nextPlayerTitle;
	@FXML
	protected Label gameTitle;
	@FXML
	protected Button saveAndExitButton;
	@FXML
	protected Button infoBoardButton;
	@FXML
	protected Button playCardButton;
	@FXML
	protected ListView<ImageView> cards;
	@FXML
	protected ProgressBar healthBar;
	@FXML
	private ImageView showSelectedCard;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		current = game.currentPlayer();
		nextAlive = game.nextPlayerAlive();
		currentPlayer.setText(current.getUsername());
		healthBar.setStyle("-fx-accent: green;");		// barra salute verde
		
		if(current.equals(nextAlive)) {
			// CASO 1	-->	il giocatore attuale è il vincitore
			initializeWinner();
			
		} else if(current.getHealthPoints()<=0) {
			// CASO 2	-->	il giocatore attuale è stato eliminato
			initializeEliminated();
		
		} else {
			// CASO 3	--> il giocatore attuale è in partita
			initializeNormal();
			
			cards.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<ImageView>() {
				@Override
				public void changed(ObservableValue<? extends ImageView> arg0, ImageView arg1, ImageView arg2) {
					selectedImage = cards.getSelectionModel().getSelectedItem();
					showSelectedCard.setImage(selectedImage.getImage());
				}
			});
		}
		
		setTitle();
		
		if(current.getUsername().startsWith("BOT")) {	// controlla se il giocatore corrente è un bot (username unovoco)
			
			cards.setMouseTransparent(true);	// disabilita click su listview
		    cards.setFocusTraversable(false);	// disabilita interazioni tastiera con listview
		    playCardButton.setDisable(true);	// disabilita bottone gioca carta
			
			// crea un thread per le azioni del bot
			botThread = new Thread(() -> {
				try {
					Thread.sleep(1000); 					// pausa di 1s prima di selezionare la carta
					
					Bot bot = new Bot(current);
					int index=bot.botCard(game.getDifficulty()); 					// a seconda della difficoltà cambia l'indice restituito
					if(bot.getHealthPoints()>0 && !current.equals(nextAlive)) {
						selectedImage = images.get(index);
						cards.getSelectionModel().select(selectedImage); 
						showSelectedCard.setImage(selectedImage.getImage()); 		// imposto la carta selezionata nell'ImageView
						Thread.sleep(1500); 				// pausa di 1s e mezzo prima di chiamare il metodo fire del bottone
					}
					
					while(infoBoard || Main.message)		// attende la chiusura di un eventuale messaggio aperto prima di continuare
						Thread.sleep(1000);
					
					Platform.runLater(new Runnable() {  	// per poter interagire con la GUI, richiamo il main thread (application)
						@Override
						public void run() {
							try {
								playCard(new ActionEvent());
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					});
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			});
			botThread.start(); 		// avvio del thread	--> il bot esegue le operazioni
		}
	}
	
	@FXML
	public void playCard(ActionEvent event) throws IOException {
		// azione bottone gioca carta
		
		if(!exited) {	// (per il bot) controlla se l'utente è uscito dalla partita	--> il botThread termina
			if(current.equals(nextAlive)) {
				// caso vittoria
				endGame();					// termina la partita
				
			} else if(isOut) {	
				// caso eliminazione
				game.removePlayer();		// giocatore corrente abbandona
				nextPlayerBoard();			// passa al giocatore successivo
				
			} else {
				// caso normale
				if(selectedImage == null) {	// controlla se è stata selezionata una carta
					Alert noCardSelected = new Alert(AlertType.ERROR);
					noCardSelected.setTitle("ERRORE!");
					noCardSelected.setHeaderText("SELEZIONA UNA CARTA");
					noCardSelected.setContentText("Prima di giocare devi selezionare una carta");
					noCardSelected.showAndWait();
					
				} else if(current.hasImprevisti() && (hand.get(images.indexOf(selectedImage)).getCode()>16 || hand.get(images.indexOf(selectedImage)).getCode()<13) ){		// controlla se il giocatore ha degli imprevisti e non li sta giocando
					infoLabel.setTextFill(Color.VIOLET);
					infoLabel.setText("Controlla le tue carte:  se hai un imprevisto sei costretto a giocarlo");
					Alert playImprevisto = new Alert(AlertType.ERROR);
					playImprevisto.setTitle("ERRORE!");
					playImprevisto.setHeaderText("HAI UN IMPREVISTO DA GIOCARE");
					playImprevisto.setContentText("Se hai un imprevisto sei costretto a giocarlo");
					playImprevisto.showAndWait();
					
				} else {
					Card c = current.getCard(images.indexOf(selectedImage));	// rimuove la carta dalla mano del giocatore
					c.effect(game);			// gioca la carta	--> chiama l'effetto della carta
					nextPlayerBoard();		// passa al giocatore successivo
				}
			}
		}
	}
	
	@FXML
	public void infoBoardDisplay(ActionEvent event) {
		// mostra il tabellone
		infoBoard=true;
		Alert info = new Alert(AlertType.INFORMATION);
		info.setTitle("Tabellone HP");
		info.setHeaderText(game.printPlayers());			// giocatori in partita
		info.setContentText(game.printEliminated());		// giocatori eliminati
		info.showAndWait();
		infoBoard=false;
	}
	
	@FXML
	public void saveAndExit(ActionEvent event) throws IOException {
		exited=true;
		game.save();
		stage = (Stage)(saveAndExitButton.getScene().getWindow());
		  FXMLLoader Loader=new FXMLLoader(MainMenuController.class.getResource("/application/MainMenu/MainMenu.fxml"));
		  root = (Parent) Loader.load();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.show();
	}
	
	private void initializeWinner() {
		// il giocatore attuale è il vincitore	--> partita terminata
		playCardButton.setText("FINE");						// bottone playCard --> bottone per terminare la partita
		infoLabel.setTextFill(Color.LIGHTGREEN);
		infoLabel.setText("HAI VINTO!");
		nextPlayerTitle.setText("Vincitore:");
		nextPlayerTitle.setTextFill(Color.LIGHTGREEN);
		nextPlayer.setText(current.getUsername());			// label next player (mostra il vincitore)
		nextPlayer.setTextFill(Color.LIGHTGREEN);
		
		if(current.getHealthPoints()<=0) {
			healthPoints.setText("1  HP");
			healthBar.setProgress(1.0);
		} else {
			healthPoints.setText(current.getHealthPoints() + "  HP"); 
			healthBar.setProgress((double)current.getHealthPoints()/current.MAXHP);
		}
		
		while(game.getPlayers().size()!=1) {				// se i giocatori eliminati non sono stati rimossi (perché non hanno abbandonato), li rimuove
			game.nextTurn();
			game.removePlayer();
		}
	}
	
	private void initializeEliminated() {
		// il giocatore attuale è stato eliminato	--> abbandona la partita
		nextPlayer.setText(nextAlive.getUsername());		// label next player (mostra il prossimo giocatore vivo)
		isOut = true;
		healthPoints.setText("0  HP");
		healthBar.setProgress(0.0);
		playCardButton.setText("ABBANDONA");				// bottone playCard --> bottone per abbandonare
		infoLabel.setTextFill(Color.RED);
		infoLabel.setText("Sei stato eliminato:  clicca su ABBANDONA per uscire dalla partita");
	}
	
	private void initializeNormal() {
		// il giocatore attuale è in partita	--> partita in corso
		nextPlayer.setText(nextAlive.getUsername());		// label next player (mostra il prossimo giocatore vivo)
		healthPoints.setText(current.getHealthPoints() + "  HP");
		healthBar.setProgress((double)current.getHealthPoints()/current.MAXHP);
		hand = current.getHand();
		images = new ArrayList<>();
		for(Card c : hand) {
			ImageView iv = new ImageView();
			iv.setImage(c.getPicture());
			iv.setFitWidth(200);
			iv.setPreserveRatio(true);
			images.add(iv);
		}
		cards.getItems().addAll(images);
	}
	
	
	// metodi astratti	--> diversi a seconda del tipo di competizione
	protected abstract void nextPlayerBoard() throws IOException;
	
	protected abstract void endGame() throws IOException;
	
	protected abstract void setTitle();
}
