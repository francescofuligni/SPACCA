package application;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class Main extends Application {
	
	// flag per bot
	public static boolean message = false;		// true se viene mostrato un messaggio (alert)

	public void start(Stage primaryStage) {
		try {
			fileCheck();	// controlla la presenza dei registro giocatori
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/MainMenu/MainMenu.fxml"));
	        AnchorPane rootLayout = (AnchorPane) loader.load();
	        primaryStage.setTitle("S.P.A.C.C.A. the GAME");
	        primaryStage.setResizable(false);
	        primaryStage.setScene(new Scene (rootLayout));
	        // imposta icona applicazione e comportamento finestra
	        Image icon= new Image("file:Images/icona.png");
	        primaryStage.getIcons().add(icon);
	        primaryStage.setFullScreen(false);
			primaryStage.show();
			
			primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {		// chiusura dell'applicazione
				@Override
				public void handle(WindowEvent event) {
					event.consume();			// chiusura solo in caso di conferma
					closeApp(primaryStage);		// messaggio di chiusura e chiusura
				}
			});
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void fileCheck() {
		// se non esiste il registro giocatori, lo crea
		Path pathToFile = Paths.get("./GiocoSPACCA/Informazioni_Partite/PLAYERS_REGISTER.csv");
		File f=new File(pathToFile.toString());
		if(!f.exists())
	        try {
	            Files.createDirectories(pathToFile.getParent());
	            Files.createFile(pathToFile);
	            FileWriter fw = new FileWriter(f.getAbsolutePath(), true);					// nel nuovo registro giocatori scrive ADMIN e tre utenti ospiti di default
				fw.write( "ADMIN,0,0\n"
						+ "OSPITE1,0,0\n"
						+ "OSPITE2,0,0\n"
						+ "OSPITE3,0,0\n");
				fw.flush();
				fw.close();
	        } catch (IOException e) {
	            System.out.println(e);
	        }
		
	}
	
	private void closeApp(Stage s) {
		// imposta messaggio di chiusura dell'applicazione
		message = true;
		Alert exitAlert = new Alert(AlertType.CONFIRMATION);
		exitAlert.setTitle("TERMINA");
		exitAlert.setHeaderText("Vuoi terminare l'applicazione?");
		exitAlert.setContentText("Se sei in partita perderai eventuali progressi.");
		
		ImageView icon=new ImageView(this.getClass().getResource("/icona.png").toString());	// icona del gioco
		icon.setFitWidth(50);
		icon.setPreserveRatio(true);
		exitAlert.setGraphic(icon); 	// imposta l'icona del gioco al posto dell'immagine predefinita
		
		if(exitAlert.showAndWait().get() == ButtonType.OK)
			s.close();
		message = false;
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
