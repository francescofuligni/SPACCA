package application.Play;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import application.Admin.CreateGameController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class InsertCodeController {

	private Stage stage;
	private Scene scene;
	private Parent root;
	
    @FXML
    private TextField codeField;

    @FXML
    private Button confirmEnter;

    @FXML
    private Text enterStatusText;

    @FXML
    private Button returnToMainMenuButton;

    @FXML
    void enterGame(ActionEvent event) {
    	
    	 Path pathToFile = Paths.get("./GiocoSPACCA/Informazioni_Partite/" + codeField.getText().trim().toUpperCase() + ".csv");
    	 File f=new File(pathToFile.toString());
    	 
    	 if(f.exists()) {
    		 //se il codice esiste dobbiamo cambiare schermata all'fxml della board e caricarla con i dati di quella partita
    	 }
    	 else {
    		Alert selectionAlert = new Alert(AlertType.ERROR);
     		selectionAlert.setTitle("ERRORE!");
     		selectionAlert.setContentText("CODICE PARTITA NON RICONOSCIUTO");
     		selectionAlert.showAndWait();
     		
     		enterStatusText.setText("Codice inserito non riconosciuto, riprovare");
     		enterStatusText.setFill(Color.RED);
    	 }
    	
    }

    @FXML
    void returnToMainMenu(ActionEvent event) throws IOException {
    	stage = (Stage)(returnToMainMenuButton.getScene().getWindow());
		  //IMPORTANTE RICORDA IL ../ nell'URL DEL FXML
		  FXMLLoader Loader=new FXMLLoader(CreateGameController.class.getResource("../MainMenu/MainMenu.fxml"));
		  root = (Parent) Loader.load();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.show();
    }
    
    

}