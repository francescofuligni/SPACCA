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
	public void back(ActionEvent event) throws IOException {
		// ritorna alla gestione dei giocatori
    	stage = (Stage)(endButton.getScene().getWindow());
		  FXMLLoader Loader=new FXMLLoader(PlayersManagerController.class.getResource("/application/Admin/PlayersManager.fxml"));
		  root = (Parent) Loader.load();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.show();
    }
	
	@FXML
	public void createPlayer(ActionEvent event) {
		// crea un nuovo giocatore
		String username = usernameField.getText().trim();
		
		if(username==null || username.equals("") || username.toUpperCase().equals("ADMIN") || username.toUpperCase().startsWith("BOT")) {		// controllo accettabilità nome inserito
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Errore");
			alert.setHeaderText("Errore nella creazione del giocatore");
			alert.setContentText("Non è possibile creare un giocatore con questo nome");
			alert.showAndWait();
			message.setTextFill(Color.RED);
			message.setText("Giocatore non aggiunto");
			
		} else {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Crea giocatore");
			alert.setHeaderText("Stai per creare un nuovo giocatore");
			alert.setContentText("Sei sicuro di voler creare il giocatore \"" + username + "\"?");
			
			if(alert.showAndWait().get() == ButtonType.OK) {		// conferma creazione
				Player player = new Player(username);
				
				if(player.exists()) {								// controllo che il giocatore non sia già presente
					Alert alert2 = new Alert(AlertType.ERROR);
					alert2.setTitle("Errore");
					alert2.setHeaderText("Errore nella creazione del giocatore");
					alert2.setContentText("Giocatore con lo stesso nome gia esistente");
					alert2.showAndWait();
					message.setTextFill(Color.RED);
					message.setText("Giocatore non aggiunto");
				} else {
					player.memorize();
					message.setTextFill(Color.GREEN);
					message.setText("Giocatore \"" + username + "\" aggiunto correttamente");
				}
				
			} else {
				message.setTextFill(Color.RED);
				message.setText("Giocatore non aggiunto");
			}
		}
		usernameField.clear();		// reset field nome giocatore
    }
}
