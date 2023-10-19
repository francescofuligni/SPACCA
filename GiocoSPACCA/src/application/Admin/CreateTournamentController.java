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
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class CreateTournamentController implements Initializable  {
	

	private Stage stage;
	private Scene scene;
	private Parent root;
	
	private BOTDIFF[] Diff= {BOTDIFF.FACILE,BOTDIFF.DIFFICILE};
	private GAMEMODE[] Mode= {GAMEMODE.CHIVINCEREGNA, GAMEMODE.LASTMANSTANDING};
	
	protected final int PlayerNumber =4;
	private  Player[] Players = new Player[PlayerNumber];
	protected GAMEMODE ChosenMode;
	protected BOTDIFF ChosenDifficulty;
	private int HumanCounter=0;
	
	 @FXML
	private Text BotNumber;
	
    @FXML
    private Button AddPlayerButton;

    @FXML
    private ChoiceBox<GAMEMODE> TournamentMode;

    @FXML
    private Button ReturnToCreateGameButton;

    @FXML
    private ChoiceBox<BOTDIFF> ChooseDifficulty;


    @FXML
    private TextField SpazioUser;
    
    @FXML
    private Button Create;
    
    @FXML
    public void AddPlayer(ActionEvent e) {
    	if(SpazioUser.getText()!="" && SpazioUser.getText()!=" " && SpazioUser.getText()!=null) {
    	Player giocatore = new Player();
    	giocatore.setUsername(SpazioUser.getText());
    	
    	boolean alreadyPlaying = false;
    	//controlla che lo user non sia gia in partita
    	
    	
    	if(HumanCounter==0) {
    		Players[0]=new Player (giocatore.getUsername(),30,0);
			SpazioUser.setText(null);
			HumanCounter++;
			BotNumber.setText("--- " + (PlayerNumber - HumanCounter) + " ---");
		}
    	else {
    		if(HumanCounter<PlayerNumber) {
    			for(int i=0; i<HumanCounter ; i++) {
    		
    				if(giocatore.getUsername().equals(Players[i].getUsername())) {  
    			
    					alreadyPlaying = true;
    					Alert AddPlayerError = new Alert(AlertType.ERROR);
    					AddPlayerError.setTitle("ERRORE!");
    					AddPlayerError.setContentText("Giocatore con stesso Username gia presente!");
    					AddPlayerError.showAndWait();
    			
    					break; 
    				}
    			}
    		    
    		if(alreadyPlaying==false ) {
    			Players[HumanCounter] = new Player (giocatore.getUsername(),30,0);
    				SpazioUser.setText(null);
    				HumanCounter++;
    				BotNumber.setText("--- " + (PlayerNumber- HumanCounter) + " ---");
    			}
    		}
    		else {
    			Alert AddPlayerError = new Alert(AlertType.ERROR);
    			AddPlayerError.setTitle("ERRORE!");
    			AddPlayerError.setContentText("MASSIMO DI GIOCATORI INSERITI!");
    			AddPlayerError.showAndWait();
    			}
    		}
    	
    	}
    }
    		
    	
    

    @FXML
    public void returnToCreateGame(ActionEvent event) throws IOException {
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
    	ChooseDifficulty.getItems().addAll(Diff);
    	TournamentMode.getItems().addAll(Mode);
		BotNumber.setText("--- " + (PlayerNumber - HumanCounter) + " ---");
	}
    

    @FXML
    void create(ActionEvent event) {
    	
    	
    		
    		String Code="";
    		for(int i=0;i<7;i++) {
    			Code= Code + (int)Math.random()*10;
    		}
    		
    		TournamentOBJ NuovoTorneo = new TournamentOBJ(TournamentMode.getValue(),ChooseDifficulty.getValue(),Players,Code);
    		
    		
    		
    		
    }
    
}