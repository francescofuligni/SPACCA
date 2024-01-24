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
	@FXML
	private Button scoreBoardButton;
	 
	@FXML
	public void enterGame() throws IOException {
		 // lancia la schermata per inserire il codice di accesso alla partita
		 stage = (Stage)(createGameButton.getScene().getWindow());
		  FXMLLoader Loader=new FXMLLoader(MainMenuController.class.getResource("/application/Play/InsertCode.fxml"));
		  root = (Parent) Loader.load();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.show();
	}
	 
	@FXML
	public void switchToRules(ActionEvent event) throws IOException {
		 // lancia la schermata delle regole
		 stage = (Stage)(createGameButton.getScene().getWindow());
		  FXMLLoader Loader=new FXMLLoader(MainMenuController.class.getResource("/application/MainMenu/Rules.fxml"));
		  root = (Parent) Loader.load();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.show();
	}
	
	@FXML
	public void switchToCredits(ActionEvent event) throws IOException {
		 // lancia la schermata dei crediti
		 stage = (Stage)(createGameButton.getScene().getWindow());
		  FXMLLoader Loader=new FXMLLoader(MainMenuController.class.getResource("/application/MainMenu/Crediti.fxml"));
		  root = (Parent) Loader.load();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.show();
	}
	 
	@FXML 
	public void switchToAdminLogin(ActionEvent event) throws IOException {
		 // lancia la schermata di accesso al menù admin
		 stage = (Stage)(createGameButton.getScene().getWindow());
		  FXMLLoader Loader=new FXMLLoader(MainMenuController.class.getResource("/application/Admin/LoginAdmin.fxml"));
		  root = (Parent) Loader.load();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.show();
	}
	
	@FXML
	public void switchToScoreBoard(ActionEvent event) throws IOException {
		 // lancia la schermata della classifica dei giocatori salvati
		 stage = (Stage)(createGameButton.getScene().getWindow());
		  FXMLLoader Loader=new FXMLLoader(MainMenuController.class.getResource("/application/MainMenu/ScoreBoard.fxml"));
		  root = (Parent) Loader.load();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.show();
	}
}
