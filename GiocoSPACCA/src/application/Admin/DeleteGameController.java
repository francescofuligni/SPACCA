package application.Admin;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import application.Player.Player;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class DeleteGameController {

	private Stage stage;
	private Scene scene;
	private Parent root;
	
	private File toDelete;
	private String code;
	private ArrayList<Player> players = new ArrayList<>();
	
    @FXML
    private TextField codeField;
    @FXML
    private Button deleteButton;
    @FXML
    private Button endButton;
    @FXML
    private Label message;

    @FXML
    public void deleteGame(ActionEvent event) { 
    	// elimina la partita con il codice inserito
    	code = codeField.getText().toUpperCase();
    	
    	if(code.startsWith("T")) {
    		// TORNEO SEMPLICE
    		toDelete = new File(Paths.get("./GiocoSPACCA//Informazioni_Partite/" + code).toString());
    		
    		if(toDelete.exists()) {		// controlla esistenza torneo inserito
    			Alert alert = new Alert(AlertType.CONFIRMATION);
   				alert.setTitle("Elimina partita");
   				alert.setHeaderText("OPERAZIONE IRREVERSIBILE");
   				alert.setContentText("Sei sicuro di voler eliminare la partita \"" + code + "\"?");
   				
   				if(alert.showAndWait().get() == ButtonType.OK) {		// conferma eliminazione
   					// svuota la directory
   					
   					toDelete = new File(Paths.get("./GiocoSPACCA/Informazioni_Partite/" + code + "/finale.csv").toString());
   		    		if(toDelete.exists()) {
   		    			getPlayers(toDelete);
   		    			toDelete.delete();
   		    		}
   					
   					toDelete = new File(Paths.get("./GiocoSPACCA/Informazioni_Partite/" + code + "/semifinale1.csv").toString());
   					getPlayers(toDelete);
   		    		if(toDelete.exists()) {
   		    			getPlayers(toDelete);
   		    			toDelete.delete();
   		    		}
   		    		
   		    		toDelete = new File(Paths.get("./GiocoSPACCA/Informazioni_Partite/" + code + "/semifinale2.csv").toString());
   		    		if(toDelete.exists()) {
   		    			getPlayers(toDelete);
   		    			toDelete.delete();
   		    		}	
   		    		
   		    		// elimina la directory
   		    		toDelete = new File(Paths.get("./GiocoSPACCA/Informazioni_Partite/" + code).toString());
   		    		toDelete.delete();
   		    		
   					message.setTextFill(Color.GREEN);
   					message.setText("Partita \"" + code + "\" eliminata correttamente");
   				} else{
   					message.setTextFill(Color.RED);
   					message.setText("Partita non eliminata");
   				}
    		} else {
    			gameNotFound();
	    	}
    	
	    } else if(code.startsWith("L") || code.startsWith("S")) {
	    	// LAST MAN STANDING O PARTITA SINGOLA
	    	toDelete = new File(Paths.get("./GiocoSPACCA/Informazioni_Partite/" + code + ".csv").toString());
	    	
    		if(toDelete.exists()) {
    			Alert alert = new Alert(AlertType.CONFIRMATION);
       		 	alert.setTitle("Elimina partita");
   				alert.setHeaderText("Stai eliminando un partita");
   				alert.setContentText("Sei sicuro di voler eliminare la partita \"" + code + "\"?");
   				
   				if(alert.showAndWait().get() == ButtonType.OK) {
   					// elimina il file partita
   					
   					getPlayers(toDelete);
   					toDelete.delete();
   					message.setTextFill(Color.GREEN);
   					message.setText("Partita \"" + code + "\" eliminata correttamente");
   				} else {
   					message.setTextFill(Color.RED);
   					message.setText("Partita non eliminata");
   				}
   				
       		} else {
       			gameNotFound();
    		}
    		
	    } else {
	    	gameNotFound();
	    }
    	subtractPlayersGames();			// decrementa di uno il contatore delle partite dei giocatori nella partita eliminata
    }

    @FXML
    public void back(ActionEvent event) throws IOException {
		// ritorna alla gestione partite
    	stage = (Stage)(endButton.getScene().getWindow());
		  FXMLLoader Loader=new FXMLLoader(GamesManagerController.class.getResource("/application/Admin/GamesManager.fxml"));
		  root = (Parent) Loader.load();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.show();
    }
    
    
    private void gameNotFound() {
    	// se non viene trovato il file della partita da eliminare, mostra un messaggio di errore
		message.setTextFill(Color.RED);
		message.setText("Partita non trovata");
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Errore");
		alert.setHeaderText("Impossibile eseguire l'operazione");
		alert.showAndWait();
    }
    
    private void subtractPlayersGames() {
    	// decrementa di uno il contatore delle partite dei giocatori
    	if(players.size()!=0)
    		for(Player p : players)
        		p.subtractGame();			// decrementa di uno il contatore delle partite del giocatore
    }
    
    private void getPlayers(File f) {
    	// estrae tutti i giocatori dal file
    	try {
			Scanner scan = new Scanner(f);
			scan.reset();
			if(scan.hasNextLine())
				scan.nextLine();			// salta la prima riga (intestazione)
			
			while(scan.hasNextLine()) {
				Player p = new Player(scan.nextLine().split(",")[1]);
				if(!players.contains(p))
					players.add(p);
			}
			scan.close();
		} catch (FileNotFoundException e) {
			Alert exceptionAlert = new Alert(AlertType.ERROR);
			exceptionAlert.setTitle("ERRORE");
			exceptionAlert.setHeaderText("File non trovato");
			exceptionAlert.setContentText(e.getClass().getSimpleName());
			exceptionAlert.showAndWait();
		}
    }
}
