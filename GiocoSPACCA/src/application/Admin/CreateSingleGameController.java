package application.Admin;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CreateSingleGameController implements Initializable {
	
	private Integer[] MaxPlayer= {2,3,4};
	private String[] Diff= {"Facile","Difficile"};
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	
    @FXML
    private Button AddPlayerButton;
    
    @FXML
    private ChoiceBox<String> SceltaDifficoltaBot;

    @FXML
    private ChoiceBox<Integer> SceltaNumeroGiocatori;

    @FXML
    private TextField SpazioUser;

    @FXML
    private Button ReturnToCreateGameButton;
    
    @FXML
    public void AddPlayer(ActionEvent event) {
    	
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
		SceltaNumeroGiocatori.getItems().addAll(MaxPlayer);
		
	}


}
