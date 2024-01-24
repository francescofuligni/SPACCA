package application.Play;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Games.*;
import application.Player.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class StartScreenController implements Initializable {

	private final String code = InsertCodeController.code;
	private Stage stage;
	private Scene scene;
	private Parent root;
	private Game game;
	private SimpleTournament t;
	
	@FXML
	private Label title;
	@FXML
	private Label playersLabel;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		// imposta titolo schermata con il nome della partita avviata
		if(code.startsWith("T")) {
			// SIMPLE TOURNAMENT
			t = new SimpleTournament(InsertCodeController.pathToGame);
			game = t.getCurrentGame();
			if(t.getCurrentGame().code.endsWith("1"))
				title.setText("SEMIFINALE 1");
			else if(t.getCurrentGame().code.endsWith("2"))
				title.setText("SEMIFINALE 2");
			else
				title.setText("FINALE");
		} else if(code.startsWith("L")){
			// LAST MAN STANDING
			game = new LastManStanding(InsertCodeController.pathToGame);
			title.setText("ROUND " + (5-game.getPlayers().size()));
		} else {
			// SINGLE GAME
			game = new SingleGame(InsertCodeController.pathToGame);
			title.setText("COMBATTI!");
		}
		
		// mostra i partecipanti alla partita avviata
		String playersList = "";
		for(Player p : game.getPlayers())
			playersList+=("  \"" + p.getUsername() + "\"  ");
		playersLabel.setText(playersList);
	}
	
	@FXML
	public void start(MouseEvent event) throws IOException {
		// lancia la board con il giusto controller
		stage = (Stage)(title.getScene().getWindow());
		FXMLLoader Loader=new FXMLLoader(StartScreenController.class.getResource("Board.fxml"));
		  
		if(code.startsWith("T"))
			Loader.setController(new SimpleTournamentBoardController(t, game));		// SIMPLE TOURNAMENT
		else if(code.startsWith("L"))
			Loader.setController(new LastManStandingBoardController(game));			// LAST MAN STANDING
		else 
			Loader.setController(new SingleGameBoardController(game));				// SINGLE GAME
		  
		root = (Parent) Loader.load();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.centerOnScreen();
		stage.show();
	}
}
