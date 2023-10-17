package application.Admin;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

public class SelectPlayerNumberSGController {
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	public static int PlayerNumber;
	
	@FXML
    private Button Button2;

    @FXML
    private Button Button3;

    @FXML
    private Button Button4;
    @FXML
    void set2Players(ActionEvent event) throws IOException {
    	PlayerNumber=2;
    	stage = (Stage)(Button2.getScene().getWindow());
		  //IMPORTANTE RICORDA IL ../ nell'URL DEL FXML
		  FXMLLoader Loader=new FXMLLoader(CreateSingleGameController.class.getResource("../Admin/CreateSingleGame.fxml"));
		  root = (Parent) Loader.load();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.show();
    }

    @FXML
    void set3Players(ActionEvent event) throws IOException {
    	PlayerNumber=3;
    	stage = (Stage)(Button3.getScene().getWindow());
		  //IMPORTANTE RICORDA IL ../ nell'URL DEL FXML
		  FXMLLoader Loader=new FXMLLoader(CreateSingleGameController.class.getResource("../Admin/CreateSingleGame.fxml"));
		  root = (Parent) Loader.load();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.show();
    }

    @FXML
    void set4Players(ActionEvent event) throws IOException {
    	PlayerNumber=4;
    	stage = (Stage)(Button4.getScene().getWindow());
		  //IMPORTANTE RICORDA IL ../ nell'URL DEL FXML
		  FXMLLoader Loader=new FXMLLoader(CreateSingleGameController.class.getResource("../Admin/CreateSingleGame.fxml"));
		  root = (Parent) Loader.load();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.show();
    }
    
    
    
}
