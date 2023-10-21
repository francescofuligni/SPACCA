package application.MainMenu;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class CreditiController {
	
	private Stage stage;
	private Scene scene;
	private Parent root;

    @FXML
    private Button returnToMainMenuButton;

    @FXML
     public void returnToMainMenu(ActionEvent event) throws IOException {
    	stage = (Stage)(returnToMainMenuButton.getScene().getWindow());
		  //IMPORTANTE RICORDA IL ../ nell'URL DEL FXML
		  FXMLLoader Loader=new FXMLLoader(MainMenuController.class.getResource("MainMenu.fxml"));
		  root = (Parent) Loader.load();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.show();
    }

}
