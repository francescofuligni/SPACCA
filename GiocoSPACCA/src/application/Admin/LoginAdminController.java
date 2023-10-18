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
	    private Button ReturnToMainMenuButton;
		@FXML
		private TextField AdminName;
		@FXML
		private PasswordField AdminPassword;
		@FXML 
		private Button ConfirmLogin;
		@FXML 
		private Text LoginStatusText;
		
		public void Login() throws IOException{
			if(AdminName.getText().equals("admin")&&
				AdminPassword.getText().equals("admin1234")) {
					
				//Se le credenziali sono giuste entra in Create game
				 stage = (Stage)(ConfirmLogin.getScene().getWindow());
				  //IMPORTANTE RICORDA IL ../ nell'URL DEL FXML
				  FXMLLoader Loader=new FXMLLoader(CreateGameController.class.getResource("../Admin/CreateGame.fxml"));
				  root = (Parent) Loader.load();
				  scene = new Scene(root);
				  stage.setScene(scene);
				  stage.show();
				
				
			}
			else {
				Alert LoginError = new Alert(AlertType.ERROR);
				LoginError.setTitle("ERRORE!");
				LoginError.setContentText("NomeAdmin o Password errati, riprovare.");
				LoginError.showAndWait();
				LoginStatusText.setText("Password o User errati, riprovare");
				LoginStatusText.setFill(Color.RED);
				
			}
			
		}
		

	    @FXML
	    void returnToMainMenu(ActionEvent event) throws IOException {
	    	stage = (Stage)(ReturnToMainMenuButton.getScene().getWindow());
			  //IMPORTANTE RICORDA IL ../ nell'URL DEL FXML
			  FXMLLoader Loader=new FXMLLoader(MainMenuController.class.getResource("MainMenu.fxml"));
			  root = (Parent) Loader.load();
			  scene = new Scene(root);
			  stage.setScene(scene);
			  stage.show();
	    }

}
