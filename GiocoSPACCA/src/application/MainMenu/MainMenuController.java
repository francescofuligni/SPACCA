package application.MainMenu;



import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MainMenuController {
	
	private Stage stage;
	 private Scene scene;
	 private Parent root;
	 
	 @FXML
	    private Button playButton;
	 @FXML
	    private Button creditiButton;
	 @FXML
	    private Button createGameButton;
	 @FXML
	 	private Button rulesButton;
	 
	 /*		METODO ENTER GAME--> richiede línserimento del codice partita precedentemente creato da admin e permette JOIN
	  * 	METODO SWITCHTOCREDITS --> rimanda a scena con crediti
	  * 	METODO SWITCHTOADMINLOGIN --> permette il Login dell'admin
	  * */
	 
	 @FXML
	public void enterGame() {
	 } 
	 
	 @FXML
	    void switchToRules(ActionEvent event) throws IOException {	   
		 stage = (Stage)(createGameButton.getScene().getWindow());
		  //IMPORTANTE RICORDA IL ../ nell'URL DEL FXML
		  FXMLLoader Loader=new FXMLLoader(MainMenuController.class.getResource("../MainMenu/Rules.fxml"));
		  root = (Parent) Loader.load();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.show();
	    }
	
	 @FXML
	   public void switchToCredits(ActionEvent event) throws IOException {
		 stage = (Stage)(createGameButton.getScene().getWindow());
		  //IMPORTANTE RICORDA IL ../ nell'URL DEL FXML
		  FXMLLoader Loader=new FXMLLoader(MainMenuController.class.getResource("../MainMenu/Crediti.fxml"));
		  root = (Parent) Loader.load();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.show();
	    }

	 
	@FXML 
	public void switchToAdminLogin(ActionEvent event) throws Exception {
		
		  stage = (Stage)(createGameButton.getScene().getWindow());
		  //IMPORTANTE RICORDA IL ../ nell'URL DEL FXML
		  FXMLLoader Loader=new FXMLLoader(MainMenuController.class.getResource("../Admin/LoginAdmin.fxml"));
		  root = (Parent) Loader.load();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.show();
		
		
	}
	
}
