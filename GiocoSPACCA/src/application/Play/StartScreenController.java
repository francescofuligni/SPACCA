package application.Play;

import java.io.IOException;

import application.Admin.CreateGameController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class StartScreenController {

	private String code = InsertCodeController.code;
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	@FXML
	private Label title;
	
	@FXML
	 public void start(MouseEvent event) throws IOException{
		stage = (Stage)(title.getScene().getWindow());
		  //IMPORTANTE RICORDA IL ../ nell'URL DEL FXML
		  FXMLLoader Loader=new FXMLLoader(StartScreenController.class.getResource("SingleGameBoard.fxml"));
		  if(code.startsWith("T"))
			  Loader.setController(new SimpleTournamentBoardController());
		  else 
			  Loader.setController(new SingleGameBoardController());
		  
		  root = (Parent) Loader.load();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.show();
	 }
	
}
