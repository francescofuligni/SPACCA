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
import java.util.Scanner;

import application.Player.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ChoiceBox;


public class CreateTournamentController extends GamesCreation {
	
	private GAMEMODE[] mode= {GAMEMODE.SEMPLICE, GAMEMODE.LASTMANSTANDING};
	private String code; 	// ESPERIMENTO PER PERMETTERE ALLA CLASSE SIMPLE-TOURNAMENT DI RICONOSCERE IL TORNEO IN QUESTIONE
	
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
	    	File f;
	    	Scanner scan;
		    do {
		    	code = "T";
			    for(int i=0;i<5;i++) {						// genera il codice partita
			    	Random rand = new Random();
			    	code = code + rand.nextInt(10);
			    }
			    
			    Path pathDirectory = Paths.get("./GiocoSPACCA/Informazioni_Partite/" + code); //creo directory in cui mettere file csv relativi alle singole partite sottostanti al torneo
			    Files.createDirectories(pathDirectory);
			    
			    Path pathToFile = Paths.get("./GiocoSPACCA/Informazioni_Partite/" + code +"/" + code + ".csv"); //creo file torneo utile per creare le singole partite
				f=new File(pathToFile.toString());
							
		    } while(f.exists());			// se esiste già un file con lo stesso codice, genera un codice diverso
		    
		    
		    f.createNewFile();				// crea il file per il codice generato
		    fillPlayersInGame();			// popola l'ArrayList playersInGame
		    fillGameFile(f); 				// popola il file della partita
		
		    
		    
		    scan=new Scanner(f);  //scanner per leggere il file principale del torneo e copiare il contenuto sui file partita
		    scan.nextLine(); //salta la prima riga
		    
		    
		    //TODO cercare un modo per metodizzare righe 219-251 e rendere più efficiente
		    if(tournamentMode.getValue()==GAMEMODE.SEMPLICE) { //se il torneo è semplice si creano i due file partita separati
		    	Path pathPartita1 = Paths.get("./GiocoSPACCA/Informazioni_Partite/" + code +"/partita1.csv"); //creo file torneo utile per creare le singole partite
		    	File f1=new File(pathPartita1.toString());
		    	f1.createNewFile();
		    	FileWriter fw1 = new FileWriter(f1,true);
		    	fw1.write("SingleGame,"+tournamentMode.getValue()+",0\n");
		    	
		    	
		    	
				Path pathPartita2 = Paths.get("./GiocoSPACCA/Informazioni_Partite/" + code +"/partita2.csv"); //creo file torneo utile per creare le singole partite
				File f2=new File(pathPartita2.toString());
				f2.createNewFile();
				FileWriter fw2 = new FileWriter(f2,true);
				fw2.write("SingleGame,"+tournamentMode.getValue()+",0\n");
				
				for(int i=0;i<4;i++) {
		    		if(i<2) {
		    			String line = scan.nextLine();
			    		fw1.write(line+"\n");
		    		}
		    		else{
		    			String line = scan.nextLine();
		    			fw2.write(line+"\n");
		    		}		
		    	}
				fw1.flush();
				fw1.close();
				fw2.flush();
				fw2.close();
		    }
		    
		    
		    Alert codeInfo = new Alert(AlertType.INFORMATION);					// mostra il codice generato
		    codeInfo.setTitle("CODICE GENERATO");
		    codeInfo.setContentText("Codice della partita creata");
		    codeInfo.setHeaderText(code);
		    codeInfo.showAndWait();
	    	scan.close();
	    	returnToHome();
	    }
    }
    
    @Override
	protected void fillGameFile(File f) {
		try {
	        FileWriter fw = new FileWriter(f.getAbsolutePath(),true);
	        Iterator<PlayerInGame> iter = playersInGame.iterator();
	        Random rand=new Random();
	        
	        fw.write(code +","+tournamentMode.getValue() + "," + chooseDifficulty.getValue() + ","+ rand.nextInt(playersInGame.size())+ "\n");
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