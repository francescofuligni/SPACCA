package application.Admin;

import java.io.IOException;

import application.MainMenu.MainMenuController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class AdminMenuController {

	private Stage stage;
	private Scene scene;
	private Parent root;
	
    @FXML
    private Button logoutButton;
    @FXML
    private Button manageGamesButton;
    @FXML
    private Button managePlayersButton;

    @FXML
	public void logout(ActionEvent event) throws IOException {
    	// ritorna al MainMenu
    	stage = (Stage)(logoutButton.getScene().getWindow());
    	  FXMLLoader Loader=new FXMLLoader(MainMenuController.class.getResource("/application/MainMenu/MainMenu.fxml"));
		  root = (Parent) Loader.load();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.show();
    }

    @FXML
    public void manageGames(ActionEvent event) throws IOException {
    	// lancia la schermata di gestione delle partite
    	stage = (Stage)(manageGamesButton.getScene().getWindow());
    	  FXMLLoader Loader=new FXMLLoader(AdminMenuController.class.getResource("/application/Admin/GamesManager.fxml"));
		  root = (Parent) Loader.load();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.show();
    }

    @FXML
    public void managePlayers(ActionEvent event) throws IOException {
    	// lancia la schermata di gestione dei giocatori
    	stage = (Stage)(managePlayersButton.getScene().getWindow());
    	  FXMLLoader Loader=new FXMLLoader(AdminMenuController.class.getResource("/application/Admin/PlayersManager.fxml"));
		  root = (Parent) Loader.load();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.show();
    }
}
