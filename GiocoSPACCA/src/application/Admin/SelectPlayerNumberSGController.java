package application.Admin;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class SelectPlayerNumberSGController {
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	public static int playerNumber;
	
	@FXML
	private Button backButton;
	@FXML
	private Button button2;
	@FXML
	private Button button3;
	@FXML
	private Button button4;
    
    @FXML
    public void set2Players(ActionEvent event) throws IOException {
    	// lancia la schermata di creazione nella partita singola con 2 giocatori
    	playerNumber=2;
    	stage = (Stage)(button2.getScene().getWindow());
    	launchCreateSingleGame(event);
    }

    @FXML
    public void set3Players(ActionEvent event) throws IOException {
    	// lancia la schermata di creazione nella partita singola con 3 giocatori
    	playerNumber=3;
    	stage = (Stage)(button3.getScene().getWindow());
		launchCreateSingleGame(event);
    }
    
    @FXML
    public void set4Players(ActionEvent event) throws IOException {
    	// lancia la schermata di creazione nella partita singola con 4 giocatori
    	playerNumber=4;
    	stage = (Stage)(button4.getScene().getWindow());
    	launchCreateSingleGame(event);
    }
    
    @FXML
    public void back(ActionEvent event) throws IOException {
    	// ritorna alla gestione partite
    	stage = (Stage)(backButton.getScene().getWindow());
		  FXMLLoader Loader=new FXMLLoader(GamesManagerController.class.getResource("/application/Admin/GamesManager.fxml"));
		  root = (Parent) Loader.load();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.show();
    }
    
    private void launchCreateSingleGame(ActionEvent event) throws IOException {
		  FXMLLoader Loader=new FXMLLoader(CreateSingleGameController.class.getResource("/application/Admin/CreateSingleGame.fxml"));
		  root = (Parent) Loader.load();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.show();
    }
}
