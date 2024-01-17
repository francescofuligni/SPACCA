package application.Admin;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class GamesManagerController {
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	
    @FXML
    private Button createSingleGameButton;
    @FXML
    private Button createTournamentButton;
    @FXML
    private Button deleteGameButton;
    @FXML
    private Button backButton;

    @FXML
    public void createSingleGame(ActionEvent event) throws IOException {
    	// lancia la schermata di selezione del numero di giocatori
    	stage = (Stage)(createSingleGameButton.getScene().getWindow());
		  FXMLLoader Loader=new FXMLLoader(SelectPlayerNumberSGController.class.getResource("/application/Admin/SelectPlayerNumberSG.fxml"));
		  root = (Parent) Loader.load();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.show();
    }

	@FXML
    public void createTournament(ActionEvent event) throws IOException {
		// lancia la schermata di creazione del torneo
		stage = (Stage)(createTournamentButton.getScene().getWindow());
		  FXMLLoader Loader=new FXMLLoader(CreateTournamentController.class.getResource("/application/Admin/CreateTournament.fxml"));
		  root = (Parent) Loader.load();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.show();
    }
	
	@FXML
    public void deleteGame(ActionEvent event) throws IOException {
		// lancia la schermata per eliminare una partita
		stage = (Stage)(deleteGameButton.getScene().getWindow());
		  FXMLLoader Loader=new FXMLLoader(DeleteGameController.class.getResource("/application/Admin/DeleteGame.fxml"));
		  root = (Parent) Loader.load();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.show();
    }
	
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
}
