package application.Admin;

import java.io.IOException;
import application.Player.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class DeletePlayerController {
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	@FXML
	private Label message;
	
	@FXML
	private Button endButton;
	
	@FXML
	private Button deleteButton;
	
	@FXML
	private TextField usernameField;
	
	@FXML
	public void returnToCreateGame(ActionEvent event) throws IOException {
    	stage = (Stage)(endButton.getScene().getWindow());
		  //IMPORTANTE RICORDA IL ../ nell'URL DEL FXML
		  FXMLLoader Loader=new FXMLLoader(CreateGameController.class.getResource("../Admin/CreateGame.fxml"));
		  root = (Parent) Loader.load();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.show();
    }
	
	@FXML
	public void deletePlayer(ActionEvent event) throws IOException {
    	String username = usernameField.getText();
    	
    	if(username==null || username.trim().equals("")) {				
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Errore");
			alert.setHeaderText("Errore nell'eliminazione del giocatore");
			alert.setContentText("Non esistono giocatori con nome vuoto");
			alert.showAndWait();
			message.setTextFill(Color.RED);
			message.setText("Giocatore non trovato");
			
		 }  else{
			 Player toDelete=new Player(username.trim());
			 
			 
			 
			 if(toDelete.exists()) {
				 Alert alert = new Alert(AlertType.CONFIRMATION);
					alert.setTitle("Elimina giocatore");
					alert.setHeaderText("Stai eliminando un giocatore");
					alert.setContentText("Sei sicuro di voler eliminare il giocatore \"" + username.trim() + "\"?");
					
					if(alert.showAndWait().get() == ButtonType.OK) {
						toDelete.forget();
						
						message.setTextFill(Color.GREEN);
						message.setText("Giocatore \"" + username.trim() + "\" eliminato correttamente");
					}else{
						
						message.setTextFill(Color.RED);
						message.setText("Giocatore non eliminato");
					}
				}else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Errore");
				alert.setHeaderText("Errore nell'eliminazione del giocatore");
				alert.setContentText("Non esistono giocatori con questo nome");
				alert.showAndWait();
				message.setTextFill(Color.RED);
				message.setText("Giocatore non trovato");
		} 
		
    }
    	usernameField.setText(null);
	}
}
