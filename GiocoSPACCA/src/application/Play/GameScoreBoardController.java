package application.Play;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class GameScoreBoardController implements Initializable {

	private LinkedHashMap<String, Integer> players = new LinkedHashMap<String,Integer>();
	private Stage stage;
	private Scene scene;
	private Parent root;
	public static File gameFile = InsertCodeController.file;
	
	@FXML
	private Label generalScoreBoardLabel, gameCodeLabel;
	
    @FXML
    private Button menuButton;

    @FXML
    private ListView<String> scoreBoard;

    @FXML
    public void returnToMainMenu(ActionEvent event) throws IOException {
    	stage = (Stage)(menuButton.getScene().getWindow());
		  //IMPORTANTE RICORDA IL ../ nell'URL DEL FXML
		  FXMLLoader Loader=new FXMLLoader(MainMenuController.class.getResource("MainMenu.fxml"));
		  root = (Parent) Loader.load();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.show();
    }
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    	
    	String code = gameFile.getName();
    	gameCodeLabel.setText("Classifica partita " + code);
    	
		try {
			Scanner scan=new Scanner(gameFile);
			if(scan.hasNextLine())
				scan.nextLine();		// salta la prima riga (intestazione file .csv)
			
			int pos=0;
			int maxPoints = 4;
			while(scan.hasNextLine()){
				String line=scan.nextLine();
				String[] tokens = line.split(",");
				this.players.put(tokens[1], maxPoints - pos);		// TODO: DECIDERE MECCANISMO PUNTI
				pos++;
			}
			// ordinamento
			for(String username : players.keySet())
				scoreBoard.getItems().add("[ +" + players.get(username) + " ]  " + username);
		
			scan.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		updateScores();
		//gameFile.delete();
    }
    
    @FXML
    public void generalScoreBoard(MouseEvent event) throws IOException {
    	stage = (Stage)(menuButton.getScene().getWindow());
		  //IMPORTANTE RICORDA IL ../ nell'URL DEL FXML
		  FXMLLoader Loader=new FXMLLoader(ScoreBoardController.class.getResource("../MainMenu/ScoreBoard.fxml"));
		  root = (Parent) Loader.load();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.show();
    }
    
    
    private void updateScores() {		// aggiorna i punteggi generali dei giocatori
		
		Path pathToFile = Paths.get("./GiocoSPACCA/Informazioni_Partite/PLAYERS_REGISTER.csv");
		File f=new File(pathToFile.toString());
		
		try {
			Scanner scan = new Scanner(f);
			scan.reset();
			String memory="";
			
			while(scan.hasNextLine() ) {
				String line = scan.nextLine();
				String[] tokens = line.split(",");
				if(players.keySet().contains(tokens[0])) {
					memory = memory + tokens[0] + "," + players.get(tokens[0]) + "\n";			// salvataggio sul file
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
