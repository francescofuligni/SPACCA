package application.MainMenu;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.ResourceBundle;
import java.util.Scanner;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class ScoreBoardController implements Initializable {
	
	private Map<String,Integer> players=new HashMap<String,Integer>();
	private Stage stage;
	private Scene scene;
	private Parent root;
	
    @FXML
    private ListView<String> scoreBoard;
    @FXML
    private Button menuButton;
    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	Path pathToFile = Paths.get("./GiocoSPACCA/Informazioni_Partite/PLAYERS_REGISTER.csv");
		File f=new File(pathToFile.toString());
		
		try {
			Scanner scan=new Scanner(f);
			while(scan.hasNextLine()){					// estrae i giocatori e i loro punteggi dal registro giocatori
				String line=scan.nextLine();
				String[] tokens = line.split(",");
				this.players.put(tokens[0], Integer.parseInt(tokens[1]));
			}
			List<Entry<String,Integer>> list= new ArrayList<>(this.players.entrySet());
			list.sort(Entry.comparingByValue());		// ordinamento (decrescente) dei giocatori in base al punteggio
			for(int i=list.size()-1;i>=0;i--)
			scoreBoard.getItems().add("["+list.get(i).getValue()+"] - " + list.get(i).getKey() );		// inizializza la classifica
			scan.close();
			
		} catch (FileNotFoundException e) {
			Alert exceptionAlert = new Alert(AlertType.ERROR);
			exceptionAlert.setTitle("ERRORE");
			exceptionAlert.setHeaderText("File non trovato");
			exceptionAlert.setContentText(e.getClass().getSimpleName());
			exceptionAlert.showAndWait();
		}
    }

    @FXML
    public void returnToMainMenu(ActionEvent e) throws IOException {
    	// torna al MainMenu
    	stage = (Stage)(menuButton.getScene().getWindow());
		  FXMLLoader Loader=new FXMLLoader(ScoreBoardController.class.getResource("/application/MainMenu/MainMenu.fxml"));
		  root = (Parent) Loader.load();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.show();
    }
}
