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
	
	// credenziali admin (non modificabili)
	public final static String adminPassword = "admin1234";
	public final static String adminName = "admin";
	
	private Stage stage;
	private Scene scene;
	private Parent root;

 	@FXML
    private Button returnToMainMenuButton;
	@FXML
	private TextField adminNameField;
	@FXML
	private PasswordField adminPasswordField;
	@FXML 
	private Button confirmLogin;
	@FXML 
	private Text loginStatusText;
	
	public void login() throws IOException{
		// effettua l'accesso al menù admin
		if(adminNameField.getText().equals(adminName) && adminPasswordField.getText().equals(adminPassword)) {	// controllo credenziali
				
			// lancia l'AdminMenu
			stage = (Stage)(confirmLogin.getScene().getWindow());
			  FXMLLoader Loader=new FXMLLoader(AdminMenuController.class.getResource("/application/Admin/AdminMenu.fxml"));
			  root = (Parent) Loader.load();
			  scene = new Scene(root);
			  stage.setScene(scene);
			  stage.show();	
			  
		} else {
			// errore credenziali errate
			Alert loginError = new Alert(AlertType.ERROR);
			loginError.setTitle("ERRORE!");
			loginError.setContentText("Nome admin o password errati: riprovare.");
			loginStatusText.setText("Nome admin o password errati: riprovare");
			loginStatusText.setFill(Color.RED);
			loginError.showAndWait();
			
			adminNameField.clear();			// reset field nome utente
			adminPasswordField.clear();		// reset field password
		}	
	}

    @FXML
    public void returnToMainMenu(ActionEvent event) throws IOException {
    	// torna alla schermata iniziale (lancia MainMenu)
    	stage = (Stage)(returnToMainMenuButton.getScene().getWindow());
		  FXMLLoader Loader=new FXMLLoader(MainMenuController.class.getResource("/application/MainMenu/MainMenu.fxml"));
		  root = (Parent) Loader.load();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.show();
    }
}
