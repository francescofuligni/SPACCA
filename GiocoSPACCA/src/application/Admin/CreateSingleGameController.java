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
import application.Player.PlayerList;
import application.SingleGame.SingleGame;

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
import javafx.scene.text.Text;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class CreateSingleGameController implements Initializable {
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	public final int maxPlayers = SelectPlayerNumberSGController.playerNumber;				// NON può essere static
	
	private Integer playersCounter=0;
	private BOTDIFF[] diff= {BOTDIFF.FACILE,BOTDIFF.DIFFICILE};
	
	private Player[] selectedPlayers = new Player[maxPlayers];
	private ArrayList<Player> allPlayers = new ArrayList<>();
	private PlayerList players;
	
	@FXML
	private ChoiceBox<Player> playersChoiceBox;
    
    @FXML
    private Label selectedPlayersLabel;
    
    @FXML
    private Text botNumber;
    
    @FXML
    private ChoiceBox<BOTDIFF> chooseDifficulty;

    @FXML
    private Button returnToCreateGameButton;
    
    @FXML
    private Button play;
    
    @FXML
    private Label undoSelection;
    
    
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


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		chooseDifficulty.getItems().addAll(diff);
		botNumber.setText(" --  " + maxPlayers + "  -- ");
		
		getPlayersFromFile();
		playersChoiceBox.getItems().addAll(allPlayers);
		playersChoiceBox.setOnAction(this::playerSelection);
	}
	
	 
	private void getPlayersFromFile() {			// popola l'ArrayList allPlayers con i giocatori memorizzati su file
		Path pathToFile = Paths.get("GiocoSPACCA/Informazioni_Partite/PLAYERS_REGISTER.csv");
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
	
    private void playerSelection(ActionEvent event) {
    	Player player = playersChoiceBox.getValue();
    	
    	if(playersCounter >= maxPlayers && playersChoiceBox.getItems().size()!=0){
    		Alert selectionAlert = new Alert(AlertType.ERROR);
    		selectionAlert.setTitle("ERRORE!");
    		selectionAlert.setContentText("Numero massimo di giocatori inseribili raggiunto");
    		selectionAlert.showAndWait();
    	}
    	if(playersCounter < maxPlayers){
    		selectedPlayers[playersCounter] = player;
			playersCounter++;

			selectedPlayersLabel.setText(selectedPlayersLabel.getText() + " '" + player.getUsername() + "' ");
			botNumber.setText(" --  " + (maxPlayers - playersCounter) + "  -- ");
			playersChoiceBox.getItems().remove(player);
    	}
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
        	botNumber.setText(" --  " + (maxPlayers - playersCounter) + "  -- ");
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
	public void play(ActionEvent event) {		// INCOMPLETO
	    		
	    String code = "S";										// magari aggiungere una lettera 'S' davanti per distinguere il codice del torneo da quello della partita singola? (facilita la ricerca)
	    for(int i=0;i<5;i++) {
	    	code = code + (int)Math.random()*10;				// TO-DO: controllare che il codice generato non sia già presente
	    }
	    
	    
	    // di seguito creo la struttura dati apposita per la turnazione, si veda la classe per le specifiche
	    if(selectedPlayers.length == 2)
	    	players = new PlayerList(selectedPlayers[0],selectedPlayers[1]);
	    else if(selectedPlayers.length == 3)
	    	players = new PlayerList(selectedPlayers[0],selectedPlayers[1],selectedPlayers[2]);
	    else 
	    	players = new PlayerList(selectedPlayers[0],selectedPlayers[1],selectedPlayers[2],selectedPlayers[3]);
	    		
	    		
	    new SingleGame(maxPlayers, chooseDifficulty.getValue(),players,code);
	    
	    
	    
	}
}
