package application.Play;

import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;

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
	private boolean isDead;
	
	@FXML
	private Label currentPlayer, nextPlayer, healthPoints, infoLabel;

	@FXML
	private Button saveAndExitButton, infoBoardButton, playCardButton;
	
	@FXML
	private ListView<ImageView> hand;
	
	@FXML
	private ProgressBar healthBar;

	private Game game = new SingleGame(InsertCodeController.file);
		
	private PlayerInGame current = game.currentPlayer();
	private PlayerInGame nextAlive = game.nextPlayerAlive();
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		currentPlayer.setText(current.getUsername());						// label current player
		nextPlayer.setText(nextAlive.getUsername());						// label next player (mostra il prossimo giocatore vivo)
		
		healthBar.setStyle("-fx-accent: green;");							// healthBar rossa
		
		healthPoints.setText("" + current.getHealthPoints());
		healthBar.setProgress((double)current.getHealthPoints()/current.MAXHP);
		
		if(current.equals(nextAlive)) {
			playCardButton.setText("FINE");
			infoLabel.setTextFill(Color.LIGHTGREEN);
			infoLabel.setText("HAI VINTO!");
			
			while(game.getPlayers().size()>1) {
				game.nextTurn();
				game.removePlayer();
			}
			
		} else if(current.getHealthPoints()<=0) {
			isDead = true;
			healthPoints.setText("" + 0);
			healthBar.setProgress(0.0);
			playCardButton.setText("ABBANDONA");
			infoLabel.setTextFill(Color.RED);
			infoLabel.setText("Sei stato eliminato:  clicca su ABBANDONA per uscire dalla partita");
		} else{
			isDead = false;
			healthPoints.setText("" + current.getHealthPoints());
			healthBar.setProgress((double)current.getHealthPoints()/current.MAXHP);
			cards = current.getHand();
			images = new ArrayList<>();
			for(Card c : cards) {
				ImageView iv = new ImageView();
				iv.setImage(c.getPicture());
				iv.setFitWidth(230);
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
		
		// TODO: controllo se è un bot --> giocata automatica (tempo per mostrare la giocata del bot)
		
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
	public void playCard(ActionEvent e) throws IOException {
		
		if(current.equals(nextAlive)) {
			endGame();
		} else if(isDead) {	
			game.removePlayer();
			nextPlayerBoard();
			
		} else {
			if(selectedImage == null) {
				Alert noCardSelected = new Alert(AlertType.ERROR);
				noCardSelected.setTitle("ERRORE!");
				noCardSelected.setHeaderText("SELEZIONA UNA CARTA");
				noCardSelected.setContentText("Prima di giocare devi selezionare una carta");
				noCardSelected.showAndWait();
				
			}else if(hasImprevisti() && (cards.get(images.indexOf(selectedImage)).getCode()>16 || cards.get(images.indexOf(selectedImage)).getCode()<13) ){
				infoLabel.setTextFill(Color.VIOLET);
				infoLabel.setText("Controlla le tue carte:  se hai un imprevisto sei costretto a giocarlo");
				Alert playImprevisto = new Alert(AlertType.ERROR);
				playImprevisto.setTitle("ERRORE!");
				playImprevisto.setHeaderText("HAI UN IMPREVISTO DA GIOCARE");
				playImprevisto.setContentText("Se hai un imprevisto sei costretto a giocarlo");
				playImprevisto.showAndWait();
				
			} else {
				Card c = cards.remove(images.indexOf(selectedImage));
				c.effect(game);
				game.nextTurn();
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
	
	private void nextPlayerBoard() throws IOException {
		game.save();
		stage = (Stage)(playCardButton.getScene().getWindow());
		  //IMPORTANTE RICORDA IL ../ nell'URL DEL FXML
		  FXMLLoader Loader=new FXMLLoader(BoardController.class.getResource("Board.fxml"));
		  root = (Parent) Loader.load();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.show();
	}
	
	private boolean hasImprevisti() {
		for(Card c: cards) {
			if(c.getCode()<=16 && c.getCode()>=13) {
				return true;
			}
		}
		return false;
	}
	
	private void endGame() throws IOException {
		
		// TODO: fxml classifica globale giocatori con bottone dalla home
		// TODO: scrivere meccanismo punteggi vittoria/eliminazione nelle regole
		// TODO: eliminare file partita a fine partita
		
		finalScores();
		
		stage = (Stage)(saveAndExitButton.getScene().getWindow());
		  //IMPORTANTE RICORDA IL ../ nell'URL DEL FXML
		  FXMLLoader Loader=new FXMLLoader(MainMenuController.class.getResource("MainMenu.fxml"));
		  root = (Parent) Loader.load();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.show();
	}
	
	private void finalScores() {
		
		Path pathToFile = Paths.get("./GiocoSPACCA/Informazioni_Partite/PLAYERS_REGISTER.csv");
		File f=new File(pathToFile.toString());
		ArrayList<String> usernames = new ArrayList<>();
		
		usernames.add(current.getUsername());
		for(PlayerInGame p:game.getEliminated())
			usernames.add(p.getUsername());
		
		int maxPoints = usernames.size()*4;
		
		try {
			Scanner scan = new Scanner(f);
			scan.reset();
			String memory="";
			
			while(scan.hasNextLine() ) {
				String line = scan.nextLine();
				String[] tokens = line.split(",");
				if(usernames.contains(tokens[0])) {
					int score = (int) (Integer.parseInt(tokens[1]) + maxPoints/Math.pow(2, usernames.indexOf(tokens[0])));
					memory = memory + tokens[0] + "," + score + "\n";			// salvataggio sul file
				} else {		
					memory = memory + line + "\n";					// le altre linee vengono riscritte nello stesso modo
				}
			}
			scan.close();	
			FileWriter fw = new FileWriter(f,false);
			fw.write(memory);
			fw.close();
			
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}
