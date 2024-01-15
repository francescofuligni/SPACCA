package application.Admin;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;

import application.Player.Player;
import application.Player.PlayerInGame;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public abstract class GamesCreation implements Initializable {
	
	protected Stage stage;
	protected Scene scene;
	protected Parent root;
	
	public int MAXPLAYERS;
	protected final int CODELENGTH = 5;
	protected Player[] selectedPlayers;
	protected ArrayList<PlayerInGame> playersInGame = new ArrayList<>();
	protected ArrayList<Player> allPlayers = new ArrayList<>();
	protected BOTDIFF[] diff= {BOTDIFF.FACILE,BOTDIFF.DIFFICILE};
	protected int playersCounter=0;
	protected String code;
	
	@FXML
	protected ChoiceBox<Player> playersChoiceBox;
    
    @FXML
    protected Label selectedPlayersLabel, undoSelection;
    
    @FXML
    protected Text botNumber;
    
    @FXML
    protected ChoiceBox<BOTDIFF> chooseDifficulty;

    @FXML
    protected Button returnToCreateGameButton, play;
    
	
	@FXML
    public void returnToCreateGame(ActionEvent event) throws IOException {
    	stage = (Stage)(returnToCreateGameButton.getScene().getWindow());
		  //IMPORTANTE RICORDA IL ../ nell'URL DEL FXML
		  FXMLLoader Loader=new FXMLLoader(CreateGameController.class.getResource("../Admin/CreateGame.fxml"));
		  root = (Parent) Loader.load();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.show();
    }
	
	@FXML
    public void undoAction(MouseEvent event) {
    	if(playersCounter>0) {
    		playersCounter--;
        	playersChoiceBox.getItems().add(selectedPlayers[playersCounter]);
        	selectedPlayers[playersCounter]=null;
        	
        	String message = "";
        	for(int i=0; i<playersCounter; i++) {
        		message += (" '" + selectedPlayers[i] + "'");
        	}
        	selectedPlayersLabel.setText(message);
        	botNumber.setText(" --  " + (MAXPLAYERS - playersCounter) + "  -- ");
    	}
    }
    
    @FXML
    public void setColorGrey(MouseEvent event) {
    	undoSelection.setTextFill(Color.GREY);
    }
    
    @FXML
    public void setColorWhite(MouseEvent event) {
    	undoSelection.setTextFill(Color.WHITE);
    }
	
    @FXML
    public void returnToHome() throws IOException {
    	stage = (Stage)(returnToCreateGameButton.getScene().getWindow());
		  //IMPORTANTE RICORDA IL ../ nell'URL DEL FXML
		  FXMLLoader Loader=new FXMLLoader(CreateGameController.class.getResource("../MainMenu/MainMenu.fxml"));
		  root = (Parent) Loader.load();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.show();
    }
    
    
	protected void fillPlayersInGame() {
		int botCounter = 0;
	    
	    for(int i=0; i<MAXPLAYERS; i++) {											
	    	if(selectedPlayers[i] != null) {									// converte ogni giocatore selezionato in PlayerInGame
	    		playersInGame.add(new PlayerInGame(selectedPlayers[i]));
	    	} else {														// inserisce i bot nella partita
	    		botCounter++;
	    		playersInGame.add(new PlayerInGame("BOT" + botCounter));
	    	}
	    }
	}
	
	protected void getPlayersFromFile() {			// popola l'ArrayList allPlayers con i giocatori memorizzati su file
		Path pathToFile = Paths.get("./GiocoSPACCA/Informazioni_Partite/PLAYERS_REGISTER.csv");
		File f=new File(pathToFile.toString());
			
		try {
			Scanner scan = new Scanner(f);
			scan.reset();
				
			while(scan.hasNextLine() ) {				// aggiunge i giocatori all'arraylist allPlayers
				String line=scan.nextLine();
				String[] tokens = line.split(",");
				
				String username = tokens[0];
			
				int totalScore = Integer.parseInt(tokens[1]);
				
				Player p = new Player(username, totalScore);
				allPlayers.add(p);
			}
			
			scan.close();	
			
		} catch(IOException e) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("ATTENZIONE");
			alert.setHeaderText("Non è stato ancora creato alcun giocatore");
			alert.setContentText("Sarà solamente possibile creare una partita tra Bot");
			e.printStackTrace();
		}	
	}
	
	protected void playerSelection(ActionEvent event) {
    	Player player = playersChoiceBox.getValue();
    	
    	if(playersCounter >= MAXPLAYERS && playersChoiceBox.getItems().size()!=0){
    		Alert selectionAlert = new Alert(AlertType.ERROR);
    		selectionAlert.setTitle("ERRORE!");
    		selectionAlert.setContentText("Numero massimo di giocatori inseribili raggiunto");
    		selectionAlert.showAndWait();
    	}
    	if(playersCounter < MAXPLAYERS){
    		selectedPlayers[playersCounter] = player;
			playersCounter++;

			selectedPlayersLabel.setText(selectedPlayersLabel.getText() + " '" + player.getUsername() + "' ");
			botNumber.setText(" --  " + (MAXPLAYERS - playersCounter) + "  -- ");
			playersChoiceBox.getItems().remove(player);
    	}
    }
    
	
	// metodi astratti
	@Override
	public abstract void initialize(URL arg0, ResourceBundle arg1);
	
	@FXML
	public abstract void play(ActionEvent event) throws IOException;
	
	protected abstract void fillGameFile();
}
