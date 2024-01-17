package application.Admin;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class PlayersManagerController {
	
	@FXML
	private Button backButton;
	
	@FXML
	private Button newPlayerButton;
	
	@FXML
	private Button deletePlayerButton;
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	@FXML
	public void back(ActionEvent event) throws IOException {
    	stage = (Stage)(backButton.getScene().getWindow());
		
		  FXMLLoader Loader=new FXMLLoader(CreateGameController.class.getResource("/application/Admin/CreateGame.fxml"));
		  root = (Parent) Loader.load();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.show();
    }
	
	@FXML
	public void newPlayer(ActionEvent event) throws IOException {
    	stage = (Stage)(newPlayerButton.getScene().getWindow());
		
		  FXMLLoader Loader = new FXMLLoader(CreatePlayerController.class.getResource("/application/Admin/CreatePlayer.fxml"));
		  root = (Parent) Loader.load();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.show();
    }
	
	@FXML
	public void deletePlayer(ActionEvent event) throws IOException {
    	stage = (Stage)(deletePlayerButton.getScene().getWindow());
		 
		  FXMLLoader Loader = new FXMLLoader(DeletePlayerController.class.getResource("/application/Admin/DeletePlayer.fxml"));
		  root = (Parent) Loader.load();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.show();
    }
}
