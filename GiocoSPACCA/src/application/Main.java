package application;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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
			fileCheck();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/MainMenu/MainMenu.fxml"));
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
	
	private void fileCheck() {
		Path pathToFile = Paths.get("./GiocoSPACCA/Informazioni_Partite/PLAYERS_REGISTER.csv");
		File f=new File(pathToFile.toString());
		if(!f.exists())
	        try {
	            Files.createDirectories(pathToFile.getParent());
	            Files.createFile(pathToFile);
	            FileWriter fw = new FileWriter(f.getAbsolutePath(), true);
				fw.write( "ADMIN,0,0\n" );
				fw.flush();
				fw.close();
	        } catch (IOException e) {
	            System.out.println(e);
	        }
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}
