package application;
	


import java.io.File;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;


public class Main extends Application {
	

	

	public void start(Stage primaryStage) throws Exception {
		try {
			
			 /*Utilizza Main.class e recupera le risorse 
			 * dalla cartella direttamente (non mettere 
			 * tutto il percorso ma solo quelli successivi ad application)
			 */
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("MainMenu/MainMenu.fxml"));
	        AnchorPane rootLayout = (AnchorPane) loader.load();
	        primaryStage.setTitle("S.P.A.C.C.A. the GAME");
	        primaryStage.setResizable(false);
	        primaryStage.setScene(new Scene (rootLayout));
	        //setto icona applicazione e coomportamento finestra
	        Image icon= new Image("file:Images/icona.png");
	        primaryStage.getIcons().add(icon);
	        primaryStage.setFullScreen(false);
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
