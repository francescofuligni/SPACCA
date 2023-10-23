package application.Admin;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import application.Player.Player;
import application.SingleGame.SingleGame;

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
import javafx.scene.text.Text;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class CreateSingleGameController implements Initializable {
	
	
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	private Integer humanCounter=0;
	private final Integer MAX = SelectPlayerNumberSGController.playerNumber;
	private BOTDIFF[] diff= {BOTDIFF.FACILE,BOTDIFF.DIFFICILE};
	private  ArrayList<Player> players = new ArrayList<Player>();
	
	
	
    @FXML
    private Button addPlayerButton;
    
    @FXML
    private Text botNumber;

    
    @FXML
    private ChoiceBox<BOTDIFF> chooseDifficulty;


    @FXML
    private TextField userNamespace;

    @FXML
    private Button returnToCreateGameButton;
    
    @FXML
    private Button create;
    
    @FXML
    public void addPlayer(ActionEvent event) {
    	
    	if( userNamespace.getText()!="" && userNamespace.getText()!=" " && userNamespace.getText()!=null) {
    	Player giocatore = new Player();
    	giocatore.setUsername(userNamespace.getText());
    	
    	boolean alreadyPlaying = false;
    	//controlla che lo user non sia gia in partita
    	
    	
    	if(humanCounter==0) {
			players.add(new Player (giocatore.getUsername(),30,0));
			userNamespace.setText(null);
			humanCounter++;
			botNumber.setText("--- " + (MAX.intValue() - humanCounter) + " ---");
		}
    	else {
    		if(humanCounter<MAX) {
    			for(int i=0; i<humanCounter ; i++) {
    		
    				if(giocatore.getUsername().equals(players.get(i).getUsername())) {  
    			
    					alreadyPlaying = true;
    					Alert addPlayerError = new Alert(AlertType.ERROR);
    					addPlayerError.setTitle("ERRORE!");
    					addPlayerError.setContentText("Giocatore con stesso Username gia presente!");
    					addPlayerError.showAndWait();
    			
    					break; 
    				}
    			}
    		    
    		if(alreadyPlaying==false ) {
    			players.add(new Player (giocatore.getUsername(),30,0));
    			userNamespace.setText(null);
    				humanCounter++;
    				botNumber.setText("--- " + (MAX.intValue() - humanCounter) + " ---");
    				
    			}
    		}
    		else {
    			Alert addPlayerError = new Alert(AlertType.ERROR);
    			addPlayerError.setTitle("ERRORE!");
    			addPlayerError.setContentText("MASSIMO DI GIOCATORI INSERITI!");
    			addPlayerError.showAndWait();
    			}
    		}
    	
    }
    }
    
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
		botNumber.setText("--- " +MAX + " ---");

	}
	
	 @FXML
	public void create(ActionEvent event) {	    	
	    		
	    		String Code="";
	    		for(int i=0;i<7;i++) {
	    			Code= Code + (int)Math.random()*10;
	    		}
	    		
	    		new SingleGame(MAX,chooseDifficulty.getValue(),players,Code);
	    		
	    		
	    		
	    		
	    }


}
