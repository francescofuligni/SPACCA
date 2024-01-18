package application.Admin;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

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

public class DeleteGameController {

	private Stage stage;
	private Scene scene;
	private Parent root;
	
	private File toDelete;
	
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
    	 
    	 if(codeField.getText().toUpperCase().startsWith("T")) {
    		 toDelete = new File(Paths.get("./GiocoSPACCA//Informazioni_Partite/" + codeField.getText().toUpperCase()).toString());
    		 if(toDelete.exists()) {
	       		 Alert alert = new Alert(AlertType.CONFIRMATION);
	   				alert.setTitle("Elimina partita");
	   				alert.setHeaderText("Stai eliminando un partita");
	   				alert.setContentText("Sei sicuro di voler eliminare la partita \"" + codeField.getText().trim() + "\"?");
	   				
	   				if(alert.showAndWait().get() == ButtonType.OK) {
	   					toDelete = new File(Paths.get("./GiocoSPACCA/Informazioni_Partite/" + codeField.getText().toUpperCase() + "/semifinale1.csv").toString());
	   		    		if(toDelete.exists()) {
	   		    			toDelete.delete();
	   		    		}
	   		    		
	   		    		toDelete = new File(Paths.get("./GiocoSPACCA/Informazioni_Partite/" + codeField.getText().toUpperCase() + "/semifinale2.csv").toString());
	   		    		if(toDelete.exists()) {
	   		    			toDelete.delete();
	   		    		}
	   		    		
	   		    		toDelete = new File(Paths.get("./GiocoSPACCA/Informazioni_Partite/" + codeField.getText().toUpperCase() + "/finale.csv").toString());
	   		    		if(toDelete.exists()) {
	   		    			toDelete.delete();
	   		    		}
	   		    		
	   		    		//a questo punto la diretory dovrebbe essere vuota quindi l'eliminazione va a buon fine
	   		    		toDelete = new File(Paths.get("./GiocoSPACCA/Informazioni_Partite/" + codeField.getText().toUpperCase()).toString());
	   		    		toDelete.delete();
	   		    		
	   					message.setTextFill(Color.GREEN);
	   					message.setText("Partita \"" + codeField.getText().trim() + "\" eliminata correttamente");
	   				}
	   				else{
	   					message.setTextFill(Color.RED);
	   					message.setText("Partita non eliminata");
	   				}
    		 }
    		 else {
	    			Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Errore");
					alert.setHeaderText("Errore nell'eliminazione della partita");
					alert.showAndWait();
					message.setTextFill(Color.RED);
					message.setText("Partita non trovata");
	    		}
	    	}
    	 
	    	else {
	    		toDelete = new File(Paths.get("./GiocoSPACCA/Informazioni_Partite/" + codeField.getText().toUpperCase() + ".csv").toString());
	    		
	    		if(toDelete.exists()) {
	       		 Alert alert = new Alert(AlertType.CONFIRMATION);
	   				alert.setTitle("Elimina partita");
	   				alert.setHeaderText("Stai eliminando un partita");
	   				alert.setContentText("Sei sicuro di voler eliminare la partita \"" + codeField.getText().trim() + "\"?");
	   				
	   				if(alert.showAndWait().get() == ButtonType.OK) {
	   					toDelete.delete(); //in caso non sia torneo semplice allora si tratta di un solo file 
	   					message.setTextFill(Color.GREEN);
	   					message.setText("Partita \"" + codeField.getText().trim() + "\" eliminata correttamente");
	   				}else{
	   					message.setTextFill(Color.RED);
	   					message.setText("Partita non eliminata");
	   				}
	       		}
	    		else {
	    			Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Errore");
					alert.setHeaderText("Errore nell'eliminazione della partita");
					alert.showAndWait();
					message.setTextFill(Color.RED);
					message.setText("Partita non trovata");
	    		}
	    	}
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
