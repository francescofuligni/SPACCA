package application.Admin;

import java.io.IOException;

import application.Player.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.paint.Color;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CreatePlayerController {
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	@FXML
	private Label message;
	
	@FXML
	private Button endButton;
	
	@FXML
	private Button createButton;
	
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
	public void createPlayer(ActionEvent event) throws IOException {
		String username = usernameField.getText();
		
		if(username==null || username.trim().equals("")) {			// controllo che non abbia nome vuoto
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Errore");
			alert.setHeaderText("Errore nella creazione del giocatore");
			alert.setContentText("Non è possibile creare un giocatore con nome vuoto");
			alert.showAndWait();
			message.setTextFill(Color.RED);
			message.setText("Giocatore non aggiunto");
			
		} else if(username.contains(",")){
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Errore");
			alert.setHeaderText("Errore nella creazione del giocatore");
			alert.setContentText("Non è possibile creare un giocatore con una virgola nel nome");
			alert.showAndWait();
			message.setTextFill(Color.RED);
			message.setText("Giocatore non aggiunto");
		}else {
			Player player = new Player(username.trim());
			if(!player.exists()) {								//controllo con metodo exists se é gia presente
				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("Crea giocatore");
				alert.setHeaderText("Stai creando un nuovo giocatore");
				alert.setContentText("Sei sicuro di voler creare il giocatore \"" + username.trim() + "\"?");
			
				if(alert.showAndWait().get() == ButtonType.OK) {
				
					player.memorize();
				
					message.setTextFill(Color.GREEN);
					message.setText("Giocatore \"" + username.trim() + "\" aggiunto correttamente");
				} else {
					message.setTextFill(Color.RED);
					message.setText("Giocatore non aggiunto");
				}
			}
			else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Errore");
				alert.setHeaderText("Errore nella creazione del giocatore");
				alert.setContentText("Giocatore giá presente sul registro");
				alert.showAndWait();
				message.setTextFill(Color.RED);
				message.setText("Giocatore non aggiunto");
				
			}
		}
		usernameField.setText(null);
    }
}
