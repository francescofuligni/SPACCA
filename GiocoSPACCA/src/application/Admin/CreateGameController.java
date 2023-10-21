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
    private Button createSingleGameButton;

    @FXML
    private Button createTournamentButton;

    @FXML
    public void createSingleGame(ActionEvent event) throws IOException {
    	stage = (Stage)(createSingleGameButton.getScene().getWindow());
		  //IMPORTANTE RICORDA IL ../ nell'URL DEL FXML
		  FXMLLoader Loader=new FXMLLoader(SelectPlayerNumberSGController.class.getResource("../Admin/SelectPlayerNumberSG.fxml"));
		  root = (Parent) Loader.load();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.show();
    }


	@FXML
    public void createTournament(ActionEvent event) throws IOException {
		stage = (Stage)(createTournamentButton.getScene().getWindow());
		  //IMPORTANTE RICORDA IL ../ nell'URL DEL FXML
		  FXMLLoader Loader=new FXMLLoader(CreateTournamentController.class.getResource("../Admin/CreateTournament.fxml"));
		  root = (Parent) Loader.load();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.show();
    }

}
