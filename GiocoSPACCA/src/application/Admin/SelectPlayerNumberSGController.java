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
    private Button button2;

    @FXML
    private Button button3;

    @FXML
    private Button button4;
    @FXML
    void set2Players(ActionEvent event) throws IOException {
    	playerNumber=2;
    	stage = (Stage)(button2.getScene().getWindow());
		  //IMPORTANTE RICORDA IL ../ nell'URL DEL FXML
		  FXMLLoader Loader=new FXMLLoader(CreateSingleGameController.class.getResource("../Admin/CreateSingleGame.fxml"));
		  root = (Parent) Loader.load();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.show();
    }

    @FXML
    void set3Players(ActionEvent event) throws IOException {
    	playerNumber=3;
    	stage = (Stage)(button3.getScene().getWindow());
		  //IMPORTANTE RICORDA IL ../ nell'URL DEL FXML
		  FXMLLoader Loader=new FXMLLoader(CreateSingleGameController.class.getResource("../Admin/CreateSingleGame.fxml"));
		  root = (Parent) Loader.load();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.show();
    }

    @FXML
    void set4Players(ActionEvent event) throws IOException {
    	playerNumber=4;
    	stage = (Stage)(button4.getScene().getWindow());
		  //IMPORTANTE RICORDA IL ../ nell'URL DEL FXML
		  FXMLLoader Loader=new FXMLLoader(CreateSingleGameController.class.getResource("../Admin/CreateSingleGame.fxml"));
		  root = (Parent) Loader.load();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.show();
    }
    
    
    
}
