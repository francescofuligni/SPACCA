package application.Admin;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
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


public class CreateSingleGameController extends GamesCreation {

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		MAXPLAYERS = SelectPlayerNumberSGController.playerNumber;
    	selectedPlayers = new Player[MAXPLAYERS];
		
		chooseDifficulty.getItems().addAll(diff);
		botNumber.setText(" --  " + MAXPLAYERS + "  -- ");
		
		getPlayersFromFile();
		playersChoiceBox.getItems().addAll(allPlayers);
		playersChoiceBox.setOnAction(this::playerSelection);
	}
    
	@Override @FXML
	public void play(ActionEvent event) throws IOException {
	    if((MAXPLAYERS-playersCounter>0) && chooseDifficulty.getValue()==null) {						// non crea la partita se non è stata inserita una difficoltà per i bot
	    	Alert selectionAlert = new Alert(AlertType.ERROR);
    		selectionAlert.setTitle("ERRORE");
    		selectionAlert.setHeaderText("Selezionare difficoltà bot");
    		selectionAlert.showAndWait();
    		
	    } else {
	    	File f;
	    	String code;

		    do {
		    	code = "S";
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
	
	@Override
	protected void fillGameFile(File f) {
		try {
	        FileWriter fw = new FileWriter(f.getAbsolutePath(),true);
	        Iterator<PlayerInGame> iter = playersInGame.iterator();
	        Random rand=new Random();
	        
	        fw.write("SingleGame," + chooseDifficulty.getValue() + ","+rand.nextInt(playersInGame.size())+"\n");
	        Collections.shuffle(playersInGame);			// mescola i giocatori
			while(iter.hasNext())
				fw.write("in," + iter.next() + "\n");
			
			fw.flush();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
