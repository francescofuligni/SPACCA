package application.Admin;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import application.Player.Player;
import application.Tournament.TournamentOBJ;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class CreateTournamentController implements Initializable  {
	

	private Stage stage;
	private Scene scene;
	private Parent root;
	
	private BOTDIFF[] Diff= {BOTDIFF.FACILE,BOTDIFF.DIFFICILE};
	private GAMEMODE[] Mode= {GAMEMODE.CHIVINCEREGNA, GAMEMODE.LASTMANSTANDING};
	
	protected int PlayerNumber =4;
	private  String [] Giocatori = new String[PlayerNumber];
	protected GAMEMODE ChosenMode;
	protected BOTDIFF ChosenDifficulty;
	int contaUmani=0;
	
    @FXML
    private Button AddPlayerButton;

    @FXML
    private ChoiceBox<GAMEMODE> ModalitàTorneo;

    @FXML
    private Button ReturnToCreateGameButton;

    @FXML
    private ChoiceBox<BOTDIFF> SceltaDifficoltaBot;


    @FXML
    private TextField SpazioUser;
    
    @FXML
    private Button Create;
    
    @FXML
    void AddPlayer() {
    	
    	Player giocatore = new Player();
    	giocatore.setUsername(SpazioUser.getText());
    	boolean alreadyPlaying = true;
    	//controlla che lo user non sia gia in partita
    	int i=0;
    	if(contaUmani==0) {
    		alreadyPlaying = false;
			Giocatori[contaUmani]=giocatore.getUsername();
		}
    	else {
    	while(contaUmani<PlayerNumber && i<=contaUmani) {
    		
    		if(giocatore.getUsername().equals(Giocatori[i])) {  
    			
    			alreadyPlaying = true;
    			Alert AddPlayerError = new Alert(AlertType.ERROR);
    			AddPlayerError.setTitle("ERRORE!");
    			AddPlayerError.setContentText("Giocatore con stesso Username gia presente!");
    			AddPlayerError.showAndWait();
    			
    			break; 
    		}
    		else {
    			i++;
    		}
    	}
    }
    	if(alreadyPlaying==false)
    	while(contaUmani<PlayerNumber) {
    		if(Giocatori[contaUmani] == null ) {
    				Giocatori[contaUmani] = giocatore.getUsername();
    				contaUmani++;
    			}
    		else {
    				contaUmani++;
    		}
    	}
    		
    }
    	
    

    @FXML
    void returnToCreateGame(ActionEvent event) throws IOException {
    	stage = (Stage)(ReturnToCreateGameButton.getScene().getWindow());
		  //IMPORTANTE RICORDA IL ../ nell'URL DEL FXML
		  FXMLLoader Loader=new FXMLLoader(CreateGameController.class.getResource("../Admin/CreateGame.fxml"));
		  root = (Parent) Loader.load();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.show();
    }
    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		SceltaDifficoltaBot.getItems().addAll(Diff);
		ModalitàTorneo.getItems().addAll(Mode);
	}
    

    @FXML
    void create(ActionEvent event) {
    	
    	
    		
    		String Code="";
    		for(int i=0;i<7;i++) {
    			Code= Code + (int)Math.random()*10;
    		}
    		
    		TournamentOBJ NuovoTorneo = new TournamentOBJ(ModalitàTorneo.getValue(),SceltaDifficoltaBot.getValue(),Giocatori,Code);
    		
    		
    		
    		
    }
    
}