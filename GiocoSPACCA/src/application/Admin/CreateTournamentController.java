package application.Admin;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Iterator;
import java.util.Random;
import java.util.ResourceBundle;

import application.Player.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ChoiceBox;


public class CreateTournamentController extends GamesCreation {
	
	private GAMEMODE[] mode = {GAMEMODE.SEMPLICE, GAMEMODE.LASTMANSTANDING};
	
    @FXML
    private ChoiceBox<GAMEMODE> tournamentMode;
    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	MAXPLAYERS = 4;
    	selectedPlayers = new Player[MAXPLAYERS];
    	chooseDifficulty.getItems().addAll(diff);
    	tournamentMode.getItems().addAll(mode);
		botNumber.setText(" --  " + (MAXPLAYERS - playersCounter) + "  -- ");
		
		getPlayersFromFile();
		playersChoiceBox.getItems().addAll(allPlayers);
		playersChoiceBox.setOnAction(this::playerSelection);
	}
    
    @Override @FXML
    public void play(ActionEvent event) throws IOException {		// INCOMPLETO
    	
    	if(((MAXPLAYERS-playersCounter>0) && chooseDifficulty.getValue()==null) || tournamentMode.getValue()==null) {
	    	Alert selectionAlert = new Alert(AlertType.ERROR);
    		selectionAlert.setTitle("ERRORE");
    		selectionAlert.setHeaderText("Selezionare difficoltà bot e/o modalità torneo");
    		selectionAlert.showAndWait();
	    	
	    } else {
	    	
	    	if(tournamentMode.getValue().equals(GAMEMODE.SEMPLICE)) {
	    		// TORNEO SEMPLICE
	    		
	    		Path pathDirectory;
			    do {
			    	if(tournamentMode.getValue().equals(GAMEMODE.LASTMANSTANDING))
			    		code = "T";
				    for(int i=0;i<5;i++) {						// genera il codice partita
				    	Random rand = new Random();
				    	code = code + rand.nextInt(10);
				    }
				    pathDirectory = Paths.get("./GiocoSPACCA/Informazioni_Partite/" + code);		// directory del torneo, contenente i file delle singole partite		
			    } while(Files.exists(pathDirectory));			// se esiste già un file con lo stesso codice, genera un codice diverso
			    Files.createDirectory(pathDirectory);
	    	
	    	} else {
	    		// TORNEO LAST MAN STANDING
	    		
	    		File gameFile;
	    		do {
			    	code = "L";
				    for(int i=0;i<5;i++) {						// genera il codice torneo LMS
				    	Random rand = new Random();
				    	code = code + rand.nextInt(10);
				    }
				    Path pathToFile = Paths.get("./GiocoSPACCA/Informazioni_Partite/" + code + ".csv");
					gameFile=new File(pathToFile.toString());
			    } while(gameFile.exists());							// se esiste già un file con lo stesso codice, genera un codice diverso
			    gameFile.createNewFile();							// crea il file per il codice generato
	    	}
		    
		    fillPlayersInGame();			// popola l'ArrayList playersInGame
		    fillGameFile(); 				// popola il file della partita
		    
		    Alert codeInfo = new Alert(AlertType.INFORMATION);					// mostra il codice generato
		    codeInfo.setTitle("CODICE GENERATO");
		    codeInfo.setContentText("Codice della partita creata");
		    codeInfo.setHeaderText(code);
		    codeInfo.showAndWait();
	    	returnToHome();
	    }
    }
    
    @Override
	protected void fillGameFile() {
    	try {
    		Collections.shuffle(playersInGame);			// mescola i giocatori
        	Random rand = new Random();
    		
	    	if(tournamentMode.getValue()==GAMEMODE.SEMPLICE) { 
	    		// TORNEO SEMPLICE
	    		
	    		// file partita singola semifinale 1
		    	File semi1=new File("./GiocoSPACCA/Informazioni_Partite/" + code + "/semifinale1.csv");
		    	FileWriter fw1 = new FileWriter(semi1);
		    	fw1.write("SingleGame,"+tournamentMode.getValue()+"," + rand.nextInt(2) + "\n");
		    	fw1.write("in," + playersInGame.remove(rand.nextInt(playersInGame.size())) + "\n");
		    	fw1.write("in," + playersInGame.remove(rand.nextInt(playersInGame.size())) + "\n");
		    	fw1.flush();
				fw1.close();
		    	
		    	// file partita singola semifinale 2
				File semi2=new File("./GiocoSPACCA/Informazioni_Partite/" + code + "/semifinale2.csv");
				FileWriter fw2 = new FileWriter(semi2);
				fw2.write("SingleGame,"+ chooseDifficulty.getValue() + "," + rand.nextInt(2) + "\n");
				fw2.write("in," + playersInGame.remove(rand.nextInt(playersInGame.size())) + "\n");
				fw2.write("in," + playersInGame.remove(rand.nextInt(playersInGame.size())) + "\n");
				fw2.flush();
				fw2.close();
				
				// file partita singola finale
		    	File fin = new File("./GiocoSPACCA/Informazioni_Partite/" + code + "/finale.csv");
		    	FileWriter fwfin = new FileWriter(fin);
		    	fwfin.write("SingleGame," + chooseDifficulty.getValue() + "," + rand.nextInt(2) + "\n");
		    	fwfin.flush();
				fwfin.close();
		    
			} else {
				// TORNEO LAST MAN STANDING
				
		    	// file parita singola iniziale
		    	File gameFile=new File("./GiocoSPACCA/Informazioni_Partite/" + code + ".csv");
		    	FileWriter fw = new FileWriter(gameFile);
		    	Iterator<PlayerInGame> iter = playersInGame.iterator();
		        
		        fw.write(tournamentMode.getValue() + "," + chooseDifficulty.getValue() + "," + rand.nextInt(playersInGame.size()) + "\n");
				while(iter.hasNext())
					fw.write("in," + iter.next() + "\n");
		    	
		    	fw.flush();
				fw.close();
		    }
    	} catch(IOException e) {
    		 e.printStackTrace();
    	}
	}
}