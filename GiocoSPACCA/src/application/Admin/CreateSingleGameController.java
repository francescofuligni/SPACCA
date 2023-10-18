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
	
	private Integer HumanCounter=0;
	private final Integer MAX = SelectPlayerNumberSGController.PlayerNumber;
	private BOTDIFF[] Diff= {BOTDIFF.FACILE,BOTDIFF.DIFFICILE};
	private  ArrayList<Player> Giocatori = new ArrayList<Player>();
	
    @FXML
    private Button AddPlayerButton;
    
    @FXML
    private Text BotNumber;

    
    @FXML
    private ChoiceBox<BOTDIFF> ChooseDifficulty;


    @FXML
    private TextField UserNamespace;

    @FXML
    private Button ReturnToCreateGameButton;
    
    @FXML
    private Button Create;
    
    @FXML
    public void AddPlayer(ActionEvent event) {
    	if( UserNamespace.getText()!="" && UserNamespace.getText()!=" " && UserNamespace.getText()!=null) {
    	Player giocatore = new Player();
    	giocatore.setUsername(UserNamespace.getText());
    	
    	boolean alreadyPlaying = false;
    	//controlla che lo user non sia gia in partita
    	
    	
    	if(HumanCounter==0) {
			Giocatori.add(new Player (giocatore.getUsername(),30,0));
			UserNamespace.setText(null);
			HumanCounter++;
			BotNumber.setText("--- " + (MAX.intValue() - HumanCounter) + " ---");
		}
    	else {
    		if(HumanCounter<MAX) {
    			for(int i=0; i<HumanCounter ; i++) {
    		
    				if(giocatore.getUsername().equals(Giocatori.get(i).getUsername())) {  
    			
    					alreadyPlaying = true;
    					Alert AddPlayerError = new Alert(AlertType.ERROR);
    					AddPlayerError.setTitle("ERRORE!");
    					AddPlayerError.setContentText("Giocatore con stesso Username gia presente!");
    					AddPlayerError.showAndWait();
    			
    					break; 
    				}
    			}
    		    
    		if(alreadyPlaying==false ) {
    			Giocatori.add(new Player (giocatore.getUsername(),30,0));
    			UserNamespace.setText(null);
    				HumanCounter++;
    				BotNumber.setText("--- " + (MAX.intValue() - HumanCounter) + " ---");
    				
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
		ChooseDifficulty.getItems().addAll(Diff);
		BotNumber.setText("--- " +MAX + " ---");
		
		
	}
	
	 @FXML
	    void create(ActionEvent event) {	    	
	    		
	    		String Code="";
	    		for(int i=0;i<7;i++) {
	    			Code= Code + (int)Math.random()*10;
	    		}
	    		
	    		new SingleGame(MAX,ChooseDifficulty.getValue(),Giocatori,Code);
	    		
	    		
	    		
	    		
	    }


}
