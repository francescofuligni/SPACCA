package application.Admin;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Scanner;

import application.Player.EasyBot;
import application.Player.HardBot;
import application.Player.Player;
import application.Player.PlayerInGame;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class CreateTournamentController implements Initializable  {
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	private BOTDIFF[] diff= {BOTDIFF.FACILE,BOTDIFF.DIFFICILE};
	private GAMEMODE[] mode= {GAMEMODE.SEMPLICE, GAMEMODE.LASTMANSTANDING};
	
	protected final static int MAXPLAYERS = 4;			// numero massimo di giocatori
	
	protected GAMEMODE chosenMode;
	protected BOTDIFF chosenDifficulty;
	private int playersCounter=0;
	
	private Player[] selectedPlayers = new Player[MAXPLAYERS];
	private ArrayList<Player> allPlayers = new ArrayList<>();
	private ArrayList<PlayerInGame> playersInGame = new ArrayList<>();
	
	@FXML
	private Text botNumber;
	
	@FXML
	private ChoiceBox<Player> playersChoiceBox;

    @FXML
    private ChoiceBox<GAMEMODE> tournamentMode;

    @FXML
    private Button returnToCreateGameButton;

    @FXML
    private ChoiceBox<BOTDIFF> chooseDifficulty;
    
    @FXML
    private Label selectedPlayersLabel;
   
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
    	tournamentMode.getItems().addAll(mode);
		botNumber.setText(" --  " + (MAXPLAYERS - playersCounter) + "  -- ");
		
		getPlayersFromFile();
		playersChoiceBox.getItems().addAll(allPlayers);
		playersChoiceBox.setOnAction(this::playerSelection);
	}
    
    
    private void getPlayersFromFile() {			// popola l'ArrayList allPlayers con i giocatori memorizzati su file
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
				
				Player p = new Player(username,totalScore);
				allPlayers.add(p);
			}
			
			scan.close();	
			
		} catch(IOException e) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("ATTENZIONE");
			alert.setHeaderText("Non è stato ancora creato alcun giocatore");
			alert.setContentText("Sarà solamente possibile creare un torneo tra Bot");
			e.printStackTrace();
		}	
    }
    
    private void playerSelection(ActionEvent event) {
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
    void play(ActionEvent event) throws IOException {		// INCOMPLETO
    	
    	if(((MAXPLAYERS-playersCounter>0) && chooseDifficulty.getValue()==null) || tournamentMode.getValue()==null) {
	    	Alert selectionAlert = new Alert(AlertType.ERROR);
    		selectionAlert.setTitle("ERRORE");
    		selectionAlert.setHeaderText("Selezionare difficoltà bot e/o modalità torneo");
    		selectionAlert.showAndWait();
	    	
	    } else {
	    	File f;
	    	String code;

		    do {
		    	code = "T";
			    for(int i=0;i<5;i++) {						// genera il codice partita
			    	Random rand = new Random();
			    	code = code + rand.nextInt(10);
			    }
			    Path pathToFile = Paths.get("./GiocoSPACCA/Informazioni_Partite/" + code + ".csv");
				f=new File(pathToFile.toString());
		    } while(f.exists());							// se esiste già un file con lo stesso codice, genera un codice diverso
		    f.createNewFile();								// crea il file per il codice generato
		    
		    
		    fillPlayersInGame();							// popola l'ArrayList playersInGame
		    fillGameFile(f); 								// popola il file della partita
		    
		    Alert codeInfo = new Alert(AlertType.INFORMATION);					// mostra il codice generato
		    codeInfo.setTitle("CODICE GENERATO");
		    codeInfo.setContentText("Codice della partita creata");
		    codeInfo.setHeaderText(code);
		    codeInfo.showAndWait();
	    	
	    	returnToHome();
	    }
    }
    
    public void returnToHome() throws IOException {
    	stage = (Stage)(returnToCreateGameButton.getScene().getWindow());
		  //IMPORTANTE RICORDA IL ../ nell'URL DEL FXML
		  FXMLLoader Loader=new FXMLLoader(CreateGameController.class.getResource("../MainMenu/MainMenu.fxml"));
		  root = (Parent) Loader.load();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.show();
    }
    
    private void fillPlayersInGame() {
		int botCounter = 0;
	    for(int i=0; i<MAXPLAYERS; i++) {											// inserisce i bot nei giocatori della partita
	    	if(selectedPlayers[i]==null) {
	    		if(chooseDifficulty.getValue().equals(BOTDIFF.FACILE)) {
	    			botCounter++;
	    			selectedPlayers[i] = new EasyBot("BOT" + botCounter);
	    		} else {
	    			botCounter++;
	    			selectedPlayers[i] = new HardBot("BOT" + botCounter);
	    		}
	    	}
	    }
	    
	    for(int i=0; i<MAXPLAYERS; i++) {											// converte ogni giocatore selezionato in PlayerInGame
	    	playersInGame.add(new PlayerInGame(selectedPlayers[i]));
	    }
	}
	
	
	private void fillGameFile(File f) {
		try {
	        FileWriter fw = new FileWriter(f.getAbsolutePath(),true);
	        Iterator<PlayerInGame> iter = playersInGame.iterator();
	        Random rand=new Random();
	        
	        fw.write(tournamentMode.getValue() + "," + chooseDifficulty.getValue() + ","+ rand.nextInt(playersInGame.size())+ "\n");
	        Collections.shuffle(playersInGame);			// mescola i giocatori per combinarli
			while(iter.hasNext())
				fw.write("in," + iter.next() + "\n");
			
			fw.flush();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}