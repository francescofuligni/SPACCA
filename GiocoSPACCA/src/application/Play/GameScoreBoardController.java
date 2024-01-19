package application.Play;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.ResourceBundle;
import java.util.Scanner;

import application.MainMenu.MainMenuController;
import application.MainMenu.ScoreBoardController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class GameScoreBoardController implements Initializable {

	private LinkedHashMap<String, Integer> players = new LinkedHashMap<String,Integer>();
	private Stage stage;
	private Scene scene;
	private Parent root;
	private final String code = InsertCodeController.code;
	private final Path pathToGame = InsertCodeController.pathToGame;
	
	@FXML
	private Label generalScoreBoardLabel, gameCodeLabel;
	
    @FXML
    private Button menuButton;

    @FXML
    private ListView<String> scoreBoard;
    
    @FXML
    private TextField emailField;
    
    @FXML
    private Button sendEmailButton;

    @FXML
    public void returnToMainMenu(ActionEvent event) throws IOException {
    	stage = (Stage)(menuButton.getScene().getWindow());
		  
		  FXMLLoader Loader=new FXMLLoader(MainMenuController.class.getResource("/application/MainMenu/MainMenu.fxml"));
		  root = (Parent) Loader.load();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.show();
    }
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    	
    	gameCodeLabel.setText("Classifica " + code);
    	try {
    		Scanner scan;
    		File f;
			if(code.startsWith("T")) {
				// SIMPLE TOURNAMENT
				String line="";
				
				// file finale
				f = new File(pathToGame.toString()+"/finale.csv");
				scan=new Scanner(f);
				
				if(scan.hasNextLine()) {
					scan.nextLine();			// salta la prima riga (intestazione)
				}
				while(scan.hasNextLine()){
					line=scan.nextLine();
					this.players.put(line.split(",")[1], null);
				}
				f.delete();
				scan.reset();
				
				// file seminfinali
				for(int i=2; i>0; i--) {
					f = new File(pathToGame.toString()+"/semifinale" + i + ".csv");
					scan = new Scanner(f); 
					while(scan.hasNextLine())
						line = scan.nextLine();
					this.players.put(line.split(",")[1], null);
					f.delete();
				}
				
				Files.delete(pathToGame);		// elimina la directory della partita
				
			} else {
				// SINGLE GAME O LAST MAN STANDING
				f = new File(pathToGame.toString());
				scan=new Scanner(f);
				if(scan.hasNextLine()) {
					scan.nextLine();			// salta la prima riga (intestazione)
				}
				while(scan.hasNextLine()) {
					this.players.put(scan.nextLine().split(",")[1], null);
				}
				scan.close();
				
				f.delete();						// elimina il file della partita
			}
			
			givePoints();						// assegna i punti
			for(String username : players.keySet()) {
				scoreBoard.getItems().add("[ +" + players.get(username) + " ] - " + username);
			}
			updateScores();						// aggiorna il punteggio totale dei giocatori nella classifica generale
			
		} catch (IOException e) {				// FileNotFoundException is a IOException
			e.printStackTrace();
		}
    }
    
    
    @FXML
    public void sendMail() throws Exception {
    	
    	String to = emailField.getText();
    	EmailSender.sendMail(to);
    	
    	
    }
    
    
    
    @FXML
    public void generalScoreBoard(MouseEvent event) throws IOException {
    	stage = (Stage)(menuButton.getScene().getWindow());
		 
		  FXMLLoader Loader=new FXMLLoader(ScoreBoardController.class.getResource("ScoreBoard.fxml"));
		  root = (Parent) Loader.load();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.show();
    }

    @FXML
    public void setColorGrey(MouseEvent event) {
    	generalScoreBoardLabel.setTextFill(Color.GREY);
    }
    @FXML
    public void setColorWhite(MouseEvent event) {
    	generalScoreBoardLabel.setTextFill(Color.WHITE);
    }
    
    
    private void updateScores() {								// aggiorna il punteggio totale dei giocatori nella classifica generale
		Path pathToFile = Paths.get("./GiocoSPACCA/Informazioni_Partite/PLAYERS_REGISTER.csv");
		File f=new File(pathToFile.toString());
		
		try {
			Scanner scan = new Scanner(f);
			scan.reset();
			String memory="";
			
			while(scan.hasNextLine() ) {
				String line = scan.nextLine();
				String[] tokens = line.split(",");
				if(players.keySet().contains(tokens[0]))		// salva su file il nuovo punteggio e decrementa di 1 il contatore delle partite del giocatore
					memory = memory + tokens[0] + "," + (players.get(tokens[0]) + Integer.parseInt(tokens[1])) + "," + (Integer.parseInt(tokens[2])-1) + "\n";
				else
					memory = memory + line + "\n";				// le altre linee vengono riscritte nello stesso modo
			}
			scan.close();	
			FileWriter fw = new FileWriter(f,false);
			fw.write(memory);
			fw.close();
			
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
    
    private void givePoints() {									// assegna i punti in base alla partita e all'ordine di classifica
    	int pos=0;
    	if(code.startsWith("T")) {
    		// punteggi torneo
    		for(String username : players.keySet()) {
    			if(pos>=players.size()-2)
    				players.putIfAbsent(username, 2);												// ultimo e penultimo classificato
    			else
    				players.putIfAbsent(username, (int)(Math.pow(2, players.size()-pos)*1.25));		// primo e secondo classificato
    			pos++;
    		}
    	} else if(code.startsWith("L")) {
    		// punteggi last man standing
    		for(String username : players.keySet()) {
    			players.putIfAbsent(username, (int)(Math.pow(2, players.size()-pos)*1.25));
    			pos++;
    		}
    	} else {
    		// punteggi single game
        	for(String username : players.keySet()) {
        		players.putIfAbsent(username, (int)Math.pow(2, players.size()-pos));
        		pos++;
        	}
    	}
    }
}
