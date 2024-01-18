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
	public void deletePlayer(ActionEvent event) throws IOException {
		
    	String username = usernameField.getText().trim();
    	
    	if(username==null || username.equals("")) {				
			eliminationError("Non esistono giocatori con nome vuoto");
    	
    	} else if (username.toUpperCase().equals("ADMIN")) {
			eliminationError("Non puoi rimuovere l'account amministratore");
		
    	} else {
			Player toDelete=new Player(username);
			
			if(!toDelete.exists()) {
				eliminationError("Giocatore non trovato");
			} else if(toDelete.isInGame()) {
				eliminationError("Il giocatore ha una partita in corso");			// impedisce di rimuovere giocatori in partita
			} else {
				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("Elimina giocatore");
				alert.setHeaderText("Stai per eliminare un giocatore");
				alert.setContentText("Sei sicuro di voler eliminare il giocatore \"" + username + "\"?");
				
				if(alert.showAndWait().get() == ButtonType.OK) {
					toDelete.delete();
					message.setTextFill(Color.GREEN);
					message.setText("Giocatore \"" + username + "\" eliminato correttamente");
				} else {
					message.setTextFill(Color.RED);
					message.setText("Giocatore non eliminato");
				}
			}
		}
    	usernameField.setText(null);
	}
	
	private void eliminationError(String text) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Errore");
		alert.setHeaderText("Eliminazione fallita");
		alert.setContentText(text);
		alert.showAndWait();
		message.setTextFill(Color.RED);
		message.setText("Eliminazione fallita");
	}
}
