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

public class CreateGameController {
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	
    @FXML
    private Button createSingleGameButton;

    @FXML
    private Button createTournamentButton;
    
    @FXML
    private Button managePlayersButton;
    
    @FXML
    private Button logoutButton;

    @FXML
    public void createSingleGame(ActionEvent event) throws IOException {
    	stage = (Stage)(createSingleGameButton.getScene().getWindow());
		 
		  FXMLLoader Loader=new FXMLLoader(SelectPlayerNumberSGController.class.getResource("/application/Admin/SelectPlayerNumberSG.fxml"));
		  root = (Parent) Loader.load();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.show();
    }


	@FXML
    public void createTournament(ActionEvent event) throws IOException {
		stage = (Stage)(createTournamentButton.getScene().getWindow());
		 
		  FXMLLoader Loader=new FXMLLoader(CreateTournamentController.class.getResource("/application/Admin/CreateTournament.fxml"));
		  root = (Parent) Loader.load();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.show();
    }
	
	@FXML
    public void managePlayers(ActionEvent event) throws IOException {
		stage = (Stage)(managePlayersButton.getScene().getWindow());
		 
		  FXMLLoader Loader=new FXMLLoader(PlayersManagerController.class.getResource("/application/Admin/PlayersManager.fxml"));
		  root = (Parent) Loader.load();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.show();
    }
	
<<<<<<< Updated upstream
	@FXML
	public void logout(ActionEvent event) throws IOException {
    	stage = (Stage)(logoutButton.getScene().getWindow());
		
    	  FXMLLoader Loader=new FXMLLoader(MainMenuController.class.getResource("/application/MainMenu/MainMenu.fxml"));
		  root = (Parent) Loader.load();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.show();
    }
=======
	
>>>>>>> Stashed changes
}
