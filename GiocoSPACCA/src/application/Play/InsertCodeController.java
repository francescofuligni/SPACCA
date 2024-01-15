package application.Play;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import application.Admin.CreateGameController;
import application.Admin.LoginAdminController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class InsertCodeController {

	private Stage stage;
	private Scene scene;
	private Parent root;
	
	public static File file;
	public static String code;
	public static Path pathToGame;
	
    @FXML
    private TextField codeField;

    @FXML
    private Button confirmEnter;

    @FXML
    private Text enterStatusText;

    @FXML
    private Button returnToMainMenuButton;
    
    @FXML
    private Label loginAdminLabel;
    

    @FXML
    public void enterGame(ActionEvent event) throws IOException {
    	
    	// TODO controllo sul codice --> se inizia con T cerca directory, altrimenti cerca file
    	// settare diverso controller a seconda del tipo di partita
    	code = codeField.getText().trim().toUpperCase();
    	
    	if(code.startsWith("T")) {
    		pathToGame = Paths.get("./GiocoSPACCA/Informazioni_Partite/" + code); 
    		
    		if(Files.exists(pathToGame)) {
    			
    			stage = (Stage)(returnToMainMenuButton.getScene().getWindow());
    	    	FXMLLoader Loader=new FXMLLoader(CreateGameController.class.getResource("../Play/SingleGameBoard.fxml"));
    	    	//Loader.setController(new SimpleTournamentBoardController());
    	    	root = (Parent) Loader.load();
    	    	scene = new Scene(root);
    	    	stage.setScene(scene);
    	    	stage.show();
    			
    		}
    		else {
    			Alert selectionAlert = new Alert(AlertType.ERROR);
         		selectionAlert.setTitle("ERRORE!");
         		selectionAlert.setContentText("CODICE TORNEO NON RICONOSCIUTO");
         		selectionAlert.showAndWait();
         		
         		enterStatusText.setText("Codice inserito non riconosciuto, riprovare");
         		enterStatusText.setFill(Color.RED);
    		}
    	}
    	else {
	    	pathToGame = Paths.get("./GiocoSPACCA/Informazioni_Partite/" + code + ".csv"); 
	    	file = new File(pathToGame.toString());
	    	if(file.exists()) {
	    		//se il codice esiste dobbiamo cambiare schermata all'fxml della board e caricarla con i dati di quella partit
	    		 
		    	stage = (Stage)(returnToMainMenuButton.getScene().getWindow());
		    	FXMLLoader Loader=new FXMLLoader(CreateGameController.class.getResource("../Play/SingleGameBoard.fxml"));
		    	Loader.setController(new SingleGameBoardController());
		    	root = (Parent) Loader.load();
		    	scene = new Scene(root);
		    	stage.setScene(scene);
		    	stage.show();
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
    	
    }

    @FXML
    public void returnToMainMenu(ActionEvent event) throws IOException {
    	stage = (Stage)(returnToMainMenuButton.getScene().getWindow());
		  //IMPORTANTE RICORDA IL ../ nell'URL DEL FXML
		  FXMLLoader Loader=new FXMLLoader(CreateGameController.class.getResource("../MainMenu/MainMenu.fxml"));
		  root = (Parent) Loader.load();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.show();
    }
    
    @FXML
    public void loginAdmin(MouseEvent event) throws IOException {
    	stage = (Stage)(loginAdminLabel.getScene().getWindow());
		  //IMPORTANTE RICORDA IL ../ nell'URL DEL FXML
		  FXMLLoader Loader=new FXMLLoader(LoginAdminController.class.getResource("../Admin/LoginAdmin.fxml"));
		  root = (Parent) Loader.load();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.show();
    }
    
    @FXML
    public void setColorGrey(MouseEvent event) {
    	loginAdminLabel.setTextFill(Color.GREY);
    }
    @FXML
    public void setColorWhite(MouseEvent event) {
    	loginAdminLabel.setTextFill(Color.WHITE);
    }
}