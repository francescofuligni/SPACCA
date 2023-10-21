package application.Admin;

import java.io.IOException;

import application.MainMenu.MainMenuController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LoginAdminController {
	
	private Stage stage;
	 private Scene scene;
	 private Parent root;
	 	@FXML
	    private Button returnToMainMenuButton;
		@FXML
		private TextField adminName;
		@FXML
		private PasswordField adminPassword;
		@FXML 
		private Button confirmLogin;
		@FXML 
		private Text loginStatusText;
		
		public void login() throws IOException{
			if(adminName.getText().equals("admin")&&
				adminPassword.getText().equals("admin1234")) {
					
				//Se le credenziali sono giuste entra in Create game
				 stage = (Stage)(confirmLogin.getScene().getWindow());
				  //IMPORTANTE RICORDA IL ../ nell'URL DEL FXML
				  FXMLLoader Loader=new FXMLLoader(CreateGameController.class.getResource("../Admin/CreateGame.fxml"));
				  root = (Parent) Loader.load();
				  scene = new Scene(root);
				  stage.setScene(scene);
				  stage.show();
				
				
			}
			else {
				Alert loginError = new Alert(AlertType.ERROR);
				loginError.setTitle("ERRORE!");
				loginError.setContentText("NomeAdmin o Password errati, riprovare.");
				loginError.showAndWait();
				loginStatusText.setText("Password o User errati, riprovare");
				loginStatusText.setFill(Color.RED);
				
			}
			
		}
		

	    @FXML
	    void returnToMainMenu(ActionEvent event) throws IOException {
	    	stage = (Stage)(returnToMainMenuButton.getScene().getWindow());
			  //IMPORTANTE RICORDA IL ../ nell'URL DEL FXML
			  FXMLLoader Loader=new FXMLLoader(MainMenuController.class.getResource("MainMenu.fxml"));
			  root = (Parent) Loader.load();
			  scene = new Scene(root);
			  stage.setScene(scene);
			  stage.show();
	    }

}
