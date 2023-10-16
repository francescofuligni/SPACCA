package application.Admin;

import java.io.IOException;
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
    private Button CreateSingleGameButton;

    @FXML
    private Button CreateTournamentButton;

    @FXML
    public void CreateSingleGame(ActionEvent event) throws IOException {
    	stage = (Stage)(CreateSingleGameButton.getScene().getWindow());
		  //IMPORTANTE RICORDA IL ../ nell'URL DEL FXML
		  FXMLLoader Loader=new FXMLLoader(CreateSingleGameController.class.getResource("../Admin/CreateSingleGame.fxml"));
		  root = (Parent) Loader.load();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.show();
    }


	@FXML
    public void CreateTournament(ActionEvent event) throws IOException {
		stage = (Stage)(CreateTournamentButton.getScene().getWindow());
		  //IMPORTANTE RICORDA IL ../ nell'URL DEL FXML
		  FXMLLoader Loader=new FXMLLoader(CreateTournamentController.class.getResource("../Admin/CreateTournament.fxml"));
		  root = (Parent) Loader.load();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.show();
    }

}
