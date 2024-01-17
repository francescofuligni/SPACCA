package application.Admin;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class DeleteGameController {

	private Stage stage;
	private Scene scene;
	private Parent root;
	
    @FXML
    private TextField codeField;
    @FXML
    private Button deleteButton;
    @FXML
    private Button endButton;
    @FXML
    private Label message;

    @FXML
    public void deleteGame(ActionEvent event) {
    	// TODO
    }

    @FXML
    public void back(ActionEvent event) throws IOException {
		// ritorna alla gestione partite
    	stage = (Stage)(endButton.getScene().getWindow());
		  FXMLLoader Loader=new FXMLLoader(GamesManagerController.class.getResource("/application/Admin/GamesManager.fxml"));
		  root = (Parent) Loader.load();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.show();
    }
}
