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
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	@FXML
	private Button backButton;
	@FXML
	private Button createPlayerButton;
	@FXML
	private Button deletePlayerButton;
	
	@FXML
	public void back(ActionEvent event) throws IOException {
		// ritorna all'AdminMenu
    	stage = (Stage)(backButton.getScene().getWindow());
		  FXMLLoader Loader=new FXMLLoader(AdminMenuController.class.getResource("/application/Admin/AdminMenu.fxml"));
		  root = (Parent) Loader.load();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.show();
    }
	
	@FXML
	public void createPlayer(ActionEvent event) throws IOException {
		// lancia la schermata per la creazione di un nuovo giocatore
    	stage = (Stage)(createPlayerButton.getScene().getWindow());
		  FXMLLoader Loader = new FXMLLoader(CreatePlayerController.class.getResource("/application/Admin/CreatePlayer.fxml"));
		  root = (Parent) Loader.load();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.show();
    }
	
	@FXML
	public void deletePlayer(ActionEvent event) throws IOException {
		// lancia la schermata per l'eliminazione di un giocatore
    	stage = (Stage)(deletePlayerButton.getScene().getWindow());
		  FXMLLoader Loader = new FXMLLoader(DeletePlayerController.class.getResource("/application/Admin/DeletePlayer.fxml"));
		  root = (Parent) Loader.load();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.show();
    }
}
