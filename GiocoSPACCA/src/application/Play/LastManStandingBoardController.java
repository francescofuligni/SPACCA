package application.Play;

import java.io.IOException;

import application.Games.LastManStanding;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LastManStandingBoardController extends Board {

	public LastManStandingBoardController() {
		game = new LastManStanding(InsertCodeController.pathToGame);
	}
	
	@Override
	protected void nextPlayerBoard() throws IOException {
		game.nextTurn();
		game.save();
		if(isOut) {
			// ricarica la schermata di inizio partita		// TODO: controllare perché non funziona (controllare se la flag isOut funziona correttamente)
			stage = (Stage)(playCardButton.getScene().getWindow());
	    	FXMLLoader Loader=new FXMLLoader(StartScreenController.class.getResource("StartScreen.fxml"));
	    	root = (Parent) Loader.load();
	    	scene = new Scene(root);
	    	stage.setScene(scene);
	    	stage.show();
		} else {
			// carica la schermata del prossimo giocatore
			stage = (Stage)(playCardButton.getScene().getWindow());
			FXMLLoader Loader=new FXMLLoader(SingleGameBoardController.class.getResource("SingleGameBoard.fxml"));
			Loader.setController(this);
			root = (Parent) Loader.load();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		}
	}

	@Override
	protected void endGame() throws IOException {
		game.save();
		stage = (Stage)(saveAndExitButton.getScene().getWindow());
    	FXMLLoader Loader=new FXMLLoader(GameScoreBoardController.class.getResource("GameScoreBoard.fxml"));
    	root = (Parent) Loader.load();
    	scene = new Scene(root);
    	stage.setScene(scene);
    	stage.show();
	}
}
