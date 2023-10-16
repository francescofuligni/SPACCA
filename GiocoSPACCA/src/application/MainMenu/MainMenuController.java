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
	    private Button CreditiButton;
	 
	 @FXML
	    private Button CreateGameButton;
	
	 @FXML
	public void EnterGame() {
	 } 
	 
	 @FXML
	   public void switchToCredits(ActionEvent event) throws IOException {
		 stage = (Stage)(CreateGameButton.getScene().getWindow());
		  //IMPORTANTE RICORDA IL ../ nell'URL DEL FXML
		  FXMLLoader Loader=new FXMLLoader(MainMenuController.class.getResource("Crediti.fxml"));
		  root = (Parent) Loader.load();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.show();
	    }

	 
	@FXML 
	public void switchToAdminLogin(ActionEvent event) throws Exception {
		
		  stage = (Stage)(CreateGameButton.getScene().getWindow());
		  //IMPORTANTE RICORDA IL ../ nell'URL DEL FXML
		  FXMLLoader Loader=new FXMLLoader(MainMenuController.class.getResource("../Admin/LoginAdmin.fxml"));
		  root = (Parent) Loader.load();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.show();
		
		
	}
	
}
